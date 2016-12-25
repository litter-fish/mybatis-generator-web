package com.fish.bean;

import com.fish.annotation.Attribute;
import com.fish.annotation.Element;
import com.fish.annotation.Need;
import lombok.Data;

/**
 * Created by yudin on 2016/12/18.
 */
@Data
@Element("property")
public class PropertyGenerator {

    @Need("true")
    @Attribute("name")
    private String name;
    @Need("true")
    @Attribute("value")
    private String value;

}
