package com.fish.bean;

import lombok.Data;

/**
 * Created by yudin on 2016/12/18.
 */
@Data
public class GeneratorConfig {

    private String name;

    private String location;

    private String projectFolder;

    private String modelPackage;

    private String modelPackageTargetFolder;

    private String daoPackage;

    private String daoTargetFolder;

    private String mappingXMLPackage;

    private String mappingXMLTargetFolder;

    private String tableName;

    private String domainObjectName;

    private String tableColumn;

    private Database database;

    private String javaClientGenerator;

    private String tableConfigValue;

    private String otherConfig;

    private boolean offsetLimit;

    private boolean comment;
}
