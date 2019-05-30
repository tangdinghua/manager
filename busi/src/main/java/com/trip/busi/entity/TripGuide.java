package com.trip.busi.bean;

import com.talkweb.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class TripGuide extends IDEntity {

    /**
	 * 发布人用户Id
	*/
	private Long userId;
	
	/**
	 * 标题
	*/
	private String title;
	
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
	
	/**
	 * 内容
	*/
	private String content;
	
	

}