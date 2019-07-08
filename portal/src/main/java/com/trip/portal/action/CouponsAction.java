package com.trip.portal.action;


import com.trip.base.aspect.ActionLog;
import com.trip.busi.entity.ProductSort;
import com.trip.busi.service.ProductAdService;
import com.trip.busi.service.ProductSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
@ActionLog(module = "小程序首页管理")
public class IndexAction {

    @Autowired
    private ProductAdService productAdService;
    @Autowired
    private ProductSortService productSortService;


    @GetMapping("/ad")
    public Object queryAd(){
        return productAdService.queryIndexAd();
    }


    @GetMapping("/getMoreProductSort")
    public Object queryProductSort(){
       return  productSortService.findList(new ProductSort());
    }





}
