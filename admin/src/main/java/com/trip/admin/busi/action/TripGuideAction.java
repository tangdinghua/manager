package com.trip.admin.busi.action;

import com.trip.commons.core.service.BaseService;
import com.trip.base.action.BaseAction;
import com.trip.busi.entity.TripGuide;
import com.trip.busi.service.TripGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/tripGuide")
public class TripGuideAction extends BaseAction<TripGuide> {
    @Autowired
    private TripGuideService tripGuideService;

    @Override
        public BaseService getService() {
        return  tripGuideService;
        }

}
