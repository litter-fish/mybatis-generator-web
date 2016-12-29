package com.fish.bean;

import com.fish.annotation.Need;
import lombok.Data;

/**
 * Created by fish on 2016/12/11.
 */
@Data
public class Database {

    @Need("true")
    private String connectionURL;

    @Need("true")
    private String userId;

    @Need("true")
    private String password;

    private String tableSchema;

    private String tableName;

    private String databaseType;

    private String ip;

    private String port;

    @Need("true")
    private String driverClass;

    private String dataBaseName;

}
