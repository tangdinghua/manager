package com.trip.admin.busi.action;

import com.trip.commons.core.service.BaseService;
import com.trip.base.action.BaseAction;
import com.trip.busi.entity.StoreBrandRelation;
import com.trip.busi.service.StoreBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/storeBrandRelation")
public class StoreBrandRelationAction extends BaseAction<StoreBrandRelation> {
    @Autowired
    private StoreBrandRelationService storeBrandRelationService;

    @Override
        public BaseService getService() {
        return  storeBrandRelationService;
        }

}
