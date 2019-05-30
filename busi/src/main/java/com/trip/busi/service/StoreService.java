package com.trip.busi.service;

import com.trip.commons.core.service.BaseService;
import com.trip.busi.entity.Store;
import com.trip.busi.dao.StoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author
 * @create 2019-05-30 21:37:12
 */

@Service
public class StoreService extends BaseService<Store> {

    @Autowired
    private StoreDao storeDao;


}
