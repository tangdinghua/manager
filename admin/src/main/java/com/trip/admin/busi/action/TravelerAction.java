package com.trip.admin.busi.action;

import com.trip.commons.core.service.BaseService;
import com.trip.base.action.BaseAction;
import com.trip.busi.entity.Traveler;
import com.trip.busi.service.TravelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/traveler")
public class TravelerAction extends BaseAction<Traveler> {
    @Autowired
    private TravelerService travelerService;

    @Override
        public BaseService getService() {
        return  travelerService;
        }

}
