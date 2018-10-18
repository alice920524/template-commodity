package com.duia.commodity.service;

import com.duia.commodity.common.dto.DiscountDTO;
import com.duia.commodity.core.Service;
import com.duia.commodity.model.VoucherDetail;

import java.util.List;


/**
 * Created by CodeGenerator on 2018/08/11.
 */
public interface VoucherDetailService extends Service<VoucherDetail> {

    List<DiscountDTO> selectYHTQDiscountList(Integer appType, Long userId);
}
