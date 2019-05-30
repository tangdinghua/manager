package com.trip.busi.service;

import com.talkweb.commons.core.service.BaseService;
import com.trip.busi.entity.MotorCade;
import com.trip.busi.dao.MotorCadeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author 玲玲
 * @create 2019-05-30 21:21:41
 */

@Service
public class MotorCadeService extends BaseService<MotorCade> {

    @Autowired
    private MotorCadeDao motorCadeDao;


}
