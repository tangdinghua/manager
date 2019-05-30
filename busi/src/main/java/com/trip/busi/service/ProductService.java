package com.trip.busi.service;

import com.trip.commons.core.service.BaseService;
import com.trip.busi.entity.Product;
import com.trip.busi.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author
 * @create 2019-05-30 21:29:29
 */

@Service
public class ProductService extends BaseService<Product> {

    @Autowired
    private ProductDao productDao;


}
