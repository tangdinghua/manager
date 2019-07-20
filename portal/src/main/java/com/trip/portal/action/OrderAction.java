package com.trip.portal.action;


import com.trip.base.aspect.ActionLog;
import com.trip.base.util.JwtUtil;
import com.trip.busi.entity.Product;
import com.trip.busi.service.ProductCommentService;
import com.trip.busi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/shopingCar")
@ActionLog(module = "购物车管理")
public class ShopingCarAction {


}
