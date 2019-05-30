package com.trip.busi.service;

import com.trip.commons.core.service.BaseService;
import com.trip.busi.entity.Ordercredit;
import com.trip.busi.dao.OrdercreditDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author
 * @create 2019-05-30 21:23:06
 */

@Service
public class OrdercreditService extends BaseService<Ordercredit> {

    @Autowired
    private OrdercreditDao ordercreditDao;


}
