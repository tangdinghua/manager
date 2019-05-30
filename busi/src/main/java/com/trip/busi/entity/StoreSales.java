package com.trip.busi.bean;

import com.talkweb.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class StoreSales extends IDEntity {

    /**
	 * 门店Id
	*/
	private Long storeId;
	
	/**
	 * 用户Id
	*/
	private Long userId;
	
	/**
	 * 邀请人Id
	*/
	private Long inviteUserId;
	
	/**
	 * 销售等级1一级销售2二级销售3VIP销售(枚举:SalesLevel)
	*/
	private Byte salesLevel;
	
	/**
	 * 利润分成(百分比)
	*/
	private Integer commissionRate;
	
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
	
	

}