package com.duia.commodity.service;
import com.duia.commodity.model.UsersDepGrpRel;
import com.duia.commodity.core.Service;


/**
 * Created by CodeGenerator on 2018/06/22.
 */
public interface UsersDepGrpRelService extends Service<UsersDepGrpRel> {
    /**
     * 老师是否有分组
     *
     * @Date: 15:41 2018/6/22
     */
    boolean isGroup(Long teacherId);
}
