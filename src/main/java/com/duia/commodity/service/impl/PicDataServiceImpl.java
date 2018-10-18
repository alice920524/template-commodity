package com.duia.commodity.service.impl;

import com.duia.commodity.common.dto.PicDataDTO;
import com.duia.commodity.dao.PicDataMapper;
import com.duia.commodity.model.PicData;
import com.duia.commodity.service.PicDataService;
import com.duia.commodity.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/07/12.
 */
@Service
@Transactional
public class PicDataServiceImpl extends AbstractService<PicData> implements PicDataService {
    @Resource
    private PicDataMapper picDataMapper;

    @Override
    public List<PicDataDTO> selectPicDataByClassType(Long evaluateId) {
        return this.picDataMapper.selectPicDataByClassType(evaluateId);
    }
}
