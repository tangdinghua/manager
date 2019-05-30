package com.trip.busi.action;

import com.talkweb.commons.core.service.BaseService;
import com.talkweb.base.action.BaseAction;
import com.trip.busi.entity.RegionSort;
import com.trip.busi.service.RegionSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/regionSort")
public class RegionSortAction extends BaseAction<RegionSort> {
    @Autowired
    private RegionSortService regionSortService;

    @Override
        public BaseService getService() {
        return  regionSortService;
        }

}
