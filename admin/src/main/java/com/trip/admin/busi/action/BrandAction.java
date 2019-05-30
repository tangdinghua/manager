package com.trip.busi.action;

import com.talkweb.commons.core.service.BaseService;
import com.talkweb.base.action.BaseAction;
import com.trip.busi.entity.Brand;
import com.trip.busi.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/brand")
public class BrandAction extends BaseAction<Brand> {
    @Autowired
    private BrandService brandService;

    @Override
        public BaseService getService() {
        return  brandService;
        }

}
