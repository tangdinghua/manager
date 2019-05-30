package com.trip.busi.service;

import com.talkweb.commons.core.service.BaseService;
import com.trip.busi.entity.RegionSort;
import com.trip.busi.dao.RegionSortDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author 玲玲
 * @create 2019-05-30 21:33:16
 */

@Service
public class RegionSortService extends BaseService<RegionSort> {

    @Autowired
    private RegionSortDao regionSortDao;


}
