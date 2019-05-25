package com.trip.commons.core.dao;

import com.trip.commons.core.bean.IDEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 基础数据库操作
 *
 * @author fqh
 * @create 2016-12-08 11:09
 */
public interface BaseDao<T extends IDEntity> {
    T findById(T t);

    int removeByEntity(T t);

    int insert(T t);

    List<T> findByExample(@Param("o") T t);

    int update(T t);
}
