package com.trip.base.entity;

import com.trip.commons.core.bean.IDEntity;

public class OperationLog extends IDEntity {
    private String ip;

    private String instr;

    private String outstr;

    private String module;

    private String url;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getInstr() {
        return instr;
    }

    public void setInstr(String instr) {
        this.instr = instr;
    }

    public String getOutstr() {
        return outstr;
    }

    public void setOutstr(String outstr) {
        this.outstr = outstr;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}