package com.trip.busi.bean;

import com.talkweb.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class StoreBrandRelation extends IDEntity {

    /**
	 * 门店Id
	*/
	private Long storeId;
	
	/**
	 * 品牌Id
	*/
	private Long brandId;
	
	/**
	 * 是否超值产品0否1是(枚举:YesNoStatus[core])
	*/
	private Byte isOnSale;
	
	/**
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	

}