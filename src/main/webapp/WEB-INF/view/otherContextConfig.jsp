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
        <div class="panel-heading">commentGenerator</div>
        <div class="panel-body">
            <form id="commentGenerator" class="form-horizontal">
                <div class="form-group col-lg-12">
                    <label class="col-lg-4 control-label">suppressAllComments</label>
                    <div class="col-lg-5">
                        <select class="form-control" id="suppressAllComments" name="suppressAllComments">
                            <option value="true">true</option>
                            <option value="false">false</option>
                        </select>
                    </div>
                </div>

                <div class="form-group col-lg-12">
                    <label class="col-lg-4 control-label">suppressDate</label>
                    <div class="col-lg-5">
                        <select class="form-control" id="suppressDate" name="suppressDate">
                            <option value="true">true</option>
                            <option value="false">false</option>
                        </select>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">javaTypeResolver</div>
        <div class="panel-body">
            <form id="javaTypeResolver" class="form-horizontal">
                <div class="form-group col-lg-12">
                    <label class="col-lg-4 control-label">forceBigDecimals</label>
                    <div class="col-lg-5">
                        <select class="form-control" id="forceBigDecimals" name="forceBigDecimals">
                            <option value="true">true</option>
                            <option value="false">false</option>
                        </select>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>

<script>



</script>



</html>
