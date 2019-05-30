package com.trip.busi.entity;

import com.trip.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class Visa extends IDEntity {

    /**
	 * 签证分类Id
	*/
	private Long sortId;
	
	/**
	 * 国家
	*/
	private String country;
	

	
	/**
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	

}