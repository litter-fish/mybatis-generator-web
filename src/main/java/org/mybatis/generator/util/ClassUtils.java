package org.mybatis.generator.util;

/**
 * Created by yudin on 2016/12/25.
 */
public class ClassUtils {

    public static String splic(String value) {

        StringBuilder builder = new StringBuilder();

        String [] values = value.split("\\.");

        builder.append(value.substring(0, value.lastIndexOf(".") + 1));


        String className = values[values.length - 1];


        String[] classNames = className.split("_");

        for (String cn : classNames) {
            builder.append(cn.substring(0, 1).toUpperCase() + cn.substring(1));
        }

        return builder.toString();

    }

    public static void main(String[] args) {
        System.out.println(splic("com.fish.bean.user_name"));
    }

}
