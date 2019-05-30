package com.trip.busi.action;

import com.talkweb.commons.core.service.BaseService;
import com.talkweb.base.action.BaseAction;
import com.trip.busi.entity.Product;
import com.trip.busi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/product")
public class ProductAction extends BaseAction<Product> {
    @Autowired
    private ProductService productService;

    @Override
        public BaseService getService() {
        return  productService;
        }

}
