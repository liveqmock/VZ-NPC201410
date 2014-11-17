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
            <h3>用户管理</h3>
            <table class="table table-hover table-bordered table-striped">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>用户名</th>
                    <th>真实姓名</th>
                    <th>手机号</th>
                    <th>用户类型</th>
                    <th>创建时间</th>
                    <th>最后登录时间</th>
                    <th>是否启用</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${!empty users && fn:length(users) > 0}">
                    <c:forEach var="user" items="${users}" varStatus="row">
                        <tr>
                            <td>${row.index + 1 }</td>
                            <td>${user.userName }</td>
                            <td>${user.realName }</td>
                            <td>${user.mobile }</td>
                            <td>${npc:formatUserType(user.userType) }</td>
                            <td>
                                <fmt:formatDate value='${user.createdDate}' pattern='yyyy-MM-dd'/>
                            </td>
                            <td>
                                <fmt:formatDate value='${user.lastLoginTime}' pattern='yyyy-MM-dd HH:mm:ss'/>
                            </td>
                            <td>${user.enabled ? '已启用' : '已禁用' }</td>
                            <td><a action="modifyUser" data-entityId="${user.userId }"
                                   class="btn btn-primary btn-xs">修改</a></td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
            <a id="addUser" class="btn btn-primary" data-toggle="modal" data-target="#usermodal">添加</a>
        </div>
        <div class="col-md-2"></div>
    </div>

    <div class="modal fade" id="usermodal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">新建用户</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal col-md-offset-1" role="form" id="userform">
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label for="userName" class="control-label">登录名</label>
                            </div>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="userName" name="userName" placeholder="登录名">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label for="password" class="control-label">密码</label>
                            </div>
                            <div class="col-sm-8">
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label for="passwordConfirm" class="control-label">确认密码</label>
                            </div>
                            <div class="col-sm-8">
                                <input type="password" class="form-control" id="passwordConfirm" name="passwordConfirm"
                                       placeholder="确认密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label for="mobile" class="control-label">手机号</label>
                            </div>
                            <div class="col-sm-8">
                                <input type="tel" class="form-control" id="mobile" name="mobile" placeholder="手机号">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label for="realName" class="control-label">真实姓名</label>
                            </div>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="realName" name="realName"
                                       placeholder="真实姓名">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label for="userType" class="control-label">用户类型</label>
                            </div>
                            <div class="col-sm-8">
                                <div class="btn-group" data-toggle="buttons">
                                    <label class="btn btn-primary active">
                                        <input type="radio" name="userType" id="userType_auditor" autocomplete="off"
                                               checked value="auditor">审核员
                                    </label>
                                    <label class="btn btn-primary">
                                        <input type="radio" name="userType" id="userType_editor" autocomplete="off"
                                               value="editor">录入员
                                    </label>
                                    <label class="btn btn-primary">
                                        <input type="radio" name="userType" id="userType_manager" autocomplete="off"
                                               value="manager">管理员
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label for="enabled" class="control-label">是否启用</label>
                            </div>
                            <div class="col-sm-8">
                                <div class="btn-group" data-toggle="buttons">
                                    <label class="btn btn-primary active">
                                        <input type="radio" name="enabled" value="true" autocomplete="off" checked>启用
                                    </label>
                                    <label class="btn btn-primary">
                                        <input type="radio" name="enabled" value="false" autocomplete="off">禁用
                                    </label>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <a class="btn btn-default" data-dismiss="modal">取消</a>
                    <a class="btn btn-primary" action="addUser">确定</a>
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
        $("[action=addUser]").click(function () {
            if ($('#userform').valid()) {
                $.ajax("users/add.html", {
                    data: $("#userform").serialize(),
                    dataType: 'json',
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert(errorThrown);
                    },
                    success: function (data, textStatus, jqXHR) {
                        $("#userform")[0].reset();

                        if (null != data['errorMessage']) {
                            alert(data['errorMessage']);
                            return;
                        }

                        var user = data['user'];
                        $("<tr></tr>")
                                .append($("<td></td>").text($(".table tbody tr").size() + 1))
                                .append($("<td></td>").text(user['userName']))
                                .append($("<td></td>").text(user['realName']))
                                .append($("<td></td>").text(user['mobile']))
                                .append($("<td></td>").text(formatUserType(user['userType'])))
                                .append($("<td></td>").text($.format.date(user['createdDate'], 'yyyy-MM-dd')))
                                .append($("<td></td>").text($.format.date(user['lastLoginTime'], 'yyyy-MM-dd HH:mm:ss')))
                                .append($("<td></td>").text(user['enabled'] ? '已启用' : '已禁用'))
                                .append($("<td></td>").html('<a action="modifyUser" data-entityId="' + user['userId'] + '" class="btn btn-primary btn-xs">修改</a>'))
                                .appendTo($(".table tbody"));

                        $("#usermodal").modal('hide');
                    },
                    type: 'post'
                });

            }

        });

        $('#usermodal').on('hidden.bs.modal', function (e) {
            $("#userform")[0].reset();
            $("#userform label.error").remove();
            $("#userform label.valid").remove();
        });

        $('#userform').validate(
                {
                    rules: {
                        userName: {
                            minlength: 4,
                            required: true
                        },
                        password: {
                            minlength: 8,
                            required: true
                        },
                        passwordConfirm: {
                            equalsTo: '#password'
                        },
                        mobile: {
                            required: true,
                            digitals: true
                        },
                        realName: {
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
    });

</script>

</body>
</html>