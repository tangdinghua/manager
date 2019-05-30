package com.trip.busi.entity;

import com.trip.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class UserCoupons extends IDEntity {

    /**
	 * 用户Id
	*/
	private Long userId;
	
	/**
	 * 优惠券Id
	*/
	private Long counponsId;
	
	/**
	 * 是否使用0否1是(枚举:YesNoStatus[core])
	*/
	private Byte isUsed;
	
	/**
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	

}