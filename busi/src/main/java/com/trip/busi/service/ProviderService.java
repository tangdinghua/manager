package com.trip.busi.service;

import com.trip.commons.core.service.BaseService;
import com.trip.busi.entity.Provider;
import com.trip.busi.dao.ProviderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author
 * @create 2019-05-30 21:30:44
 */

@Service
public class ProviderService extends BaseService<Provider> {

    @Autowired
    private ProviderDao providerDao;


}
