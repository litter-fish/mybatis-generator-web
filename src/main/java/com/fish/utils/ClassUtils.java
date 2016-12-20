package com.fish.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by yudin on 2016/12/18.
 */
public class ClassUtils {


    public static  <T> T toJavaObject(String body, Class<T> clazz){
        try {
            return JSON.toJavaObject(JSON.parseObject(body), clazz);
        } catch (Exception e) {
        }
        return null;
    }

    public static JSONObject toJSONObject(String body) {
        return JSON.parseObject(body);
    }
}
