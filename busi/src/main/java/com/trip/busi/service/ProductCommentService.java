package com.trip.busi.service;

import com.talkweb.commons.core.service.BaseService;
import com.trip.busi.entity.ProductComment;
import com.trip.busi.dao.ProductCommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author 玲玲
 * @create 2019-05-30 21:29:48
 */

@Service
public class ProductCommentService extends BaseService<ProductComment> {

    @Autowired
    private ProductCommentDao productCommentDao;


}