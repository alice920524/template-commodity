package com.duia.commodity.service;
import com.duia.commodity.core.Service;
import com.duia.commodity.model.ClassLiveRoom;


/**
 * Created by CodeGenerator on 2018/05/08.
 */
public interface ClassLiveRoomService extends Service<ClassLiveRoom> {

    /**
     * @Description:直播课房间信息
     * @Date: 11:39 2018/5/8
     */
    ClassLiveRoom findLiveClassLiveRoom(Long relationId, Integer type);

}
