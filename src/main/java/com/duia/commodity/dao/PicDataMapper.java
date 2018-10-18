package com.duia.commodity.dao;

import com.duia.commodity.common.dto.PicDataDTO;
import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.PicData;

import java.util.List;

public interface PicDataMapper extends Mapper<PicData> {

    List<PicDataDTO> selectPicDataByClassType(Long evaluateId);

}