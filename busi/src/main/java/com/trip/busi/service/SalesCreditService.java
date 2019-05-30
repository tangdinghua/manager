package com.trip.busi.service;

import com.talkweb.commons.core.service.BaseService;
import com.trip.busi.entity.SalesCredit;
import com.trip.busi.dao.SalesCreditDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author 玲玲
 * @create 2019-05-30 21:35:34
 */

@Service
public class SalesCreditService extends BaseService<SalesCredit> {

    @Autowired
    private SalesCreditDao salesCreditDao;


}
