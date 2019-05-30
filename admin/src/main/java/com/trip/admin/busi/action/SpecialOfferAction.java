package com.trip.admin.busi.action;

import com.trip.commons.core.service.BaseService;
import com.trip.base.action.BaseAction;
import com.trip.busi.entity.SpecialOffer;
import com.trip.busi.service.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/specialOffer")
public class SpecialOfferAction extends BaseAction<SpecialOffer> {
    @Autowired
    private SpecialOfferService specialOfferService;

    @Override
        public BaseService getService() {
        return  specialOfferService;
        }

}
