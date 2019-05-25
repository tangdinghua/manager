package com.trip.base.entity;

import com.trip.commons.core.bean.IDEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by yk on 2017/2/8.
 */
public class Resource extends IDEntity  implements Comparable<Resource>,Serializable{

    private String resourceCode;

    private String resourceName;

    private String resourceType;

    private Long parentId;

    private String url;

    private String icon;

    private Integer sort;

    private String title;

    private boolean checked;

    private boolean expand;


    private boolean isParent;

    private String name;

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private List<Resource> children;

    public List<Resource> getChildren() {
        return children;
    }

    public void setChildren(List<Resource> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 添加子节点
     * @param child
     */
    public void addChild(Resource child) {
        if(this.children == null){
            this.children = new ArrayList<Resource>();
        }
        this.children.add(child);
    }

    public int compareTo(Resource o) {
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
