package com.trip.admin.busi.action;

import com.trip.commons.core.service.BaseService;
import com.trip.base.action.BaseAction;
import com.trip.busi.entity.Provider;
import com.trip.busi.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/provider")
public class ProviderAction extends BaseAction<Provider> {
    @Autowired
    private ProviderService providerService;

    @Override
        public BaseService getService() {
        return  providerService;
        }

}
