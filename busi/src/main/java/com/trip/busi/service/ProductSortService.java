package com.trip.busi.service;

import com.talkweb.commons.core.service.BaseService;
import com.trip.busi.entity.ProductSort;
import com.trip.busi.dao.ProductSortDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author 玲玲
 * @create 2019-05-30 21:30:23
 */

@Service
public class ProductSortService extends BaseService<ProductSort> {

    @Autowired
    private ProductSortDao productSortDao;


}
