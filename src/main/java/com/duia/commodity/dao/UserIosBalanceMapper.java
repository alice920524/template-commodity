package com.duia.commodity.dao;

import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.UserIosBalance;
import org.apache.ibatis.annotations.Param;

public interface UserIosBalanceMapper extends Mapper<UserIosBalance> {

    /**
     * 扣减余额
     * */
    Integer updateSubBalance(@Param("costPrice") Double costPrice, @Param("userId") Long userId);
}