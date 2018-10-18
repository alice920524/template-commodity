package com.duia.commodity.web;

import com.duia.commodity.service.CommodityProductService;
import com.duia.resultG.Result;
import com.duia.resultG.ResultGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/classes")
public class ClassesController {

    @Resource
    private CommodityProductService commodityProduct;

    /**
     * 获取班级ID
     * */
    @PostMapping("/getClassId")
    public Result getClassId(Long comId) {
        Map<String, Object> result = new HashMap<>(1);
        result.put("classId", this.commodityProduct.selectLiveCommodityClassId(comId));
        return ResultGenerator.genSuccessResult(result);
    }
}
