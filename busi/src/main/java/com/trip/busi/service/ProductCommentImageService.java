package com.trip.busi.service;

import com.talkweb.commons.core.service.BaseService;
import com.trip.busi.entity.ProductCommentImage;
import com.trip.busi.dao.ProductCommentImageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author 玲玲
 * @create 2019-05-30 21:30:08
 */

@Service
public class ProductCommentImageService extends BaseService<ProductCommentImage> {

    @Autowired
    private ProductCommentImageDao productCommentImageDao;


}
