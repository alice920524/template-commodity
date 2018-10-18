package com.duia.commodity.service.impl;

import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.ClassLiveRoomMapper;
import com.duia.commodity.model.ClassLiveRoom;
import com.duia.commodity.service.ClassLiveRoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/05/08.
 */
@Service
@Transactional
public class ClassLiveRoomServiceImpl extends AbstractService<ClassLiveRoom> implements ClassLiveRoomService {
    @Resource
    private ClassLiveRoomMapper classLiveRoomMapper;

    @Override
    public ClassLiveRoom findLiveClassLiveRoom(Long relationId, Integer type) {

        List<ClassLiveRoom> classLiveRoomList = this.findClassLiveRoom(relationId, 0, type);

        if (null == classLiveRoomList || classLiveRoomList.isEmpty()) {
            return null;
        }
        return classLiveRoomList.get(0);
    }

    private List<ClassLiveRoom> findClassLiveRoom (Long relationId, Integer relationType, Integer type) {
        Condition condition = new Condition(ClassLiveRoom.class);
        condition.createCriteria().
                andEqualTo("relationId", relationId).
                andEqualTo("relationType", relationType).
                andEqualTo("type", type);

        return this.findByCondition(condition);
    }


}
