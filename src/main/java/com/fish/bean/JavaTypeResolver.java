package com.fish.bean;

import com.fish.annotation.Attribute;
import com.fish.annotation.Element;
import lombok.Data;

/**
 * Created by yudin on 2016/12/19.
 */
@Data
@Element("javaTypeResolver")
public class JavaTypeResolver {

    @Attribute("type")
    private String type;

    private PropertyGenerator property;
}
