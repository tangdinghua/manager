package com.trip.base.service;

import com.trip.base.dao.RoleDao;
import com.trip.base.entity.Role;
import com.trip.base.entity.User;
import com.trip.base.util.ResultUtil;
import com.trip.commons.core.service.BaseService;
import com.trip.base.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 业务处理
 * @author fqh
 * @create 2017-02-19 12:00:26
 */

@Service
public class RoleService extends BaseService<Role> {

    @Autowired
    private RoleDao roleDao;

    @Transactional
    public Map<String,Object> addResource(Long roleid, String resourceid) throws BusinessException{
        Map<String, Object> resultMap = ResultUtil.getResultMap("0", "操作成功");
        try{
            Role role = new Role();
            role.setId(roleid);
            roleDao.deleteResource(role);
            if(resourceid!=null&&!"".equals(resourceid)){
                String []temp = resourceid.split(",");
                for(String id:temp){
                    role.setResourceId(id);
                    roleDao.addResource(role);
                }
            }
        }catch(Exception e){
            throw new BusinessException(e);
        }
        return resultMap;
    }


    @Transactional
    public Map<String,Object> addUser(Long roleid, String userid) throws BusinessException {
        Map<String, Object> resultMap = ResultUtil.getResultMap("0", "操作成功");
        try{
            Role role = new Role();
            role.setId(roleid);
            roleDao.deleteUser(role);
            if(userid!=null&&!"".equals(userid)){
                String []temp = userid.split(",");
                for(String id:temp){
                    role.setUserId(Long.parseLong(id));
                    roleDao.addUser(role);
                }
            }
        }catch(Exception e){
            throw new BusinessException(e);
        }
        return resultMap;
    }
    public void deleteRoleByUser(Long userid){
        roleDao.deleteRoleByUser(userid);
    }

    public List<Role> queryResourceById(Long roleid){
        return roleDao.queryResourceById(roleid);
    }

    public List<User> queryUserById(Long roleid){
        return roleDao.queryUserById(roleid);
    }
    public List<User> queryNoSelectUser(Role role){
        return roleDao.queryNoSelectUser(role);
    }
}
