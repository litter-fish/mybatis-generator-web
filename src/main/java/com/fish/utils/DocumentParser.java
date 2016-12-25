package com.fish.utils;

import org.apache.commons.collections.CollectionUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Set;

/**
 * Created by yudin on 2016/12/23.
 */
public class DocumentParser {

    public static Document createDocument() {
        Document document = DocumentHelper.createDocument();

        document.addDocType("generatorConfiguration", "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN\" \"http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd", null);

        return document;
    }


    public static Element createRootElement(Document document, String rootName) {

        return document.addElement(rootName);
    }


    public static Element createElement(Element root, String elementName) {

        Element element = root.addElement(elementName);

        return element;
    }

    public static void addAttribute(Element root, Map<String, Object> map) {
        Set<String> keys =  map.keySet();
        if (CollectionUtils.isNotEmpty(keys)) {
            for (String key : keys) {
                Object value = map.get(key);
                root.addAttribute(key, String.valueOf(value));
            }
        }
    }


    protected  static void fileWriter(Document document) {
        //创建字符串缓冲区
        StringWriter stringWriter = new StringWriter();
        //设置文件编码
        OutputFormat xmlFormat = new OutputFormat();
        xmlFormat.setEncoding("UTF-8");
        // 设置换行
        xmlFormat.setNewlines(true);
        // 生成缩进
        xmlFormat.setIndent(true);
        // 使用4个空格进行缩进, 可以兼容文本编辑器
        xmlFormat.setIndent("    ");

        try {
            Writer fileWriter = new FileWriter("D:\\data\\study\\generator-web\\src\\test\\generator.xml");

            //创建写文件方法
            XMLWriter xmlWriter = new XMLWriter(fileWriter, xmlFormat);
            //写入文件
            xmlWriter.write(document);
            //关闭
            xmlWriter.close();
            // 输出xml
            System.out.println(fileWriter.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
