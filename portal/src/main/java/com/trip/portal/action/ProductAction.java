package com.trip.portal.action;


import com.trip.base.aspect.ActionLog;
import com.trip.base.exception.BusinessException;
import com.trip.base.util.JwtUtil;
import com.trip.busi.constants.ErrorCons;
import com.trip.busi.entity.Coupons;
import com.trip.busi.entity.ProductSort;
import com.trip.busi.entity.UserCoupons;
import com.trip.busi.service.CouponsService;
import com.trip.busi.service.ProductAdService;
import com.trip.busi.service.ProductSortService;
import com.trip.busi.service.UserCouponsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coupons")
@ActionLog(module = "小程序优惠券管理")
public class CouponsAction {

    @Autowired
    private CouponsService couponsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserCouponsService userCouponsService;



    @GetMapping("/list")
    public Object list() throws  Exception{
        Coupons c=new Coupons();
        c.setUserId(jwtUtil.getValue("userId"));;
        return couponsService.findList(c);
    }

    @GetMapping("/receive")
    public Object receive(String id) throws Exception{
        Coupons c=new Coupons();
        c.setId(Long.parseLong(id));
       c= couponsService.findById(c);
       if(c!=null &&   Integer.parseInt(c.getLimit()) >0 && userCouponsService.queryUserCouponsId(c.getId().toString())<1 ){
              UserCoupons userCoupons=new UserCoupons();
                userCoupons.setCounponsId(c.getId());
                userCoupons.setCouponsAmount(c.getAmount().toString());
                userCoupons.setEffDate(c.getStartTime());
                userCoupons.setExpDate(c.getEndTime());
                userCoupons.setUserId(Long.parseLong(jwtUtil.getValue("userId")));
                userCoupons.setIsUsed(0);
              userCouponsService.insert(userCoupons);
              couponsService.update(c);
              return null;
       }else{
           throw new BusinessException(ErrorCons.COUPONS_RECEIVE_ERR.getCode(), ErrorCons.COUPONS_RECEIVE_ERR.getMessage());

       }
    }
    @GetMapping("/myCoupons")
    public Object myCoupons(String isValid) throws Exception{
        UserCoupons uc=new UserCoupons();
        uc.setUserId(Long.parseLong(jwtUtil.getValue("userId")));
        uc.setIsValid(isValid);
        return userCouponsService.findList(uc);
    }





}
