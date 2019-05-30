package com.trip.busi.bean;

import com.talkweb.commons.core.bean.IDEntity;
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