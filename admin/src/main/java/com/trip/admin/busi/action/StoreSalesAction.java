package com.trip.admin.busi.action;

import com.trip.commons.core.service.BaseService;
import com.trip.base.action.BaseAction;
import com.trip.busi.entity.StoreSales;
import com.trip.busi.service.StoreSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/storeSales")
public class StoreSalesAction extends BaseAction<StoreSales> {
    @Autowired
    private StoreSalesService storeSalesService;

    @Override
        public BaseService getService() {
        return  storeSalesService;
        }

}
