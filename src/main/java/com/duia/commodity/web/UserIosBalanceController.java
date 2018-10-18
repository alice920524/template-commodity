package com.duia.commodity.web;

import com.duia.commodity.model.Users;
import com.duia.commodity.service.UserIosBalanceService;
import com.duia.resultG.Result;
import com.duia.resultG.ResultGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/userIosBalance")
public class UserIosBalanceController {
    @Resource
    private UserIosBalanceService userIosBalanceService;

    /**
     * 用户余额
     *
     */
    @PostMapping("/userBalance")
    public Result userBalance(@SessionAttribute Users user) {
        Map result = new HashMap<>(1);
        Double balance = this.userIosBalanceService.getUserIosBalance(user.getId());
        result.put("userBalance", balance);
        return ResultGenerator.genSuccessResult(result);
    }

    /**
     * ios支付
     * */
    @PostMapping("/iosPay")
    public Result iosPay(@SessionAttribute Users user, String payNum) {

        return this.userIosBalanceService.createIosPay(user, payNum);
    }

}
