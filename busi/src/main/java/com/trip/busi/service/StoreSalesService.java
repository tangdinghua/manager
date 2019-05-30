package com.trip.busi.service;

import com.talkweb.commons.core.service.BaseService;
import com.trip.busi.entity.StoreSales;
import com.trip.busi.dao.StoreSalesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author 玲玲
 * @create 2019-05-30 21:38:05
 */

@Service
public class StoreSalesService extends BaseService<StoreSales> {

    @Autowired
    private StoreSalesDao storeSalesDao;


}
