package com.duia.commodity.service.impl;

import com.duia.commodity.dao.BookMapper;
import com.duia.commodity.model.Book;
import com.duia.commodity.service.BookService;
import com.duia.commodity.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/04/09.
 */
@Service
@Transactional
public class BookServiceImpl extends AbstractService<Book> implements BookService {
    @Resource
    private BookMapper bookMapper;

    /**
     * 通过班型id查询图书信息
     * @param classTypeId
     * @return
     */
    @Override
    public List<Book> findBookByClassTypeId(Long classTypeId) {
        return this.bookMapper.findBookByClassTypeId(classTypeId);
    }

    /**
     * 查询图书信息
     * @param upgradeId
     * @return
     */
    @Override
    public List<Book> findBookByUpgradeId(Long upgradeId) {
        return this.bookMapper.findBookByUpgradeId(upgradeId);
    }

}
