package com.trip.busi.entity;

import com.trip.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class Role extends IDEntity {

    /**
	 * 角色名称
	*/
	private String roleName;
	
	/**
	 * 创建人Id
	*/
	private Long createdBy;
	

	
	/**
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	

}