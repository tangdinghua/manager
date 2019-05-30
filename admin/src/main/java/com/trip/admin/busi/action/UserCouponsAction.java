package com.trip.busi.action;

import com.talkweb.commons.core.service.BaseService;
import com.talkweb.base.action.BaseAction;
import com.trip.busi.entity.UserCoupons;
import com.trip.busi.service.UserCouponsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/userCoupons")
public class UserCouponsAction extends BaseAction<UserCoupons> {
    @Autowired
    private UserCouponsService userCouponsService;

    @Override
        public BaseService getService() {
        return  userCouponsService;
        }

}
