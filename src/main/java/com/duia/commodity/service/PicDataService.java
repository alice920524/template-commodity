package com.duia.commodity.service;

import com.duia.commodity.common.dto.PicDataDTO;
import com.duia.commodity.core.Service;
import com.duia.commodity.model.PicData;

import java.util.List;


/**
 * Created by CodeGenerator on 2018/07/12.
 */
public interface PicDataService extends Service<PicData> {
    List<PicDataDTO> selectPicDataByClassType(Long evaluateId);
}
