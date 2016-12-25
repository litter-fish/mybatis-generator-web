package com.fish.bean;

import com.fish.annotation.Attribute;
import com.fish.annotation.Element;
import lombok.Data;

/**
 * Created by yudin on 2016/12/24.
 */
@Data
@Element("ignoreColumn")
public class IgnoreColumn {

    @Attribute("column")
    private String column;

    /**
     *  当匹配对从数据库中返回的列时，如果为真，则MBG将执行区分大小写完全匹配。 如果为false(默认值)，则被认为是不区分大小写的名称。
     */
    @Attribute("delimitedColumnName")
    private String delimitedColumnName;
}
