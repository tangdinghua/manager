package com.trip.base.action;

import com.trip.base.aspect.ActionLog;
import com.trip.base.entity.Column;
import com.trip.base.service.BusiService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "busi")
@ActionLog(module = "业务管理")
public class BusiAction {

    @Autowired
    private Configuration configuration;
    @Autowired
    private BusiService busiService;

    @RequestMapping(value = "column",name = "列查询")
    public Object queryColumn(String table) throws SQLException {
        return busiService.queryTableColumn(table);
    }

    private String upperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    @RequestMapping(value = "generate",name = "生成代码")
    public Object generate(HttpServletResponse response,Column column) throws SQLException, IOException, TemplateException {
        Template template = configuration
                .getTemplate("list.ftl");
        String module = column.getModule();
        String modulename = column.getModulename();
        String remarks [] = column.getRemark().split(",");
        String columnnames [] = column.getColumnname().split(",");
        String islists [] = column.getIslist().split(",");
        String isquerys [] = column.getIsquery().split(",");
        List<Map<String,String>> queryList = new ArrayList<>();
        List<Map<String,String>> dataList = new ArrayList<>();
        List<Map<String,String>> allList = new ArrayList<>();
        for(int i =0;i<remarks.length;i++){
            String islist = islists[i];
            String columnname = columnnames[i];
            String []columnArray = columnname.split("_");
            for(int j=0;j<columnArray.length;j++){
                if(j==0){
                    columnname = columnArray[j];
                }else{
                    columnname = columnname+ upperCase(columnArray[j]);
                }
            }
            String remark = remarks[i];
            Map<String,String> map = new HashMap<>();
            map.put("name",remark);
            map.put("code",columnname);
            if(islist.equals("true")){
                dataList.add(map);
            }
            String isquery = isquerys[i];
            if(isquery.equals("true")){
                queryList.add(map);
            }
            allList.add(map);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("query",queryList);
        map.put("data",dataList);
        map.put("module",module);
        map.put("modulename",modulename);
        map.put("all",allList);
        // 合并输出 创建页面文件
        response.setCharacterEncoding("utf-8");
        StringWriter sw = new StringWriter();
        template.process(map,sw);
        Map<String,String> result = new HashMap<>();
        result.put("list",sw.toString());
        sw = new StringWriter();
        template = configuration
                .getTemplate("add.ftl");
        template.process(map,sw);
        result.put("add",sw.toString());
        return result;
    }

}
