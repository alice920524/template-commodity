package com.duia.commodity.service;

import com.duia.commodity.common.dto.CommodityBaseDTO;
import com.duia.commodity.common.dto.CommodityDTO;
import com.duia.commodity.core.Service;
import com.duia.commodity.model.Commodity;
import com.duia.security.param.CommodityParam;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/06/30.
 */
public interface CommodityService extends Service<Commodity> {

    /**
     * 验证用户是否符合老生优惠
     *
     * @param userId
     * @return
     */
    boolean validateAccordOldSale(Long userId);
    /**
     * 查询商品上架状态，1：上架：0：下架
     *
     * @param comId
     * @param appType
     * @return
     */
    int queryCommodityStatus(Long comId, Integer appType);

    /**
     * 获得赠品列表
     *
     * @param idList
     * @return
     */
    List<Commodity> findByIdList(List<Long> idList);
    /**
     * 套餐/组合商品信息
     *
     * @param specialId
     * @param comId
     * @param checked
     * @return
     */
    List<CommodityBaseDTO> selectSpecialCommodityDTO(Long specialId, Long comId, Integer checked, Integer bookTypeChecked);
    /**
     * 获取单品商品
     * */
    List<CommodityDTO> selectCommodityByParam(List<CommodityParam> params);
    /**
     * 获取套餐、组合商品(主商品第一位)
     * */
    List<CommodityDTO> selectSpecialCommodityByParam(Long specialId, List<CommodityParam> params);
    /**
     * 所有商品,不区分上下架
     * */
    List<CommodityDTO> selectAllCommodityByParam(List<CommodityParam> params);
}
