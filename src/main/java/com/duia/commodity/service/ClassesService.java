package com.duia.commodity.service;

import com.duia.commodity.core.Service;
import com.duia.commodity.model.Classes;
import com.duia.commodity.model.PayOrder;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/11/24.
 */
public interface ClassesService extends Service<Classes> {

    /**
     * 获取老师ids(第一个为主讲)
     */
    List<Long> findTeacherUserIds(Long classId);
    /**
     * 获取订单对应的班级ID
     * */
    Long findClassId(PayOrder payOrder);
}
