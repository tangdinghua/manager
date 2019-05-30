package com.trip.admin.busi.action;

import com.trip.commons.core.service.BaseService;
import com.trip.base.action.BaseAction;
import com.trip.busi.entity.ProductComment;
import com.trip.busi.service.ProductCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/productComment")
public class ProductCommentAction extends BaseAction<ProductComment> {
    @Autowired
    private ProductCommentService productCommentService;

    @Override
        public BaseService getService() {
        return  productCommentService;
        }

}
