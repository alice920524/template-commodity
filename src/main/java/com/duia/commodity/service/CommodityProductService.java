package com.duia.commodity.service;
import com.duia.commodity.model.CommodityProduct;
import com.duia.commodity.core.Service;


/**
 * Created by CodeGenerator on 2017/07/19.
 */
public interface CommodityProductService extends Service<CommodityProduct> {
    /**
     * 根据班级ID和类型查询指定商品ID
     * @param proId
     * @param type
     * @return
     */
    CommodityProduct findBy(Long proId, Integer type);

    /**
     * 获取直播商品ID
     * */
    Long selectLiveCommodityComId(Long classId);

    /**
     * 获取直播班级ID
     * */
    Long selectLiveCommodityClassId(Long comId);

}
