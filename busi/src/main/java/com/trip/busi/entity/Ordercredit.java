package com.trip.busi.entity;

import com.trip.commons.core.bean.IDEntity;
import java.util.Date;
import java.util.Date;

import lombok.Data;

@Data
public class Ordercredit extends IDEntity {

    /**
	 * 订单Id
	*/
	private Long orderId;
	
	/**
	 * 可分配积分
	*/
	private Integer credit;
	
	/**
	 * 销售推介人分成比例(百分比)
	*/
	private Integer inviteCommissionRate;
	
	/**
	 * 推介人积分
	*/
	private Integer inviteCredit;
	
	/**
	 * 销售分成比例(百分比)
	*/
	private Integer salesCommissionRate;
	
	/**
	 * 销售积分
	*/
	private Integer salesCredit;
	
	/**
	 * 分配状态0未分配1已分配(枚举:AllotStatus)
	*/
	private Byte allotStatus;
	

	
	/**
	 * 创建时间
	*/
	private Date createdTime;
	
	/**
	 * 更新时间
	*/
	private Date updatedTime;
	
	

}