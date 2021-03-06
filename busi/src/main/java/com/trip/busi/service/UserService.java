package com.trip.busi.service;

import com.trip.commons.core.service.BaseService;
import com.trip.busi.entity.User;
import com.trip.busi.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author
 * @create 2019-05-30 21:41:53
 */

@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserDao userDao;


}
