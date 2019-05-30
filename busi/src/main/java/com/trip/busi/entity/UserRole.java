package com.trip.busi.entity;

import com.trip.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class UserRole extends IDEntity {

    /**
	 * 用户Id
	*/
	private Long userId;
	
	/**
	 * 角色Id
	*/
	private Long roleId;
	
	/**
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	

}