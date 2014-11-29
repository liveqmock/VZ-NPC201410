<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="common.jsp" %>

<%@ include file="doctype.jsp" %>
<body>
<%@ include file="header.jsp" %>
<div id="container">
    <div id="flashContent">
	    <noscript><p>您还没有安装flash，<a href="http://get.adobe.com/cn/flashplayer" target="_blank">点击下载安装</a></p></noscript>
    </div>
</div>
<%@ include file="script.jsp" %>
<script>
    function openUrl(val){
       //alert("参数：" + window.location.href.replace("360", "congress/" + val));
	   window.location = window.location.href.replace("360", "congress/" + val);
    }
</script>
<script src="360/tour.js"></script>
<script>
	embedpano({swf:"360/tour.swf", xml:"360/tour.xml", target:"flashContent", html5:"auto", passQueryParameters:true});
</script>
</body>
</html>