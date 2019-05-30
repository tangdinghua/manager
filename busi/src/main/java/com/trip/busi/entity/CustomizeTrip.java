package com.trip.busi.bean;

import com.talkweb.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class CustomizeTrip extends IDEntity {

    private Long userId;
	
	private Long storeId;
	
	/**
	 * 1个人定制 2企业定制
	*/
	private String customizeType;
	
	private String departure;
	
	private String destination;
	
	private String departureTime;
	
	private String backTime;
	
	private Integer total;
	
	private String budget;
	
	private Integer planDesign;
	
	private Integer hotelDesign;
	
	private Integer airDesign;
	
	private Integer goodsDesign;
	
	private Integer carDesign;
	
	private Integer ticketDesign;
	
	private String enterpriseName;
	
	private String linkMan;
	
	private String linkPhone;
	
	private Date customTime;
	
	private Byte isDelete;
	
	private Integer createUser;
	
	private Integer maintainUserId;
	
	private Date maintainTime;
	
	private String profit;
	
	private String salesProption1;
	
	private String salesMoney1;
	
	private String salesProption2;
	
	private String salesMoney2;
	
	private String finalMoeny;
	
	private String remark;
	
	

}