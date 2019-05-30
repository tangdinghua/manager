package com.trip.busi.entity;

import com.trip.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class User extends IDEntity {

    /**
	 * 小程序openid
	*/
	private String openId;
	
	/**
	 * 昵称
	*/
	private String nickName;
	
	/**
	 * 真实姓名
	*/
	private String realName;
	
	/**
	 * 性别0男1女(枚举:Sex[core])
	*/
	private Byte sex;
	
	/**
	 * 手机号
	*/
	private String phone;
	
	/**
	 * 用户类型1超管2供应商4供应商销售人员5门店老板6门店一级销售7门店二级销售8VIP销售9游客(枚举:UserType)
	*/
	private Byte userType;
	
	/**
	 * 头像
	*/
	private String avatar;
	

	
	/**
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	private String address;
	
	

}