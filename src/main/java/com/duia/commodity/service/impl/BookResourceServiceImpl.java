package com.duia.commodity.service.impl;

import com.duia.commodity.dao.BookResourceMapper;
import com.duia.commodity.model.BookResource;
import com.duia.commodity.service.BookResourceService;
import com.duia.commodity.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/06/25.
 */
@Service
@Transactional
public class BookResourceServiceImpl extends AbstractService<BookResource> implements BookResourceService {
    @Resource
    private BookResourceMapper bookResourceMapper;

}
