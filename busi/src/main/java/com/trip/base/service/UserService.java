package com.trip.base.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.trip.base.dao.RoleDao;
import com.trip.base.dao.UserDao;
import com.trip.base.entity.Resource;
import com.trip.base.entity.Role;
import com.trip.base.entity.User;
import com.trip.base.util.JwtUtil;
import com.trip.base.util.Md5Util;
import com.trip.commons.core.service.BaseService;
import com.trip.base.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yk on 2017/2/8.
 */
@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RoleDao roleDao;

//    public User login(User user) throws Exception {
//        User queryUser = new User();
//        queryUser.setMobile(user.getMobile());
//        long smsLogId = smsLogService.checkSmsCode(user.getCode(),queryUser.getMobile());
//        if(smsLogId!=0) {
//            User resultUser = this.queryEntity(queryUser);
//            if (resultUser != null) {
//                    return resultUser;
//            }
//        }
//        return null;
//    }

    public User loginUser(User user) throws Exception {
        User queryUser = new User();
        queryUser.setUserName(user.getUserName());

        User resultUser = this.queryEntity(queryUser);
        if (resultUser != null) {
            if(user.getPassword().equals(resultUser.getPassword()))
                return resultUser;
        }

        return null;
    }

    @Transactional
    public void save(User user) throws Exception {
        if(user.isNew()) {
            user.setPassword(Md5Util.MD5(user.getPassword()+ Constants.PASSWORD_EXT));
            user.setStatus("1");
            user.setCreateUser(jwtUtil.getValue("userid"));
            insert(user);
        } else {
            update(user);
            roleDao.deleteRoleByUser(user.getId());
        }
        String roleids = user.getRoleIds();
        if(roleids!=null) {
        	 String []roleArr = roleids.split(",");
             if(roleArr.length>0){
                 for(String r:roleArr){
                     if(!"".equals(r)){
                         Role role = new Role();
                         role.setId(Long.parseLong(r));
                         role.setUserId(user.getId());
                         roleDao.addUser(role);
                     }
                 }
             }
        }
    }

    public void update(User user){
        userDao.update(user);
    }

    public void modPassword(User user){
        userDao.modPassword(user);
    }

    public List<Resource> queryUserResource(Long id){
        return userDao.queryUserResource(id);
    }

    public User queryUserByRolecode(String rolecode,String orgid){
        return userDao.queryUserByRolecode(rolecode,orgid);
    }

    public void updateOrgId(Long orgId,String userName){
        userDao.updateOrgId(orgId,userName);
    }

    public void resetPassword(User user){
        userDao.resetPassword(user);
    }
    public void insertToken(String token,Long createuser){
         userDao.insertToken(token,createuser);
    }
    public Map<String,String> queryToken(String token, Long createuser){
        return userDao.queryToken(token,createuser);
    }




    public List<User> getMyTeam(Integer page,Integer rows) throws Exception{
        String userId = jwtUtil.getValue("userId");
        String isLeader=jwtUtil.getValue("isLeader");
        if("1".equals(isLeader)) {
            setupPage(new Page(page, rows));
            User user=new User();
            user.setLeader(Long.parseLong(userId));
            PageInfo pageInfo = new PageInfo<User>(userDao.findByExample(user));
            return pageInfo.getList();
        }
        return new ArrayList();
    }
}
