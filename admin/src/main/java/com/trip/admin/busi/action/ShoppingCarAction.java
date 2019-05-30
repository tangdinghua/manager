package com.trip.admin.busi.action;

import com.trip.commons.core.service.BaseService;
import com.trip.base.action.BaseAction;
import com.trip.busi.entity.ShoppingCar;
import com.trip.busi.service.ShoppingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/shoppingCar")
public class ShoppingCarAction extends BaseAction<ShoppingCar> {
    @Autowired
    private ShoppingCarService shoppingCarService;

    @Override
        public BaseService getService() {
        return  shoppingCarService;
        }

}
