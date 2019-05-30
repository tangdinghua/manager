package com.trip.busi.service;

import com.talkweb.commons.core.service.BaseService;
import com.trip.busi.entity.Train;
import com.trip.busi.dao.TrainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author 玲玲
 * @create 2019-05-30 21:38:14
 */

@Service
public class TrainService extends BaseService<Train> {

    @Autowired
    private TrainDao trainDao;


}
