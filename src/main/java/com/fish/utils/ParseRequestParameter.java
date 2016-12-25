package com.fish.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fish.bean.GeneratorConfiguration;

import java.lang.reflect.Field;
import java.util.*;

import static com.fish.utils.ReflectionUtil.isSerialVersionUID;

/**
 * Created by yudin on 2016/12/24.
 */
public class ParseRequestParameter {

    public static Object jsonToObject(String body, Object object) throws IllegalAccessException, InstantiationException {
        JSONObject jsonObject = ClassUtils.toJSONObject(body);

        Field[] allField = ReflectionUtil.getDeclaredField(object);
        for(Field field : allField) {
            String fieldName = field.getName();
            if(isSerialVersionUID(fieldName)) {
                continue;
            }

            Class<?> fieldType = field.getType();
            if ( !ReflectionUtil.isBaseDataType(fieldType) ) {
                // 判断
                if (fieldType.equals(List.class) || fieldType.equals(Set.class)) {
                    Class<?> clazz = ReflectionUtil.genericClazz(field);

                    String innerSimplName = clazz.getSimpleName();
                    Object value = jsonObject.get(innerSimplName.substring(0, 1).toLowerCase() + innerSimplName.substring(1));

                    if (null == value) continue;

                    List<Object> list = new ArrayList<>();

                    if (value instanceof  JSONArray) {
                        JSONArray jsonArray = (JSONArray) value;
                        for (int offset2 = 0; offset2 < jsonArray.size(); offset2++) {
                            Object value2 =  jsonArray.get(offset2);
                            if (value2 instanceof  JSONObject) {
                                JSONObject oo = (JSONObject) value2;
                                Object innerObject = clazz.newInstance();
                                jsonToObject(oo.toJSONString(), innerObject);
                                list.add(innerObject);
                            }
                        }
                    }

                    ReflectionUtil.setFieldValue(object, fieldName, list);

                }  else if (fieldType.equals(Map.class)) {
                    continue;
                } else if (fieldType instanceof Class) {
                    Object innerObject = fieldType.newInstance();
                    String simpleName = fieldType.getSimpleName();
                    Object value = jsonObject.get(simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1));
                    if (value instanceof  JSONObject) {
                        JSONObject oo = (JSONObject) value;
                        jsonToObject(oo.toJSONString(), innerObject);
                        ReflectionUtil.setFieldValue(object, fieldName, innerObject);
                    }
                }


            } else {
                Object value = jsonObject.get(fieldName);
                if (null != value) {
                    ReflectionUtil.setFieldValue(object, fieldName, value);
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {

        String text = "{\n" +
                "  \"classPathEntry\": [\n" +
                "    {\n" +
                "      \"location\": \"D:\\\\data\\\\generator\\\\mysql-connector-java-5.1.34.jar\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"projectFolder\": \"D:/data/generator\",\n" +
                "  \"properties\": {\n" +
                "    \"url\": \"url\",\n" +
                "    \"resource\": \"resource\"\n" +
                "  },\n" +
                "  \"contextConfig\": [\n" +
                "    {\n" +
                "      \"javaModelGenerator\": {\n" +
                "        \"propertyGenerator\": [\n" +
                "          {\n" +
                "            \"name\": \"enableSubPackages\",\n" +
                "            \"value\": \"true\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"exampleMethodVisibility\",\n" +
                "            \"value\": \"public\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"modelPackage\": \"com.fish.model\",\n" +
                "        \"modelPackageTargetFolder\": \"src/main/java\"\n" +
                "      },\n" +
                "      \"javaClientGenerator\": {\n" +
                "        \"propertyGenerator\": [\n" +
                "          {\n" +
                "            \"name\": \"enableSubPackages\",\n" +
                "            \"value\": \"true\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"exampleMethodVisibility\",\n" +
                "            \"value\": \"public\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"javaClientGenerator.type\": \"XMLMAPPER\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        Map<String, List<Object>> classMap = new HashMap<>();
        try {
            GeneratorConfiguration generatorConfiguration = new GeneratorConfiguration();
            jsonToObject(text, generatorConfiguration);
            System.out.println();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


    }





}
