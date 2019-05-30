package com.trip.busi.service;

import com.talkweb.commons.core.service.BaseService;
import com.trip.busi.entity.SpecialOffer;
import com.trip.busi.dao.SpecialOfferDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author 玲玲
 * @create 2019-05-30 21:36:17
 */

@Service
public class SpecialOfferService extends BaseService<SpecialOffer> {

    @Autowired
    private SpecialOfferDao specialOfferDao;


}
