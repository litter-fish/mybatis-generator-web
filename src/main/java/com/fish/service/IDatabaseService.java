package com.fish.service;

import com.fish.bean.Database;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by fish on 2016/12/11.
 */
public interface IDatabaseService {


    List<String> getAllTableName(Database database) throws SQLException;



    List<String> getTableColumn(Database dataBase) throws SQLException;


    List<String> getAllDatabase(Database dataBase) throws SQLException;


}
