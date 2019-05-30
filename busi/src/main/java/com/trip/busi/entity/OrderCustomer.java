package com.trip.busi.entity;

import com.trip.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class OrderCustomer extends IDEntity {

    /**
	 * 订单号
	*/
	private Long orderId;
	
	/**
	 * 姓名
	*/
	private Long travelerId;
	
	/**
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	private Long customId;
	
	

}