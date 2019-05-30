package com.trip.busi.entity;

import com.trip.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class PurchaseRecord extends IDEntity {

    /**
	 * 角色Id
	*/
	private Long roleId;
	
	/**
	 * 业务主键Id
	*/
	private Long businessId;
	
	/**
	 * 购买时长
	*/
	private Integer totalDays;
	
	/**
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	

}