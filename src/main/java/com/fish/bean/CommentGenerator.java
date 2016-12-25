package com.fish.bean;

import com.fish.annotation.Attribute;
import com.fish.annotation.Element;
import lombok.Data;

import java.util.List;

/**
 * Created by yudin on 2016/12/19.
 */
@Data
@Element("commentGenerator")
public class CommentGenerator {

    @Attribute("type")
    private String type;

    private List<PropertyGenerator> propertyGenerator;
}
