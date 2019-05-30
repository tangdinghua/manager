package com.trip.busi.bean;

import com.talkweb.commons.core.bean.IDEntity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class ProductComment extends IDEntity {

    /**
	 * 订单Id
	*/
	private String orderId;
	
	/**
	 * 产品Id
	*/
	private Long productId;
	
	/**
	 * 用户Id
	*/
	private Long userId;
	
	/**
	 * 评分
	*/
	private BigDecimal credit;
	
	/**
	 * 评价内容
	*/
	private String comment;
	
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
	
	private String commentStauts;
	
	private String commentPic1;
	
	private String commentPic2;
	
	private String commentPic3;
	
	private String commentPic4;
	
	private String commentPic5;
	
	private Integer grade;
	
	private Integer guideGrade;
	
	private Integer goodsGrade;
	
	private Integer planGrade;
	
	private Integer trafficGrade;
	
	

}