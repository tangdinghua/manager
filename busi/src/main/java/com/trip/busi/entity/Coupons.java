package com.trip.busi.bean;

import com.talkweb.commons.core.bean.IDEntity;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class Coupons extends IDEntity {

    /**
	 * 优惠券名称
	*/
	private String name;
	
	/**
	 * 适用消费额
	*/
	private BigDecimal limitCost;
	
	/**
	 * 优惠券金额
	*/
	private BigDecimal amount;
	
	/**
	 * 是否删除0否1是(枚举:YesNoStatus[core])
	*/
	private Byte isDelete;
	
	/**
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	/**
	 * 优惠券描述
	*/
	private String note;
	
	

}