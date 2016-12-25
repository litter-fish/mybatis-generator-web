package com.fish.bean;

import com.fish.annotation.Element;
import lombok.Data;

import java.util.List;

/**
 * Created by yudin on 2016/12/24.
 */
@Data
@Element("generatorConfiguration")
public class GeneratorConfiguration {

    private String projectFolder;

    private Properties properties;

    private List<ClassPathEntry> classPathEntry;

    private List<ContextConfig> contextConfig;


}
