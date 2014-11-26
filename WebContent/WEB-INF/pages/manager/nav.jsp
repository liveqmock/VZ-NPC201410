<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ include file="../common.jsp" %>

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
            <a class="navbar-brand" href="#">后台管理</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="javascript:void(0);">您好，${user.realName }</a></li>
                <c:if test="${user.userType == 'manager'}">
                <li><a href="${context}/manager/users.html">用户管理</a></li>
                </c:if>
                <c:if test="${user.userType == 'editor'}">
                <li><a href="${context}/manager/content.html">内容管理</a></li>
                </c:if>
                <c:if test="${user.userType == 'auditor'}">
                <li><a href="${context}/manager/auditing.html">内容审核</a></li>
                </c:if>
                <li><a href="${context}/manager/stats.html">统计分析</a></li>
                <li><a href="${context}/logout.html">退出系统</a></li>
            </ul>
        </div>
    </div>
</nav>