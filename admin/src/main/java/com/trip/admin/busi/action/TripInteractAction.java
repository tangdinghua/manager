package com.trip.busi.action;

import com.talkweb.commons.core.service.BaseService;
import com.talkweb.base.action.BaseAction;
import com.trip.busi.entity.TripInteract;
import com.trip.busi.service.TripInteractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/tripInteract")
public class TripInteractAction extends BaseAction<TripInteract> {
    @Autowired
    private TripInteractService tripInteractService;

    @Override
        public BaseService getService() {
        return  tripInteractService;
        }

}
