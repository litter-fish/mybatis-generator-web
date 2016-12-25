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
@Element("javaClientGenerator")
public class JavaClientGenerator {

    @Need("true")
    @Attribute("type")
    private String type;

    @Need("true")
    @Attribute("targetPackage")
    private String daoPackage;

    @Need("true")
    @Attribute("targetProject")
    private String daoTargetFolder;

    @Need("true")
    private List<PropertyGenerator> propertyGenerator;

}
