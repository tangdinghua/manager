package com.trip.busi.service;

import com.talkweb.commons.core.service.BaseService;
import com.trip.busi.entity.PurchaseRecord;
import com.trip.busi.dao.PurchaseRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author 玲玲
 * @create 2019-05-30 21:32:44
 */

@Service
public class PurchaseRecordService extends BaseService<PurchaseRecord> {

    @Autowired
    private PurchaseRecordDao purchaseRecordDao;


}
