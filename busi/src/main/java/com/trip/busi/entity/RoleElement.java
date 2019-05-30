package com.trip.busi.bean;

import com.talkweb.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class RoleElement extends IDEntity {

    /**
	 * 角色Id
	*/
	private Long roleId;
	
	/**
	 * 权限Id
	*/
	private Long elementId;
	
	/**
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	

}