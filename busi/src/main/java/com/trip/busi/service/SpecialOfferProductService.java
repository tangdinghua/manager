package com.trip.busi.service;

import com.trip.commons.core.service.BaseService;
import com.trip.busi.entity.SpecialOfferProduct;
import com.trip.busi.dao.SpecialOfferProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author
 * @create 2019-05-30 21:36:54
 */

@Service
public class SpecialOfferProductService extends BaseService<SpecialOfferProduct> {

    @Autowired
    private SpecialOfferProductDao specialOfferProductDao;


}
