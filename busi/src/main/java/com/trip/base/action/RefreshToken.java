package com.trip.base.action;

import com.alibaba.fastjson.JSONObject;
import com.trip.base.util.JwtUtil;
import com.trip.base.constants.Constants;
import com.trip.base.entity.User;
import io.jsonwebtoken.Claims;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class RefreshToken{

    private Logger logger = LogManager.getLogger(RefreshToken.class);

    private static final long serialVersionUID = 2573245614706073195L;

    @Autowired
    private JwtUtil jwt;

    @RequestMapping("/refreshToken")
    @ResponseBody
    public Object refresh(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/event-stream;charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Connection", "keep-alive");
            String token = request.getParameter("token");
            Claims claims = jwt.parseJWT(token);
            String json = claims.getSubject();
            User user = JSONObject.parseObject(json, User.class);
            String subject = JwtUtil.generalSubject(user);
            String refreshToken = jwt.createJWT(Constants.JWT_ID, subject, Constants.JWT_TTL);
            return refreshToken;
        } catch (Exception e) {
            logger.error(e);
        }
        return "";
    }


}
