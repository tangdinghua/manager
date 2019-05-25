package com.trip.commons.core.bean;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体
 *
 * @author fqh
 * @create 2016-12-08 11:07
 */
public class IDEntity implements Serializable {

    private static final long serialVersionUID = 3056478380811734043L;
    /**
     * 实体主键
     */
    private Long id;

    /**
     * 添加时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String createUser;
    private String createUserName;

    private String queryStartTime;
    private String queryEndTime;

    private String realName;
    
    private String isDelete;

    private String isMod;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 检查bean是否为新实体
     * @return 新实体返回true，否则返回false
     */
    public boolean isNew() {
        return id == null || id.equals("");
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getQueryStartTime() {
        return queryStartTime;
    }

    public void setQueryStartTime(String queryStartTime) {
        this.queryStartTime = queryStartTime;
    }

    public String getQueryEndTime() {
        return queryEndTime;
    }

    public void setQueryEndTime(String queryEndTime) {
        this.queryEndTime = queryEndTime;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getIsMod() {
        return isMod;
    }

    public void setIsMod(String isMod) {
        this.isMod = isMod;
    }
}
