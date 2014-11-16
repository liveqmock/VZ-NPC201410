<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common.jsp" %>    

<div class="row">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				 <a href="javascript:void(0);" class="brand">后台管理</a>
				 <a href="${context}/logout.html" class="nav pull-right">退出</a>
				 <a href="javascript:void(0);" class="nav pull-right" style="margin-right:15px;">${user.realName }</a>
			</div>
		</div>
	</div>
</div>