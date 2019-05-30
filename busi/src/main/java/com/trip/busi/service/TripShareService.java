package com.trip.busi.service;

import com.trip.commons.core.service.BaseService;
import com.trip.busi.entity.TripShare;
import com.trip.busi.dao.TripShareDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author
 * @create 2019-05-30 21:41:35
 */

@Service
public class TripShareService extends BaseService<TripShare> {

    @Autowired
    private TripShareDao tripShareDao;


}
