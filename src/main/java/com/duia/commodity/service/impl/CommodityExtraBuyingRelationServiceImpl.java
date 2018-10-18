package com.duia.commodity.service.impl;

import com.duia.commodity.common.Constant;
import com.duia.commodity.common.dto.CommodityDTO;
import com.duia.commodity.common.dto.CommodityExtraDTO;
import com.duia.commodity.common.enums.CommodityEnum;
import com.duia.commodity.common.enums.OrderEnum;
import com.duia.commodity.common.util.OrderPriceUtil;
import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.CommodityExtraBuyingRelationMapper;
import com.duia.commodity.model.ClassType;
import com.duia.commodity.model.CommodityExtraBuyingRelation;
import com.duia.commodity.service.ClassTypeService;
import com.duia.commodity.service.CommodityExtraBuyingRelationService;
import com.duia.enums.StudyPackage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/05/18.
 */
@Service
@Transactional
public class CommodityExtraBuyingRelationServiceImpl extends AbstractService<CommodityExtraBuyingRelation> implements CommodityExtraBuyingRelationService {
    @Resource
    private CommodityExtraBuyingRelationMapper commodityExtraBuyingRelationMapper;
    @Resource
    private ClassTypeService classTypeService;

    /**
     * 加价购商品列表
     * @param comId
     * @param specialId
     * @param comType
     * @param appType
     * @return
     */
     @Override
    public List<CommodityExtraDTO> findCommodityExtra(Long comId,Long specialId,Integer comType, Integer appType) {
        List<CommodityExtraDTO> commodityList;
        //判断是单品还是套餐组合
        if (CommodityEnum.COMMODITY_TYPE_SINGLE.getKey().equals(comType)){
            //单品加价购列表
            commodityList = commodityExtraBuyingRelationMapper.findCommodityExtra(comId, comType, appType);
        } else {
            //套餐或组合加价购列表
            commodityList = commodityExtraBuyingRelationMapper.findSpecialCommodityExtra(specialId, comType, appType);
        }
        for (CommodityExtraDTO commodityExtraDTO:commodityList){
            //根据id获取classType
            Long classTypeId = commodityExtraDTO.getClassTypeId();
            ClassType classType = classTypeService.findById(classTypeId);

            // TODO 邮寄状态由加价购和班型同时控制
            if (isMail(commodityExtraDTO.getHasStudyPack(), classTypeId)) {
                classType.setAddressMark(StudyPackage.VALID.getState());
            } else {
                classType.setAddressMark(StudyPackage.INVALID.getState());
            }

            commodityExtraDTO.setAgreements(classTypeService.getAggrements(classType)); //设置协议icon列表
            //判断当加价购和班型同时包含学习包的时候才有学习包的信息，否则没有学习包
            if (classType.getAddressMark() == StudyPackage.VALID.getState()){
                Double bookPrice = classType.getBookPrice(); //学习包价格
                commodityExtraDTO.setStudyPackPrice(bookPrice); //设置学习包价格
                commodityExtraDTO.setCostPrice(bookPrice + commodityExtraDTO.getCostPrice());
                commodityExtraDTO.setRealpayPrice(bookPrice + commodityExtraDTO.getRealpayPrice());
                commodityExtraDTO.setFirstPrice(bookPrice + commodityExtraDTO.getFirstPrice());
                commodityExtraDTO.setSecondPrice(bookPrice + commodityExtraDTO.getSecondPrice());
                commodityExtraDTO.setExtraPrice(bookPrice + commodityExtraDTO.getExtraPrice());
                commodityExtraDTO.setBookType(classType.getBookType());
            } else {
                //不需要邮寄
                commodityExtraDTO.setHasStudyPack(StudyPackage.INVALID.getState());
            }
            //设置web、wap封面图片
            commodityExtraDTO.setWebImg(classType.getCoverUrlTwo());
            commodityExtraDTO.setAppImg(classType.getCoverUrlThree());
        }
        return commodityList;
    }

    /*是否需要邮寄*/
    private boolean isMail(Integer addMoneyAddressMark, Long classTypeId) {
        if (null == addMoneyAddressMark) {
            return false;
        }

        if (addMoneyAddressMark.equals(StudyPackage.VALID.getState())) {
            ClassType classType = this.classTypeService.findById(classTypeId);
            if (null != classType && classType.getAddressMark() != null && classType.getAddressMark().equals(StudyPackage.VALID.getState())) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    /**
     * 查询组合加价购商品、单品加价购商品、套餐加价购商品
     */
    public CommodityDTO selectCommodity(Long relationId, Integer type,Long comId) {
        CommodityDTO commodityDTO = this.commodityExtraBuyingRelationMapper.selectCommodity(relationId, type, comId);

        // 加价购有学习包情况下,需要从班型上确认学习包状态值
        if (null != commodityDTO) {
            ClassType classType = this.classTypeService.findById(commodityDTO.getClassTypeId());

            if (this.isMail(commodityDTO.getAddressMark(), commodityDTO.getClassTypeId())) {
                commodityDTO.setAddressMark(classType.getAddressMark());
                commodityDTO.setBookPrice(classType.getBookPrice());
            } else {
                commodityDTO.setAddressMark(StudyPackage.INVALID.getState());
                commodityDTO.setBookPrice(0.0);
            }

            commodityDTO.setClassType(classType);
            // 设置价格
            commodityDTO.setComCostPrice(commodityDTO.getPrice());
            commodityDTO.setComRealPrice(OrderPriceUtil.getAddMoneyCommodityPrice(commodityDTO, commodityDTO.getGuaType()));

            commodityDTO.setBuyType(OrderEnum.BUY_TYPE_ADD_MONEY.getKey());
            commodityDTO.setComMode(Constant.COM_MODE_NORMAL);
        }
        return commodityDTO;
    }

}
