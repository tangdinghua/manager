package com.trip.busi.service;

import com.talkweb.commons.core.service.BaseService;
import com.trip.busi.entity.ShoppingCar;
import com.trip.busi.dao.ShoppingCarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author 玲玲
 * @create 2019-05-30 21:35:50
 */

@Service
public class ShoppingCarService extends BaseService<ShoppingCar> {

    @Autowired
    private ShoppingCarDao shoppingCarDao;


}
