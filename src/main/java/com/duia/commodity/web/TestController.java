package com.duia.commodity.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 李恒名 on 2017/7/21.
 *
 * 运维要求
 */
@Controller
public class TestController {
    @RequestMapping("/test")
    @ResponseBody
    public void test(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }
}
