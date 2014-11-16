<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../common.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>用户管理</title>

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
		<jsp:include page="nav.jsp"></jsp:include>

     <div class="row">
        <jsp:include page="left.jsp"></jsp:include>
        
        <div class="col-md-8">
          <table class="table table-hover">
            <thead>
              <tr>
                <th>序号</th>
                <th>标题</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
			<c:if test="${!empty congresses && fn:length(congresses) > 0}">
			    <c:forEach var="congress" items="${congresses}" varStatus="row">
              <tr>
                <td>${row.index + 1 }</td>
                <td>【界别】${congress.congressTitle }</td>
                <td><a class="btn btn-link btn-xs" href="imagemains.html?congressId=${congress.congressId}">管理主题</a></td>
              </tr>			        
			    </c:forEach>
			</c:if>
            </tbody>
          </table>
        </div>
        <div class="col-md-2"></div>
    </div>

</div>

	<script type="text/javascript" src="/npc/js/vendor/jquery.min.js"></script>
	<script type="text/javascript" src="/npc/static/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/npc/static/jquery/jquery-dateFormat.js"></script>

</body>
</html>