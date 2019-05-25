package com.trip.busi.constants;

import lombok.Data;

public enum ErrorCons {

    LOGIN_CODE_ERROR("1000","验证码错误"),
    WORK_ORDER_NO_ERROR("2000","工单信息错误"),
    WORK_ORDER_EXISTS_APPRAISAL_ERROR("2001","工单已评价"),
    FAMILIY_ORDER_EXISTS_APPRAISAL_ERROR("3000","宽带产品已经预约"),
    BROADBAND_COVER_NO("8001","该小区没有覆盖"),
    MAINTAIN_LOGIN_ERROR("1010","用户名或密码错误"),
    SIGN_ERR("4000","用户今天已经签到，不能重复签到"),
    FILE_FORMAT_ERR("6000","导入文件必须是EXCEL文件格式"),
    EXCEL_IMP_ERR("5000","导出Excel文件时发生异常"),
    BIND_CNT_ERR("9000","一个会员最多绑定三个宽带账号"),
    USER_NOT_EXISTS_ERR("7000","用户信息不存在，请检查后重新输入");
    private String code;
    private String message;

    ErrorCons(String code,String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
