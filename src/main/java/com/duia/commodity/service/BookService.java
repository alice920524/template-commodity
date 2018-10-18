package com.duia.commodity.service;
import com.duia.commodity.model.Book;
import com.duia.commodity.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2018/04/09.
 */
public interface BookService extends Service<Book> {
    /**
     * 班型ID查询书
     * @Date: 19:21 2018/6/13
     */
    List<Book> findBookByClassTypeId(Long classTypeId);

    /**
     * 升级ID查询书
     *
     * @Date: 19:14 2018/6/13
     */
    List<Book> findBookByUpgradeId(Long upgradeId);
}
