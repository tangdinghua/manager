package com.trip.busi.action;

import com.talkweb.commons.core.service.BaseService;
import com.talkweb.base.action.BaseAction;
import com.trip.busi.entity.OrderInfo;
import com.trip.busi.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/orderInfo")
public class OrderInfoAction extends BaseAction<OrderInfo> {
    @Autowired
    private OrderInfoService orderInfoService;

    @Override
        public BaseService getService() {
        return  orderInfoService;
        }

}
