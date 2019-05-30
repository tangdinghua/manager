package com.trip.busi.service;

import com.trip.commons.core.service.BaseService;
import com.trip.busi.entity.CustomizeTrip;
import com.trip.busi.dao.CustomizeTripDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author
 * @create 2019-05-30 21:20:12
 */

@Service
public class CustomizeTripService extends BaseService<CustomizeTrip> {

    @Autowired
    private CustomizeTripDao customizeTripDao;


}
