package com.trip.busi.action;

import com.talkweb.commons.core.service.BaseService;
import com.talkweb.base.action.BaseAction;
import com.trip.busi.entity.Coupons;
import com.trip.busi.service.CouponsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/coupons")
public class CouponsAction extends BaseAction<Coupons> {
    @Autowired
    private CouponsService couponsService;

    @Override
        public BaseService getService() {
        return  couponsService;
        }

}
