package com.trip.busi.service;

import com.trip.commons.core.service.BaseService;
import com.trip.busi.entity.RoleElement;
import com.trip.busi.dao.RoleElementDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author
 * @create 2019-05-30 21:33:57
 */

@Service
public class RoleElementService extends BaseService<RoleElement> {

    @Autowired
    private RoleElementDao roleElementDao;


}
