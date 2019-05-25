package com.trip.base.constants;

public class Constants {

    public static String PASSWORD_EXT = "saas";

    public static String SYS_FAIL_MSG = "系统忙，请稍候再试";

    public static String SYS_FAIL_CODE = "-1";


    /**
     * jwt
     */
    public static final String JWT_ID = "jwt";
    public static final String JWT_SECRET = "7712df7fc3a34e26a61c034d5ec8245d";
    public static final String JWT_KEY = "7786df7fc3a34e26a61c034d5ec8245e";
    public static final int JWT_TTL = 7*24*60*60*1000;  //millisecond
    public static final int JWT_REFRESH_INTERVAL = 55*60*1000;  //millisecond
    public static final int JWT_REFRESH_TTL = 12*60*60*1000;  //millisecond

    public static final String SYS_ID="manager";
}
