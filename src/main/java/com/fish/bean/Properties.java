package com.fish.bean;

import com.fish.annotation.Attribute;
import com.fish.annotation.Element;
import lombok.Data;

/**
 * Created by yudin on 2016/12/24.
 */
@Data
@Element("properties")
public class Properties {

    @Attribute("resource")
    private String resource;

    @Attribute("url")
    private String url;
}
