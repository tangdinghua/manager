package com.trip.busi.action;

import com.talkweb.commons.core.service.BaseService;
import com.talkweb.base.action.BaseAction;
import com.trip.busi.entity.RoleElement;
import com.trip.busi.service.RoleElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/roleElement")
public class RoleElementAction extends BaseAction<RoleElement> {
    @Autowired
    private RoleElementService roleElementService;

    @Override
        public BaseService getService() {
        return  roleElementService;
        }

}
