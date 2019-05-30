package com.trip.busi.action;

import com.talkweb.commons.core.service.BaseService;
import com.talkweb.base.action.BaseAction;
import com.trip.busi.entity.Ordercredit;
import com.trip.busi.service.OrdercreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/ordercredit")
public class OrdercreditAction extends BaseAction<Ordercredit> {
    @Autowired
    private OrdercreditService ordercreditService;

    @Override
        public BaseService getService() {
        return  ordercreditService;
        }

}
