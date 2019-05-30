package com.trip.busi.bean;

import com.talkweb.commons.core.bean.IDEntity;
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