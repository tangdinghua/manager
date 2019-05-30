package com.trip.busi.entity;

import com.trip.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class TripShare extends IDEntity {

    /**
	 * 用户Id
	*/
	private Long userId;
	
	/**
	 * 地点
	*/
	private String location;
	
	/**
	 * 经度
	*/
	private Double longitude;
	
	/**
	 * 纬度
	*/
	private Double latitude;
	

	/**
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	/**
	 * 分享内容
	*/
	private String content;
	
	

}