package com.trip.busi.bean;

import com.talkweb.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class SpecialOfferProduct extends IDEntity {

    /**
	 * 优惠活动Id
	*/
	private Long specialOfferId;
	
	/**
	 * 产品Id
	*/
	private Long productId;
	
	/**
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	

}