package com.duia.commodity.web;

import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhenghui on 2018/5/30.
 */
@RestController
//@RequestMapping("/cet/promotion")
public class CetPromotionController {
//
//    @Autowired
//    private CetPromotionService cetPromotionService;
//
//    /**
//     * 四级活动成绩保存
//     *
//     * @param cetPromotion
//     * @return
//     */
//    @PostMapping("/save")
//    public Result save(CetPromotion cetPromotion, @RequestParam(defaultValue = "") String accessToken) throws IOException, WxPayException {
//        cetPromotionService.saveCetPromotion(cetPromotion, accessToken);
//        return ResultGenerator.genSuccessResult();
//    }
//
//    /**
//     * 更新活动成绩
//     * @param cetPromotion
//     * @return
//     */
//    @PostMapping("/updateScore")
//    public Result updateScore(CetPromotion cetPromotion) {
//        cetPromotionService.updateScore(cetPromotion);
//        return ResultGenerator.genSuccessResult();
//    }
//
//    /**
//     * 四级活动查询
//     *
//     * @param openid
//     * @param appType
//     * @return
//     */
//    @PostMapping("/query")
//    public Result query(@RequestParam String openid, @RequestParam(defaultValue = "0") Integer appType) {
//        return ResultGenerator.genSuccessResult(cetPromotionService.query(openid, appType));
//    }
//
//    /**
//     * 四级活动统计计数
//     *
//     * @param countType 1：访问测试页 2:再测试一遍 3：访问测试题1 4：成绩生成页 5：分享成绩单 6：点击彩蛋 7：点击购买
//     * @param openid
//     * @param appType   0：公众号 6：英语四级
//     * @return
//     */
//    @PostMapping("/count")
//    public Result count(@RequestParam Integer countType, @RequestParam String openid, @RequestParam(defaultValue = "0") Integer appType) {
//        try {
//            cetPromotionService.count(countType, openid, appType);
//        } catch (Exception e) {
//            return ResultGenerator.genFailResult("四级活动统计计数失败，参数[" + countType + "," + openid + "]");
//        }
//        return ResultGenerator.genSuccessResult();
//    }
//
//    /**
//     * 根据端类型获取用户信息
//     * @param accessToken
//     * @param openid
//     * @param appType
//     * @return
//     */
//    @PostMapping("/getAuthorizedUser")
//    public Result getAuthorizedUser(@RequestParam(defaultValue = "") String accessToken, @RequestParam String openid, @RequestParam(defaultValue = "0") Integer appType) {
//        Map dataMap = Maps.newHashMap();
//        try {
//            dataMap = cetPromotionService.getAuthorizedUser(openid, accessToken, appType);
//        } catch (Exception e) {
//            return ResultGenerator.genFailResult("根据端类型获取用户信息失败，参数[" + accessToken + "," + openid + "]");
//        }
//        return ResultGenerator.genSuccessResult(dataMap);
//    }

}
