package com.trip.admin.sys.action;

import com.trip.base.action.BaseAction;
import com.trip.base.aspect.ActionLog;
import com.trip.base.entity.Role;
import com.trip.base.service.RoleService;
import com.trip.commons.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制器
 *
 * @author fqh
 * @create 2017-01-30 19:05
 */
@RestController
@RequestMapping("role")
@ActionLog(module = "角色管理")
public class RoleAction extends BaseAction<Role> {
    @Autowired
    private RoleService roleService;

    @Override
    public BaseService getService() {
    return roleService;
    }


    @RequestMapping(value = "config",name="配置权限")
    public Object config(Long roleid,String resourceid){
        return roleService.addResource(roleid,resourceid);
    }

    @RequestMapping(value = "configuser",name = "配置用户")
    public Object configuser(Long roleid,String userid){
        return roleService.addUser(roleid,userid);
    }

    @RequestMapping(value = "resource",name = "查询资源")
    public Object resource(Long id){
        return roleService.queryResourceById(id);
    }

    @RequestMapping(value = "user",name = "查询用户")
    public Object user(Long id){
        return roleService.queryUserById(id);
    }

    @RequestMapping(value = "nouser",name = "查询没配角色的用户")
    public Object nouser(Long roleid,String orgid){
        Role role = new Role();
        role.setId(roleid);
        role.setOrgId(orgid);
        return roleService.queryNoSelectUser(role);
    }
}
