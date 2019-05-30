package com.trip.busi.action;

import com.talkweb.commons.core.service.BaseService;
import com.talkweb.base.action.BaseAction;
import com.trip.busi.entity.StoreCustomer;
import com.trip.busi.service.StoreCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/storeCustomer")
public class StoreCustomerAction extends BaseAction<StoreCustomer> {
    @Autowired
    private StoreCustomerService storeCustomerService;

    @Override
        public BaseService getService() {
        return  storeCustomerService;
        }

}
