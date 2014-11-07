<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="common.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>后台管理</title>

	<link href="/npc/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">


	<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
      <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
      <!--[if lt IE 9]>
         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->
      
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="row">
			<div class="navbar">
				<div class="navbar-inner">
					<div class="container-fluid">
						 <a data-target=".navbar-responsive-collapse" data-toggle="collapse" class="btn btn-navbar"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></a> <a href="#" class="brand">后台管理</a>
						<div class="nav-collapse collapse navbar-responsive-collapse">
							<ul class="nav">
								<li class="dropdown open">
								</li>
								
								<ul class="dropdown-menu">
								</ul>
							</ul>
							<ul class="nav pull-right">
								<li>
									<a href="javascript:void(0);">请登录</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
				</div>
				<div class="col-md-4">
					<form class="form-horizontal" method="post">
						<div class="control-group">
							<label class="control-label" for="userName">用户名</label>
							<div class="controls">
								<input id="userName" name="userName" type="text" autocomplete="off" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="password">密码</label>
							<div class="controls">
								<input id="password" name="password" type="password" autocomplete="off" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="verifyCode">验证码</label>
							<div class="controls">
								<input id="verifyCode" name="verifyCode" type="text" />
								<img id="imgcode" title="点击刷新" style="cursor:pointer;" src="getcode.html">
							</div>
						</div>
						
						<c:if test="${!empty errorMessage }">				
						<div class="alert alert-warning" role="alert">${errorMessage }</div>
						</c:if>
						
						<div class="control-group">
							<div class="controls">
								<button class="btn" type="submit">登录</button>
							</div>
						</div>
					</form>
				</div>
				<div class="col-md-4">
				</div>
			</div>
		</div>
	</div>
</div>

	<script type="text/javascript" src="/npc/js/vendor/jquery.min.js"></script>
	<script type="text/javascript" src="/npc/static/bootstrap/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		$("#imgcode").click(function(){
			$("#imgcode").attr("src", $("#imgcode").attr("src"));
		});
	</script>
</body>
</html>