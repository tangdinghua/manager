package com.trip.busi.bean;

import com.trip.commons.core.bean.IDEntity;
import java.util.Date;

import lombok.Data;

@Data
public class ProductAd extends IDEntity {

    private String title;
	
	private String img;
	
	private Long productId;
	
	private Date createTime;
	
	/**
	 * 1生效  0未生效
	*/
	private String status;
	
	private Long userId;
	
	

}