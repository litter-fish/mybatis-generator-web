package com.fish.controller;

import com.fish.bean.Database;
import com.fish.bean.GeneratorConfiguration;
import com.fish.service.IDatabaseService;
import com.fish.utils.ClassUtils;
import com.fish.utils.GeneratorXmlFile;
import com.fish.utils.ParseRequestParameter;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by fish on 2016/12/11.
 */
@RequestMapping("/base")
@Controller
public class BaseController {

    @Resource
    private IDatabaseService databaseService;


    @RequestMapping("/login")
    public String login(HttpServletRequest request, @RequestBody String body) {

        ModelAndView view = new ModelAndView("main");

        Database database = ClassUtils.toJavaObject(body, Database.class);

        String url = "jdbc:mysql://" + database.getIp() + "/test?characterEncoding=utf8";

        // jdbc:mysql://localhost:3306/test?characterEncoding=utf8
        database.setConnectionURL(url);

        try {

            List<String> list = databaseService.getAllDatabase(database);
            request.getSession().setAttribute("allDataBase", list);
            request.getSession().setAttribute("databaseType", database.getDatabaseType());
            request.getSession().setAttribute("userId", database.getUserId());
            request.getSession().setAttribute("password", database.getPassword());
            request.getSession().setAttribute("connectionURL", url);
            request.getSession().setAttribute("ip", database.getIp());
            request.getSession().setAttribute("port", database.getPort());
            request.getSession().setAttribute("driverClass", "com.mysql.jdbc.Driver");
        } catch (Exception w) {
            w.printStackTrace();
        }


        return "/main";
    }

    @RequestMapping({"/main","/"})
    public String main(){
        return "/main";
    }

    @RequestMapping({"/tableConfig"})
    public String tableConfig(){
        return "/tableConfig";
    }


    @RequestMapping({"/otherContextConfig"})
    public String otherContextConfig(){
        return "/otherContextConfig";
    }


    @ResponseBody
    @RequestMapping("/getDatabase")
    public String getDatabase(HttpServletRequest request, @RequestBody String body) {

        Database database = new Database();

        HttpSession session = request.getSession();
        String userId = (String)session.getAttribute("userId");
        String password = (String)session.getAttribute("password");
        String ip = (String)session.getAttribute("ip");
        String port = (String)session.getAttribute("port");
        database.setConnectionURL("jdbc:mysql://" + ip + ":" + port + "/" + body.substring(1, body.length() - 1) + "?characterEncoding=utf8");
        database.setUserId(userId);
        database.setPassword(password);
        database.setTableSchema(body.substring(1, body.length() - 1));

        request.getSession().setAttribute("TABLE_SCHEMA", body.substring(1, body.length() - 1));

        StringBuilder buf = new StringBuilder("{" +
                "  \"code\" : 200, " +
                "  \"data\" : [");
        try {
            List<String> list = databaseService.getAllTableName(database);
            if (CollectionUtils.isNotEmpty(list)) {
                for (String str : list) {
                    buf.append("{" +
                            "\"table\" : \"" + str +" \"},");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        buf.delete(buf.length() - 1, buf.length()).append("]}");



        return buf.toString();

    }


    @ResponseBody
    @RequestMapping("/getTableInfo")
    public String getTableInfo(HttpServletRequest request, @RequestBody String body) {

        Database database = new Database();

        HttpSession session = request.getSession();
        String userId = (String)session.getAttribute("userId");
        String password = (String)session.getAttribute("password");
        String ip = (String)session.getAttribute("ip");
        String port = (String)session.getAttribute("port");
        String tableSchema = (String)session.getAttribute("TABLE_SCHEMA");



        database.setConnectionURL("jdbc:mysql://" + ip + ":" + port + "/" + tableSchema + "?characterEncoding=utf8");
        database.setUserId(userId);
        database.setPassword(password);
        database.setTableName(body.substring(1, body.length() - 1));


        //Database database = toJavaObject(body, Database.class);
        StringBuilder buf = new StringBuilder("{" +
                "  \"code\" : 200, " +
                "  \"data\" : [");

        try {

            List<String> colums = databaseService.getTableColumn(database);
            System.out.print(colums);

            for (String str : colums) {
                buf.append("{" +
                        "\"column\" : \"" + str +" \"},");
            }

        } catch (Exception e) {

        }

        buf.delete(buf.length() - 1, buf.length()).append("]}");

        return buf.toString();
    }

    @ResponseBody
    @RequestMapping("/generatorConfig")
    public String generatorConfig(HttpServletRequest request, @RequestBody String body) {


        GeneratorConfiguration generatorConfiguration = new GeneratorConfiguration();
        try {
            ParseRequestParameter.jsonToObject(body, generatorConfiguration);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        GeneratorXmlFile.generatorXmlFiles(generatorConfiguration);




        /*Database database = getDatabase(request);

        GeneratorConfig generatorConfig = ClassUtils.toJavaObject(body, GeneratorConfig.class);
        generatorConfig.setDatabase(database);

        JSONObject jsonObject = ClassUtils.toJSONObject(body);
        String tableColumn = jsonObject.getString("tableColumn");
        String tableName = jsonObject.getString("tableName");
        String tableConfigValue = jsonObject.getString("tableConfigValue");
        generatorConfig.setTableColumn(tableColumn);
        generatorConfig.setTableConfigValue(tableConfigValue);
        generatorConfig.setTableName(tableName);

        GeneratorConfigUtils.generatorXmlFile(generatorConfig);*/

        return "";

    }


    protected  Database getDatabase(HttpServletRequest request) {
        Database database = new Database();

        HttpSession session = request.getSession();
        String userId = (String)session.getAttribute("userId");
        String password = (String)session.getAttribute("password");
        String ip = (String)session.getAttribute("ip");
        String port = (String)session.getAttribute("port");
        String tableSchema = (String)session.getAttribute("TABLE_SCHEMA");
        String databaseType = (String)session.getAttribute("databaseType");

        database.setConnectionURL("jdbc:mysql://" + ip + ":" + port + "/" + tableSchema + "?characterEncoding=utf8");
        database.setUserId(userId);
        database.setPassword(password);
        database.setTableSchema(tableSchema);
        database.setPort(port);
        database.setIp(ip);
        database.setDatabaseType(databaseType);
        database.setDriverClass("com.mysql.jdbc.Driver");

        return database;
    }


}
