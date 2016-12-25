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
@Element("javaModelGenerator")
public class JavaModelGenerator {

    @Need("true")
    @Attribute("targetPackage")
    private String modelPackage;

    @Need("true")
    @Attribute("targetProject")
    private String modelPackageTargetFolder;


    @Need("true")
    private List<PropertyGenerator> propertyGenerator;
}
