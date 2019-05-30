package com.trip.busi.action;

import com.talkweb.commons.core.service.BaseService;
import com.talkweb.base.action.BaseAction;
import com.trip.busi.entity.TripShare;
import com.trip.busi.service.TripShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/tripShare")
public class TripShareAction extends BaseAction<TripShare> {
    @Autowired
    private TripShareService tripShareService;

    @Override
        public BaseService getService() {
        return  tripShareService;
        }

}
