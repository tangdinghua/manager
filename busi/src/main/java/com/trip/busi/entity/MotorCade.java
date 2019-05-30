package com.trip.busi.bean;

import com.talkweb.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class MotorCade extends IDEntity {

    /**
	 * 供应商Id
	*/
	private Long providerId;
	
	/**
	 * 门店Id
	*/
	private Long storeId;
	
	/**
	 * 车型(枚举:MotorcadeType)
	*/
	private Byte motorcadeType;
	
	/**
	 * 联系人
	*/
	private String linkMan;
	
	/**
	 * 联系电话
	*/
	private String linkNum;
	
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