<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ include file="../common.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>人大60周年纪念展--内容审核</title>
    <link href="${context }/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${context }/js/vendor/html5.min.js"></script>
    <script src="${context }/js/vendor/respond.min.js"></script>
    <![endif]-->
    <link href="${context }/css/manager.css" rel="stylesheet">
    <style>
        #loading-mask {
            position: absolute;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            z-index: 20000;
            background-color: #fff;
            filter: alpha(opacity=80);
            -moz-opacity: 0.8;
            opacity: 0.8;
        }

        #loading-mask p {
            position: absolute;
            top: 200px;
            left: 50%;
            width: 200px;
            text-align: center;
            font-size: 14px;
            font-weight: bold;
            margin-left: -100px;
        }

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

<jsp:include page="nav.jsp"></jsp:include>
<div id="loading-mask"><p> 页面载入中，请等待...</p></div>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <c:if test="${user.userType == 'manager'}">
                    <li><a href="${context}/manager/users.html">用户管理</a></li>
                </c:if>
                <c:if test="${user.userType == 'editor'}">
                    <li><a href="${context}/manager/content.html">内容管理</a></li>
                </c:if>
                <c:if test="${user.userType == 'auditor'}">
                    <li class="active"><a href="${context}/manager/auditing.html">内容审核</a></li>
                </c:if>
                <li><a href="${context}/manager/stats.html">统计分析</a></li>
                <li><a href="${context}/logout.html">退出系统</a></li>
            </ul>
        </div>
        <div class="col-md-10 col-sm-offset-2">

            <h1 class="page-header">内容审核（显示需要审核的主题或相关资料）</h1>

            <div class="row">
                <div class="col-md-12">
                    <table class="table table-hover table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>类型</th>
                            <th style="width:400px;">标题</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:set var="rowIndex" value="1"/>

                        <c:if test="${!empty imageMains && fn:length(imageMains) > 0 }">
                            <c:forEach var="imageMain" items="${imageMains}" varStatus="imageMainRow">
                                <tr>
                                    <td>${rowIndex }</td>
                                    <td>主题</td>
                                    <td>${imageMain.imageMainTitle }</td>
                                    <td>
                                        <a class="btn btn-primary btn-xs" action="viewlog"
                                           data-entityId="${imageMain.imageMainId }"
                                           data-resourceType="ImageMain">日志</a>
                                        <a class="btn btn-primary btn-xs" action="audit"
                                           data-entityId="${imageMain.imageMainId }"
                                           data-resourceType="ImageMain">审核</a>
                                    </td>
                                </tr>

                                <c:set var="rowIndex" value="${rowIndex + 1 }"/>
                            </c:forEach>
                        </c:if>

                        <c:if test="${!empty imageRelateds && fn:length(imageRelateds) > 0 }">
                            <c:forEach var="imageRelated" items="${imageRelateds}" varStatus="imageRelatedRow">
                                <tr>
                                    <td>${rowIndex }</td>
                                    <td>相关资料</td>
                                    <td>${imageRelated.imageRelatedTitle }</td>
                                    <td>
                                        <a class="btn btn-primary btn-xs" action="viewlog"
                                           data-entityId="${imageRelated.imageRelatedId }"
                                           data-resourceType="ImageRelated">日志</a>
                                        <a class="btn btn-primary btn-xs" action="audit"
                                           data-entityId="${imageRelated.imageRelatedId }"
                                           data-resourceType="ImageRelated">审核</a>
                                    </td>
                                </tr>

                                <c:set var="rowIndex" value="${rowIndex + 1 }"/>
                            </c:forEach>
                        </c:if>

                        <c:if test="${!empty documents && fn:length(documents) > 0 }">
                            <c:forEach var="document" items="${documents}" varStatus="documentRow">
                                <tr>
                                    <td>${rowIndex }</td>
                                    <td>相关资料</td>
                                    <td>${document.documentTitle }</td>
                                    <td>
                                        <a class="btn btn-primary btn-xs" action="viewlog"
                                           data-entityId="${document.documentId }"
                                           data-resourceType="Document">日志</a>
                                        <a class="btn btn-primary btn-xs" action="audit"
                                           data-entityId="${document.documentId }"
                                           data-resourceType="Document">审核</a>
                                    </td>
                                </tr>

                                <c:set var="rowIndex" value="${rowIndex + 1 }"/>
                            </c:forEach>
                        </c:if>

                        </tbody>
                    </table>
                </div>
            </div>

            <div class="modal fade bs-example-modal-lg" id="auditingModal">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h4 class="modal-title">审批</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" role="form" id="detailForm">
                            </form>

                            <form class="form-horizontal" role="form" id="auditingForm" action="resource/audit.html">
                                <input type="hidden" name="resourceType" id="resourceType"/>
                                <input type="hidden" name="resourceId" id="resourceId"/>

                                <div class="form-group text-right">
                                    <div class="col-sm-2">
                                        <label for="note" class="control-label">审核意见</label>
                                    </div>
                                    <div class="col-sm-8">
                                        <textarea class="form-control" id="note" name="note"
                                                  placeholder="审核意见"></textarea>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-primary" action="reject">审核拒绝</a>
                            <a class="btn btn-primary" action="ratify">审核通过</a>
                        </div>
                    </div>
                </div>
            </div>


            <div class="modal fade" id="logmodal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h4 class="modal-title">日志信息</h4>
                        </div>
                        <div class="modal-body">

                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-default" data-dismiss="modal">确定</a>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>

</div>
<script type="text/javascript" src="${context }/js/vendor/jquery.min.js"></script>
<script type="text/javascript" src="${context }/static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${context }/static/jquery/jquery-dateFormat.js"></script>
<script type="text/javascript" src="${context }/js/common.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        $("#loading-mask").hide();
        $("[action=audit]").click(function () {
            var resourceType = $(this).attr('data-resourceType');
            var resourceId = $(this).attr('data-entityId');

            $("#resourceType").val(resourceType);
            $("#resourceId").val(resourceId);

            // 加载数据详情日志
            var url = resourceType.toLowerCase() + "view.html?id=" + resourceId;
            $("#detailForm").load(url, function () {
                $("#auditingModal").modal('show');
            });
        });

        $("[action=reject],[action=ratify]").click(function () {
            var note = $('#note').val();
            if (!note || note.trim() == '') {
                alert("请填写审核信息");
                return;
            }

            var target = $(this);

            $.ajax({
                type: "POST",
                url: 'resource/audit.html',
                data: $("#auditingForm").serialize() + '&operation=' + target.attr('action'),
                success: function (data, textStatus, jqXHR) {
                    if (data['msg']) {
                        alert(data['msg']);
                        return;
                    }

                    $("a[data-entityId=" + $("#resourceId").val() + "][data-resourceType=" + $("#resourceType").val() + "]").parents("tr").remove();
                    $("#auditingModal").modal('hide');
                    $("#auditingForm")[0].reset();
                },
                dataType: 'json'
            });
        });

        // 查看日志信息
        $(".table").on("click", "[action=viewlog]", function () {
            var resourceId = $(this).attr("data-entityId");
            var resourceType = $(this).attr("data-resourceType");

            var url = 'auditlogs.html?resourceType=' + resourceType + '&resourceId=' + resourceId;
            $("#logmodal .modal-body").load(url, function () {
            });
            $("#logmodal").modal('show');
        });
    });

</script>

</body>
</html>