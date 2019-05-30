package com.trip.busi.entity;

import com.trip.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class Region extends IDEntity {

    /**
	 * 父Id
	*/
	private Long parentId;
	
	/**
	 * 地区分类Id
	*/
	private Long regionSortId;
	
	/**
	 * 地区名
	*/
	private String name;
	
	/**
	 * 是否热门0否1是(枚举:YesNoStatus[core])
	*/
	private Byte isHot;


	
	/**
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	

}