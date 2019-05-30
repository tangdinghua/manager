package com.trip.busi.entity;

import com.trip.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class OrderInfo extends IDEntity {

    /**
	 * 订单名称
	*/
	private String name;
	
	/**
	 * 产品Id
	*/
	private Long productId;
	
	/**
	 * 数量
	*/
	private Integer amout;
	
	/**
	 * 客户姓名
	*/
	private String customer;
	
	/**
	 * 联系方式
	*/
	private String phone;
	
	/**
	 * 地址
	*/
	private String address;
	
	/**
	 * 所属供应商Id
	*/
	private Long providerId;
	
	/**
	 * 所属门店Id
	*/
	private Long storeId;
	
	/**
	 * 销售人员Id
	*/
	private Long salesId;
	
	/**
	 * 订单状态0待付款1待签约2待出行3待评价4已完成9已废除(枚举:OrderStatus)
	*/
	private Byte orderStatus;
	
	/**
	 * 确认编号
	*/
	private String vertifyCode;
	

	
	/**
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	private String orderid;
	
	/**
	 * 1产品订单 2定制游
	*/
	private Integer productType;
	
	

}