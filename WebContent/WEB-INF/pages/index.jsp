<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>人大60周年展</title>
    <link rel="stylesheet" href="css/style.css"/>
    <!--[if lt IE 9]>
    <link rel="stylesheet" href="css/ie.css"/>
    <![endif]-->
</head>
<body>
<header>
    <div>
        <div id="logo">
            <a href="index.do" title="回到首页"></a>
        </div>
        <nav>
            <ul>
                <li>
                    <input type="text"/>
                    <button>搜索</button>
                </li>
                <li><a href="#">届别</a>
                    <ul>
		<c:if test="${!empty congresses && fn:length(congresses) > 0}">
	            <c:forEach var="congress" items="${congresses}" varStatus="row">
	            	<c:if test="${congress.congressId > 0 }">
						<li><a href="congress/${congress.congressId }.do">${congress.congressTitle }</a></li>
		          	</c:if>
	            </c:forEach>
	    </c:if>
                    </ul>
                </li>
                <li><a href="javascript:void(0);">发现</a></li>
            </ul>
            <div class="clearfix"></div>
        </nav>
    </div>
</header>
<div id="container">
    <div id="main-content">
        <img src="image/temp01.jpg" alt=""/>
    </div>
    <div id="session-nav">
        <ul>
			<c:if test="${!empty congresses && fn:length(congresses) > 0}">
				<c:set var="rowIndex" value="1"></c:set>
	            <c:forEach var="congress" items="${congresses}" varStatus="row">
	            	<c:if test="${congress.congressId > 0 }">
			<li><a href="congress/${congress.congressId }.do">${congress.congressTitle }<i>${rowIndex }</i>
			    <span><c:if test="${!empty congress.congressResumes && fn:length(congress.congressResumes) > 0 }">
			                		<c:forEach var="congressResume" items="${congress.congressResumes }">
			                			<p>${congressResume.resume }</p></c:forEach></c:if></span>
			<img src="Img/Image_congress/${congress.congressImageFilePath }.png" alt=""/>
			</a></li>
			        <c:set var="rowIndex" value="${rowIndex+1 }"></c:set>
		            </c:if>
	            </c:forEach>
		    </c:if>        
        </ul>
        <div class="clearfix"></div>
    </div>
</div>
<footer>
    <p>Copyright 2014</p>
</footer>
<script src="js/vendor/jquery.min.js"></script>
<script src="js/main.js"></script>
</body>
</html>