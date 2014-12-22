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
            padding-top: 30px;
            padding-bottom: 30px;
            background: url(${context }/image/login_bg.jpg) no-repeat center top;
            background-size: 100%;
        }

        .form-signin {
            width: 665px;
            height: 390px;
            padding: 15px;
            margin: 0 auto;
            background: #989fa5 url(${context }/image/index_main5.jpg) no-repeat center top;
            background-size: 665px 370px;
            border-radius: 5px;
            -webkit-border-radius: 5px;
            border: 10px solid rgba(255, 255, 255, 0.5);
            -webkit-background-clip: padding-box;
            background-clip: padding-box;
        }

        .form-signin .login-group {
            margin: 295px 10px 10px 0;
            background: #333;
            padding: 10px;
            width: 620px;
            height: 50px;
            border-radius: 5px;
            -webkit-border-radius: 5px;
            filter: alpha(opacity=90);
            -moz-opacity: 0.9;
            opacity: 0.9;
        }

        .form-signin #imgcode {
            width: 70px;
        }

        .form-signin input {
            float: left;
            width: 140px;
            margin-right: 10px;
        }

        .form-signin input#verifyCode {
            width: 110px;
        }

        .form-signin .btn-primary {
            height: 30px;
            padding: 4px 12px;
        }
    </style>
</head>
<body>
<div class="container">
    <form class="form-signin form-horizontal" role="form" method="post">
        <c:if test="${!empty errorMessage }">
            <div class="alert alert-warning" role="alert">${errorMessage }</div>
        </c:if>
        <div class="login-group">
            <div class="row">
                <div class="col-sm-10">
                    <input id="userName" name="userName" type="text" autocomplete="off" class="form-control input-sm"
                           placeholder="请填写用户名"/>
                    <input id="password" name="password" type="password" autocomplete="off"
                           class="form-control input-sm"
                           placeholder="请填写密码"/>
                    <input id="verifyCode" name="verifyCode" type="text" autocomplete="off"
                           class="form-control input-sm"
                           placeholder="请输入验证码"/>
                    <img id="imgcode" title="点击刷新" style="cursor:pointer;" src="getcode.html">
                </div>
                <div class="col-sm-2">
                    <button class="btn btn-primary btn-block" type="submit">登录</button>
                </div>
            </div>
        </div>

        <!--<div class="form-group">-->
        <!--<div class="col-sm-9 col-md-offset-2 bg-warning">-->
        <!--<br/>-->
        <!--<ul>-->
        <!--<li>测试版用户</li>-->
        <!--<li>管理员：admin/123456</li>-->
        <!--<li>编辑员：editor/12345678</li>-->
        <!--<li>审核员：auditor/123456789</li>-->
        <!--</ul>-->
        <!--<br/>-->
        <!--</div>-->
        <!--</div>-->
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