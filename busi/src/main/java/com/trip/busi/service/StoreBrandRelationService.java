package com.trip.busi.service;

import com.talkweb.commons.core.service.BaseService;
import com.trip.busi.entity.StoreBrandRelation;
import com.trip.busi.dao.StoreBrandRelationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author 玲玲
 * @create 2019-05-30 21:37:34
 */

@Service
public class StoreBrandRelationService extends BaseService<StoreBrandRelation> {

    @Autowired
    private StoreBrandRelationDao storeBrandRelationDao;


}
