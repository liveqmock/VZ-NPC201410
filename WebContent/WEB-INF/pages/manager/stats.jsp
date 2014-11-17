<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ include file="../common.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>人大60周年纪念展--信息统计</title>
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

            <div class="col-md-12">
                <h3>信息统计</h3>

            </div>
        </div>

        <div class="modal fade" id="errormodal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title">提示信息</h4>
                    </div>
                    <div class="modal-body">
                        <p></p>
                    </div>
                    <div class="modal-footer">
                        <a class="btn btn-default" data-dismiss="modal">确定</a>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <script type="text/javascript" src="${context }/js/vendor/jquery.min.js"></script>
    <script type="text/javascript" src="${context }/static/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${context }/static/jquery-validation/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${context }/static/jquery-validation/additional-methods.min.js"></script>
    <script type="text/javascript" src="${context }/static/jquery/jquery-dateFormat.js"></script>
    <script type="text/javascript" src="${context }/js/validate.message.cn.js"></script>
    <script type="text/javascript" src="${context }/js/common.js"></script>

    <script type="text/javascript">

        $(document).ready(function () {

        });

    </script>

</body>
</html>