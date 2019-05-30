package com.trip.admin.busi.action;

import com.trip.commons.core.service.BaseService;
import com.trip.base.action.BaseAction;
import com.trip.busi.entity.MotorCade;
import com.trip.busi.service.MotorCadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/motorCade")
public class MotorCadeAction extends BaseAction<MotorCade> {
    @Autowired
    private MotorCadeService motorCadeService;

    @Override
        public BaseService getService() {
        return  motorCadeService;
        }

}
