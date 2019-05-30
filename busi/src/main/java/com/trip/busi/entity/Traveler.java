package com.trip.busi.bean;

import com.talkweb.commons.core.bean.IDEntity;
import java.util.Date;

import lombok.Data;

@Data
public class Traveler extends IDEntity {

    private Long userId;
	
	private String traverUser;
	
	private String phone;
	
	private String idCard;
	
	private String idCardPic;
	
	private String idCardPic2;
	
	private String passportPic;
	
	private String passportPic2;
	
	private Byte isDelete;
	
	private Date createTime;
	
	

}