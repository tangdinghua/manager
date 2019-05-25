package com.trip.base.dao;

import com.trip.base.entity.Org;
import com.trip.commons.core.dao.BaseDao;

import java.util.List;

/**
 * 数据库操作
 * @author fqh
 * @create 2017-02-19 12:00:27
 */
public interface OrgDao extends BaseDao<Org> {


    List<Org> queryAllSonOrg();
}
