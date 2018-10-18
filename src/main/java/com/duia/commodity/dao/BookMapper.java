package com.duia.commodity.dao;

import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.Book;

import java.util.List;

public interface BookMapper extends Mapper<Book> {

    /**
     * 班型ID查询书
     *
     * @Date: 19:14 2018/6/13
     */
    List<Book> findBookByClassTypeId(Long classTypeId);

    /**
     * 升级ID查询书
     *
     * @Date: 19:14 2018/6/13
     */
    List<Book> findBookByUpgradeId(Long upgradeId);

}