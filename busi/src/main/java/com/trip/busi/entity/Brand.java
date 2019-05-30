package com.trip.busi.entity;

import com.trip.commons.core.bean.IDEntity;
import com.trip.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class Brand extends IDEntity {

    /**
	 * 品牌名
	*/
	private String name;
	
	/**
	 * 品牌logo
	*/
	private String logo;
	

	
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