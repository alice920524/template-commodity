package com.duia.commodity.service.impl;

import com.duia.commodity.common.Constant;
import com.duia.commodity.common.dto.CommodityBaseDTO;
import com.duia.commodity.common.dto.CommodityDTO;
import com.duia.commodity.common.dto.InsuranceDTO;
import com.duia.commodity.common.enums.CommodityEnum;
import com.duia.commodity.common.enums.OrderEnum;
import com.duia.commodity.common.util.OrderPriceUtil;
import com.duia.commodity.core.AbstractService;
import com.duia.commodity.core.ServiceException;
import com.duia.commodity.dao.CommodityMapper;
import com.duia.commodity.dao.PayOrderMapper;
import com.duia.commodity.model.AggrementTemplate;
import com.duia.commodity.model.Commodity;
import com.duia.commodity.model.PayOrder;
import com.duia.commodity.service.AggrementTemplateService;
import com.duia.commodity.service.ClassTypeService;
import com.duia.commodity.service.CommodityService;
import com.duia.security.exception.CheckPayOrderParamException;
import com.duia.security.param.CommodityParam;
import com.duia.security.param.InsuranceParam;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * Created by CodeGenerator on 2017/06/30.
 */
@Service
@Transactional
public class CommodityServiceImpl extends AbstractService<Commodity> implements CommodityService {
    @Resource
    private CommodityMapper commodityMapper;
    @Resource
    private PayOrderMapper orderMapper;
    @Resource
    private ClassTypeService classTypeService;
    @Resource
    private AggrementTemplateService aggrementTemplateService;

    @Override
    public boolean validateAccordOldSale(Long userId) {
        Condition condition = new Condition(PayOrder.class);
        condition.createCriteria()
                .andEqualTo("payStatus", "pay_status_success")
                .andEqualTo("userId", userId)
                .andEqualTo("courseType", 0);// 不包含专题课
        List<PayOrder> orderList = orderMapper.selectByCondition(condition);
        for (PayOrder order : orderList) {
            try {
                Date orderDate = DateUtils.parseDate(order.getPayTime(), "yyyy-MM-dd HH:mm:ss");
                long timeDifference = System.currentTimeMillis() - orderDate.getTime();//时间差
                if (timeDifference > TimeUnit.DAYS.toMillis(7)) {//大于 7天
                    return true;
                }
            } catch (ParseException e) {
                throw new ServiceException(e.getMessage(), e);
            }
        }
        return false;
    }

    /**
     *
     * 查询商品上架状态，1：上架：0：下架
     * @param comId
     * @param appType
     * @return
     */
    @Override
    public int queryCommodityStatus(Long comId, Integer appType) {
        int count = commodityMapper.countByComIdAndAppType(comId, appType);
        return count > 0 ? CommodityEnum.COMMODITY_VALID.getKey() : CommodityEnum.COMMODITY_INVALID.getKey();
    }

    @Override
    public List<Commodity> findByIdList(List<Long> idList) {
        return commodityMapper.selectByIdList(idList);
    }

    @Override
    public List<CommodityBaseDTO> selectSpecialCommodityDTO(Long specialId, Long comId, Integer checked, Integer bookTypeChecked) {

        List<CommodityBaseDTO> commodityBaseDTOList = this.commodityMapper.selectCommodityDTOBySpecialId(specialId);
        if (null != commodityBaseDTOList && !commodityBaseDTOList.isEmpty()) {
            // 主商品放到第一位
            commodityToFirst(commodityBaseDTOList, comId, new Function<CommodityBaseDTO, Long>() {
                @Override
                public Long apply(CommodityBaseDTO input) {
                    return input.getComId();
                }
            });

            int size = commodityBaseDTOList.size();


            for (int i = 0; i < size; i++) {
                CommodityBaseDTO commodityBaseDTO = commodityBaseDTOList.get(i);
                // 商品价格+学习包价格
                commodityBaseDTO.setCostPrice(commodityBaseDTO.getCostPrice(), commodityBaseDTO.getStudyPackPrice());
                commodityBaseDTO.setRealpayPrice(commodityBaseDTO.getCostPrice());

                // 学习包类型勾选状态
                commodityBaseDTO.setBookTypeChecked(bookTypeChecked);

                // 配置了保险
                if (commodityBaseDTO.insuranceStatus()) {
                    List<InsuranceDTO> insuranceDTOList = this.aggrementTemplateService.findInsuranceByClassTypeIdList(Arrays.asList(commodityBaseDTO.getClassTypeId()));
                    if (null != insuranceDTOList && insuranceDTOList.size() > 0) {
                        commodityBaseDTO.setInsurance(insuranceDTOList.get(0));
                    }
                }
                //首商品
                if (i == 0) {

                    // 单品保险默认选中装填
                    commodityBaseDTO.setInsuranceChecked(checked);
                    // icon
                    commodityBaseDTO.setAgreements(this.classTypeService.getAggrements(this.classTypeService.findById(commodityBaseDTO.getClassTypeId())));
                }
            }

        }

        return commodityBaseDTOList;
    }

    @Override
    public List<CommodityDTO> selectCommodityByParam(List<CommodityParam> params) {
        List<CommodityDTO> commodityDTOList = this.commodityMapper.selectCommodityByParam(params, 1);

        // 多余一个商品的时候才进行主商品排序
        this.mainCommodityDTOToFirst(commodityDTOList, params.get(0).getComId());

        setCommodityDTOByCommodityParam(commodityDTOList, params);

        return commodityDTOList;
    }

    @Override
    public List<CommodityDTO> selectSpecialCommodityByParam(Long specialId, List<CommodityParam> params) {
        List<CommodityDTO> commodityDTOList = this.commodityMapper.selectSpecialCommodityByParam(specialId, params, 1);

        // 多余一个商品的时候才进行主商品排序
        this.mainCommodityDTOToFirst(commodityDTOList, params.get(0).getComId());

        // key 商品ID、value 参数对象(商品ID、guaType、学习包选中类型、选中保险信息)
        Map<Long, CommodityParam> commodityParamMap = Maps.uniqueIndex(params, new Function<CommodityParam, Long>() {
            @Override
            public Long apply(CommodityParam input) {
                return input.getComId();
            }
        });

        for (CommodityDTO commodityDTO : commodityDTOList) {
            CommodityParam commodityParam = commodityParamMap.get(commodityDTO.getId());
            commodityDTO.setSpecialId(specialId);
            commodityDTO.setComMode(Constant.COM_MODE_SPECIAL);
            commodityDTO.setBuyType(OrderEnum.BUY_TYPE_COMMON.getKey());
            // 价格同步
            commodityDTO.setComCostPrice(commodityDTO.getPrice());
            commodityDTO.setComRealPrice(commodityDTO.getPrice());

            // 学习包类型选中状态
            commodityDTO.setBookTypeChecked(commodityParam.getBookTypeChecked());
            // 查询保险信息
            this.setInsuranceInfo(commodityParam, commodityDTO);
            commodityDTO.setClassType(this.classTypeService.findById(commodityDTO.getClassTypeId()));
        }
        return commodityDTOList;
    }

    @Override
    public List<CommodityDTO> selectAllCommodityByParam(List<CommodityParam> params) {
        List<CommodityDTO> commodityDTOList = this.commodityMapper.selectCommodityByParam(params, null);

        this.mainCommodityDTOToFirst(commodityDTOList, params.get(0).getComId());

        setCommodityDTOByCommodityParam(commodityDTOList, params);

        return commodityDTOList;
    }

    /**
     *
     * */
    private void setCommodityDTOByCommodityParam(List<CommodityDTO> commodityDTOList, List<CommodityParam> params) {

        // key 商品ID、value 参数对象(商品ID、guaType、学习包选中类型、选中保险信息)
        Map<Long, CommodityParam> commodityParamMap = Maps.uniqueIndex(params, new Function<CommodityParam, Long>() {
            @Override
            public Long apply(CommodityParam input) {
                return input.getComId();
            }
        });

        for (CommodityDTO commodityDTO : commodityDTOList) {
            CommodityParam commodityParam = commodityParamMap.get(commodityDTO.getId());

            commodityDTO.setComMode(Constant.COM_MODE_NORMAL);
            commodityDTO.setBuyType(OrderEnum.BUY_TYPE_COMMON.getKey());

            Integer guaType = commodityParam.getGuaType();
            if (null == guaType) {
                guaType = this.getGuaTypeByGuaMul(commodityDTO.getGuaMul());
            }

            // 根据guaType取价格
            commodityDTO.setComCostPrice(OrderPriceUtil.getCommodityPrice(commodityDTO, guaType));
            commodityDTO.setComRealPrice(commodityDTO.getComCostPrice());
            commodityDTO.setGuaType(guaType);
            // 学习包类型选中状态
            commodityDTO.setBookTypeChecked(commodityParam.getBookTypeChecked());
            // 查询保险信息
            this.setInsuranceInfo(commodityParam, commodityDTO);
            commodityDTO.setClassType(this.classTypeService.findById(commodityDTO.getClassTypeId()));
        }
    }
    /**
     * 组装保险信息
     * */
    private void setInsuranceInfo(CommodityParam param, CommodityDTO commodityDTO) {
        if (null != param && param.getInsurance() != null) {
            InsuranceParam insuranceParam = param.getInsurance();

            // 选中
            if (OrderEnum.INSURANCE_CHECKED.getKey().equals(insuranceParam.getChecked())) {
                AggrementTemplate aggrementTemplate = aggrementTemplateService.findInsuranceAggrementTemplate(param.getComId(), insuranceParam.getId());
                if (null == aggrementTemplate) {
                    throw new CheckPayOrderParamException("保险数据被篡改>>>商品ID>>>" + param.getComId() + ">>>保险ID>>>" + param.getInsurance().getId() + ">>>保险选中状态>>>" + !OrderEnum.INSURANCE_UN_CHECKED.getKey().equals(param.getInsurance().getChecked()));
                }
                commodityDTO.setAggrementTemplate(aggrementTemplate);
            }
        }
    }

    /**
     * 通过guaMul获取guaType
     * */
    private Integer getGuaTypeByGuaMul(Integer guaMul) {
        if (null == guaMul || Constant.GUA_MUL_COMMON.equals(guaMul)) {
            return Constant.GUA_TYPE_COMMON;
        } else {
            return Constant.GUA_TYPE_FIRST;
        }
    }

    private void mainCommodityDTOToFirst(List<CommodityDTO> commodityDTOList, Long comId) {
        // 多余一个商品的时候才进行主商品排序
        if (null != commodityDTOList && commodityDTOList.size() > 1) {
            // 主商品第一位
            this.commodityToFirst(commodityDTOList, comId, new Function<CommodityDTO, Long>() {
                @Override
                public Long apply(CommodityDTO input) {
                    return input.getId();
                }
            });
        }
    }

    /**
     * @Description:单品到第一位
     * @Date: 13:11 2018/5/15
     */
    private <V, T> void commodityToFirst(List<V> commodityBaseDTOList, T comId, Function<V, T> function) {
        int size = commodityBaseDTOList.size();
        for (int i = 0; i < size; i++) {
            V commodityBaseDTO = commodityBaseDTOList.get(i);
            if (function.apply(commodityBaseDTO).equals(comId)) {
                swapCommodity(i, commodityBaseDTOList);
            }
        }
    }

    /**
     * 交换
     */
    private void swapCommodity(int index, List commoditys) {
        // 单品
        Object commodity = commoditys.get(index);
        // 交换数据
        commodity = commoditys.set(0, commodity);
        commoditys.set(index, commodity);
    }

}
