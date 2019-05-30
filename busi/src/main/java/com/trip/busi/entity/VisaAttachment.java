package com.trip.busi.entity;

import com.trip.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class VisaAttachment extends IDEntity {

    /**
	 * 签证Id
	*/
	private Long visaId;
	
	/**
	 * 文件链接
	*/
	private String fileUrl;
	
	/**
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	

}