package com.duia.commodity.service.impl;

import com.duia.commodity.common.dto.CommodityBaseDTO;
import com.duia.commodity.common.dto.CommodityResultDTO;
import com.duia.commodity.common.dto.CommoditySpecialBaseDTO;
import com.duia.commodity.common.enums.CommodityEnum;
import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.CommoditySpecialMapper;
import com.duia.commodity.model.CommoditySpecial;
import com.duia.commodity.service.CommodityService;
import com.duia.commodity.service.CommoditySpecialService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/07/13.
 */
@Service
@Transactional
public class CommoditySpecialServiceImpl extends AbstractService<CommoditySpecial> implements CommoditySpecialService {
    @Resource
    private CommoditySpecialMapper commoditySpecialMapper;
    @Resource
    private CommodityService commodityService;

    /**
     * 套餐组合信息
     * @param comId 商品ID
     * @param type  类型，1：套餐；2：组合
     */
    @Override
    public List<CommodityResultDTO> findByComIdAndType(Long comId, Integer type, Integer checked, Integer appType, Integer bookTypeChecked) {
        // 套餐、组合列表 返回gua_type进行排序，先显示五年，再显示三年。
        List<CommoditySpecialBaseDTO> commoditySpecialBaseDTOList = commoditySpecialMapper.selectByComIdAndType(comId, type, appType);

        //套餐组合result类
        List<CommodityResultDTO> commodityResultDTOList = new ArrayList<>(commoditySpecialBaseDTOList.size());

        for (CommoditySpecialBaseDTO dto : commoditySpecialBaseDTOList) {

            //TODO 商品价格包含学习包价格
            /*套餐商品集合(套餐/组合 含有主商品)*/
            List<CommodityBaseDTO> commodityBaseDTOList = this.commodityService.selectSpecialCommodityDTO(dto.getId(), comId, checked, bookTypeChecked);

            dto.setComList(commodityBaseDTOList);

            /*TODO 价格计算*/
            this.calculationPrice(dto, commodityBaseDTOList);


            /*组装数据*/
            CommodityResultDTO commodityResultDTO = new CommodityResultDTO();
            commodityResultDTO.setCommoditySpecial(dto);// 套餐/组合商品  集合中包含主商品信息方便前端使用

            commodityResultDTO.setCommodity(commodityBaseDTOList.get(0));// 套餐/组合中主商品信息

            commodityResultDTO.setType(type);// 返回套餐/组合标识

            commodityResultDTOList.add(commodityResultDTO);
        }
        return commodityResultDTOList;
    }

    /**
     * 根据商品id，特殊商品类型，终端显示找出特殊商品数量
     * @param comId
     * @param type
     * @param appType
     * @return
     */
    @Override
    public Integer findCountByComIdAndType(Long comId, Integer type, Integer appType) {
        return commoditySpecialMapper.selectCountByComIdAndType(comId, type, appType);
    }

    /**
     * @Description:计算套餐价格
     *  realPrice:真实价格(套餐价+所有商品学习包价格)
     *  costPrice:成本价格(商品价格+学习包价格)
     * @Date: 14:39 2018/5/16
     */
    private void calculationPrice(CommoditySpecialBaseDTO dto, List<CommodityBaseDTO> comList) {
        if (CommodityEnum.COMMODITY_TYPE_COMBO.getKey().equals(dto.getType())) {
            /*TODO 套餐   套餐价格 realPrice*/
            BigDecimal costPrice = new BigDecimal(0); // 用商品+学习包的价格
            BigDecimal realPrice = new BigDecimal(dto.getRealpayPrice() == null ? 0 : dto.getRealpayPrice()); // 只加商品学习包价格
            if (null != comList) {
                for (CommodityBaseDTO commodityBaseDTO : comList) {
                    costPrice = costPrice.add(new BigDecimal(commodityBaseDTO.getCostPrice()));
                    realPrice = realPrice.add(new BigDecimal(commodityBaseDTO.getStudyPackPrice()));
                }
            }

            dto.setCostPrice(realPrice.doubleValue());
            dto.setRealpayPrice(costPrice.doubleValue());
        } else if(CommodityEnum.COMMODITY_TYPE_COMBINATION.getKey().equals(dto.getType())) {
            dto.setCostPrice(comList.get(0).getCostPrice());
            dto.setRealpayPrice(comList.get(0).getRealpayPrice());
        }

    }

    /**
     *查询套餐和组合的总数量
     */
    public List<Double>  findTzCountByComIdAndType(Long comId,Integer appType){
        return commoditySpecialMapper.selectTzCountByComIdAndType(comId,appType);
    }
}
