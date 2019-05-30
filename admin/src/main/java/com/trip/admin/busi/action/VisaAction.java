package com.trip.admin.busi.action;

import com.trip.commons.core.service.BaseService;
import com.trip.base.action.BaseAction;
import com.trip.busi.entity.Visa;
import com.trip.busi.service.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/visa")
public class VisaAction extends BaseAction<Visa> {
    @Autowired
    private VisaService visaService;

    @Override
        public BaseService getService() {
        return  visaService;
        }

}
