package com.trip.admin.busi.action;

import com.trip.commons.core.service.BaseService;
import com.trip.base.action.BaseAction;
import com.trip.busi.entity.User;
import com.trip.busi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/user")
public class UserAction extends BaseAction<User> {
    @Autowired
    private UserService userService;

    @Override
        public BaseService getService() {
        return  userService;
        }

}
