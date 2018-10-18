package com.duia.commodity.service.impl;

import com.duia.commodity.common.Constant;
import com.duia.commodity.common.dto.ChapterDTO;
import com.duia.commodity.common.dto.CourseDTO;
import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.ClassScheduleChapterMapper;
import com.duia.commodity.model.ClassLiveRoom;
import com.duia.commodity.model.ClassScheduleChapter;
import com.duia.commodity.model.CourseLecture;
import com.duia.commodity.service.ClassLiveRoomService;
import com.duia.commodity.service.ClassScheduleChapterService;
import com.duia.commodity.service.CourseLectureService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by CodeGenerator on 2017/07/13.
 */
@Service
@Transactional
public class ClassScheduleChapterServiceImpl extends AbstractService<ClassScheduleChapter> implements ClassScheduleChapterService {
    @Resource
    private ClassScheduleChapterMapper classScheduleChapterMapper;
    @Resource
    private CourseLectureService courseLectureService;
    @Resource
    private ClassLiveRoomService classLiveRoomService;
    @Resource
    private RedisTemplate<String, List<ChapterDTO>> redisTemplate;

    /**
     * 通过班级ID获取课表下的章、节
     * @param classId 班级ID
     * @return
     */
    @Override
    public List<ChapterDTO> findByClassId(Long classId) {
        String key = Constant.CACHE_KEY_PREFIX_CHAPTER_LIST + classId;
        if (redisTemplate.hasKey(key)) {
            List<ChapterDTO> chapterDTOS = redisTemplate.opsForValue().get(key);
            return chapterDTOS;
        } else {
            List<ChapterDTO> chapterDTOS = classScheduleChapterMapper.selectByClassId(classId);

            // 视频时长
            this.setVideoLength(chapterDTOS);

            redisTemplate.opsForValue().set(key, chapterDTOS, 1, TimeUnit.DAYS);//设置超时时间，防止后台故障导致没有删除缓存。
            return chapterDTOS;
        }
    }

    /**
     * @Description:设置视频时长(视频+回放)
     * @Date: 10:45 2018/5/8
     */
    private void setVideoLength(List<ChapterDTO> chapterDTOS) {
        if (null == chapterDTOS || chapterDTOS.isEmpty()) {
            return;
        }
        // 章
        for (ChapterDTO chapterDTO : chapterDTOS) {
            // 节
            List<CourseDTO> courseDTOS = chapterDTO.getCourseList();
            if (null != courseDTOS && !courseDTOS.isEmpty()) {
                for (CourseDTO courseDTO : courseDTOS) {

                    courseDTO.setVideoLength("0");// 默认赋值为0

                    if (Constant.CLASS_SCHEDULE_COURSE_PLAY_TYPE_VIDEO.equals(courseDTO.getPlayType())) {// 视频

                        CourseLecture courseLecture = this.courseLectureService.findById(courseDTO.getLectureId());
                        if (null != courseLecture && courseLecture.getVideoLength() != null) {
                            courseDTO.setVideoLength(courseLecture.getVideoLength());
                        }

                    } else if (Constant.CLASS_SCHEDULE_COURSE_PLAY_TYPE_PLAYBACK.equals(courseDTO.getPlayType())) { // 回放

                        ClassLiveRoom classLiveRoom = this.classLiveRoomService.findLiveClassLiveRoom(courseDTO.getId(), courseDTO.getType());
                        if (null != classLiveRoom) {
                            courseDTO.setVideoLength(this.getMinute(classLiveRoom.getVideoLength()));
                        }

                    }
                }
            }
        }
    }

    /**
     * @Description:计算分钟数
     * @Date: 18:46 2018/5/8
     */
    private String getMinute(String videoLength) {
        if (null == videoLength) {
            return "0";
        }
        String[] v = videoLength.split(":");
        if (v.length == 2) {
            return v[0];
        } else if (v.length == 3) {
            return Integer.parseInt(v[0]) * 60 + Integer.parseInt(v[1]) + "";
        }
        return "0";
    }

}
