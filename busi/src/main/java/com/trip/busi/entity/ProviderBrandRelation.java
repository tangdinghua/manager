package com.trip.busi.entity;

import com.trip.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class ProviderBrandRelation extends IDEntity {

    /**
	 * 供应商Id
	*/
	private Long providerId;
	
	/**
	 * 品牌Id
	*/
	private Long brandId;
	
	/**
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	

}