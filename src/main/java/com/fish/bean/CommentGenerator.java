package com.fish.bean;

import lombok.Data;

import java.util.List;

/**
 * Created by yudin on 2016/12/19.
 */
@Data
public class CommentGenerator {

    private String type;

    private List<PropertyGenerator> property;
}
