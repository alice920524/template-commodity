package com.duia.commodity.service.impl;

import com.duia.commodity.dao.SysDictMapper;
import com.duia.commodity.model.SysDict;
import com.duia.commodity.service.SysDictService;
import com.duia.commodity.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/07/24.
 */
@Service
@Transactional
public class SysDictServiceImpl extends AbstractService<SysDict> implements SysDictService {
    @Resource
    private SysDictMapper sysDictMapper;

}
