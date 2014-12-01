<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>

<%@ include file="common.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>人大60周年纪念展--后台管理</title>
    <link href="${context }/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${context }/js/vendor/html5.min.js"></script>
    <script src="${context }/js/vendor/respond.min.js"></script>
    <![endif]-->
    <style>
        body {
            padding-top: 70px;
            padding-bottom: 30px;
        }

        .form-signin {
            max-width: 530px;
            padding: 15px;
            margin: 0 auto;
        }

        .form-signin .form-control {
            position: relative;
            height: auto;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            padding: 10px;
            font-size: 16px;
        }

        .form-signin .form-control:focus {
            z-index: 2;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">人大60周年展--后台管理</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="javascript:void(0);">请登录</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <form class="form-signin form-horizontal" role="form" method="post">
		<div class="form-group">
            <p class="text-center"><img src="image/unity/zi.png" /></p>
        </div>
        <c:if test="${!empty errorMessage }">
            <div class="alert alert-warning" role="alert">${errorMessage }</div>
        </c:if>
        <div class="form-group">
            <label class="col-sm-2 control-label" for="userName">用户名</label>

            <div class="col-sm-10">
                <input id="userName" name="userName" type="text" autocomplete="off" class="form-control"
                       placeholder="请填写用户名"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label" for="password">密码</label>

            <div class="col-sm-10">
                <input id="password" name="password" type="password" autocomplete="off" class="form-control"
                       placeholder="请填写密码"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label" for="verifyCode">验证码</label>

            <div class="col-sm-5">
                <input id="verifyCode" name="verifyCode" type="text" autocomplete="off" class="form-control"
                       placeholder="请输入右侧验证码"/>
            </div>
            <div class="col-sm-5">
                <img id="imgcode" title="点击刷新" style="cursor:pointer;" src="getcode.html">
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-9 col-md-offset-2">
                <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-9 col-md-offset-2 bg-warning">
                <br/>
                <ul>
                    <li>测试版用户</li>
                    <li>管理员：admin/123456</li>
                    <li>编辑员：editor/12345678</li>
                    <li>审核员：auditor/123456789</li>
                </ul>
                <br/>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript" src="${context }/js/vendor/jquery.min.js"></script>
<script type="text/javascript" src="${context }/static/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript">
    $("#imgcode").click(function () {
        $("#imgcode").attr("src", $("#imgcode").attr("src"));
    });
</script>
</body>
</html>