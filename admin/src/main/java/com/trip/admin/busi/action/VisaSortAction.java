package com.trip.admin.busi.action;

import com.trip.commons.core.service.BaseService;
import com.trip.base.action.BaseAction;
import com.trip.busi.entity.VisaSort;
import com.trip.busi.service.VisaSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/visaSort")
public class VisaSortAction extends BaseAction<VisaSort> {
    @Autowired
    private VisaSortService visaSortService;

    @Override
        public BaseService getService() {
        return  visaSortService;
        }

}
