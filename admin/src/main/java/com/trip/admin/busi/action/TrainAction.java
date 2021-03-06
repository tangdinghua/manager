package com.trip.admin.busi.action;

import com.trip.commons.core.service.BaseService;
import com.trip.base.action.BaseAction;
import com.trip.busi.entity.Train;
import com.trip.busi.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/train")
public class TrainAction extends BaseAction<Train> {
    @Autowired
    private TrainService trainService;

    @Override
        public BaseService getService() {
        return  trainService;
        }

}
