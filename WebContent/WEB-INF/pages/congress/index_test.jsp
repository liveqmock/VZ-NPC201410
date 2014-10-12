<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>界别列表</title>
</head>
<body>
       <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
          	<th>序号</th>
            <th>界别ID</th>
            <th>标题</th>
          </tr>
	<c:if test="${!empty datas && fn:length(datas) > 0}">
            <c:forEach var="congress" items="${datas}" varStatus="row">
	          <tr>
	          	<td>${row.index + 1 }</td>
	            <td>${congress.congressId }</td>
	            <td>${congress.congressTitle }</td>
	          </tr>
            </c:forEach>
    </c:if>
    </table>
</body>
</html>