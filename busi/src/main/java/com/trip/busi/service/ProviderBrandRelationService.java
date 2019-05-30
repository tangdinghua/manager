package com.trip.busi.service;

import com.talkweb.commons.core.service.BaseService;
import com.trip.busi.entity.ProviderBrandRelation;
import com.trip.busi.dao.ProviderBrandRelationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author 玲玲
 * @create 2019-05-30 21:32:22
 */

@Service
public class ProviderBrandRelationService extends BaseService<ProviderBrandRelation> {

    @Autowired
    private ProviderBrandRelationDao providerBrandRelationDao;


}
