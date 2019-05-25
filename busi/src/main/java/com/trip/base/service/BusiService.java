package com.trip.base.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务处理
 *
 * @author fqh
 * @create 2017-08-14 16:08:24
 */

@Service
public class BusiService {

    @Autowired
    protected SqlSessionTemplate sqlSession;


    public List<Map<String, String>> queryTableColumn(String table) throws SQLException {
        Connection connection = null;
        ResultSet rs = null;
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        try {
            connection = SqlSessionUtils.getSqlSession(
                    sqlSession.getSqlSessionFactory(), sqlSession.getExecutorType(),
                    sqlSession.getPersistenceExceptionTranslator()).getConnection();
            DatabaseMetaData dbmd = connection.getMetaData();
            rs = dbmd.getColumns(null, null, table, null);
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");  //列名
                String dataTypeName = rs.getString("TYPE_NAME");  //java.sql.Types类型名称(列类型名称)
                String remarks = rs.getString("REMARKS");  //列描述
                Map<String, String> map = new HashMap<String, String>();
                columnName = columnName.toLowerCase();
                if (!"createuser".equals(columnName) && !"createtime".equals(columnName) && !"id".equals(columnName)) {
                    map.put("columnname", columnName.toLowerCase());
                    map.put("datatype", dataTypeName.toLowerCase());
                    map.put("remark", remarks.toLowerCase());
                    list.add(map);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }


}
