package com.trip.busi.entity;

import com.trip.commons.core.bean.IDEntity;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class Product extends IDEntity {

    /**
	 * 产品名称
	*/
	private String name;
	
	/**
	 * 产品简介
	*/
	private String briefDesc;
	
	/**
	 * 所属分类Id
	*/
	private Long sortId;
	
	/**
	 * 所属地区Id
	*/
	private Long regionId;
	
	/**
	 * 所属地区分类Id
	*/
	private Long regionSortId;
	
	/**
	 * 所属供应商Id
	*/
	private Long providerId;
	
	/**
	 * 商品成本
	*/
	private BigDecimal cost;
	
	/**
	 * 成交价
	*/
	private BigDecimal strikePrice;
	
	/**
	 * 售价
	*/
	private BigDecimal price;
	
	/**
	 * 门店分成比例(百分比)
	*/
	private Integer commissionRate;
	
	/**
	 * 图片链接
	*/
	private String imageUrl;
	
	/**
	 * 视频链接
	*/
	private String videoUrl;
	
	/**
	 * 备注
	*/
	private String remark;
	

	
	/**
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	/**
	 * 产品描述
	*/
	private String description;
	
	

}