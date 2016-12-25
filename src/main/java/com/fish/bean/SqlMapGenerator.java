package com.fish.bean;

import com.fish.annotation.Attribute;
import com.fish.annotation.Element;
import com.fish.annotation.Need;
import lombok.Data;

import java.util.List;

/**
 * Created by yudin on 2016/12/18.
 */
@Data
@Element("sqlMapGenerator")
public class SqlMapGenerator {

    @Need("true")
    @Attribute("targetPackage")
    private String mappingXMLPackage;

    @Need("true")
    @Attribute("targetProject")
    private String mappingXMLTargetFolder;

    @Need("true")
    private List<PropertyGenerator> propertyGenerator;
}
