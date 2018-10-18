package com.duia.commodity.service.impl;

import com.duia.commodity.common.dto.DiscountDTO;
import com.duia.commodity.dao.VoucherDetailMapper;
import com.duia.commodity.model.VoucherDetail;
import com.duia.commodity.service.VoucherDetailService;
import com.duia.commodity.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/08/11.
 */
@Service
@Transactional
public class VoucherDetailServiceImpl extends AbstractService<VoucherDetail> implements VoucherDetailService {
    @Resource
    private VoucherDetailMapper voucherDetailMapper;

    /**
     * 获取优惠特权列表
     * @param appType
     * @param userId
     * @return
     */
    @Override
    public List<DiscountDTO> selectYHTQDiscountList(Integer appType, Long userId) {
        return this.voucherDetailMapper.selectYHTQDiscountList(appType, userId);
    }
}
