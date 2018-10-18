package com.duia.commodity.service.impl;

import com.duia.commodity.common.Constant;
import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.TeacherScoreMapper;
import com.duia.commodity.model.TeacherScore;
import com.duia.commodity.service.TeacherScoreService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;


@Service
@Transactional
public class TeacherScoreServiceImpl extends AbstractService<TeacherScore> implements TeacherScoreService {

    @Resource
    private TeacherScoreMapper teacherScoreMapper;
    @Resource
    private RedisTemplate redisTemplate;

    /*初始化默认值*/
    private final Double[] fake_average_score_array = {4.8, 4.9, 5.0};
    private final Integer[] fake_server_num_array = {500, 510, 520, 530, 540, 550, 560, 570, 580, 590, 600};
    private final Integer[] fake_teaching_time_array = {260, 270, 280, 290, 300};


    @Override
    public TeacherScore getTeacherScore(Long userId) {
        TeacherScore teacherScore = null;
        String key = Constant.CACHE_KEY_PREFIX_TEACHER_SCORE + userId;
        if (redisTemplate.hasKey(key)) {
            teacherScore = (TeacherScore) redisTemplate.boundValueOps(key).get();
            return teacherScore;
        } else {
            teacherScore = this.teacherScoreMapper.selectTeacherByUserId(userId);
            if (null == teacherScore) {
                teacherScore = this.initTeacherScore(userId);
            } else if (TeacherScore.status_false.equals(teacherScore.getStatus())) {// 假评分->为null就设置默认值
                teacherScore = this.updateTeacherScore(teacherScore);
            }
            redisTemplate.boundValueOps(key).set(teacherScore, 1, TimeUnit.DAYS);
        }
        return teacherScore;
    }

    /*
    * 初始化数据
    * */

    private TeacherScore initTeacherScore(Long userId) {
        TeacherScore teacherScore = new TeacherScore();
        teacherScore.setAuthorityUserId(userId);
        teacherScore.setStatus(TeacherScore.status_false);
        teacherScore.setUpdateTime(new Date());

        teacherScore.setFakeAverageScore(this.getDefaultFakeAverageScore());
        teacherScore.setFakeServerNum(this.getDefaultFakeServerNum());
        teacherScore.setFakeTeachingTime(this.getDefaultFakeTeachingTime());
        this.teacherScoreMapper.initTeacherScore(teacherScore);

        return teacherScore;
    }

    private TeacherScore updateTeacherScore(TeacherScore teacherScore) {
        boolean update = false;
        if (teacherScore.getFakeAverageScore() == null || Double.valueOf(0.0).equals(teacherScore.getFakeAverageScore())) {
            update = true;
            teacherScore.setFakeAverageScore(this.getDefaultFakeAverageScore());
        }

        if (teacherScore.getFakeServerNum() == null || Integer.valueOf(0).equals(teacherScore.getFakeServerNum())) {
            update = true;
            teacherScore.setFakeServerNum(this.getDefaultFakeServerNum());
        }

        if (teacherScore.getFakeTeachingTime() == null || Integer.valueOf(0).equals(teacherScore.getFakeTeachingTime())) {
            update = true;
            teacherScore.setFakeTeachingTime(this.getDefaultFakeTeachingTime());
        }

        if (update) {
            this.teacherScoreMapper.updateByPrimaryKey(teacherScore);
        }

        return teacherScore;
    }

    /*默认假平均分*/
    private Double getDefaultFakeAverageScore() {
        return fake_average_score_array[new Random().nextInt(fake_average_score_array.length)];
    }

    /*默认假服务学员人数*/
    private Integer getDefaultFakeServerNum() {
        return fake_server_num_array[new Random().nextInt(fake_server_num_array.length)];
    }

    /*默认假授课时长*/
    private Integer getDefaultFakeTeachingTime() {
        return fake_teaching_time_array[new Random().nextInt(fake_teaching_time_array.length)];
    }
}
