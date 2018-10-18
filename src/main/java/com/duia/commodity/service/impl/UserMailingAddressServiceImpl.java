package com.duia.commodity.service.impl;

import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.UserMailingAddressMapper;
import com.duia.commodity.model.UserMailingAddress;
import com.duia.commodity.service.UserMailingAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/07/19.
 */
@Service
@Transactional
public class UserMailingAddressServiceImpl extends AbstractService<UserMailingAddress> implements UserMailingAddressService {

    @Resource
    private UserMailingAddressMapper userMailingAddressMapper;

    /**
     *
     * 保存用户邮寄地址 {addressee:"", mobile:"", userId:"",province:"",city:"",county:"", detailAddress:""}
     * @param userMailingAddress
     * @return
     */
    @Override
    public UserMailingAddress storeUserMailingData(UserMailingAddress userMailingAddress) {
        UserMailingAddress data = this.findByUserId(userMailingAddress.getUserId());

        if (data != null) {
            data.setAddressee(userMailingAddress.getAddressee());
            data.setMobile(userMailingAddress.getMobile());
            data.setProvince(userMailingAddress.getProvince());
            data.setCity(userMailingAddress.getCity());
            data.setCounty(userMailingAddress.getCounty());
            data.setDetailAddress(userMailingAddress.getDetailAddress());
            this.update(data);
        } else {
            this.save(userMailingAddress);
        }
        return userMailingAddress;
    }

    /**
     *  保存用户QQ信息
     * @param userMailingAddress
     * @return
     */
    @Override
    public UserMailingAddress storeUserQQData(UserMailingAddress userMailingAddress) {
        UserMailingAddress data = this.findByUserId(userMailingAddress.getUserId());

        if (data != null) {
            data.setQqNum(userMailingAddress.getQqNum());
            data.setQqValidate(userMailingAddress.getQqValidate());
            this.update(data);
        } else {
            this.save(userMailingAddress);
        }
        return userMailingAddress;
    }

    /**
     * 查询用户邮寄地址
     *
     * @param userId
     * @return
     */
    @Override
    public UserMailingAddress findByUserId(Long userId) {
        //寄送信息
        Condition condition = new Condition(UserMailingAddress.class);
        condition.createCriteria().andEqualTo("userId", userId);
        List<UserMailingAddress> userMailingAddressList = findByCondition(condition);
        UserMailingAddress address = null;
        if (userMailingAddressList != null && !userMailingAddressList.isEmpty()) {
            address = userMailingAddressList.get(0);
        }
        return address;
    }
}
