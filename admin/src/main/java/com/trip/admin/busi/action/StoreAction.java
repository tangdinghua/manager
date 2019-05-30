package com.trip.busi.action;

import com.talkweb.commons.core.service.BaseService;
import com.talkweb.base.action.BaseAction;
import com.trip.busi.entity.Store;
import com.trip.busi.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/store")
public class StoreAction extends BaseAction<Store> {
    @Autowired
    private StoreService storeService;

    @Override
        public BaseService getService() {
        return  storeService;
        }

}