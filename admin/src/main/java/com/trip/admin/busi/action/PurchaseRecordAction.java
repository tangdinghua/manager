package com.trip.admin.busi.action;

import com.trip.commons.core.service.BaseService;
import com.trip.base.action.BaseAction;
import com.trip.busi.entity.PurchaseRecord;
import com.trip.busi.service.PurchaseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/purchaseRecord")
public class PurchaseRecordAction extends BaseAction<PurchaseRecord> {
    @Autowired
    private PurchaseRecordService purchaseRecordService;

    @Override
        public BaseService getService() {
        return  purchaseRecordService;
        }

}
