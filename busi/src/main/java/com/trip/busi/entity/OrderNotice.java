package com.trip.busi.entity;

import com.trip.commons.core.bean.IDEntity;
import java.util.Date;

import lombok.Data;

@Data
public class OrderNotice extends IDEntity {

    private String orderid;
	
	private String noticeFilename;
	
	private String realFilename;
	
	private Date uploadTime;
	
	private Long userid;
	
	

}