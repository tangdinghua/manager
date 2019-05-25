package com.trip.base.entity;

import com.trip.commons.core.bean.IDEntity;
import lombok.Data;

/**
 * Created by yk on 2017/2/9.
 */
@Data
public class User extends IDEntity {

	private String userName;

	private String mobile;

	private String password;

	private String status;


	private String oldpassword;

	private String realName;

	private Long orgId;

	private String orgIds;

	private String roleIds;

	private String userType;

	private String token;


	private String resources;

	private String roleCode;

	private String roleName;

	private String orgName;

	private String area;

	private String group;

	private String areaName;

	private String groupName;


	private String gender;

	private String nation;

	private String workJob;

	private String empNo;

	private String isLeader;

	private String cardNo;

	private String edu;

	private String ext1;

	private String ext2;

	private String maintainProfession;
	private String workDepartment;
	private String mainOrgId;
	private String mainOrgName;

	private String orgCode;

	private String communityCode;
	private String communityName;
	private String headImg;

	private Long leader;

	private String code;
}
