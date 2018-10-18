package com.duia.commodity.dao;

import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.Commodity;
import com.duia.commodity.model.SysDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDictMapper extends Mapper<SysDict> {
    String findSysDictBysku(@Param("sku") Integer sku);
}