package com.fish.bean;

import com.fish.annotation.Need;
import lombok.Data;

/**
 * Created by yudin on 2016/12/18.
 */
@Data
public class PropertyGenerator {

    @Need("true")
    private String name;
    @Need("true")
    private String value;

}
