package com.trip.admin.busi.action;

import com.trip.commons.core.service.BaseService;
import com.trip.base.action.BaseAction;
import com.trip.busi.entity.Element;
import com.trip.busi.service.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/element")
public class ElementAction extends BaseAction<Element> {
    @Autowired
    private ElementService elementService;

    @Override
        public BaseService getService() {
        return  elementService;
        }

}
