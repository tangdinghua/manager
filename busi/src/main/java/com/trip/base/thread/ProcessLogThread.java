package com.trip.base.thread;

import com.trip.base.entity.OperationLog;
import com.trip.base.util.SpringContextTool;
import com.trip.commons.core.utils.JsonUtils;
import com.trip.base.service.OperationLogService;

import java.util.Map;

public class ProcessLogThread implements Runnable{

    private String ip;

    private String userid;

    private Map<String,String[]> inMap;

    private Object result;

    private String module;

    private String url;



    public ProcessLogThread(String module,String url,String ip, String userid, Map<String,String[]> inMap, Object result) {
        this.ip = ip;
        this.userid = userid;
        this.inMap = inMap;
        this.result = result;
        this.module = module;
        this.url = url;
    }

    @Override
    public void run() {
        OperationLogService operationLogService = SpringContextTool.getBean(OperationLogService.class);
        OperationLog operationLog = new OperationLog();
        operationLog.setInstr(JsonUtils.toJson(inMap));
        operationLog.setOutstr(JsonUtils.toJson(result));
        operationLog.setIp(ip);
        operationLog.setCreateUser(userid);
        operationLog.setModule(module);
        operationLog.setUrl(url);
        try {
            operationLogService.insert(operationLog);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
