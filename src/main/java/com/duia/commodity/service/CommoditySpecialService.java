package com.duia.commodity.service;

import com.duia.commodity.common.dto.CommodityResultDTO;
import com.duia.commodity.core.Service;
import com.duia.commodity.model.CommoditySpecial;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/07/13.
 */
public interface CommoditySpecialService extends Service<CommoditySpecial> {
    /**
     * 套餐组合信息
     * @param comId 商品ID
     * @param type  类型，1：套餐；2：组合
     */
    List<CommodityResultDTO> findByComIdAndType(Long comId, Integer type, Integer checked, Integer appType, Integer bookTypeChecked);

    /**
     * 根据商品id，特殊商品类型，终端显示找出特殊商品数量
     *
     * @param comId
     * @param type
     * @param appType
     * @return
     */
    Integer findCountByComIdAndType(Long comId, Integer type, Integer appType);

    /**
     * 查询套餐组合的总个数
     * @param comId
     * @param appType
     * @return
     */
    List<Double> findTzCountByComIdAndType(Long comId,Integer appType);

}
