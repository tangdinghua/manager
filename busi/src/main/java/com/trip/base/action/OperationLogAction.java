package com.trip.base.action;

import com.trip.commons.core.service.BaseService;
import com.trip.base.entity.OperationLog;
import com.trip.base.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(value = "operationLog",name = "操作日志")
public class OperationLogAction extends BaseAction<OperationLog> {
    @Autowired
    private OperationLogService operationLogService;

    @Override
        public BaseService getService() {
        return  operationLogService;
        }

}
