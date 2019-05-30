package com.trip.busi.service;

import com.trip.commons.core.service.BaseService;
import com.trip.busi.entity.Element;
import com.trip.busi.dao.ElementDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author
 * @create 2019-05-30 21:20:38
 */

@Service
public class ElementService extends BaseService<Element> {

    @Autowired
    private ElementDao elementDao;


}
