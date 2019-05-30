package com.trip.busi.bean;

import com.talkweb.commons.core.bean.IDEntity;
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