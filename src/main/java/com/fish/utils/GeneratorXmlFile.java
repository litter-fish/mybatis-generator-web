package com.fish.utils;

import com.fish.annotation.Attribute;
import com.fish.annotation.Element;
import com.fish.annotation.Ignore;
import com.fish.annotation.Need;
import com.fish.bean.GeneratorConfiguration;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;

import java.lang.reflect.Field;
import java.util.*;

import static com.fish.utils.DocumentParser.fileWriter;
import static com.fish.utils.ReflectionUtil.*;

/**
 * Created by yudin on 2016/12/23.
 */
public class GeneratorXmlFile {

    private final static Logger log = Logger.getLogger(GeneratorXmlFile.class);


    public static void generatorXmlFiles(Object generatorConfig) {

        Class<?> clazz = generatorConfig.getClass();

        Element element = clazz.getAnnotation(Element.class);

        String elementValue = element.value();

        Document document = DocumentParser.createDocument();

        org.dom4j.Element rootElement = DocumentParser.createRootElement(document, elementValue);

        List<org.dom4j.Element> deleteElement = new ArrayList<>();

        parseField(generatorConfig, rootElement, deleteElement);

        if (CollectionUtils.isNotEmpty(deleteElement)) {
            for (org.dom4j.Element element1 : deleteElement) {
                element1.getParent().remove(element1);
            }
        }

        fileWriter(document);

        GeneratorConfigUtils.generate(document);

    }

    private static void parseField(Object object, org.dom4j.Element element, List<org.dom4j.Element> deleteElement) {

        Class<?> clazz = object.getClass();

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {

            String fieldName = field.getName();
            if(isSerialVersionUID(fieldName)) {
                continue;
            }

            Need need = field.getAnnotation(Need.class);
            if (null != need && "false".equals(need.value())) {
                continue;
            }

            Class<?> fieldType = field.getType();
            if ( !ReflectionUtil.isBaseDataType(fieldType) ) {

                Object obj = getFieldValue(object, fieldName);

                // 判断
                if (fieldType.equals(List.class) || fieldType.equals(Set.class)) {

                    Collection<?> collection = (Collection<?>) obj;
                    if (CollectionUtils.isEmpty(collection)) continue;
                    Iterator<?> iterator = collection.iterator();
                    while (iterator.hasNext()) {
                        Object object2 = iterator.next();

                        Element ele = object2.getClass().getAnnotation(Element.class);
                        if (null == ele) break;
                        String elementValue = ele.value();
                        org.dom4j.Element resultElement = DocumentParser.createElement(element, elementValue);
                        parseField(object2, resultElement, deleteElement);
                    }
                } else if (fieldType.equals(Map.class)) {
                    continue;
                } else if (fieldType instanceof Class) {

                    if (null == obj) continue;
                    Element ele = obj.getClass().getAnnotation(Element.class);
                    if (null == ele) continue;
                    String elementValue = ele.value();
                    org.dom4j.Element resultElement = DocumentParser.createElement(element, elementValue);
                    parseField(obj, resultElement, deleteElement);
                }
            } else {
                Object fieldValue = getFieldValue(object, fieldName);

                Ignore ignore = field.getAnnotation(Ignore.class);
                if ( null != ignore && "true".equals(ignore.value()) && Boolean.valueOf(null != fieldValue ? fieldValue.toString() : "false")) {
                    deleteElement.add(element);
                }

                Attribute attribute = field.getAnnotation(Attribute.class);
                if (null == attribute) continue;
                String key = attribute.value();

                if (null != fieldValue) {
                    Map<String, Object> map = new HashMap<>();
                    map.put(key, fieldValue);
                    DocumentParser.addAttribute(element, map);
                }
            }

        }
    }

    public static GeneratorConfiguration generatorConfiguration(Map<String, List<Object>> allList) {

        GeneratorConfiguration generatorConfiguration = new GeneratorConfiguration();

        generatorConfiguration(allList, generatorConfiguration);

        return generatorConfiguration;

    }

    private static Object generatorConfiguration(Map<String, List<Object>> allList, Object generatorConfiguration) {
        try {
            Field[] allField = getAllDeclaredField(generatorConfiguration);
            for (Field field : allField) {
                String fieldName = field.getName();
                if (isSerialVersionUID(fieldName)) continue;
                Class<?> fieldType = field.getType();
                if (!ReflectionUtil.isBaseDataType(fieldType)) {
                    Class<?> clazz = null;
                    Object objects = null;
                    if (fieldType.equals(List.class) || fieldType.equals(Set.class)) {
                        clazz = ReflectionUtil.genericClazz(field);
                        String simpleName = clazz.getSimpleName();
                        objects = allList.get(simpleName);

                        Object object = generatorConfiguration(allList, CollectionUtils.isNotEmpty(allList.get(simpleName)) ?
                                allList.get(simpleName).get(0) : clazz.newInstance());

                        if (null != object && CollectionUtils.isEmpty(allList.get(simpleName))) {
                            objects = Arrays.asList(object);
                        }
                    } else if (fieldType.equals(Map.class)) {
                        continue;
                    } else if (fieldType instanceof Class) {
                        clazz = fieldType;
                        String simpleName = clazz.getSimpleName();
                        Object object = generatorConfiguration(allList, CollectionUtils.isNotEmpty(allList.get(simpleName)) ?
                                allList.get(simpleName).get(0) : clazz.newInstance());

                        if (CollectionUtils.isNotEmpty(allList.get(simpleName))) {
                            objects = allList.get(simpleName).get(0);
                        } else {
                            if (null != object) {
                                objects = object;
                            }
                        }
                    }
                    if (null != clazz && !judgmentObjectValue(objects)) {
                        ReflectionUtil.setFieldValue(generatorConfiguration, fieldName, objects);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return generatorConfiguration;
    }

    public static <T> boolean judgmentObjectValue(T t) {

        Field[] allField = ReflectionUtil.getDeclaredField(t);

        for (Field field : allField) {

            String fieldName = field.getName();
            if (isSerialVersionUID(fieldName)) continue;
            Class<?> fieldType = field.getType();
            Object value = ReflectionUtil.getFieldValue(t, fieldName);

            if (null == value) continue;

            if (!ReflectionUtil.isBaseDataType(fieldType)) {

                if (fieldType.equals(List.class) || fieldType.equals(Set.class)) {
                    Collection<?> collection = (Collection) value;
                    for (Object c : collection) {
                        boolean result = judgmentObjectValue(c);
                        if (!result) {
                            return result;
                        }
                    }
                } else if (fieldType instanceof Class) {
                    boolean result = judgmentObjectValue(value);
                    if (!result) {
                        return result;
                    }
                } else {
                    continue;
                }
            } else {
                if (null != value)  return false;
            }
        }

        return true;
    }
}
