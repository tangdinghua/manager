package com.trip.busi.service;

import com.talkweb.commons.core.service.BaseService;
import com.trip.busi.entity.OrderInfo;
import com.trip.busi.dao.OrderInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author 玲玲
 * @create 2019-05-30 21:28:49
 */

@Service
public class OrderInfoService extends BaseService<OrderInfo> {

    @Autowired
    private OrderInfoDao orderInfoDao;


}
