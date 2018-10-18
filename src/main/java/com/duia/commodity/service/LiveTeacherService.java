package com.duia.commodity.service;

import com.duia.commodity.model.LiveTeacher;
import com.duia.commodity.core.Service;


/**
 * Created by CodeGenerator on 2018/05/28.
 */
public interface LiveTeacherService extends Service<LiveTeacher> {

    /**
     * 根据用户邮箱查找
     */
    public LiveTeacher getLiveTeacherByUserEmail(String email);

    /**
     * 根据后台账户id查找讲师信息
     *
     * @param authorityUserId
     * @return
     */
    LiveTeacher getLiveTeacherByAuthorityUserId(Long authorityUserId);

}
