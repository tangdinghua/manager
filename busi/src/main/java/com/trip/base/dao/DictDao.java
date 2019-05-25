package com.trip.base.dao;

import com.trip.commons.core.dao.BaseDao;

import com.trip.base.entity.Dict;

import java.util.List;


public interface DictDao extends BaseDao<Dict>{

    List<Dict> findByCode(String code);

    void removeByParentid(Long parentid);
}
