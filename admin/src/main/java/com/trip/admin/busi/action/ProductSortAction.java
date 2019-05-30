package com.trip.busi.action;

import com.talkweb.commons.core.service.BaseService;
import com.talkweb.base.action.BaseAction;
import com.trip.busi.entity.ProductSort;
import com.trip.busi.service.ProductSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/productSort")
public class ProductSortAction extends BaseAction<ProductSort> {
    @Autowired
    private ProductSortService productSortService;

    @Override
        public BaseService getService() {
        return  productSortService;
        }

}
