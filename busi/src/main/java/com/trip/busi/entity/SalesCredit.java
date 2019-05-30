package com.trip.busi.entity;

import com.trip.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class SalesCredit extends IDEntity {

    /**
	 * 销售人员用户Id
	*/
	private Long userId;
	
	/**
	 * 分配人Id
	*/
	private Long allotId;
	
	/**
	 * 订单Id
	*/
	private Long orderId;
	
	/**
	 * 获得积分
	*/
	private Integer credit;
	
	/**
	 * 积分分配状态0预分配1分配确认(枚举:CreditAllotStatus)
	*/
	private Byte creditAllotStatus;
	
	/**
	 * 预分配时间
	*/
	private Date preAllotTime;
	
	/**
	 * 确认分配时间
	*/
	private Date ackAllotTime;
	

	
	/**
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	

}