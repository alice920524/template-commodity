package com.duia.commodity.service;
import com.duia.commodity.model.ClassStudentDetail;
import com.duia.commodity.core.Service;


/**
 * Created by CodeGenerator on 2018/01/04.
 */
public interface ClassStudentDetailService extends Service<ClassStudentDetail> {
    /**
     * 通过学员id查询班级学员详情
     * @param userId
     * @return
     */
    ClassStudentDetail findClassStudentDetailByUserId(Integer userId);

}
