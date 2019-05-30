package com.trip.busi.action;

import com.talkweb.commons.core.service.BaseService;
import com.talkweb.base.action.BaseAction;
import com.trip.busi.entity.ProductCommentImage;
import com.trip.busi.service.ProductCommentImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/productCommentImage")
public class ProductCommentImageAction extends BaseAction<ProductCommentImage> {
    @Autowired
    private ProductCommentImageService productCommentImageService;

    @Override
        public BaseService getService() {
        return  productCommentImageService;
        }

}
