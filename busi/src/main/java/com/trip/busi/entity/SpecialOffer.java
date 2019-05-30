package com.trip.busi.bean;

import com.talkweb.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class SpecialOffer extends IDEntity {

    /**
	 * 标题
	*/
	private String title;
	
	/**
	 * 所属门店Id
	*/
	private Long storeId;
	
	/**
	 * 活动图片
	*/
	private String imageUrl;
	
	/**
	 * 开始时间
	*/
	private Date startTime;
	
	/**
	 * 结束时间
	*/
	private Date endTime;
	
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