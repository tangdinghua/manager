package com.trip.base.entity;

import com.trip.commons.core.bean.IDEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Dict extends IDEntity  implements Comparable<Dict>,Serializable {
	
	private String code;
	
	private String value;
	
	private String name;
	
	private Long parentId;

	private boolean expand;

	private List<Dict> children;

	private String title;
	
	private Integer sort;

	private String remark;


	private boolean isParent;

	private String parentCode;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public boolean getIsParent() {
		return isParent;
	}

	public void setParent(boolean parent) {
		isParent = parent;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public boolean isExpand() {
		return expand;
	}

	public void setExpand(boolean expand) {
		this.expand = expand;
	}

	public List<Dict> getChildren() {
		return children;
	}

	public void setChildren(List<Dict> children) {
		this.children = children;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 添加子节点
	 * @param child
	 */
	public void addChild(Dict child) {
		if(this.children == null){
			this.children = new ArrayList<Dict>();
		}
		this.children.add(child);
	}

	public int compareTo(Dict o) {
		// TODO Auto-generated method stub
		if(this == o){
			return 0;
		}else if(o == null || o.getId() == null){
			return 1;
		}else if(o.getId() == null){
			return -1;
		}else{
			return this.getId().compareTo(o.getId());
		}
	}
}
