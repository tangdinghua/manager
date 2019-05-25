package com.trip.base.service;

import com.trip.base.dao.OperationLogDao;
import com.trip.base.entity.OperationLog;
import com.trip.commons.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author yko2008
 * @create 2018-08-07 14:26:01
 */

@Service
public class OperationLogService extends BaseService<OperationLog> {

    @Autowired
    private OperationLogDao operationLogDao;


}
