package com.fish.bean;

import lombok.Data;

/**
 * Created by yudin on 2016/12/19.
 */
@Data
public class JdbcConnection {


    private String driverClass;

    private String connectionURL;

    private String userId;

    private String password;

}
