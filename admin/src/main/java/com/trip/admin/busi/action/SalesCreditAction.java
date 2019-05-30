package com.trip.busi.action;

import com.talkweb.commons.core.service.BaseService;
import com.talkweb.base.action.BaseAction;
import com.trip.busi.entity.SalesCredit;
import com.trip.busi.service.SalesCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/salesCredit")
public class SalesCreditAction extends BaseAction<SalesCredit> {
    @Autowired
    private SalesCreditService salesCreditService;

    @Override
        public BaseService getService() {
        return  salesCreditService;
        }

}