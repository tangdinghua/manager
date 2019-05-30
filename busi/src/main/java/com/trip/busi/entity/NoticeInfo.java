package com.trip.busi.entity;

import com.trip.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class NoticeInfo extends IDEntity {

    /**
	 * 标题
	*/
	private String title;
	

	/**
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	/**
	 * 内容
	*/
	private String content;
	
	

}