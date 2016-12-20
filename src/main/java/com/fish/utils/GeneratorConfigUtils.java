package com.fish.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fish.annotation.Need;
import com.fish.bean.Database;
import com.fish.bean.GeneratorConfig;
import com.fish.bean.TableConfig;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by yudin on 2016/12/18.
 */
public class GeneratorConfigUtils {

    public static void generatorXmlFile(GeneratorConfig generatorConfig) {

        Document document = DocumentHelper.createDocument();

        document.addDocType("generatorConfiguration", "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN\" \"http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd", null);

        Element root = document.addElement("generatorConfiguration");

        //生成root的一个接点
        Element classPathEntry = root.addElement("classPathEntry");
        // 为节点添加属性
        classPathEntry.addAttribute("location", generatorConfig.getLocation());


        // <context id="DB2Tables" targetRuntime="MyBatis3">
        //生成root的一个接点
        Element context = root.addElement("context");
        // 为节点添加属性
        String projectFolder = generatorConfig.getProjectFolder();
        context.addAttribute("id", projectFolder.substring(projectFolder.lastIndexOf("/") + 1));
        context.addAttribute("targetRuntime", "MyBatis3");


        /**
         * <!-- 为了防止生成的代码中有很多注释，比较难看，加入下面的配置控制 -->
         <commentGenerator>
         <property name="suppressAllComments" value="true" />
         <property name="suppressDate" value="true" />
         </commentGenerator>
         */
       /* Element commentGenerator = context.addElement("commentGenerator");
        Element commentGeneratorProperty1 = commentGenerator.addElement("property");
        commentGeneratorProperty1.addAttribute("name", "suppressAllComments");
        commentGeneratorProperty1.addAttribute("value", "true");
        Element commentGeneratorProperty2 = commentGenerator.addElement("property");
        commentGeneratorProperty2.addAttribute("name", "suppressDate");
        commentGeneratorProperty2.addAttribute("value", "true");*/
        createOtherElementToContext(context, generatorConfig);

        /**
         * <!-- 数据库链接URL、用户名、密码 -->
         <jdbcConnection
         driverClass="com.mysql.jdbc.Driver"
         connectionURL="jdbc:mysql://localhost:3306/test?characterEncoding=utf8"
         userId="root"
         password="root" />
         */
        //生成root的一个接点
        Element jdbcConnection = context.addElement("jdbcConnection");
        Database database = generatorConfig.getDatabase();
        if (null != database) {
            // 为节点添加属性
            addAttributeToElement(jdbcConnection, database);
        }


        /**
         * <!-- 生成模型的包名和位置 -->
         <javaModelGenerator targetPackage="andy.model" targetProject="src/main/java">
         <property name="enableSubPackages" value="true" />
         <property name="trimStrings" value="true" />
         </javaModelGenerator>
         */
        Element javaModelGenerator = context.addElement("javaModelGenerator");
        javaModelGenerator.addAttribute("targetPackage", generatorConfig.getModelPackage());
        javaModelGenerator.addAttribute("targetProject", projectFolder + "/" + generatorConfig.getModelPackageTargetFolder());
        /**
         * <property name="enableSubPackages" value="true" />
         */
        Element javaModelGeneratorProperty = javaModelGenerator.addElement("property");
        javaModelGeneratorProperty.addAttribute("name", "enableSubPackages");
        javaModelGeneratorProperty.addAttribute("value", "true");

        /**
         * <!-- 生成的映射文件包名和位置 -->
         <sqlMapGenerator targetPackage="andy.mapping" targetProject="src/main/resources">
         <property name="enableSubPackages" value="true" />
         </sqlMapGenerator>
         */
        Element sqlMapGenerator = context.addElement("sqlMapGenerator");
        sqlMapGenerator.addAttribute("targetPackage", generatorConfig.getMappingXMLPackage());
        sqlMapGenerator.addAttribute("targetProject", projectFolder + "/" + generatorConfig.getMappingXMLTargetFolder());

        Element sqlMapGeneratorProperty = sqlMapGenerator.addElement("property");
        sqlMapGeneratorProperty.addAttribute("name", "enableSubPackages");
        sqlMapGeneratorProperty.addAttribute("value", "true");

        /**
         * <!-- 生成DAO的包名和位置 -->
         <javaClientGenerator type="XMLMAPPER" targetPackage="andy.dao" targetProject="src/main/java">
         <property name="enableSubPackages" value="true" />
         </javaClientGenerator>
         */
        Element javaClientGenerator = context.addElement("javaClientGenerator");
        javaClientGenerator.addAttribute("type", generatorConfig.getJavaClientGenerator());
        javaClientGenerator.addAttribute("targetPackage", generatorConfig.getDaoPackage());
        javaClientGenerator.addAttribute("targetProject", projectFolder + "/" + generatorConfig.getDaoTargetFolder());

        Element javaClientGeneratorProperty = javaClientGenerator.addElement("property");
        javaClientGeneratorProperty.addAttribute("name", "enableSubPackages");
        javaClientGeneratorProperty.addAttribute("value", "true");

        createTableElement(generatorConfig, context);


        fileWriter(document);

        try {
            generate(parse(document));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static void createOtherElementToContext(Element context, GeneratorConfig generatorConfig) {

        String otherConfig = generatorConfig.getOtherConfig();

        JSONObject jsonObject = JSONObject.parseObject(otherConfig);
        Set<Map.Entry<String, Object>> entries = jsonObject.entrySet();

        Map<String, List<String>> allEl = new HashMap<>();

        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            Object value = entry.getValue();

            String[] keies = key.split("\\.");

            List<String> list = allEl.get(keies[0]);

            if(CollectionUtils.isEmpty(list)) {
                list = new ArrayList<>();
            }

            list.add(keies[1] + "=" + value);
            allEl.put(keies[0], list);
        }

        for (Map.Entry<String, List<String>> entry : allEl.entrySet()) {
            String key = entry.getKey();
            List<String> list = entry.getValue();

            Element keyElement =context.addElement(key);

            if (CollectionUtils.isNotEmpty(list)) {
                for (String str : list) {
                    Element property = keyElement.addElement("property");
                    String[] strs = str.split("=");
                    property.addAttribute("name", strs[0]);
                    property.addAttribute("value", strs[1]);
                }
            }


        }
    }



    public static org.w3c.dom.Document parse(Document doc) throws Exception {
        if (doc == null) {
            return (null);
        }
        java.io.StringReader reader = new java.io.StringReader(doc.asXML());
        org.xml.sax.InputSource source = new org.xml.sax.InputSource(reader);
        javax.xml.parsers.DocumentBuilderFactory documentBuilderFactory =
                javax.xml.parsers.DocumentBuilderFactory.newInstance();
        javax.xml.parsers.DocumentBuilder documentBuilder = documentBuilderFactory.
                newDocumentBuilder();
        return (documentBuilder.parse(source));
    }

    protected  static void generate(org.w3c.dom.Document document) {
        List<String> warnings = new ArrayList<String>();
        Set<String> fullyqualifiedTables = new HashSet<String>();
        Set<String> contexts = new HashSet<String>();
        try {

            MyConfigurationParser myConfigurationParser = new MyConfigurationParser(warnings);
            Configuration config = myConfigurationParser.parseConfiguration(document);

            DefaultShellCallback shellCallback = new DefaultShellCallback(false);

            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, shellCallback, warnings);

            ProgressCallback progressCallback = new VerboseProgressCallback();

            myBatisGenerator.generate(progressCallback, contexts, fullyqualifiedTables);

        } catch (IOException | XMLParserException | InterruptedException | InvalidConfigurationException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected  static void generate(InputStream inputStream) {
        List<String> warnings = new ArrayList<String>();
        Set<String> fullyqualifiedTables = new HashSet<String>();
        Set<String> contexts = new HashSet<String>();
        try {
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(inputStream);

            DefaultShellCallback shellCallback = new DefaultShellCallback(false);

            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, shellCallback, warnings);

            ProgressCallback progressCallback = new VerboseProgressCallback();

            myBatisGenerator.generate(progressCallback, contexts, fullyqualifiedTables);

        } catch (IOException | XMLParserException | InterruptedException | InvalidConfigurationException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected static JSONObject toJSONObject(String body) {
        return JSON.parseObject(body);
    }


    protected  static void createTableElement(GeneratorConfig generatorConfig, Element context) {
        /**
         * <!-- 要生成那些表(更改tableName和domainObjectName就可以) -->
         <table tableName="student" domainObjectName="Student" enableCountByExample="false" enableUpdateByExample="false" enableDeleteByExample="false" enableSelectByExample="false" selectByExampleQueryId="false" />
         */
        String tableName = generatorConfig.getTableName();
        String tableColumn = generatorConfig.getTableColumn();

        JSONObject jsonObject = toJSONObject(tableColumn);
        Set<Map.Entry<String, Object>> set = jsonObject.entrySet();

        Map<String, List<String>> map = new HashMap();

        for(Map.Entry<String, Object> entry : set) {
            String key = entry.getKey();
            String[] keys = key.split("_");
            Boolean value = (Boolean)entry.getValue();
            if (value) {

                List<String> list = map.get(keys[0].trim().toLowerCase());
                if (CollectionUtils.isEmpty(list)) {
                    list = new ArrayList<>();
                }
                list.add(key.substring(key.indexOf("_") + 1));
                map.put(keys[0].trim().toLowerCase(), list);

            }
        }

        String tableConfigValue = generatorConfig.getTableConfigValue();
        JSONObject jsonObject1 = ClassUtils.toJSONObject(tableConfigValue);
        if (StringUtils.isNotEmpty(tableName)) {
            String [] tableNames = tableName.split(",");
            for (String str : tableNames) {
                Element table = context.addElement("table");
                table.addAttribute("tableName", str.trim());
                table.addAttribute("domainObjectName", str.trim().substring(0, 1).toUpperCase() + str.trim().substring(1));

                String tableConfigValueOne = jsonObject1.getString(str.trim());

                if (StringUtils.isNotEmpty(tableConfigValueOne)) {

                    TableConfig tableConfig = ClassUtils.toJavaObject(tableConfigValueOne, TableConfig.class);
                    if (null != tableConfig) {
                        addAttributeToElement(table, tableConfig);
                    }
                }

                List<String> list = map.get(str.trim().toLowerCase());
                if (CollectionUtils.isNotEmpty(list)) {
                    /**
                     * <ignoreColumn> 元素
                     */
                    for (String column : list) {
                        Element ignoreColumn = table.addElement("ignoreColumn");
                        ignoreColumn.addAttribute("column", column);
                    }
                }

            }
        }

    }

    public static void generatorProperty(Element element ) {
        element.addAttribute("name", "enableSubPackages");
        element.addAttribute("value", "true");
    }

    /**
     * 新增属性到指定元素中
     * @param table
     * @param t
     * @param <T>
     */
    private static <T>  void addAttributeToElement(Element table, T t) {
        Class<?> clazz = t.getClass();
        //Method[] methods = clazz.getDeclaredMethods();

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            Need need = field.getAnnotation(Need.class);
            if (null != need && Boolean.valueOf(need.value())) {
                try {
                    String name = field.getName();

                    String prop = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);

                    Method method = clazz.getMethod(prop);
                    method.setAccessible(true);
                    Object object = method.invoke(t);

                    if (null != object) {
                        table.addAttribute(name, object + "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

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
