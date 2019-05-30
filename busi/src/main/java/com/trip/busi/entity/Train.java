package com.trip.busi.entity;

import com.trip.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class Train extends IDEntity {

    /**
	 * 标题
	*/
	private String title;
	
	/**
	 * 描述
	*/
	private String description;
	
	/**
	 * 所属供应商Id
	*/
	private Long providerId;
	
	/**
	 * 视频url
	*/
	private String videoUrl;
	

	
	/**
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	

}