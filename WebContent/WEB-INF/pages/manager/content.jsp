<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ include file="../common.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>人大60周年纪念展--用户管理</title>
    <link href="${context }/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${context }/js/vendor/html5.min.js"></script>
    <script src="${context }/js/vendor/respond.min.js"></script>
    <![endif]-->

    <style type="text/css">
        body {
            padding-top: 70px;
            padding-bottom: 30px;
        }

        label.error {
            font-weight: bold;
            color: red;
            padding: 2px 8px;
            margin-top: 2px;
        }
    </style>
</head>
<body>

<div class="container-fluid">
    <div class="row-fluid">
        <jsp:include page="nav.jsp"></jsp:include>

        <div class="row">
            <jsp:include page="left.jsp"></jsp:include>

            <div class="col-md-10">
                <h3>届别列表</h3>
                <table class="table table-hover table-bordered table-striped">
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
                                <td>【届别】${congress.congressTitle }</td>
                                <td><a class="btn btn-link btn-xs"
                                       href="imagemains.html?congressId=${congress.congressId}">管理主题</a></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
            <div class="col-md-2"></div>
        </div>

    </div>

    <script type="text/javascript" src="${context }/js/vendor/jquery.min.js"></script>
    <script type="text/javascript" src="${context }/static/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${context }/static/jquery/jquery-dateFormat.js"></script>

</body>
</html>