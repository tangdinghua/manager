package com.trip.admin.sys.action;

import com.trip.base.action.BaseAction;
import com.trip.base.aspect.ActionLog;
import com.trip.base.aspect.LoginCheck;
import com.trip.base.constants.Constants;
import com.trip.base.entity.Resource;
import com.trip.base.entity.User;
import com.trip.base.exception.BusinessException;
import com.trip.base.service.UserService;
import com.trip.base.util.Md5Util;
import com.trip.base.util.ResultUtil;
import com.trip.base.util.JwtUtil;
import com.trip.commons.core.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by yk on 2017/2/9.
 */
@RestController
@RequestMapping("user")
@ActionLog(module = "用户管理")
public class UserAction extends BaseAction<User> {
    @Autowired
    private JwtUtil jwt;

    @Autowired
    private UserService userService;

    @Override
    public BaseService getService() {
        return userService;
    }


    @RequestMapping("login")
    public Object login(User user) throws BusinessException {
        Map<String, Object> resultMap = ResultUtil.getResultMap("0", "登录成功");
        try {
            user.setPassword(Md5Util.MD5(user.getPassword()+Constants.PASSWORD_EXT));
            if(StringUtils.isEmpty(user.getUserName())){
                resultMap = ResultUtil.getResultMap(Constants.SYS_FAIL_CODE, "用户名不能为空");
                return resultMap;
            }
            User resultUser = userService.loginUser(user);
            if(resultUser!=null){
                resultUser.setPassword("");
                //查询用户有的资源
                List<Resource> list = userService.queryUserResource(resultUser.getId());
                String resources = "";
                for(Resource r :list){
                    resources = resources+","+r.getResourceCode();
                }
                resultUser.setResources(resources);
                String tokenStr = UUID.randomUUID().toString().replaceAll("-","");
                resultUser.setToken(tokenStr);
                /**
                 * 保存token至日志表
                 */
                userService.insertToken(tokenStr,resultUser.getId());
                String subject = JwtUtil.generalSubject(resultUser);
                String token = jwt.createJWT(Constants.JWT_ID, subject, Constants.JWT_TTL);
                resultUser.setToken(token);
                ResultUtil.putKey(resultMap,"user",resultUser);
            }else{
                resultMap = ResultUtil.getResultMap(Constants.SYS_FAIL_CODE, "用户名或密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(Constants.SYS_FAIL_CODE, Constants.SYS_FAIL_MSG,e);
        }
        return resultMap;
    }

//    @RequestMapping("modpassword")
//    @LoginCheck
//    public Object modPassword(String password, String oldpassword, HttpServletRequest request) throws BusinessException {
//        Map<String, Object> resultMap = ResultUtil.getResultMap("0", "操作成功");
//        try {
//            User user = new User();
//            user.setUserName(jwt.getValue("userName"));
//            user.setPassword(Md5Util.MD5(oldpassword+Constants.PASSWORD_EXT));
//            User resultUser = userService.login(user);
//            if(resultUser!=null){
//                resultUser.setPassword(Md5Util.MD5(password+Constants.PASSWORD_EXT));
//                resultUser.setOldpassword(Md5Util.MD5(oldpassword+Constants.PASSWORD_EXT));
//                userService.modPassword(resultUser);
//            }else{
//                resultMap = ResultUtil.getResultMap(Constants.SYS_FAIL_CODE, "原密码错误");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new BusinessException(Constants.SYS_FAIL_CODE, Constants.SYS_FAIL_MSG,e);
//        }
///       return resultMap;
//    }


    @RequestMapping("resetpassword")
    @LoginCheck
    public Object resetPassword(User user,HttpServletRequest request) throws BusinessException {
        Map<String, Object> resultMap = ResultUtil.getResultMap("0", "操作成功");
        try {
            user.setPassword(Md5Util.MD5("123456"+Constants.PASSWORD_EXT));
            userService.resetPassword(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(Constants.SYS_FAIL_CODE, Constants.SYS_FAIL_MSG,e);
        }
        return resultMap;
    }

}


