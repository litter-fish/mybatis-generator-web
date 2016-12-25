package com.fish.bean;

import com.fish.annotation.Attribute;
import com.fish.annotation.Element;
import com.fish.annotation.Need;
import lombok.Data;

/**
 * Created by yudin on 2016/12/19.
 */
@Data
@Element("classPathEntry")
public class ClassPathEntry {

    @Need("true")
    @Attribute("location")
    private String location;

}
