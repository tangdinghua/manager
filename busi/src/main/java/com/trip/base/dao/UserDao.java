package com.trip.base.dao;

import com.trip.commons.core.dao.BaseDao;
import com.trip.base.entity.Resource;
import com.trip.base.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by yk on 2017/2/9.
 */
public interface UserDao  extends BaseDao<User> {

    void modPassword(User user);

    void updateOrgId(@Param("orgId") Long orgId,@Param("userName") String userName);

    List<Resource> queryUserResource(Long id);

    User queryUserByRolecode(String rolecode, String orgid);

    void resetPassword(User user);

    void insertToken(@Param("token") String token, @Param("createUser") Long createuser);

    Map<String,String> queryToken(@Param("token") String token, @Param("createUser") Long createuser);
}
