package com.trip.base.dao;

import com.trip.commons.core.dao.BaseDao;
import com.trip.base.entity.Role;
import com.trip.base.entity.User;

import java.util.List;

/**
 * 数据库操作
 * @author fqh
 * @create 2017-02-19 12:00:26
 */
public interface RoleDao extends BaseDao<Role> {

    int addResource(Role role);

    int deleteResource(Role role);

    int addUser(Role role);

    int deleteUser(Role role);

    List<Role> queryResourceById(Long id);

    List<User> queryUserById(Long id);

    List<User> queryNoSelectUser(Role role);

    void deleteRoleByUser(Long userid);
}
