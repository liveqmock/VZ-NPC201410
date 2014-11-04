<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理</title>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="navbar">
				<div class="navbar-inner">
					<div class="container-fluid">
						 <a data-target=".navbar-responsive-collapse" data-toggle="collapse" class="btn btn-navbar"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></a> <a href="#" class="brand">网站名</a>
						<div class="nav-collapse collapse navbar-responsive-collapse">
							<ul class="nav">
								<li class="dropdown open">
								</li>
								
								<ul class="dropdown-menu">
								</ul>
							</ul>
							<ul class="nav pull-right">
								<li>
									<a href="#">请登录</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span4">
				</div>
				<div class="span4">
					<form class="form-horizontal">
						<div class="control-group">
							<label class="control-label" for="inputEmail">用户名</label>
							<div class="controls">
								<input id="inputEmail" type="text" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputPassword">密码</label>
							<div class="controls">
								<input id="inputPassword" type="password" />
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button class="btn" type="submit">登陆</button>
							</div>
						</div>
					</form>
				</div>
				<div class="span4">
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>