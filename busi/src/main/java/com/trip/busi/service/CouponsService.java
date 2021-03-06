package com.trip.busi.service;

import com.trip.commons.core.service.BaseService;
import com.trip.busi.entity.Coupons;
import com.trip.busi.dao.CouponsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author
 * @create 2019-05-30 21:18:17
 */

@Service
public class CouponsService extends BaseService<Coupons> {

    @Autowired
    private CouponsDao couponsDao;


}
