package com.duia.commodity.web;

import com.duia.commodity.model.UserMailingAddress;
import com.duia.commodity.model.Users;
import com.duia.commodity.service.UserMailingAddressService;
import com.duia.commodity.service.UsersService;
import com.duia.resultG.Result;
import com.duia.resultG.ResultGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by CodeGenerator on 2017/06/30.
 */
@RestController
@RequestMapping("/userMailing")
public class UserMailingAddressController {

    @Resource
    private UserMailingAddressService userMailingAddressService;
    @Resource
    private UsersService userService;

    /**
     * 保存用户邮寄地址 {addressee:"", mobile:"", userId:"",province:"",city:"",county:"", detailAddress:""}
     *
     * @param userMailingAddress
     * @return
     */
    @RequestMapping(value = "/storeUserMailing", method = RequestMethod.POST)
    public Result storeUserMailingData(@SessionAttribute Users user, UserMailingAddress userMailingAddress) {
        // 保存用户邮寄地址
        userMailingAddress.setUserId(user.getId());
        UserMailingAddress mailingAddress = userMailingAddressService.storeUserMailingData(userMailingAddress);
        return ResultGenerator.genSuccessResult(mailingAddress);
    }

    /**
     * 保存用户QQ信息 {userId:"",qqNum:"",qqValidate:""}
     *
     * @param userMailingAddress
     * @return
     */
    @Deprecated
    @RequestMapping(value = "/storeUserQQData", method = RequestMethod.POST)
    public Result storeUserQQData(@SessionAttribute Users user, UserMailingAddress userMailingAddress) {
        // 保存用户QQ信息
        userMailingAddress.setUserId(user.getId());
        UserMailingAddress mailingAddress = userMailingAddressService.storeUserQQData(userMailingAddress);
        return ResultGenerator.genSuccessResult(mailingAddress);
    }

    /**
     * 通过用户id查询用户邮寄地址
     *
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "/findUserMailingAddressByUserId", method = RequestMethod.POST)
    public Result findUserMailingAddressByUserId(Long userId) {
        //寄送信息
        Condition condition = new Condition(UserMailingAddress.class);
        condition.createCriteria().andEqualTo("userId", userId);
        condition.orderBy("id").desc();
        List<UserMailingAddress> userMailingAddressList = userMailingAddressService.findByCondition(condition);
        UserMailingAddress address = null;
        if (userMailingAddressList != null && !userMailingAddressList.isEmpty()) {
            address = userMailingAddressList.get(0);
        }
        return ResultGenerator.genSuccessResult(address);
    }

    /**
     * 确认订单地址信息查询
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "/orderAddress", method = RequestMethod.POST)
    public Result orderAddress(@SessionAttribute Users user) {
        //寄送信息
        UserMailingAddress userMailingAddress = userMailingAddressService.findByUserId(user.getId());
        //如果查询用户寄送信息为空，则返回用户注册手机号
        if(userMailingAddress==null){
            Users users = userService.findUserById(user.getId());
            userMailingAddress = new UserMailingAddress();
            userMailingAddress.setMobile(users.getMobile());
        }
        return ResultGenerator.genSuccessResult(userMailingAddress);
    }

}
