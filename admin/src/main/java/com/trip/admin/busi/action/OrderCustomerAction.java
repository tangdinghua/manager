package com.trip.admin.busi.action;

import com.trip.commons.core.service.BaseService;
import com.trip.base.action.BaseAction;
import com.trip.busi.entity.OrderCustomer;
import com.trip.busi.service.OrderCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/orderCustomer")
public class OrderCustomerAction extends BaseAction<OrderCustomer> {
    @Autowired
    private OrderCustomerService orderCustomerService;

    @Override
        public BaseService getService() {
        return  orderCustomerService;
        }

}
