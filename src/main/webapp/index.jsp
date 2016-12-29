<%--
  Created by IntelliJ IDEA.
  User: fish
  Date: 2016/12/11
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!-- 引入css样式 -->
<link href="/generator-web/statics/css/sui.min.css" rel="stylesheet" type="text/css"/>
<link href="/generator-web/statics/css/sui-append.min.css" rel="stylesheet" type="text/css"/>
<link href="/generator-web/statics/css/default.css" rel="stylesheet" type="text/css"/>
<link href="/generator-web/statics/css/bootstrap/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="/generator-web/statics/css/bootstrap/bootstrap-theme.css" rel="stylesheet" type="text/css"/>

<!-- 引入js -->
<script type="text/javascript" src="/generator-web/statics/js/jquery.js"></script>
<script type="text/javascript" src="/generator-web/statics/js/jquery.cookie.js"></script>
<script type="text/javascript" src="/generator-web/statics/js/sui.min.js"></script>
<script type="text/javascript" src="/generator-web/statics/plugins/layer/layer.js"></script>
<script type="text/javascript" src="/generator-web/statics/plugins/my97datepicker/WdatePicker.js"></script>
<head>
    <title>Title</title>
</head>
<body>

<div class="panel panel-default">
    <div  class="panel-heading">
        <h3 class="panel-title">数据库配置</h3>
    </div>
    <div class="panel-body">
        <form id="database" class="form-horizontal">

            <div class="form-group">
                <label class="col-sm-2 control-label">数据库类型</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="databaseType" name="databaseType" id="databaseType" value="mysql">
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">IP</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="ip" name="ip" id="ip" value="127.0.0.1">&nbsp;&nbsp;
                    <input type="text" class="form-control" placeholder="port" name="port" id="port" value="3306">
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">用户名</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="userId" name="userId" id="userId" value="root">
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">数据库密码</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="password" name="password" id="password" value="root">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">数据库</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="dataBaseName" name="dataBaseName" id="dataBaseName" value="root">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" class="btn btn-default" id="btnQuery">查询</button>
                </div>
            </div>
        </form>
    </div>
</div>

</body>

<script>

    function checkTr() {
        alert("ok");
    }

    var _formDomId = "database";
    //保存
    $(document).on('click', '#btnQuery', function() {

        var database = getPostData(_formDomId);
        console.log(database);
        ajaxPostBody("/generator-web/base/login", database, function(resData) {

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

    function queryAllTable(data) {
        layer.closeAll('loading');
        var obj = eval( "(" + data + ")" );//转换后的JSON对象
        /*$("#content").refresh();
         $("#content").append("<tr><td>表名称</td><td>操作</td></tr>");*/
        $.each(obj.data, function(i, item) {
            $("#content").append("<tr tableName=" + item.table + "><td>" + item.table + "</td><td><input type='text' name='" + item.table + "'></td></tr>");
        });

        $("#content > tr").click(function () {
            var tableName = $(this).attr("tableName");
            alert(tableName);
            var database = getPostData(_formDomId);
            database["tableName"] = tableName;
            ajaxBody("/generator-web/base/getTableInfo", database, queryTableInfo);
        });
    }

    function queryTableInfo(data) {
        layer.closeAll('loading');
        var obj = eval( "(" + data + ")" );//转换后的JSON对象
        /*$("#content").refresh();
         $("#content").append("<tr><td>表名称</td><td>操作</td></tr>");*/
        $.each(obj.data, function(i, item) {
            $("#content2").append("<tr tableName=" + item.column + "><td>" + item.column + "</td><td>配置</td></tr>");
        });
    }


    function ajaxBody(url, data, callback) {
        layer.load(1,{
            shade: [0.1,'#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url:url,
            type:"post",
            data: JSON.stringify(data),
            contentType:"application/json",
            async: true,
            success:function(data){
                layer.closeAll('loading');
                window.location.href="/generator-web/base/main";
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
