package com.trip.busi.service;

import com.talkweb.commons.core.service.BaseService;
import com.trip.busi.entity.VisaSort;
import com.trip.busi.dao.VisaSortDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author 玲玲
 * @create 2019-05-30 21:43:18
 */

@Service
public class VisaSortService extends BaseService<VisaSort> {

    @Autowired
    private VisaSortDao visaSortDao;


}
