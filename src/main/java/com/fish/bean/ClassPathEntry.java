package com.fish.bean;

import com.fish.annotation.Need;
import lombok.Data;

/**
 * Created by yudin on 2016/12/19.
 */
@Data
public class ClassPathEntry {

    @Need("true")
    private String location;
}
