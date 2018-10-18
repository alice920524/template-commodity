package com.duia.commodity.service.impl;

import com.duia.commodity.common.Constant;
import com.duia.commodity.common.dto.CommodityPromotionDTO;
import com.duia.commodity.common.enums.ClassTypeEnum;
import com.duia.commodity.common.enums.CommodityPromotionEnum;
import com.duia.commodity.common.util.DateUtils;
import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.CommodityPromotionMapper;
import com.duia.commodity.model.CommodityPromotion;
import com.duia.commodity.model.PayOrder;
import com.duia.commodity.service.CommodityPromotionService;
import com.duia.commodity.service.PayOrderService;
import com.duia.commodity.service.TerminalDisplayService;
import com.duia.enums.Sales;
import com.duia.resultG.Result;
import com.duia.resultG.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * Created by CodeGenerator on 2018/03/16.
 */
@Service
@Transactional
public class CommodityPromotionServiceImpl extends AbstractService<CommodityPromotion> implements CommodityPromotionService {

    private final Logger logger = LoggerFactory.getLogger(CommodityPromotionServiceImpl.class);

    @Resource
    private CommodityPromotionMapper commodityPromotionMapper;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private PayOrderService payOrderService;
    @Resource
    private TerminalDisplayService terminalDisplayService;

    private final String promotionRedisKey = "promotion:sales";

    private final Integer expireHours = 56;

    /**
     * 查询有有效的分享购信息
     *
     * @param comId
     * @return
     */
    @Override
    public CommodityPromotion findOnLinePromotion(Long comId, Integer appType) {
        return commodityPromotionMapper.selectCommodityPromotionOnLine(comId, appType);
    }

    @Override
    public CommodityPromotion isShareCommodity(Long comId, Long userId, Integer appType) {
        CommodityPromotion commodityPromotion = this.findOnLinePromotion(comId, appType);

        if (commodityPromotion != null) {
            BoundHashOperations operations = redisTemplate.boundHashOps(Constant.CACHE_KEY_PREFIX_SHARE_HASH + DateUtils.currDay());
            if (operations.get(userId + "." + comId) != null) {
                return commodityPromotion;
            }
        }

        return new CommodityPromotion();
    }

    @Override
    public boolean isShareCommodity(Long comId, Long userId) {

        BoundHashOperations operations = redisTemplate.boundHashOps(Constant.CACHE_KEY_PREFIX_SHARE_HASH + DateUtils.currDay());
        if (null == operations) {
            return false;
        }

        return operations.get(userId + "." + comId) != null;
    }

    @Override
    public boolean isOpenActivity(Integer guaType, CommodityPromotion commodityPromotion) {

        // 普通参与活动
        if (Constant.GUA_TYPE_COMMON.equals(guaType)) {
            return true;
        }

        Integer activityJoin = CommodityPromotionEnum.ACTIVITY_JOIN.getKey();
        // 一类参与活动
        if (Constant.GUA_TYPE_FIRST.equals(guaType) && activityJoin.equals(commodityPromotion.getFirstPro())) {
            return true;
        }

        // 二类参与活动
        if (Constant.GUA_TYPE_SECOND.equals(guaType) && activityJoin.equals(commodityPromotion.getSecondPro())) {
            return true;
        }

        return false;
    }

    /**
     * 商品详情页活动数据
     *
     * @Date: 17:22 2018/6/6
     */
    @Override
    public CommodityPromotionDTO selectCommodityRushDetail(Long comId, Integer appType) {
        CommodityPromotion promotion = this.selectLiveCommodityPromotion(comId, Sales.PANIC.getState(), appType);

        if (null == promotion) {
            return null;
        }

        CommodityPromotionDTO dto = new CommodityPromotionDTO();
        Date nowDate = new Date();

        dto.setId(promotion.getId());
        dto.setStartDate(promotion.getStartDate());
        dto.setEndDate(promotion.getEndDate());
        dto.setServerDate(nowDate);//服务器时间
        dto.setCharge(promotion.getCharge());
        dto.setRushType(promotion.getRushType());
        dto.setMaximum(promotion.getMaximum());// 数量上限
        dto.setCostPrice(promotion.getCostPrice());
        dto.setFirstPrice(promotion.getFirstPrice());
        dto.setSecondPrice(promotion.getSecondPrice());
        dto.setGuaMul(promotion.getGuaMul());
        dto.setFirstPro(promotion.getFirstPro());
        dto.setSecondPro(promotion.getSecondPro());
        dto.setCourseType(promotion.getCourseType());

        if (promotion.getSales() > promotion.getMaximum()) {
            dto.setSales(promotion.getMaximum());
        } else {
            dto.setSales(promotion.getSales());
        }

        // 限制时间
        if (Objects.equals(CommodityPromotionEnum.RUSH_TYPE_TIME.getKey(), promotion.getRushType())) {
            if (nowDate.before(promotion.getStartDate())) {
                dto.setStatus(CommodityPromotionEnum.STATUS_NO_START.getKey());
                return dto;
                // 未开始
            } else if (nowDate.after(promotion.getEndDate())) {
                // 已结束
                dto.setStatus(CommodityPromotionEnum.STATUS_END.getKey());
                return dto;
            }
        }

        // 售罄
        if (promotion.getSales() >= promotion.getMaximum()) {
            dto.setStatus(CommodityPromotionEnum.STATUS_SELL_OUT.getKey());
        }

        return dto;
    }

    @Override
    public CommodityPromotion selectLiveCommodityPromotion(Long comId, Integer activityType, Integer appType) {
        CommodityPromotion param = new CommodityPromotion();
        param.setComId(comId);
        param.setComType(6);
        param.setType(activityType);
        param.setStatus(1);
        param = this.commodityPromotionMapper.selectOne(param);
        //如果课程类型是系统班，并且终端不符，则返回null
         if(param ==null || !this.checkPromotionTerminalDisplay(param.getCourseType(), param.getId(), param.getType(), appType)){
             return null;
         }
         return param;
    }

    /**
     * 获取下架的直播课商品活动信息
     * @Date: 17:27 2018/6/26
     */
    @Override
    public CommodityPromotionDTO selectInvalidLiveCommodityPromotion(Long comId, Integer activityType, Integer appType) {
        CommodityPromotionDTO commodityPromotionDTO = this.commodityPromotionMapper.selectInvalidLiveCommodityPromotion(comId, activityType);
        //无效 -1
        if(commodityPromotionDTO!=null){
            commodityPromotionDTO.setStatus(CommodityPromotionEnum.STATUS_INVALID.getKey());
            //如果课程类型是系统班，并且终端不符，则返回null
            if(!this.checkPromotionTerminalDisplay(commodityPromotionDTO.getCourseType(), commodityPromotionDTO.getId(), commodityPromotionDTO.getType(), appType)){
                return null;
            }
        }
        return commodityPromotionDTO;
    }


    @Override
    public void rushCommodityAddSales(Long orderId, Long promotionId) {

        CommodityPromotion promotion = this.commodityPromotionMapper.selectByPrimaryKey(promotionId);
        if (null == promotion) {
            return;
        }
        // 销量增加
        this.commodityPromotionMapper.updateAddSales(promotionId);

        // 收费活动,数据存入redis
        if (Objects.equals(promotion.getCharge(), ClassTypeEnum.CHARGE.getKey())) {
            ListOperations listOperations = this.redisTemplate.opsForList();
            listOperations.rightPush(promotionRedisKey, this.getPromotionRedisInfo(orderId, promotionId));
            this.redisTemplate.expire(promotionRedisKey, expireHours, TimeUnit.HOURS);

        }
    }

    /**
     * 减少销量
     * */
    @Override
    public Result subSales(List<Long> orderIds) {
        if (null == orderIds) {
            return ResultGenerator.genFailResult("参数为空");
        }
        // 根据订单ID查询未支付的订单
        List<PayOrder> payOrders = this.payOrderService.findPayStatusNonOrder(orderIds);
        if (null == payOrders || payOrders.isEmpty()) {
            return ResultGenerator.genSuccessResult();
        }

        for (PayOrder payOrder : payOrders) {
            Long promotionId = payOrder.getPromotionId();
            if (null != promotionId) {
                logger.info("活动销量减少操作>>>>>订单ID:" + payOrder.getId() + ">>>活动ID:" + promotionId);
                this.commodityPromotionMapper.updateSubSales(promotionId);
            }
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * redis中存储的活动数据
     * */
    private Map getPromotionRedisInfo(Long orderId, Long promotionId) {
        Map<String, Object> value = new HashMap<>();
        value.put("orderId", orderId);
        value.put("promotionId", promotionId);
        value.put("date", new Date());
        return value;
    }

    /**
     * 判断系统班终端是否符合
     */
    private boolean checkPromotionTerminalDisplay(Integer courseType,Long relationId, Integer relationType, Integer appType){
        if(ClassTypeEnum.COURSE_TYPE_SPECIAL.getKey().equals(courseType)) {
            return true;
        } else if(ClassTypeEnum.COURSE_TYPE_SYSTEM.getKey().equals(courseType)
                && this.terminalDisplayService.terminalCheck(relationId, relationType, appType)) {
            return true;
        }
        return false;
    }
}
