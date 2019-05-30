package com.trip.busi.service;

import com.trip.commons.core.service.BaseService;
import com.trip.busi.entity.Visa;
import com.trip.busi.dao.VisaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author tdh
 * @create 2019-05-30 21:42:38
 */

@Service
public class VisaService extends BaseService<Visa> {

    @Autowired
    private VisaDao visaDao;


}
