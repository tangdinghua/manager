package com.trip.admin.busi.action;

import com.trip.commons.core.service.BaseService;
import com.trip.base.action.BaseAction;
import com.trip.busi.entity.OrderNotice;
import com.trip.busi.service.OrderNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/orderNotice")
public class OrderNoticeAction extends BaseAction<OrderNotice> {
    @Autowired
    private OrderNoticeService orderNoticeService;

    @Override
        public BaseService getService() {
        return  orderNoticeService;
        }

}
