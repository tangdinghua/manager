package com.trip.base.entity;

import com.trip.commons.core.bean.IDEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Org extends IDEntity   implements Comparable<Org>,Serializable {
    private String orgName;

    private Long parentId;

    private boolean isParent;

    private boolean isUser;

    private boolean expand;

    private String title;

    private int sort;

    private List<Org> children;

    private String isCity;

    private String cityCode;

    private String orgCode;

    private String isGrid;

    private String parentName;


    private String orgDepartment;

    /**
     * 添加子节点
     * @param child
     */
    public void addChild(Org child) {
        if(this.children == null){
            this.children = new ArrayList<Org>();
        }
        this.children.add(child);
    }

    public int compareTo(Org o) {
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