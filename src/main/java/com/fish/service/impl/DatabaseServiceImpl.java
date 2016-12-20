package com.fish.service.impl;

import com.fish.bean.Database;
import com.fish.service.IDatabaseService;
import com.fish.utils.ConnectionUtils;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fish on 2016/12/11.
 */
@Service
public class DatabaseServiceImpl implements IDatabaseService {


    @Override
    public List<String> getAllTableName(Database database) throws SQLException {

        Connection conn = null;
        PreparedStatement pst = null;
        List<String> list = new ArrayList<>();
        try {
            // 1.2
            conn = ConnectionUtils.getConnection(database);
            // 3创建执行对象
            pst = conn.prepareStatement("select TABLE_NAME from information_schema.tables where TABLE_SCHEMA=?");
            pst.setObject(1, database.getTableSchema());
            // 4
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                list.add(rs.getString(1));
            }

            conn.commit();
        } finally {
            // 5
            ConnectionUtils.close(conn, pst, null);
        }

        return list;
    }

    @Override
    public  List<String> getTableColumn(Database database) throws SQLException {


        Connection conn = null;
        PreparedStatement pst = null;
        List<String> list = new ArrayList<>();
        try {
            // 1.2
            conn = ConnectionUtils.getConnection(database);
            // 3创建执行对象
            pst = conn.prepareStatement("select * from " + database.getTableName());
            // 4
            ResultSet rs = pst.executeQuery();

            ResultSetMetaData rsm = rs.getMetaData();

            for (int offset = 0; offset < rsm.getColumnCount(); offset++) {
                list.add(rsm.getColumnName(offset + 1));
            }
            conn.commit();
        } finally {
            // 5
            ConnectionUtils.close(conn, pst, null);
        }

        return list;
    }

    @Override
    public List<String> getAllDatabase(Database dataBase) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        List<String> list = new ArrayList<>();
        try {
            // 1.2
            conn = ConnectionUtils.getConnection(dataBase);
            // 3创建执行对象
            pst = conn.prepareStatement("SELECT `SCHEMA_NAME` FROM `information_schema`.`SCHEMATA`");
            // 4
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                list.add(rs.getString(1));
            }
            conn.commit();
        } finally {
            // 5
            ConnectionUtils.close(conn, pst, null);
        }

        return list;
    }
}
