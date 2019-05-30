package com.trip.busi.entity;

import com.trip.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class TripInteract extends IDEntity {

    /**
	 * 业务Id
	*/
	private Long businessId;
	
	/**
	 * 用户Id
	*/
	private Long userId;
	
	/**
	 * 互动类型1旅游攻略2趣分享(枚举:InteractType)
	*/
	private Byte interactType;
	
	/**
	 * 操作类型1点赞2收藏3评论(枚举:InteractOperType)
	*/
	private Byte interactOperType;
	
	/**
	 * 评论内容
	*/
	private String comment;
	

	
	/**
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	

}