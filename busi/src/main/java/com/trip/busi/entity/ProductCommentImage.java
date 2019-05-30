package com.trip.busi.entity;

import com.trip.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class ProductCommentImage extends IDEntity {

    /**
	 * 点评记录Id
	*/
	private Long commentId;
	
	/**
	 * 图片链接
	*/
	private String imageUrl;
	
	/**
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	

}