<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ include file="../common.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <base href="${context }/"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>内容管理</title>
    <link href="static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/jqwidgets/styles/jqx.base.css" rel="stylesheet">
    <link href="static/jqwidgets/styles/jqx.bootstrap.css" rel="stylesheet">
    <link href="css/manager.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="js/vendor/html5.min.js"></script>
    <script src="js/vendor/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>

<div class="container-fluid mainContainer">
<div class="row">
<div class="col-md-10">
<!--届别选择-->
<div class="row">
    <div id="congressSelect"></div>
    <button type="button" class="btn btn-primary" id="btnSelectContent" data-toggle="modal"
            data-target="#belongContentSelectModal">选择主题或相关资料
    </button>
    <div class="col-md-12">
        <p>开发完毕后将这些type改为hidden</p>
        <label>所属届别Id</label><input type="text" id="belongCongressId" name="belongCongressId" /> <!--所属届别Id-->
        <label>所属主题Id</label><input type="text" id="belongImageMainId" name="belongImageMainId" /> <!--所属主题Id-->
        <label>主题Id</label><input type="text" id="imageMainId" name="imageMainId" /> <!--主题Id，用于编辑-->
        <label>相关资料Id</label><input type="text" id="imageRelatedId" name="imageRelatedId" /> <!--相关资料Id，用于编辑-->
        <label>内容类型</label><input type="text" id="contentTypeNo" name="contentTypeNo"  /> <!--操作的内容类型，0-主题；1-相关资料-->
        <label>内容Id</label><input type="text" id="contentId" name="contentId" /> <!--操作的内容Id-->
    </div>
</div>

<div id="contentContainer">
<!--基本信息-->
<h5>基本信息</h5>
<hr/>
<div class="row">
    <div class="col-md-6">
        <label>所属届别：</label><span id="belongCongress"></span><br/>
        <label>内容类型：</label><span id="contentType"></span><br/>
        <label>提交人：</label><span id="submitPerson"></span>
    </div>
    <div class="col-md-6">
        <label>所属主题：</label><span id="belongImageMain"></span><br/>
        <label>操作类型：</label><span id="operationType"></span><br/>
        <label>提交时间：</label><span id="submitTime"></span>
    </div>
    <div class="col-md-12">
        <label class="txt-red">状态：</label><span id="contentState" class="txt-red"></span>
    </div>
</div>
<!--内容表单-->
<h5>内容信息</h5>
<hr/>
<div class="row form-horizontal">
    <div class="form-group form-group-sm">
        <label class="col-md-2 control-label">内容标题：</label>

        <div class="col-md-10">
            <input type="text" id="contentTitle" class="form-control"/>
        </div>
    </div>
    <div class="form-group form-group-sm">
        <label class="col-md-2 control-label">内容描述：</label>

        <div class="col-md-10">
            <textarea id="contentDescription" class="form-control"></textarea>
        </div>
    </div>
    <div class="form-group form-group-sm imageMainItem">
        <label class="col-md-2 control-label">上传主题图片：</label>

        <div class="col-md-6">
            <div class="row">
                <div class="col-md-6">
                    <input type="text" id="imageMainImgPath" name="imageMainImgPathDisplay" class="form-control unEditable"/>
                    <input type="hidden" id="imageMainImgPathHidden" name="imageMainImgPath"/>
                </div>
                <div class="col-md-6">
                    <input id="file_upload_1" type="file" multiple="false">
                </div>
            </div>
            <div class="alert alert-warning small">
                <p>建议上传图片尺寸高度不小于500像素<br/>支持的图片类型jpg、png、gif</p>
            </div>
        </div>
        <div class="col-md-4">
                        <span class="thumbnail">
                            <img src="" id="imageMainImgPreview"/>
                        </span>
        </div>
    </div>
    <!--相关资料内容-->
    <div class="form-group form-group-sm imageRelatedItem">
        <label class="col-md-2 control-label">相关资料类型：</label>

        <div class="col-md-4">
            <select id="imageRelatedType" class="form-control">
                <option value="">--请选择类型--</option>
                <option value="1">图片</option>
                <option value="2">视频</option>
                <option value="4">文章</option>
            </select>
        </div>
    </div>
    <div class="form-group form-group-sm imageRelatedItem" id="imageRelatedImgForm">
        <label class="col-md-2 control-label">上传图片：</label>

        <div class="col-md-6">
            <div class="row">
                <div class="col-md-6">
                    <input type="text" id="imageRelatedImgPath" name="imageRelatedImgPathDisplay" class="form-control unEditable"/>
                    <input type="hidden" id="imageRelatedImgPathHidden" name="imageRelatedImgPath"  />
                </div>
                <div class="col-md-6">
                    <input id="file_upload_2" type="file" multiple="false">
                </div>
            </div>
            <div class="alert alert-warning small">
                <p>建议上传图片尺寸高度不小于500像素<br/>支持的图片类型jpg、png、gif</p>
            </div>
        </div>
        <div class="col-md-4">
                        <span class="thumbnail">
                            <img src="" id="imageRelatedImgPreview"/>
                        </span>
        </div>
    </div>
    <div class="form-group form-group-sm imageRelatedItem" id="imageRelatedVideoForm">
        <label class="col-md-2 control-label small">上传视频：</label>

        <div class="col-md-10">
            <div class="row">
                <div class="col-md-6">
                    <input type="text" id="imageRelatedVideoPath" name="imageRelatedVideoPathDisplay" class="form-control unEditable"/>
                    <input type="hidden" id="imageRelatedVideoPathHidden" name="imageRelatedVideoPath" />
                </div>
                <div class="col-md-6">
                    <input id="file_upload_3" type="file" multiple="false">
                </div>
            </div>
            <div class="alert alert-warning">
                <p>建议上传视频尺寸为：宽度720像素，高度576像素<br/>仅支持flv格式的视频</p>
            </div>
        </div>
        <label class="col-md-2 control-label">上传视频缩略图：</label>

        <div class="col-md-6">
            <div class="row">
                <div class="col-md-6">
                    <input type="text" id="imageRelatedVideoThumbImgPath" name="imageRelatedVideoThumbImgPathDisplay" class="form-control unEditable"/>
                    <input type="hidden" id="imageRelatedVideoThumbImgPathHidden" name="imageRelatedVideoThumbImgPath" />
                </div>
                <div class="col-md-6">
                    <input id="file_upload_4" type="file" multiple="false">
                </div>
            </div>
            <div class="alert alert-warning small">
                <p>建议上传图片尺寸为：宽度140像素，高度140像素<br/>支持的图片类型jpg、png、gif</p>
            </div>
        </div>
        <div class="col-md-4">
                        <span class="thumbnail">
                            <img src="" id="imageRelatedVideoThumbImgPreview"/>
                        </span>
        </div>
    </div>
    <div class="form-group form-group-sm imageRelatedItem" id="imageRelatedDocumentForm">
        <label class="col-md-2 control-label">段落：</label>

        <div class="col-md-10 para">
            <div class="col-md-10">
                <textarea class="form-control" name="paragraphContents"></textarea>
            </div>
            <div class="col-md-2"></div>
        </div>
        <div class="col-md-9 col-md-offset-2">
            <br/>
            <button type="button" class="btn btn-primary btn-sm"
                    id="btnImageRelatedDocumentParaAdd">增加段落
            </button>
        </div>
    </div>
</div>
<!--专题及索引信息-->
<h5>专题及索引信息</h5>
<hr/>
<div class="row form-horizontal">

    <div class="form-group form-group-sm">
        <label class="col-md-2 control-label">关键字：</label>

        <div class="col-md-8">
            <input type="text" id="contentKeyword" class="form-control"/>
        </div>
        <div class="col-md-2">
            <button type="button" class="btn btn-primary btn-sm"
                    id="btnSelectKeyword">选择关键字
            </button>
        </div>
        <div class="col-md-10 col-md-offset-2">
            <div class="alert alert-warning small">
                <p>关键字不能加空格，请用英文（半角）逗号分隔开</p>
            </div>
        </div>
    </div>
    <div class="form-group form-group-sm">
        <label class="col-md-2 control-label">时间：</label>

        <div class="col-md-5">
            <input type="text" id="contentDate" class="form-control"/>
        </div>
    </div>
    <div class="form-group form-group-sm">
        <label class="col-md-2 control-label">人物：</label>

        <div class="col-md-6">
            <input type="text" id="contentPerson" class="form-control unEditable"/>
        </div>
        <div class="col-md-4">
            <button type="button" class="btn btn-primary btn-sm"
                    id="btnSelectPerson">选择人物
            </button>
            <button type="button" class="btn btn-primary btn-sm"
                    id="btnCreatePerson">新建人物
            </button>
            <button type="button" class="btn btn-primary btn-sm"
                    id="btnResetPerson">清空人物
            </button>
        </div>
    </div>
    <div class="form-group form-group-sm">
        <label class="col-md-2 control-label">地点：</label>

        <div class="col-md-4">
            <input type="text" id="contentLocation" class="form-control"/>
        </div>
        <label class="pull-left" style="padding-top: 6px;">经度：</label>

        <div class="col-md-2">
            <input type="text" id="contentLocationLong" class="form-control"/>
        </div>
        <label class="pull-left" style="padding-top: 6px;">纬度：</label>

        <div class="col-md-2">
            <input type="text" id="contentLocationLat" class="form-control"/>
        </div>
        <div class="col-md-10 col-md-offset-2">
            <div class="alert alert-warning small">
                <p>如果要将此内容在时间专题中展示，则需要填写时间信息，时间为这个内容所描述的事件发生的时间；<br/>
                    如果要将此内容在人物专题中展示，请选择人物，人物可以选择多个；<br/>
                    如果要将此内容在地点专题中展示，请填写地点信息
                </p>
            </div>
        </div>
    </div>
</div>
<!--审核日志-->
<div id="auditContainer">
    <h5>审核日志</h5>
    <hr/>
    <div class="row">
        <div class="col-md-10 col-md-offset-2">
            <div id="auditGrid" class="col-md-12"></div>
        </div>
    </div>
</div>
<!--操作-->
<hr/>
<div class="row text-center">
    <button type="button" class="btn btn-primary" id="btnSave">保存</button>
    <button type="button" class="btn btn-primary" id="btnSubmitAudit">提交审批</button>
    <button type="button" class="btn btn-primary" id="btnPublish">发布</button>
    <button type="button" class="btn btn-primary" id="btnUnPublish">撤销发布</button>
</div>
</div>
</div>
<!--右侧操作菜单-->
<div class="col-md-2 text-right" id="controlMenu">
    <p>
        <button type="button" class="btn btn-primary" id="btnCreateImageMain">新建主题</button>
    </p>
    <p>
        <button type="button" class="btn btn-primary" id="btnCreateImageRelated">新建相关资料</button>
    </p>
    <p>
        <button type="button" class="btn btn-primary" id="btnModify">修改</button>
    </p>
    <p>
        <button type="button" class="btn btn-primary" id="btnDelete">删除</button>
    </p>
</div>
</div>
</div>

<!--选择主题或相关资料-->
<div class="modal fade" id="belongContentSelectModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">选择主题或相关资料</h4>
            </div>
            <div class="modal-body">
                <div id="contentDataGridList"></div>
            </div>
        </div>
    </div>
</div>

<script src="js/vendor/jquery.min.js"></script>
<script src="static/bootstrap/js/bootstrap.min.js"></script>
<script src="static/uploadify/jquery.uploadify.min.js"></script>
<!--jqwidgets -->
<script src="static/jqwidgets/jqx-all.js"></script>
<script src="static/jqwidgets/globalization/globalize.js"></script>
<script src="static/jqwidgets/globalization/globalize.culture.zh-CN.js"></script>

<script src="static/manager/common.js"></script>
<script src="static/manager/content.js"></script>
</body>
</html>