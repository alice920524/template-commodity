package com.duia.commodity.service;

import com.duia.commodity.model.UserMailingAddress;
import com.duia.commodity.core.Service;


/**
 * Created by CodeGenerator on 2017/07/19.
 */
public interface UserMailingAddressService extends Service<UserMailingAddress> {

    /**
     * 查询用户邮寄地址
     *
     * @param userId
     * @return
     */
    UserMailingAddress findByUserId(Long userId);

    /**
     * 保存用户邮寄地址
     *
     * @param userMailingAddress
     * @return
     */
    UserMailingAddress storeUserMailingData(UserMailingAddress userMailingAddress);

    /**
     * 保存用户QQ信息
     *
     * @param userMailingAddress
     * @return
     */
    UserMailingAddress storeUserQQData(UserMailingAddress userMailingAddress);
}
