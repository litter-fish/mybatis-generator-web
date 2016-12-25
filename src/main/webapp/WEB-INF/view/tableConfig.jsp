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
    <div class="panel-body">
        <form id="tableConfig" class="form-horizontal">

            <div class="form-group">
                <label class="col-sm-7 control-label">enableInsert</label>
                <div class="col-sm-5">
                    <select class="form-control" id="enableInsert" name="enableInsert">
                        <option value="true">true</option>
                        <option value="false">false</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-7 control-label">enableSelectByPrimaryKey</label>
                <div class="col-sm-5">
                    <select class="form-control" id="enableSelectByPrimaryKey" name="enableSelectByPrimaryKey">
                        <option value="true">true</option>
                        <option value="false">false</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-7 control-label">enableUpdateByPrimaryKey</label>
                <div class="col-sm-5">
                    <select class="form-control" id="enableUpdateByPrimaryKey" name="enableUpdateByPrimaryKey">
                        <option value="true">true</option>
                        <option value="false">false</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-7 control-label">enableDeleteByPrimaryKey</label>
                <div class="col-sm-5">
                    <select class="form-control" id="enableDeleteByPrimaryKey" name="enableDeleteByPrimaryKey">
                        <option value="true">true</option>
                        <option value="false">false</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-7 control-label">enableSelectByExample</label>
                <div class="col-sm-5">
                    <select class="form-control" id="enableSelectByExample" name="enableSelectByExample">
                        <option value="false">false</option>
                        <option value="true">true</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-7 control-label">enableDeleteByExample</label>
                <div class="col-sm-5">
                    <select class="form-control" id="enableDeleteByExample" name="enableDeleteByExample">
                        <option value="false">false</option>
                        <option value="true">true</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-7 control-label">enableCountByExample</label>
                <div class="col-sm-5">
                    <select class="form-control" id="enableCountByExample" name="enableCountByExample">
                        <option value="false">false</option>
                        <option value="true">true</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-7 control-label">enableUpdateByExample</label>
                <div class="col-sm-5">
                    <select class="form-control" id="enableUpdateByExample" name="pdateByExample">
                        <option value="false">false</option>
                        <option value="true">true</option>
                    </select>
                </div>
            </div>



           <%-- <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" class="btn btn-default" id="btnQuery">查询</button>
                </div>
            </div>--%>
        </form>
    </div>
</div>

</body>

<script>



</script>



</html>
