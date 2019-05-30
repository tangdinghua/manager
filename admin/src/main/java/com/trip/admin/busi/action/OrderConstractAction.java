package com.trip.admin.busi.action;

import com.trip.commons.core.service.BaseService;
import com.trip.base.action.BaseAction;
import com.trip.busi.entity.OrderConstract;
import com.trip.busi.service.OrderConstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/orderConstract")
public class OrderConstractAction extends BaseAction<OrderConstract> {
    @Autowired
    private OrderConstractService orderConstractService;

    @Override
        public BaseService getService() {
        return  orderConstractService;
        }

}
