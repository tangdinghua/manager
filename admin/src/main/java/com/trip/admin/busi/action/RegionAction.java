package com.trip.busi.action;

import com.talkweb.commons.core.service.BaseService;
import com.talkweb.base.action.BaseAction;
import com.trip.busi.entity.Region;
import com.trip.busi.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/region")
public class RegionAction extends BaseAction<Region> {
    @Autowired
    private RegionService regionService;

    @Override
        public BaseService getService() {
        return  regionService;
        }

}
