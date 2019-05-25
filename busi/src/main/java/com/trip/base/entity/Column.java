package com.trip.base.entity;

public class Column {

    private String remark;

    private String datatype;

    private String columnname;

    private String islist;

    private String isquery;

    private String module;

    private String table;

    private String modulename;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getColumnname() {
        return columnname;
    }

    public void setColumnname(String columnname) {
        this.columnname = columnname;
    }

    public String getIslist() {
        return islist;
    }

    public void setIslist(String islist) {
        this.islist = islist;
    }

    public String getIsquery() {
        return isquery;
    }

    public void setIsquery(String isquery) {
        this.isquery = isquery;
    }
}
