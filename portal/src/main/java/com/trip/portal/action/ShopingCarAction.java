package com.trip.portal.action;


import com.trip.base.aspect.ActionLog;
import com.trip.base.exception.BusinessException;
import com.trip.base.util.JwtUtil;
import com.trip.busi.constants.ErrorCons;
import com.trip.busi.entity.Coupons;
import com.trip.busi.entity.Product;
import com.trip.busi.entity.UserCoupons;
import com.trip.busi.service.CouponsService;
import com.trip.busi.service.ProductCommentService;
import com.trip.busi.service.ProductService;
import com.trip.busi.service.UserCouponsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/product")
@ActionLog(module = "产品管理")
public class ProductAction {
    @Autowired
    private ProductService productService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ProductCommentService commentService;



    @RequestMapping("/getProductDetail")
    public Object getProductDetail(String productId) throws Exception{
        Product p=new Product();
        p.setId(Long.parseLong(productId));
        p.setUserId(jwtUtil.getValue("userId"));
        return productService.findById(p);
    }

    @RequestMapping("/getCommentGrade")
    public Object getProductComment(String productId){
        return commentService.queryCommentState(productId);
    }
    @RequestMapping("/getProcductComment")
    public Object queryCommentByProductId(String productId){
        return commentService.queryCommentByProductId(productId);
    }


    public Object favoriteProduct(String productId,String isFavrite)throws Exception{
        Map<String,String> map=new HashMap<String,String>();
        map.put("productId",productId);
        map.put("userId",jwtUtil.getValue("userId"));
        if("0".equals(isFavrite)){
            productService.deleteFavorite(map);
        }else{
            productService.insertFavorite(map);
        }
        return null;
    }

}
