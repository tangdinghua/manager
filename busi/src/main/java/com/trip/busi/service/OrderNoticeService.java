package com.trip.busi.service;

import com.trip.commons.core.service.BaseService;
import com.trip.busi.entity.OrderNotice;
import com.trip.busi.dao.OrderNoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author
 * @create 2019-05-30 21:29:11
 */

@Service
public class OrderNoticeService extends BaseService<OrderNotice> {

    @Autowired
    private OrderNoticeDao orderNoticeDao;


}
