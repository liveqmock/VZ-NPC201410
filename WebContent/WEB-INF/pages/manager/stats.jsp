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
                    <li><a href="${context}/manager/auditing.html">内容审核</a></li>
                </c:if>
                <li class="active"><a href="${context}/manager/stats.html">统计分析</a></li>
                <li><a href="${context}/logout.html">退出系统</a></li>
            </ul>
        </div>
        <div class="col-md-10 col-sm-offset-2">

            <h1 class="page-header">统计分析（统计每一届主题和相关内容数量）</h1>

            <div class="row">
                <div class="col-md-12">
                    <div id="myChart" style="width: 95%;height:500px;">

                    </div>
                </div>
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


<script type="text/javascript" src="${context }/js/vendor/jquery.min.js"></script>
<script type="text/javascript" src="${context }/static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${context }/static/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="${context }/static/jquery-validation/additional-methods.min.js"></script>
<script type="text/javascript" src="${context }/static/jquery/jquery-dateFormat.js"></script>
<script type="text/javascript" src="${context }/js/validate.message.cn.js"></script>
<script type="text/javascript" src="${context }/js/common.js"></script>
<script type="text/javascript" src="${context }/static/bootstrap/js/echarts-all.js"></script>


<script type="text/javascript">

    $(document).ready(function () {
        $("#loading-mask").hide();
        var option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data: ['主题', '相关图片', '相关视频', '相关文章']
            },
            toolbox: {
                show: true,
                orient: 'vertical',
                x: 'right',
                y: 'center',
                feature: {
                    mark: {show: true},
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    data: ["序言", "第一届", "第二届", "第三届", "第四届", "第五届",
                        "第六届", "第七届", "第八届", "第九届", "第十届", "第十一届", "第十二届"]
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: '主题',
                    type: 'bar',
                    data: [18, 20, 25, 5, 4, 15, 20, 24, 27, 30, 28, 25, 32]
                },
                {
                    name: '相关图片',
                    type: 'bar',
                    data: [28, 45, 50, 15, 18, 25, 49, 56, 58, 49, 59, 62, 70]
                },
                {
                    name: '相关视频',
                    type: 'bar',
                    data: [16, 20, 28, 6, 8, 19, 24, 28, 32, 36, 39, 42, 46]
                },
                {
                    name: '相关文章',
                    type: 'bar',
                    data: [6, 12, 18, 3, 2, 4, 8, 12, 14, 5, 12, 8, 12]
                }
            ]
        };
        var myChart = echarts.init(document.getElementById('myChart'));

        myChart.setOption(option);
    });

</script>

</body>
</html>