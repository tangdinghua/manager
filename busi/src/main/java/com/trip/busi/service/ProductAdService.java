package com.trip.busi.service;

import com.trip.commons.core.service.BaseService;
import com.trip.busi.entity.ProductAd;
import com.trip.busi.dao.ProductAdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author
 * @create 2019-06-15 10:55:30
 */

@Service
public class ProductAdService extends BaseService<ProductAd> {

    @Autowired
    private ProductAdDao productAdDao;


}
