package com.trip.busi.entity;

import com.trip.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class Store extends IDEntity {

    /**
	 * 门店名称
	*/
	private String storeName;
	
	/**
	 * 联系人
	*/
	private String linkMan;
	
	/**
	 * 联系方式
	*/
	private String linkNum;
	
	/**
	 * 门店地址
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
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	

}