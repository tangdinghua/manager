package com.trip.base.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.trip.base.constants.Constants;
import com.trip.base.dao.RoleDao;
import com.trip.base.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.applet.Main;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
	

	@Autowired
    private RoleDao roleDao;

	/**
	 * 由字符串生成加密key
	 * @return
	 */
	public SecretKey generalKey(){
		String stringKey =  Constants.JWT_SECRET;
		byte[] encodedKey = Base64.decodeBase64(stringKey);
	    SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
	    return key;
	}

	/**
	 * 创建jwt
	 * @param id
	 * @param subject
	 * @param ttlMillis
	 * @return
	 * @throws Exception
	 */
	public String createJWT(String id, String subject, long ttlMillis) throws Exception {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		SecretKey key = generalKey();
		JwtBuilder builder = Jwts.builder()
			.setId(id)
			.setIssuedAt(now)
			.setSubject(subject)
		    .signWith(signatureAlgorithm, key);
		if (ttlMillis >= 0) {
		    long expMillis = nowMillis + ttlMillis;
		    Date exp = new Date(expMillis);
		    builder.setExpiration(exp);
		}
		return builder.compact();
	}
	
	/**
	 * 解密jwt
	 * @param jwt
	 * @return
	 * @throws Exception
	 */
	public Claims parseJWT(String jwt) throws Exception{
		SecretKey key = generalKey();
		Claims claims = Jwts.parser()
		   .setSigningKey(key)
		   .parseClaimsJws(jwt).getBody();
		return claims;
	}
	
	/**
	 * 生成subject信息
	 * @param user
	 * @return
	 */
	public static String generalSubject(User user){
		JSONObject jo = new JSONObject();
		jo.put("userId", user.getId()+"");
		jo.put("userName", user.getUserName());
		jo.put("realName", user.getRealName());
		jo.put("mobile",user.getMobile());
		jo.put("resources",user.getResources());
		jo.put("roleCode",user.getRoleCode());
		jo.put("orgId",user.getOrgId()+"");
		jo.put("token",user.getToken());
		jo.put("isLeader",user.getIsLeader());
		return jo.toJSONString();
	}

	/**
	 * 生成subject信息
	 * @param member
	 * @return
	 */
//	public static String generalMemberSubject(Member member){
//		JSONObject jo = new JSONObject();
//		jo.put("memberId", member.getId()+"");
//		jo.put("mobile", member.getMobile());
//		jo.put("hasBind", member.getHasBind());
//		return jo.toJSONString();
//	}

	/**
	 * 生成subject信息
	 * @return
	 */
	public static String generalSubjectForMap(Map<String,String> map){
		JSONObject jo = new JSONObject();
		jo.putAll(map);
		return jo.toJSONString();
	}

	public String getValue(String key) throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String token = request.getHeader("token");
		if(token==null){
			token = request.getParameter("token");
		}
		if(token==null){
			return null;
		}
		Claims claims = parseJWT(token);
		Date expirDate = claims.getExpiration();
		Date now = new Date();
		if(now.getTime()>=expirDate.getTime()){
			return "";
		}
		String subject = claims.getSubject();
		JSONObject jo = JSON.parseObject(subject);
		return (String)jo.get(key);

	}

	public static void main(String[] args) throws  Exception {
		String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NTYyNDU4NTEsInN1YiI6IntcImhhc0JpbmRcIjpcIjFcIixcIm1vYmlsZVwiOlwiMTg5NzQ4Mjg0MDhcIixcIm1lbWJlcklkXCI6XCIyXCJ9IiwiZXhwIjoxNTU2ODUwNjUxfQ.XSwxE7SbS44n9jgw4l2UmBbyRYriSzOHuax-LfQ4O1g";
		Claims claims = new JwtUtil().parseJWT(token);
		Date expirDate = claims.getExpiration();
		Date now = new Date();
		if(now.getTime()>=expirDate.getTime()){
			System.out.println("aaaaaaa");
		}
		String subject = claims.getSubject();
		JSONObject jo = JSON.parseObject(subject);
		System.out.println("json:::::"+jo.toJSONString());

	}
}
