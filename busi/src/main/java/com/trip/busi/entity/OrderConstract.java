package com.trip.busi.entity;

import com.trip.commons.core.bean.IDEntity;
import java.util.Date;

import lombok.Data;

@Data
public class OrderConstract extends IDEntity {

    private String orderid;
	
	private String constractPic;
	
	private Date updateTime;
	
	private Long userId;
	
	

}