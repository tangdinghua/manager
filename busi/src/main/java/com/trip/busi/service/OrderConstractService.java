package com.trip.busi.service;

import com.talkweb.commons.core.service.BaseService;
import com.trip.busi.entity.OrderConstract;
import com.trip.busi.dao.OrderConstractDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author 玲玲
 * @create 2019-05-30 21:22:23
 */

@Service
public class OrderConstractService extends BaseService<OrderConstract> {

    @Autowired
    private OrderConstractDao orderConstractDao;


}
