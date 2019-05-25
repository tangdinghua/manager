package com.trip.base.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeNode implements Comparable<TreeNode> {
	/**
	 * 节点标识
	 */
	private Long id;
	
	/**
	 * 父节点标识
	 */
	private Long parentId;

	/**
	 * 节点名称
	 */
	private String text;

	/**
	 * 节点使用的图标
	 */
	private String iconCls;

	/**
	 * 节点状态，'open' 或 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点。
	 */
	private String state = "open";

	/**
	 * 使用复选框，表示该节点是否被选中。
	 */
	private String checked;

	/**
	 * 被添加到节点的自定义属性
	 */
	private Map<String, String> attributes = new HashMap<String, String>();
	
	/**
	 * 子节点容器
	 */
	private List<TreeNode> children;
	
	/**
	 * 设置树节点的属性
	 * @param attrName 属性名称
	 * @param attrValue 属性值
	 */
	public void addAttributes(String attrName,String attrValue){
		attributes.put(attrName, attrValue);
	}
	

	public Long getId() {
		return id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public List<TreeNode> getChildren() {
		return children;
	}
	
	/**
	 * 添加子节点
	 * @param child
	 */
	public void addChild(TreeNode child) {
		if(this.children == null){
			this.children = new ArrayList<TreeNode>();
		}
		this.children.add(child);
	}
	
	/**
	 * 排序以及二分查找用
	 */
	@Override
	public int compareTo(TreeNode o) {
		// TODO Auto-generated method stub
		//如果两个对象的id相同则认为相同
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
