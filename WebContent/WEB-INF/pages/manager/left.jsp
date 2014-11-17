<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>

<div class="col-md-2">
	<ul class="nav nav-pills nav-stacked">
		<c:if test="${user.userType == 'manager'}">
			<li class="active"><a href="users.html">用户管理</a></li>
		</c:if>
		<c:if test="${user.userType == 'editor'}">
			<li class="active"><a href="content.html">内容管理</a></li>
		</c:if>
		<c:if test="${user.userType == 'auditor'}">
			<li class="active"><a href="auditing.html">内容审核</a></li>
		</c:if>
		<li><a href="stats.html">信息统计</a></li>
	</ul>
</div>