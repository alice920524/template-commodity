package com.duia.commodity.service.impl;

import com.duia.commodity.dao.ClassStudentDetailMapper;
import com.duia.commodity.model.ClassStudentDetail;
import com.duia.commodity.service.ClassStudentDetailService;
import com.duia.commodity.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/01/04.
 */
@Service
@Transactional
public class ClassStudentDetailServiceImpl extends AbstractService<ClassStudentDetail> implements ClassStudentDetailService {
    @Resource
    private ClassStudentDetailMapper classStudentDetailMapper;

    /**
     * 通过学员id查询班级学员详情
     * @param userId
     * @return
     */
    @Override
    public ClassStudentDetail findClassStudentDetailByUserId(Integer userId) {

        Condition condition = new Condition(ClassStudentDetail.class);
        condition.createCriteria().andEqualTo("userId", userId);
        List<ClassStudentDetail> classStudentDetailList = this.findByCondition(condition);
        if(classStudentDetailList != null && !classStudentDetailList.isEmpty()){
            return classStudentDetailList.get(0);
        }
        return null;
    }
}
