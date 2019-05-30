package com.trip.busi.entity;

import com.trip.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class Element extends IDEntity {

    /**
	 * 权限名
	*/
	private String name;
	
	/**
	 * 权限类型1管理后台2小程序(枚举:ElementType)
	*/
	private Byte elementType;
	
	/**
	 * 父Id
	*/
	private Long parentId;
	
	/**
	 * 页面链接
	*/
	private String url;
	

	
	/**
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	

}