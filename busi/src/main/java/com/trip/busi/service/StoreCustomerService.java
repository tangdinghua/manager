package com.trip.busi.service;

import com.trip.commons.core.service.BaseService;
import com.trip.busi.entity.StoreCustomer;
import com.trip.busi.dao.StoreCustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author
 * @create 2019-05-30 21:37:48
 */

@Service
public class StoreCustomerService extends BaseService<StoreCustomer> {

    @Autowired
    private StoreCustomerDao storeCustomerDao;


}
