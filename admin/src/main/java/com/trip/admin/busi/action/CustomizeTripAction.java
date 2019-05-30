package com.trip.admin.busi.action;

import com.trip.commons.core.service.BaseService;
import com.trip.base.action.BaseAction;
import com.trip.busi.entity.CustomizeTrip;
import com.trip.busi.service.CustomizeTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/customizeTrip")
public class CustomizeTripAction extends BaseAction<CustomizeTrip> {
    @Autowired
    private CustomizeTripService customizeTripService;

    @Override
        public BaseService getService() {
        return  customizeTripService;
        }

}
