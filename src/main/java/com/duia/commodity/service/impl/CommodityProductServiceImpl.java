package com.duia.commodity.service.impl;

import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.CommodityProductMapper;
import com.duia.commodity.model.CommodityProduct;
import com.duia.commodity.service.CommodityProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/07/19.
 */
@Service
@Transactional
public class CommodityProductServiceImpl extends AbstractService<CommodityProduct> implements CommodityProductService {
    @Resource
    private CommodityProductMapper commodityProductMapper;

    @Override
    public CommodityProduct findBy(Long proId, Integer type) {
        Condition condition = new Condition(CommodityProduct.class);
        condition.createCriteria().andEqualTo("proId", proId).andEqualTo("proType", type);
        List<CommodityProduct> list = this.findByCondition(condition);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 获取直播商品ID
     * @param classId
     * @return
     */
    @Override
    public Long selectLiveCommodityComId(Long classId) {
        CommodityProduct commodityProduct = this.findBy(classId, 6);
        if (null == commodityProduct) {
            return null;
        }
        return commodityProduct.getComId();
    }

    /**
     * 获取直播班级ID
     * @param comId
     * @return
     */
    @Override
    public Long selectLiveCommodityClassId(Long comId) {
        CommodityProduct commodityProduct = this.findBy("comId", comId);
        if (null == commodityProduct) {
            return null;
        }
        return commodityProduct.getProId();
    }
}
