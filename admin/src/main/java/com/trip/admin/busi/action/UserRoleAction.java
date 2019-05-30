package com.trip.admin.busi.action;

import com.trip.commons.core.service.BaseService;
import com.trip.base.action.BaseAction;
import com.trip.busi.entity.UserRole;
import com.trip.busi.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/userRole")
public class UserRoleAction extends BaseAction<UserRole> {
    @Autowired
    private UserRoleService userRoleService;

    @Override
        public BaseService getService() {
        return  userRoleService;
        }

}
