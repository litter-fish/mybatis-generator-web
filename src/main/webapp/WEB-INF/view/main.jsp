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
                        <input type="text" class="form-control" style="width: 100%;" id="projectFolder" name="projectFolder" placeholder="projectFolder" value="D:/data/generator">
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
                            <select class="form-control" id="javaClientGenerator" name="javaClientGenerator">
                                <option value="XMLMAPPER">XMLMAPPER</option>
                                <option value="ANNOTATEDMAPPER">ANNOTATEDMAPPER</option>
                                <option value="MIXEDMAPPER">MIXEDMAPPER</option>
                                <option value="SPRING">SPRING</option>
                                <option value="GENERIC-SI">GENERIC-SI5</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="hidden">
                    <input type="hidden" id="tableName" name="tableName">
                    <input type="hidden" id="domainObjectName" name="domainObjectName">
                    <input type="hidden" id="tableColumn" name="tableColumn">
                    <input type="hidden" id="tableConfigValue" name="tableConfigValue">
                    <input type="hidden" id="otherConfig" name="otherConfig">
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
                    <table class="table-hover table-bordered table-responsive" width="600px;">
                        <thead>
                            <th width="200px">tableName</th>
                            <th width="200px">domainObjectName</th>
                            <th width="200px">config</th>
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

    $(document).on('click', '#getAllTable >li >a', function() {
        layer.load(1,{
            shade: [0.1,'#fff'] //0.1透明度的白色背景
        });
        var tableName = $(this).attr("tableName");
        /*var database = getPostData(_formDomId);
        database["tableName"] = tableName;*/
        ajaxBody("/generator-web/base/getDatabase", tableName, queryAllTable);
    });

    var _formDomId = "GeneratorConfig";
    //保存
    $(document).on('click', '#btnQuery', function() {

        var database = getPostData(_formDomId);
        database["tableColumn"] = columnMapTable;
        database["javaClientGenerator"] = $("#javaClientGenerator").val();
        console.log(database);
        ajaxPostBody("/generator-web/base/generatorConfig", database, function(resData) {

        });
    });

    $(document).on('click', '#btnOtherConfig', function() {

        ajaxBody("/generator-web/base/otherContextConfig", null, function(resData) {
            var index = layer.open({
                type: 0,
                content: resData, //这里content是一个普通的String
                area: ['800px', '600px'],
                btn: ['yes', 'no'],
                yes : function (index, layero) {
                    var otherConfig = {};
                    $("#commentGenerator").find('select').each(function(){
                        var input = $(this),name= input.attr("name"),value = input.val();
                        if(isEmpty(input.val())){
                            return true;
                        }
                        otherConfig["commentGenerator." + name] = value;
                    });

                    $("#javaTypeResolver").find('select').each(function(){
                        var input = $(this),name= input.attr("name"),value = input.val();
                        if(isEmpty(input.val())){
                            return true;
                        }
                        otherConfig["javaTypeResolver." + name] = value;
                    });



                    $("#otherConfig").val(JSON.stringify(otherConfig));
                    layer.closeAll();
                }
            });
        });
    });

    var tableConfig = {};
    $(document).on('click', '.tableConfig', function() {

        var tableName = $(this).attr("name");
        ajaxBody("/generator-web/base/tableConfig", null, function (data) {
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
                        postData[name] = value;
                    });
                    tableConfig[tableName] = postData;
                    $("#tableConfigValue").val(JSON.stringify(tableConfig));
                    layer.close(index);
                }
            });
        });


    });


    function ajaxPostBody(url, data, succ, err){
        layer.load(1,{
            shade: [0.1,'#fff'] //0.1透明度的白色背景
        });
        ajaxBody(url, data,queryAllTable);

    };


    /**
     * 获取表单数据
     */
    function getPostData(_formDomId){
        var postData = {};
        $("#"+_formDomId).find('input[type="hidden"],input[type="text"],input[type="radio"],input[type="checkbox"],textarea').each(function(){
            var input = $(this),name= input.attr("name"),value = input.val();
            //单选框
            if('radio' == input.attr("type")){
                var $radio = input.radio();
                if(!$radio.prop('checked')){
                    return true;
                }
            }
            //复选框
            else if('checkbox' == input.attr("type")){
                var $checkbox = input.checkbox();
                if(!$checkbox.prop('checked')){
                    return true;
                }
            }
            //输入框，隐藏域
            else{
                if(isEmpty(input.val())){
                    return true;
                }
            }
            postData[name] = value;
        });
        return postData;
    }

    var isEmpty = function(obj){
        return null === obj || (typeof obj === 'undefined') || '' === obj;
    }

    function queryAllTable(data, reqParam) {
        $("#content").empty();
        layer.closeAll('loading');
        var obj = eval( "(" + data + ")" );//转换后的JSON对象
        /*$("#content").refresh();
         $("#content").append("<tr><td>表名称</td><td>操作</td></tr>");*/
        var allTable = '';
        $.each(obj.data, function(i, item) {
            var content = "<tr tableName=" + item.table + "><td width='200px'>" + item.table + "</td><td  width='200px'>" + item.table + "</td>";

            content += "<td  width='200px'><button type=\"button\" class=\"btn btn-default tableConfig\" name=" + item.table + ">config</button></td></tr>";
            allTable = allTable + item.table + ',';

            $("#content").append(content);
        });
        $("#tableName").val(allTable);

        $("#content > tr").click(function () {
            var tableName = $(this).attr("tableName");
            /*var database = getPostData(_formDomId);
            database["tableName"] = tableName;*/
            ajaxBody("/generator-web/base/getTableInfo", tableName, queryTableInfo);
        });
    }

    var columnMapTable = {};

    function queryTableInfo(data, reqParam) {
        $("#content2").empty();
        layer.closeAll('loading');
        var obj = eval( "(" + data + ")" );//转换后的JSON对象
        /*$("#content").refresh();
         $("#content").append("<tr><td>表名称</td><td>操作</td></tr>");*/
        $.each(obj.data, function(i, item) {
            var content = "<tr tableName=" + item.column + "><td><div class='checkbox'><label><input type='checkbox' " +
                    "column=" + reqParam + "_" + item.column + "></label></div></td>";
            content += "<td>" + item.column + "</td><td>配置</td></tr>";
            $("#content2").append(content);
            columnMapTable[reqParam + "_" + item.column] = false;
        });

        $("input[type=checkbox]").change(function() {
            var column = $(this).attr("column");
            if ($(this).is(":checked")) {
                columnMapTable[column] = true;
            } else {
                columnMapTable[column] = false;
            }
            $("#tableColumn").val(JSON.stringify(columnMapTable));
        });
    }


    function ajaxBody(url, reqParam, callback) {
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
                callback(data, reqParam);
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
