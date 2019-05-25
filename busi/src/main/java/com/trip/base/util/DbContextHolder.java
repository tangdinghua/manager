package com.trip.base.util;

public class DbContextHolder {
	public static final ThreadLocal contextHolder = new ThreadLocal();   
	   
    public static void setDataSource(String dataSource) {   
        contextHolder.set(dataSource);   
    }   
  
    public static String getDataSource() {   
        return (String) contextHolder.get();   
    }   
  
    public static void clearDataSource() {   
        contextHolder.remove();   
    }
}
