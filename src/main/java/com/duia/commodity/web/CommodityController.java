package com.duia.commodity.web;

import api.WxShareApi;
import com.duia.commodity.common.Constant;
import com.duia.commodity.common.dto.ChapterDTO;
import com.duia.commodity.common.dto.ClassTypeEvaluateDTO;
import com.duia.commodity.common.dto.ClassesStudentDTO;
import com.duia.commodity.common.dto.TeacherDTO;
import com.duia.commodity.common.util.DateUtils;
import com.duia.commodity.common.util.WebUtil;
import com.duia.commodity.model.*;
import com.duia.commodity.service.*;
import com.duia.enums.Status;
import com.duia.resultG.Result;
import com.duia.resultG.ResultGenerator;
import com.duia.sso.client.common.Common;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DefaultValue;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by CodeGenerator on 2017/06/30.
 */
@RestController
@RequestMapping("/commodity")
public class CommodityController {
    @Resource
    private AuthorityUsersService authorityUsersService;
    @Resource
    private ClassStudentDetailService classStudentDetailService;
    @Resource
    private ClassScheduleChapterService chapterService;
    @Resource
    private ClassStudentService studentService;
    @Resource
    private CommodityService commodityService;
    @Resource
    private ClassTypeService classTypeService;
    @Resource
    private CommodityPromotionService commodityPromotionService;
    @Resource
    private CommoditySpecialService commoditySpecialService;
    @Resource
    private ClassTypeEvaluateService classTypeEvaluateService;
    @Resource
    private ClassStudentAgreementService classStudentAgreementService;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource(name = "binaryRedisTemplate")
    private RedisTemplate binaryRedisTemplate;

    @Value("${wx.share.app_id}")
    private String appId;
    @Value("${wx.share.app_secret}")
    private String appSecret;

    /**
     * 通过班级id获取老师列表
     * @param classId
     * @return
     */
    @PostMapping("/teacher/list")
    public Result teacherList(@RequestParam Long classId) {
        List<TeacherDTO> teacherList = authorityUsersService.findByClassId(classId);
        return ResultGenerator.genSuccessResult(teacherList);
    }

    /**
     * 商品评价内容
     * @param classTypeId
     * @return 班型ID
     */
    @PostMapping("/evaluateContent")
    public Result reviewContent(@RequestParam Long classTypeId, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size){
        PageHelper.startPage(page, size);
        List<ClassTypeEvaluateDTO> classTypeEvaluateDTOs = this.classTypeEvaluateService.selectEvaluateContentByClassTypeId(classTypeId);
        return ResultGenerator.genSuccessResult(classTypeEvaluateDTOs);
    }

    /**
     * 查询评论数的总数量
     * @param classTypeId
     * @return 评论总数量
     */
    @PostMapping("/evaluateCount")
    public Result evaluateCount(@RequestParam Long classTypeId){
         return ResultGenerator.genSuccessResult(this.classTypeEvaluateService.selectEvaluateCountByClassTypeId(classTypeId));
    }

    /**
     * 购买学员列表n
     * @param classId
     * @param page
     * @param size
     * @return
     */
    @Deprecated
    @PostMapping("/buy/list")
    public Result buyList(@RequestParam Long classId, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<ClassesStudentDTO> buyStudentList = studentService.findByClassId(classId);
        for (ClassesStudentDTO dto : buyStudentList) {
            ClassTypeEvaluate classTypeEvaluate = classTypeEvaluateService.selectByClassIdAndUserId(classId, dto.getId());
            if (classTypeEvaluate != null) {
                dto.setStar(classTypeEvaluate.getStar());
                dto.setContent(classTypeEvaluate.getContent());
                dto.setReplyContent(classTypeEvaluate.getReplyContent());
            }
        }
        return ResultGenerator.genSuccessResult(buyStudentList);
    }

    /**
     * 商品课表
     * */
    @PostMapping("/chapter/list")
    public Result chapterList(@RequestParam Long classId) {
        List<ChapterDTO> chapterList = chapterService.findByClassId(classId);
        return ResultGenerator.genSuccessResult(chapterList);
    }

    /**
     * 商品状态
     * */
    @PostMapping("/status")
    public Result status(HttpServletRequest request, @RequestParam Long comId, Integer appType) {
        if (appType == null) {
            appType = WebUtil.isWap(request) ? Constant.WAP_TYPE : Constant.WEB_TYPE;
        }
        int status = commodityService.queryCommodityStatus(comId, appType);
        Map<String, Object> data = Maps.newHashMap();
        data.put("status", status);
        data.put("serverDate", new Date());
        return ResultGenerator.genSuccessResult(data);
    }

    /**
     * 购买状态
     * */
    @PostMapping("/buy/status")
    public Result buyStatus(@RequestParam Long classId, HttpServletRequest request) {
        Integer status = Status.INVALID.getState();
        Integer insuranceCount = 0, guaranteeCount = 0;
        Users user = (Users) request.getSession().getAttribute(Common.USERS_CLIENT);
        Map<String, Object> data = Maps.newHashMap();
        if (user != null) {

            ClassStudentDetail classStudentDetail = classStudentDetailService.findClassStudentDetailByUserId(user.getId().intValue());
            if (classStudentDetail != null) {
                // 检测学员班级购买情况
                ClassStudent classStudent = studentService.findUserIsBuyClassIdAndStudentId(classId, classStudentDetail.getId());
                if (classStudent != null) {
                    status = Status.VALID.getState();
                    data.put("username", StringUtils.isNotBlank(classStudentDetail.getName()) ? 1 : 0);
                    List<ClassStudentAgreement> list = classStudentAgreementService.findClassStudentAgreementByClassId(user.getId(), classId);
                    if (!CollectionUtils.isEmpty(list)) {
                        for (int i = 0; i < list.size(); i++) {
                            if (Objects.equals(list.get(i).getType(), 1)) {
                                insuranceCount = 1;
                                continue;
                            }
                            if (Objects.equals(list.get(i).getType(), 2)) {
                                guaranteeCount = 1;
                                continue;
                            }
                        }
                    }
                }
            }
        }
        data.put("status", status);
        data.put("insuranceCount", insuranceCount);
        data.put("guaranteeCount", guaranteeCount);
        return ResultGenerator.genSuccessResult(data);
    }

    //获取优惠组合按钮URL
    @PostMapping("/special/url")
    public Result specialUrl(HttpServletRequest request, @RequestParam Long comId) {
        Map<String, Object> data = Maps.newHashMap();

        String appType = request.getParameter("appType");
        if (StringUtils.isEmpty(appType)) {
            appType = WebUtil.isWap(request) ? Constant.WAP_TYPE.toString() : Constant.WEB_TYPE.toString();
        }
        Integer intAppType = Integer.parseInt(appType);
        int status = commodityService.queryCommodityStatus(comId, intAppType);
        if (status == 1) {//上架
            // 优先检查套餐
            Integer taocanCount = commoditySpecialService.findCountByComIdAndType(comId, 1, intAppType);
            String specialUrl;
            if (taocanCount > 0) {
                specialUrl = "/commodity/select/2?comId=" + comId;
            } else {
                // 检查组合
                Integer zuheCount = commoditySpecialService.findCountByComIdAndType(comId, 2, intAppType);
                if (zuheCount > 0) {
                    specialUrl = "/commodity/select/3?comId=" + comId;
                } else {
                    specialUrl = null;
                }
            }
            data.put("specialUrl", specialUrl);
        } else {
            data.put("specialUrl", null);
        }
        return ResultGenerator.genSuccessResult(data);
    }

    /**
     * 查询当前班型购买的人数
     *
     * @param classId
     * @return
     */
    @PostMapping("/buy/listCount")
    public Result buyListCount(@RequestParam Long classId) {
        Map<String, Object> data = Maps.newHashMap();
        Integer count = studentService.findCountIsBuyClassId(classId);
        data.put("count", count);
        return ResultGenerator.genSuccessResult(data);
    }

    /**
     * 查询商品的分享购信息
     *
     * @param request
     * @param comId
     * @return
     */
    @PostMapping("/promotion")
    public Result promotion(HttpServletRequest request, @RequestParam Long comId, @RequestParam Integer appType) {
        Users user = (Users) request.getSession().getAttribute(Common.USERS_CLIENT);
        Map<String, Object> data = Maps.newHashMap();
        Integer pro = 0;
        Integer isShared = 0;
        CommodityPromotion promotion = commodityPromotionService.findOnLinePromotion(comId, appType);
        if (promotion != null) {
            pro = 1;
            ClassType classType = classTypeService.findById(promotion.getClassTypeId());
            Double studyPackagePrice = Objects.equals(classType.getAddressMark(), 2) ? classType.getBookPrice() : 0.0;
            data.put("serverDate", new Date());
            data.put("endDate", promotion.getEndDate());
            data.put("costPrice", promotion.getCostPrice() + studyPackagePrice);
            if (user != null) { // 学员已经登录，且进行过分享，则变更分享标识
                BoundHashOperations operations = redisTemplate.boundHashOps(Constant.CACHE_KEY_PREFIX_SHARE_HASH + DateUtils.currDay());
                if (operations.get(user.getId() + "." + comId) != null) {
                    isShared = 1;
                    data.put("promotionId", promotion.getId());
                }
            }
            data.put("promotionType", promotion.getType());
        }
        data.put("promotion", pro);
        data.put("isShared", isShared);
        return ResultGenerator.genSuccessResult(data);
    }

    /**
     * @param comId
     * @param  appType
     * 最高可节省金额，以及套餐组合的总个数
     */
    @PostMapping("/countAndMoney")
    public Result moneyAndCount(Long comId, Integer appType){
        Map<String, Object> map = new HashMap();
        //套餐组合的总个数
        List<Double> countAndMoney = commoditySpecialService.findTzCountByComIdAndType(comId, appType);
        if(countAndMoney.size()!=0 ) {
            map.put("count", countAndMoney.size());
            Object maxMoney = Collections.max(countAndMoney);
            map.put("money", maxMoney);
        }else{
            map.put("count", 0);
            map.put("money",0);
        }
        return ResultGenerator.genSuccessResult(map);
    }

    /**
     * 学员分享商品回调地址
     *
     * @param request
     * @param userId  学员id
     * @param comId   商品id
     * @param type    分享类型 0:未知,1:微信朋友圈，2：微信好友
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @GetMapping(value = "/share/appNotify")
    public void shareNotify(HttpServletRequest request, Long userId, Long comId, Integer type)
            throws ServletException, IOException {
        // 存储学员分享课程信息
        BoundHashOperations operations = redisTemplate.boundHashOps(Constant.CACHE_KEY_PREFIX_SHARE_HASH + DateUtils.currDay());
        operations.put(userId + "." + comId, "{ \"type\" : " + type + "}");
    }

    /**
     * 学员分享商品回调地址
     *
     * @param request
     * @param comId   商品id
     * @param type    分享类型 0:未知,1:微信朋友圈，2：微信好友
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @PostMapping(value = "/share/notify")
    public void shareNotify(HttpServletRequest request, Long comId, Integer type)
            throws ServletException, IOException {
        Users user = (Users) request.getSession().getAttribute(Common.USERS_CLIENT);
        if (user != null) {
            // 存储学员分享课程信息
            BoundHashOperations operations = redisTemplate.boundHashOps(Constant.CACHE_KEY_PREFIX_SHARE_HASH + DateUtils.currDay());
            operations.put(user.getId() + "." + comId, "{ \"type\" : " + type + "}");
        }
    }

    /**
     * 微信分享请求
     *
     * @param request
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @PostMapping(value = "/share/wx")
    public Object shareData(HttpServletRequest request, String pageUrl)
            throws ServletException, IOException {

        String ticketKey = Constant.CACHE_KEY_PREFIX_TICKET + appId;
        BoundValueOperations operations = binaryRedisTemplate.boundValueOps(ticketKey);
        String ticket = (String) operations.get();
        if (ticket == null) {
            // 获取accessToken
            String key = Constant.CACHE_KEY_PREFIX_ACCESS_TOKEN + appId;
            BoundValueOperations operations2 = binaryRedisTemplate.boundValueOps(key);
            String accessToken = (String) operations2.get();
            if (accessToken == null) {
                accessToken = WxShareApi.getAccessToken(appId, appSecret);
                operations2.set(accessToken, 5400, TimeUnit.SECONDS);
            }
            if (accessToken != null) {
                ticket = WxShareApi.getTicket(accessToken);
                operations.set(ticket, 5400, TimeUnit.SECONDS);
            }
        }
        return ResultGenerator.genSuccessResult(WxShareApi.getShareData(appId, pageUrl, ticket));
    }

    /**
     * 商品选择页 - 商品查询(单品、套餐、组合)
     *
     * @param request
     * @param comId   单品id
     * @param checked 保险是否选中
     * @param bookTypeChecked 学习包图书类型选中状态
     * @param type    类型 1:单品 2:套餐 3:组合
     * @param appType 显示终端 999：web， 998：wap
     * @return
     */
    @PostMapping("/commoditySelect")
    public Result commoditySelectNew(HttpServletRequest request, Long comId, Integer
            checked, Integer bookTypeChecked, @DefaultValue(value = "1") Integer type, Integer appType) {

        if (appType == null) {
            appType = WebUtil.isWap(request) ? Constant.WAP_TYPE : Constant.WEB_TYPE;
        }
        Map result = new HashMap(3);// 指定初始化容量
        // 套餐/组合 是否配置标识
        result.put("tc", commoditySpecialService.findCountByComIdAndType(comId, 1, appType) > 0);
        result.put("zh", commoditySpecialService.findCountByComIdAndType(comId, 2, appType) > 0);

        if (1 == type) {// 单品
            result.put("commodity", new ArrayList<>(0));
        } else if (2 == type || 3 == type) {// 套餐/组合
            /*套餐组合表中的   套餐标识是1  组合标识是2*/
            result.put("commodity", commoditySpecialService.findByComIdAndType(comId, type - 1, checked, appType, bookTypeChecked));
        } else {
            return ResultGenerator.genFailResult("商品类型错啦！！！！");
        }

        return ResultGenerator.genSuccessResult(result);
    }
}
