package com.trip.busi.action;

import com.talkweb.commons.core.service.BaseService;
import com.talkweb.base.action.BaseAction;
import com.trip.busi.entity.Role;
import com.trip.busi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/role")
public class RoleAction extends BaseAction<Role> {
    @Autowired
    private RoleService roleService;

    @Override
        public BaseService getService() {
        return  roleService;
        }

}
