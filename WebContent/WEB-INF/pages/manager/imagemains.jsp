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
        <h3>【第${congressId==0?'(序言)': congressId}届】主题列表 <a href="javascript:history.go(-1);" class="btn btn-primary btn-sm pull-right">返回</a></h3>
        <table class="table table-hover table-bordered table-striped">
            <thead>
            <tr>
                <th>序号</th>
                <th>标题</th>
                <th>次序</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${!empty imageMains && fn:length(imageMains) > 0}">
                <c:forEach var="imageMain" items="${imageMains}" varStatus="row">
                    <tr>
                        <td>${row.index + 1 }</td>
                        <td>【主题】${imageMain.imageMainTitle }</td>
                        <td>${imageMain.imageMainSequence }</td>
                        <td>${npc:formatModelStatus(imageMain.status) }</td>
                        <td>
                            <a class="btn btn-primary btn-xs" action="viewlog"
                               data-entityId="${imageMain.imageMainId }">日志</a>&nbsp;
                            <c:if test="${imageMain.status == 'rejected' || imageMain.status == 'submitted' }">
                                <a class="btn btn-primary btn-xs" action="modify"
                                   data-entityId="${imageMain.imageMainId }">编辑</a>&nbsp;
                                <a class="btn btn-primary btn-xs" action="remove"
                                   data-entityId="${imageMain.imageMainId }">删除</a>&nbsp;
                            </c:if>
                            <c:if test="${imageMain.status == 'ratified' }">
                                <a class="btn btn-primary btn-xs" action="publish"
                                   data-entityId="${imageMain.imageMainId }">发布</a>&nbsp;
                            </c:if>
                            <a class="btn btn-link btn-xs"
                               href="relateds.html?imageMainId=${imageMain.imageMainId }">管理相关资料</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
        <a id="addImageMain" class="btn btn-primary" data-toggle="modal" data-target="#imageMainModal">新建主题</a>
    </div>
    <div class="col-md-3"></div>
</div>

<div class="modal fade" id="imageMainModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">新建主题</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="imageMainForm" action="imagemains/add.html"
                      enctype="multipart/form-data">
                    <input type="hidden" id="congressId" name="congressId" value="${congressId }"/>

                    <div class="form-group">
                        <div class="col-sm-2">
                            <label for="imageMainTitle" class="control-label">标题</label>
                        </div>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="imageMainTitle" name="imageMainTitle"
                                   placeholder="标题"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label for="imageMainDescription" class="control-label">描述</label>
                        </div>
                        <div class="col-sm-8">
                            <input type="textarea" class="form-control" id="imageMainDescription"
                                   name="imageMainDescription" placeholder="描述"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label for="imageMainSequence" class="control-label">次序</label>
                        </div>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="imageMainSequence" name="imageMainSequence"
                                   placeholder="次序"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label for="imageMainFile" class="control-label">图片</label>
                        </div>
                        <div class="col-sm-8">
                            <input type="file" class="form-control" id="imageMainFile" name="imageMainFile"
                                   accept="image/jpeg, image/png"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label for="imageMainFilePreview" class="control-label">图片预览</label>
                        </div>
                        <div class="col-sm-8" id="imageMainFilePreview">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a class="btn btn-default" data-dismiss="modal">取消</a>
                <a class="btn btn-primary" action="addImageMain">确定</a>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modifyModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">修改主题</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="modifyForm" action="imagemains/modify.html"
                      enctype="multipart/form-data">
                    <input type="hidden" name="imageMainId"/>

                    <div class="form-group">
                        <div class="col-sm-2">
                            <label for="imageMainTitle" class="control-label">标题</label>
                        </div>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="imageMainTitle" placeholder="标题"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label for="imageMainDescription" class="control-label">描述</label>
                        </div>
                        <div class="col-sm-8">
                            <input type="textarea" class="form-control" name="imageMainDescription"
                                   placeholder="描述"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label for="imageMainSequence" class="control-label">次序</label>
                        </div>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="imageMainSequence" placeholder="次序"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label for="imageMainFile" class="control-label">图片</label>
                        </div>
                        <div class="col-sm-8">
                            <input type="file" class="form-control" id="imageMainFile_modify" name="imageMainFile"
                                   accept="image/jpeg, image/png"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label for="imageMainFilePreview" class="control-label">图片预览</label>
                        </div>
                        <div class="col-sm-8" id="imageMainFilePreview_modify" name="imageMainFilePreview">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a class="btn btn-default" data-dismiss="modal">取消</a>
                <a class="btn btn-primary" action="modifyImageMain">确定</a>
            </div>
        </div>
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

<script type="text/javascript" src="${context }/js/vendor/jquery.min.js"></script>
<script type="text/javascript" src="${context }/static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${context }/static/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="${context }/static/jquery-validation/additional-methods.min.js"></script>
<script type="text/javascript" src="${context }/static/jquery/jquery-dateFormat.js"></script>
<script type="text/javascript" src="${context }/js/validate.message.cn.js"></script>
<script type="text/javascript" src="${context }/js/common.js"></script>

<script type="text/javascript">

$(document).ready(function () {

    $("[action=addImageMain]").click(function () {
        if ($('#imageMainForm').valid()) {

            var fd = new FormData();
            var ajax = new XMLHttpRequest();
            fd.append('congressId', $("#congressId").val());
            fd.append('imageMainTitle', $("#imageMainTitle").val());
            fd.append('imageMainDescription', $("#imageMainDescription").val());
            fd.append('imageMainSequence', $("#imageMainSequence").val());
            fd.append('imageMainFile', $("#imageMainFile").get(0).files[0]);
            ajax.open("post", "imagemains/add.html", true);

            ajax.onload = function () {
                console.log(ajax.responseText);
            };
            ajax.onreadystatechange = function () {
                if (ajax.readyState == 4 && ajax.status == 200) {
                    var data = JSON.parse(ajax.responseText);

                    $("#imageMainForm")[0].reset();

                    if (null != data['errorMessage']) {
                        alert(data['errorMessage']);
                        return;
                    }

                    var imageMain = data['imageMain'];

                    var opHtml = "";
                    opHtml += '<a class="btn btn-primary btn-xs" action="viewlog" data-entityId="' + imageMain['imageMainId'] + '">日志</a>'
                    opHtml += '<a class="btn btn-primary btn-xs" action="modifyImageMain" data-entityId="' + imageMain['imageMainId'] + '">编辑</a>';
                    opHtml += '<a class="btn btn-primary btn-xs" action="removeImageMain" data-entityId="' + imageMain['imageMainId'] + '">删除</a>'
                    opHtml += '<a class="btn btn-link btn-xs" href="relateds.html?imageMainId="' + imageMain['imageMainId'] + '">管理相关资料</a>'

                    $("<tr></tr>")
                            .append($("<td></td>").text($(".table tbody tr").size() + 1))
                            .append($("<td></td>").text('【主题】' + imageMain['imageMainTitle']))
                            .append($("<td></td>").text(imageMain['imageMainSequence']))
                            .append($("<td></td>").text(formatModelStatus(imageMain['status'])))
                            .append($("<td></td>").html(opHtml))
                            .appendTo($(".table tbody"));

                    $("#imageMainModal").modal('hide');
                }
            };

            ajax.send(fd);
        }

    });

    $('.modal').on('hidden.bs.modal', function (e) {
        $(".modal form")[0].reset();
        $(".modal form label.error").remove();
        $(".modal form label.valid").remove();
    });

    $('#imageMainForm').validate(
            {
                rules: {
                    imageMainTitle: {
                        required: true
                    },
                    imageMainSequence: {
                        required: true
                    },
                    imageMainFile: {
                        required: true
                    }
                },
                highlight: function (element) {
                    $(element).closest('.control-group').removeClass('success').addClass('error');
                },
                success: function (element) {
                    element.closest('.control-group').removeClass('error').addClass('success');
                    element.remove();
                }
            });

    // 图片预览
    $("#imageMainFile").change(function (e) {
        var file = e.target.files[0];
        var img = new Image(), url = img.src = URL.createObjectURL(file)
        var $img = $(img)
        img.style.width = '360px';
        img.style.height = '300px';
        img.onload = function () {
            URL.revokeObjectURL(url)
            $('#imageMainFilePreview').empty().append($img)
        }
    });
    
    // 图片预览
    $("#imageMainFile_modify").change(function (e) {
        var file = e.target.files[0];
        var img = new Image(), url = img.src = URL.createObjectURL(file)
        var $img = $(img)
        img.style.width = '360px';
        img.style.height = '300px';
        img.onload = function () {
            URL.revokeObjectURL(url)
            $('#imageMainFilePreview_modify').empty().append($img)
        }
    });    

    // 发布主题
    $("[action=publish]").click(function () {
        var source = $(this);
        var resourceId = source.attr('data-entityId');

        $.ajax({
            type: "POST",
            url: 'publish.html',
            data: 'resourceId=' + resourceId + '&resourceType=ImageMain',
            success: function (data, textStatus, jqXHR) {
                if (data['msg']) {
                    alert(data['msg']);
                    return;
                }

                source.parents("td").prev().text('已发布');
                source.remove();
            },
            dataType: 'json'
        });
    });

    // 查看日志信息
    $(".table").on("click", "[action=viewlog]", function () {
        var url = 'auditlogs.html?resourceType=ImageMain&resourceId=' + $(this).attr('data-entityId');
        $("#logmodal .modal-body").load(url, function () {
        });
        $("#logmodal").modal('show');
    });


    // 修改主题表单的验证
    $('#modifyForm').validate(
            {
                rules: {
                    imageMainTitle: {
                        required: true
                    },
                    imageMainSequence: {
                        required: true
                    }
                },
                highlight: function (element) {
                    $(element).closest('.control-group').removeClass('success').addClass('error');
                },
                success: function (element) {
                    element.closest('.control-group').removeClass('error').addClass('success');
                    element.remove();
                }
            });

    // 显示修改主题对话框
    $(".table").on("click", "[action=modify]", function () {
        var source = $(this);
        var entityId = source.attr("data-entityId");

        $.ajax({
            url: 'imagemains/' + entityId + '.html',
            success: function (data, textStatus, jqXHR) {
                $("#modifyForm [name=imageMainId]").val(data['imageMainId']);
                $("#modifyForm [name=imageMainTitle]").val(data['imageMainTitle']);
                $("#modifyForm [name=imageMainDescription]").val(data['imageMainDescription']);
                $("#modifyForm [name=imageMainSequence]").val(data['imageMainSequence']);

                $("#modifyModal").modal('show');
            },
            dataType: 'json'
        });
    });

    $("[action=modifyImageMain]").click(function () {
        if ($('#modifyForm').valid()) {

            var fd = new FormData();
            var ajax = new XMLHttpRequest();
            fd.append('imageMainId', $("#modifyForm [name=imageMainId]").val());
            fd.append('imageMainTitle', $("#modifyForm [name=imageMainTitle]").val());
            fd.append('imageMainDescription', $("#modifyForm [name=imageMainDescription]").val());
            fd.append('imageMainSequence', $("#modifyForm [name=imageMainSequence]").val());
            if ($("#modifyForm [name=imageMainFile]").val() != "") {
                fd.append('imageMainFile', $("#modifyForm [name=imageMainFile]").get(0).files[0]);
            }
            ajax.open("post", "imagemains/modify.html", true);

            ajax.onload = function () {
                console.log(ajax.responseText);
            };
            ajax.onreadystatechange = function () {
                if (ajax.readyState == 4 && ajax.status == 200) {
                    var data = JSON.parse(ajax.responseText);

                    $("#modifyForm")[0].reset();

                    if (null != data['msg']) {
                        alert(data['msg']);
                        return;
                    }

                    $("#modifyModal").modal('hide');
                    location.reload();
                }
            };

            ajax.send(fd);
        }
    });
});

</script>

</body>
</html>