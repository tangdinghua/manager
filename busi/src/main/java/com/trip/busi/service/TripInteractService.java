package com.trip.busi.service;

import com.talkweb.commons.core.service.BaseService;
import com.trip.busi.entity.TripInteract;
import com.trip.busi.dao.TripInteractDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author 玲玲
 * @create 2019-05-30 21:41:16
 */

@Service
public class TripInteractService extends BaseService<TripInteract> {

    @Autowired
    private TripInteractDao tripInteractDao;


}
