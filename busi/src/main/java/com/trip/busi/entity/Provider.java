package com.trip.busi.entity;

import com.trip.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class Provider extends IDEntity {

    /**
	 * 供应商名称
	*/
	private String providerName;
	
	/**
	 * 联系人
	*/
	private String linkMan;
	
	/**
	 * 联系方式
	*/
	private String linkNum;
	
	/**
	 * 供应商地址
	*/
	private String address;
	
	/**
	 * 有效天数
	*/
	private Integer totalDays;
	
	/**
	 * 到期时间
	*/
	private Date endTime;
	
	/**
	 * 子账号个数
	*/
	private Integer subCount;


	
	/**
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	

}