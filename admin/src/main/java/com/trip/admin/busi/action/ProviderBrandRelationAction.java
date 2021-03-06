package com.trip.admin.busi.action;

import com.trip.commons.core.service.BaseService;
import com.trip.base.action.BaseAction;
import com.trip.busi.entity.ProviderBrandRelation;
import com.trip.busi.service.ProviderBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/providerBrandRelation")
public class ProviderBrandRelationAction extends BaseAction<ProviderBrandRelation> {
    @Autowired
    private ProviderBrandRelationService providerBrandRelationService;

    @Override
        public BaseService getService() {
        return  providerBrandRelationService;
        }

}
