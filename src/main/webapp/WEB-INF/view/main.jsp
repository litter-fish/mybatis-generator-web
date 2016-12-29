<%--
  Created by IntelliJ IDEA.
  User: fish
  Date: 2016/12/11
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<!-- 引入css样式 -->
<link href="/generator-web/statics/css/default.css" rel="stylesheet" type="text/css"/>
<link href="/generator-web/statics/css/bootstrap/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="/generator-web/statics/css/bootstrap/bootstrap-theme.css" rel="stylesheet" type="text/css"/>

<!-- 引入js -->
<script type="text/javascript" src="/generator-web/statics/js/jquery.js"></script>
<script type="text/javascript" src="/generator-web/statics/js/jquery.cookie.js"></script>
<script type="text/javascript" src="/generator-web/statics/js/layui.js"></script>
<script type="text/javascript" src="/generator-web/statics/plugins/layer/layer.js"></script>
<script type="text/javascript" src="/generator-web/statics/plugins/my97datepicker/WdatePicker.js"></script>
<head>
    <title>Title</title>
    <style>
        body {
            padding-top: 1px;
            border:1px solid black;
        }

        .mygrid-wrapper-div {
            overflow: scroll;
        }

    </style>
</head>
<body>
<div class="container mygrid-wrapper-div" style="width: 100%;height: 100%;">
    <nav class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">数据库</a>
            </div>
            <div>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            mysql <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu" id="getAllTable">
                            <c:forEach items="${allDataBase}" var="database">
                                <li><a href="#" tableName="${database}">${database}</a></li>
                            </c:forEach>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="row">
        <div class="panel panel-default">
            <div class="panel-heading">表配置</div>
            <form class="form-horizontal" role="form" id="GeneratorConfig">
                <div class="row form-inline form-group">
                    <label for="location" class="control-label col-lg-2">connectorJarPath</label>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" style="width: 100%;" id="location" name="location" placeholder="location" value="D:\data\generator\mysql-connector-java-5.1.34.jar">
                    </div>
                </div>
                <div class="row form-inline form-group">
                    <label for="projectFolder" class="col-lg-2 control-label">projectFolder</label>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" style="width: 100%;" id="projectFolder" name="projectFolder" placeholder="projectFolder" value="D:/data/generator/">
                    </div>
                </div>
                <div class="row form-inline form-group">
                    <label for="modelPackage" class="col-lg-2 control-label">Model Package</label>
                    <div class="col-lg-2">
                        <input type="text" class="form-control" id="modelPackage" name="modelPackage" placeholder="modelPackage" value="com.fish.model">
                    </div>
                    <label for="modelPackageTargetFolder" class="col-lg-2 control-label">Tager Folder</label>
                    <div class="col-lg-2">
                        <input type="text" class="form-control" id="modelPackageTargetFolder" name="modelPackageTargetFolder" placeholder="src/main/java" value="src/main/java">
                    </div>
                </div>
                <div class="form-group form-inline row">
                    <label for="mappingXMLPackage" class="col-lg-2 control-label">Mapping XML Package</label>
                    <div class="col-lg-2">
                        <input type="text" class="form-control" id="mappingXMLPackage" name="mappingXMLPackage" placeholder="targetPackage" value="com.fish.mapping">
                    </div>
                    <label for="mappingXMLTargetFolder" class="col-lg-2 control-label">Tager Folder</label>
                    <div class="col-lg-2">
                        <input type="text" class="form-control" id="mappingXMLTargetFolder" name="mappingXMLTargetFolder" placeholder="src/main/resources" value="src/main/resources">
                    </div>
                </div>
                <div class="form-group form-inline row">
                    <div class="form-group col-sm-4">
                        <label for="daoPackage" class="col-sm-4 control-label">DAO Package</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="daoPackage" name="daoPackage" placeholder="targetPackage" value="com.fish.dao">
                        </div>
                    </div>
                    <div class="form-group col-sm-4">
                        <label for="daoTargetFolder" class="col-sm-4 control-label">Tager Folder</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="daoTargetFolder" name="daoTargetFolder" placeholder="src/main/java" value="src/main/java">
                        </div>
                    </div>
                    <div class="form-group col-sm-4">
                        <label for="javaClientGenerator" class="col-sm-4 control-label">type</label>
                        <div class="col-sm-4">
                            <select class="form-control" id="javaClientGenerator" name="type">
                                <option value="XMLMAPPER">XMLMAPPER</option>
                                <option value="ANNOTATEDMAPPER">ANNOTATEDMAPPER</option>
                                <option value="MIXEDMAPPER">MIXEDMAPPER</option>
                                <option value="SPRING">SPRING</option>
                                <option value="GENERIC-SI">GENERIC-SI5</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="hidden" id="hiddenDiv">
                    <input type="hidden" id="tableName" name="tableName">
                    <%-- <input type="hidden" id="domainObjectName" name="tableConfig.domainObjectName">
                     <input type="hidden" id="tableColumn" name="tableConfig.tableColumn">--%>

                    <input type="hidden" id="userId" name="userId" value="${userId}">
                    <input type="hidden" id="driverClass" name="driverClass" value="${driverClass}">
                    <input type="hidden" id="connectionURL" name="connectionURL" value="${connectionURL}">
                    <input type="hidden" id="password" name="password" value="${password}">

                    <input type="hidden" id="id" name="id" value="test">
                    <input type="hidden" id="defaultModelType" name="defaultModelType" value="conditional">
                    <input type="hidden" id="targetRuntime" name="targetRuntime" value="MyBatis3">

                    <input type="hidden" id="commentGeneratorCom" name="commentGeneratorCom">
                    <input type="hidden" id="javaTypeResolverCom" name="javaTypeResolverCom">
                    <input type="hidden" id="sqlMapGenerator_property" name="sqlMapGenerator.property" value="${password}">
                    <input type="hidden" id="javaClientGenerator_property" name="javaClientGenerator.property" value="${password}">
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-9">
                        <button type="button" class="btn btn-default" id="btnOtherConfig">OtherConfig</button>
                        <button type="button" class="btn btn-default" id="btnQuery">Generator</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">表配置</div>
                    <!-- Table -->
                    <table class="table table-hover">
                        <thead>
                            <th>ignoreTable</th>
                            <th>tableName</th>
                            <th>config</th>
                        </thead>
                        <tbody id="content">

                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">字段配置</div>
                    <!-- Table -->
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <td>ignoreColumn</td>
                                <td>表名称</td>
                                <td>操作</td>
                            </tr>
                        </thead>
                        <tbody id="content2">

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

<script>

    var contextConfigArr = new Array();
    var contextConfig = {};

    $(document).on('click', '#getAllTable >li >a', function() {
        layer.load(1,{
            shade: [0.1,'#fff'] //0.1透明度的白色背景
        });
        
        var tableName = $(this).attr("tableName");
        $("#tableName").val(tableName);
        ajaxBody("/generator-web/base/getDatabase", tableName, 0, queryAllTable);
    });

    //保存
    $(document).on('click', '#btnQuery', function() {
        var database = {};
        database["projectFolder"] = $("#projectFolder").val();
        var classPathEntry = {};
        var classPathEntryArr = new Array();
        classPathEntry["location"] = $("#location").val();
        classPathEntryArr.push(classPathEntry);

        database["classPathEntry"] = classPathEntryArr;

        contextConfig["id"] = $("#id").val();
        contextConfig["defaultModelType"] = $("#defaultModelType").val();
        contextConfig["targetRuntime"] = $("#targetRuntime").val();
        contextConfig["introspectedColumnImpl"] = $("#introspectedColumnImpl").val();

        var javaModelGenerator = {};
        javaModelGenerator["modelPackage"] = $("#modelPackage").val();
        javaModelGenerator["modelPackageTargetFolder"] = $("#projectFolder").val() + $("#modelPackageTargetFolder").val();
        var propertyGeneratorArrModel = new Array();
        var propertyGeneratorModel = {};
        propertyGeneratorModel["name"] = "enableSubPackages";
        propertyGeneratorModel["value"] = "true";
        propertyGeneratorArrModel.push(propertyGeneratorModel);
        javaModelGenerator["propertyGenerator"] = propertyGeneratorArrModel;
        contextConfig["javaModelGenerator"] = javaModelGenerator;


        var sqlMapGenerator = {};
        sqlMapGenerator["mappingXMLPackage"] = $("#mappingXMLPackage").val();
        sqlMapGenerator["mappingXMLTargetFolder"] = $("#projectFolder").val() + $("#mappingXMLTargetFolder").val();
        sqlMapGenerator["propertyGenerator"] = propertyGeneratorArrModel;
        contextConfig["sqlMapGenerator"] = sqlMapGenerator;



        var javaClientGenerator = {};
        javaClientGenerator["type"] = $("#javaClientGenerator").val();
        javaClientGenerator["daoPackage"] = $("#daoPackage").val();
        javaClientGenerator["daoTargetFolder"] = $("#projectFolder").val() + $("#daoTargetFolder").val();
        javaClientGenerator["propertyGenerator"] = propertyGeneratorArrModel;
        contextConfig["javaClientGenerator"] = javaClientGenerator;

        var jdbcConnection = {};
        jdbcConnection["userId"] = $("#userId").val();
        jdbcConnection["driverClass"] = $("#driverClass").val();
        jdbcConnection["connectionURL"] = "jdbc:mysql://${ip}/" + $("#tableName").val() + "?characterEncoding=utf8";
        jdbcConnection["password"] = $("#password").val();
        contextConfig["jdbcConnection"] = jdbcConnection;


        contextConfigArr.push(contextConfig);

        database["contextConfig"] = contextConfigArr;


        ajaxBody("/generator-web/base/generatorConfig", database,0, function(resData) {
            layer.msg('文件生成成功', function(){
                layer.closeAll('loading');
                contextConfigArr = new Array();
                contextConfig = {};
                $("#content").empty();
                $("#content2").empty();
                $("<input type=text>").val("");
            });

        });
    });

    $(document).on('click', '#btnOtherConfig', function() {

        ajaxBody("/generator-web/base/otherContextConfig", null, null, function(resData) {
            var index = layer.open({
                type: 0,
                content: resData, //这里content是一个普通的String
                area: ['800px', '600px'],
                btn: ['yes', 'no'],
                yes : function () {
                    var commentGeneratorCom = {};
                    var javaTypeResolverCom = {};
                    var commentGeneratorArr = new Array();
                    $("#commentGenerator").find('select').each(function(){
                        var input = $(this),name = input.attr("name"),value = input.val();
                        if(isEmpty(input.val())){
                            return true;
                        }

                        var propertyGeneratorComment = {};
                        propertyGeneratorComment["name"] = name;
                        propertyGeneratorComment["value"] = value;
                        commentGeneratorArr.push(propertyGeneratorComment);
                    });
                    commentGeneratorCom["propertyGenerator"] = commentGeneratorArr;
                    contextConfig["commentGenerator"] = commentGeneratorCom;

                    var javaTypeResolverArr = new Array();

                    $("#javaTypeResolver").find('select').each(function(){
                        var input = $(this),name= input.attr("name"),value = input.val();
                        if(isEmpty(input.val())){
                            return true;
                        }
                        var javaTypeResolverComment = {};
                        javaTypeResolverComment["name"] = name;
                        javaTypeResolverComment["value"] = value;
                        javaTypeResolverArr.push(javaTypeResolverComment);
                    });
                    javaTypeResolverCom["propertyGenerator"] = javaTypeResolverArr;
                    contextConfig["javaTypeResolver"] = javaTypeResolverCom;

                    layer.closeAll();
                }
            });
        });
    });

    $(document).on('click', '.tableConfig', function() {

        var tableName = $(this).attr("name");
        var number = $(this).attr("number");
        var tableValue = tableArr[number];
        ajaxBody("/generator-web/base/tableConfig", null, number, function (data) {
            layer.open({
                type: 0,
                content: data, //这里content是一个普通的String
                area: ['600px', '600px'],
                btn: ['yes', 'no'],
                yes : function (index, layero) {
                    var postData = {};

                    $("#tableConfig").find('select').each(function(){
                        var input = $(this),name= input.attr("name"),value = input.val();
                        if(isEmpty(input.val())){
                            return true;
                        }
                        tableValue[name] = value;
                    });
                    tableArr[number] = tableValue;
                    //$("#tableConfigValue").val(JSON.stringify(array));
                    layer.close(index);
                }
            });
        });


    });


    function ajaxPostBody(url, data, succ){
        layer.load(1,{
            shade: [0.1,'#fff'] //0.1透明度的白色背景
        });
        ajaxBody(url, data, 0, queryAllTable);

    };

    var isEmpty = function(obj){
        return null === obj || (typeof obj === 'undefined') || '' === obj;
    }

    var tableConfig = {};
    var tableArr = new Array();
    function queryAllTable(data, number, reqParam) {
        $("#content").empty();
        layer.closeAll('loading');
        var obj = eval("("+data+")");

        $.each(obj.data, function(i, item) {
            var tableValue = {};
            var content = "<tr tableName=" + item.table + " number=" + i + "><td><div class='checkbox'><label><input type='checkbox' tableName=" + item.table + " number=" + i + "></label></div></td>";
            content += "<td width='200px'>" + item.table + "</td>";

            content += "<td  width='200px'><button type=\"button\" class=\"btn btn-default tableConfig\" name=" + item.table + " number=" + i + ">config</button></td></tr>";

            $("#content").append(content);

            var table = {};
            table["tableName"] = $.trim(item.table);
            table["domainObjectName"] = $.trim(item.table);
            tableArr.push(table);
        });
        contextConfig["tableConfig"] = tableArr;

        $("#content > tr").click(function () {
            var tableName = $(this).attr("tableName");
            var number = $(this).attr("number");
            /*var database = getPostData(_formDomId);
            database["tableName"] = tableName;*/
            ajaxBody("/generator-web/base/getTableInfo", tableName, number, queryTableInfo);
        });

        $("#content input[type=checkbox]").change(function() {
            var tableName = $(this).attr("tableName");
            var number = $(this).attr("number");
            var tableValue = tableArr[number];
            var ignoreTable = false;


            if ($(this).is(":checked")) {
                ignoreTable = true;
            } else {
                ignoreTable = false;
            }
            tableValue["ignoreTable"] = ignoreTable;
            tableArr[number] = tableValue;
            //$("#tableName").val(JSON.stringify(tableValue));
        });

    }

    function queryTableInfo(data, number, reqParam) {
        var ignoreColumn = new Array();
        var tableValue = tableArr[number];
        var columnMapTable = {};
        $("#content2").empty();
        layer.closeAll('loading');
        var obj = eval( "(" + data + ")" );//转换后的JSON对象
        /*$("#content").refresh();
         $("#content").append("<tr><td>表名称</td><td>操作</td></tr>");*/
        $.each(obj.data, function(i, item) {
            var content = "<tr tableName=" + reqParam + "><td><div class='checkbox'><label><input type='checkbox' number=" + i + " column=" + item.column + "></label></div></td>";
            content += "<td>" + item.column + "</td><td>配置</td></tr>";

            $("#content2").append(content);

            //columnMapTable[item.column] = false;
        });

        $("#content2 input[type=checkbox]").change(function() {
            var column = $(this).attr("column");


            if ($(this).is(":checked")) {
                columnMapTable["column"] = column;
                columnMapTable["delimitedColumnName"] = false;
                ignoreColumn.push(columnMapTable);
            } else {
                //ignoreColumn.
                //columnMapTable[column] = false;
            }
            tableValue["ignoreColumn"] = ignoreColumn;
            tableArr[number] = tableValue;
            //table[number] = tableValue;
            //alert(JSON.stringify(table));
            /*$("#tableColumn").val(JSON.stringify(columnMapTable));*/
        });
    }


    function ajaxBody(url, reqParam, number, callback) {
        layer.load(1,{
            shade: [0.1,'#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url:url,
            type:"post",
            data: JSON.stringify(reqParam),
            contentType:"application/json",
            async: true,
            success:function(data){
                callback(data, number, reqParam);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                layer.closeAll('loading');
                console.log("请求异常状态:" + XMLHttpRequest.status);
                console.log(XMLHttpRequest);
                alert('请求服务器异常:' + XMLHttpRequest.status)
            }
        });
    }


</script>



</html>
