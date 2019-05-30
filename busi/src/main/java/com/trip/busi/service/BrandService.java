package com.trip.busi.service;

import com.trip.commons.core.service.BaseService;
import com.trip.busi.entity.Brand;
import com.trip.busi.dao.BrandDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author
 * @create 2019-05-30 20:49:08
 */

@Service
public class BrandService extends BaseService<Brand> {

    @Autowired
    private BrandDao brandDao;


}
