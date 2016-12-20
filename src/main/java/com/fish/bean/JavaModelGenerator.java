package com.fish.bean;

import com.fish.annotation.Need;
import lombok.Data;

import java.util.List;

/**
 * Created by yudin on 2016/12/18.
 */
@Data
public class JavaModelGenerator {
    @Need("true")
    private String targetPackage;
    @Need("true")
    private String targetProject;
    @Need("true")
    private List<PropertyGenerator> property;
}
