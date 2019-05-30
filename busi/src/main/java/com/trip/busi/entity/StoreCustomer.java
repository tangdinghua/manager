package com.trip.busi.entity;

import com.trip.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class StoreCustomer extends IDEntity {

    /**
	 * 门店Id
	*/
	private Long storeId;
	
	/**
	 * 是否VIP0否1是(枚举:YesNoStatus[core])
	*/
	private Byte isVip;
	
	/**
	 * 用户Id
	*/
	private Long userId;
	
	/**
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	

}