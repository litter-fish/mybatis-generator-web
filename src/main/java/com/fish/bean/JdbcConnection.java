package com.fish.bean;

import com.fish.annotation.Attribute;
import com.fish.annotation.Element;
import lombok.Data;

/**
 * Created by yudin on 2016/12/19.
 */
@Data
@Element("jdbcConnection")
public class JdbcConnection {


    @Attribute("driverClass")
    private String driverClass;
    @Attribute("connectionURL")
    private String connectionURL;
    @Attribute("userId")
    private String userId;
    @Attribute("password")
    private String password;

}
