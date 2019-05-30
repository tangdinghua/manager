package com.trip.admin.busi.action;

import com.trip.commons.core.service.BaseService;
import com.trip.base.action.BaseAction;
import com.trip.busi.entity.SpecialOfferProduct;
import com.trip.busi.service.SpecialOfferProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/specialOfferProduct")
public class SpecialOfferProductAction extends BaseAction<SpecialOfferProduct> {
    @Autowired
    private SpecialOfferProductService specialOfferProductService;

    @Override
        public BaseService getService() {
        return  specialOfferProductService;
        }

}
