package com.duia.commodity.web;

import com.duia.commodity.model.Users;
import com.duia.commodity.service.BookCommodityService;
import com.duia.commodity.service.BookStudentService;
import com.duia.resultG.Result;
import com.duia.resultG.ResultGenerator;
import com.duia.sso.client.common.Common;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 李国勇 on 2018/6/25.
 */
@RestController
@RequestMapping("/bookCommodity")
public class BookCommodityController {

    @Resource
    private BookStudentService bookStudentService;
    @Resource
    private BookCommodityService bookCommodityService;

    /**
     * 商品上下架状态及是否购买接口
     */
    @PostMapping("/status")
    public Result status(HttpServletRequest request, @RequestParam Long comId, @RequestParam Integer appType) {
        Map<String, Integer> map = new HashMap<String, Integer>(2);

        Users users = (Users) request.getSession().getAttribute(Common.USERS_CLIENT);

        Long userId = null;
        //客户没有购买
        if (null != users) {
            userId = users.getId();
        }

        //商品是否购买 返回值类型为:int	1:购买 0:未购买
        map.put("isBuy", this.bookStudentService.isBuyBookCommodity(userId, comId));
        //商品上下架 是否上架 1:上架 0:下架
        map.put("status", this.bookCommodityService.selectBookCommodityStatus(comId, appType));

        return ResultGenerator.genSuccessResult(map);
    }

}
