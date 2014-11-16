<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../common.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>相关资料管理</title>

	<link href="/npc/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">

	<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
      <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
      <!--[if lt IE 9]>
         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->
      
     <style type="text/css">
		label.valid {
		  width: 24px;
		  height: 24px;
		  background: url(../static/images/valid.png) center center no-repeat;
		  display: inline-block;
		  text-indent: -9999px;
		  position:relative;
		  top:-30px;
		  right:-92%;
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

        <div class="col-md-8">
          <table class="table table-hover">
            <thead>
              <tr>
                <th>序号</th>
                <th>类型</th>
                <th style="width:400px;">标题</th>
                <th>次序</th>
                <th>状态</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
            
            <c:set var="rowIndex" value="1" />
            
            <c:if test="${!empty documents && fn:length(documents) > 0 }">
			    <c:forEach var="document" items="${documents}" varStatus="documentRow">
              <tr>
                <td>${rowIndex }</td>
                <td>${npc:formatMaterialType(document.materialId) }</td>
                <td>${document.documentTitle }</td>
                <td>${document.documentSequence }</td>
                <td>${npc:formatModelStatus(document.status) }</td>
                <td>
                	<a class="btn btn-primary btn-xs" action="viewlog" data-resourceType="Document" data-entityId="${document.documentId }">日志</a>
                	<c:if test="${document.status == 'rejected' || document.status == 'submitted' }">
                	<a class="btn btn-primary btn-xs" action="modify" data-resourceType="Document" data-entityId="${document.documentId }">修改</a>
                	<a class="btn btn-primary btn-xs" action="remove" data-entityId="${document.documentId }">删除</a>
                	</c:if>
                
                	<c:if test="${document.status == 'ratified'}">
                	<a class="btn btn-primary btn-xs" action="publish" data-resourceType="Document" data-entityId="${document.documentId }">发布</a>
                	</c:if>
                </td>
              </tr>			
              
              		<c:set var="rowIndex" value="${rowIndex + 1 }" />       
			    </c:forEach>            	
            </c:if>
            
			<c:if test="${!empty relateds && fn:length(relateds) > 0}">
			    <c:forEach var="related" items="${relateds}" varStatus="relatedRow">
              <tr>
                <td>${rowIndex }</td>
                <td>${npc:formatMaterialType(related.materialId) }</td>
                <td>${related.imageRelatedTitle }</td>
                <td>${related.imageRelatedSequence }</td>
                <td>${npc:formatModelStatus(related.status) }</td>
                <td>
                	<a class="btn btn-primary btn-xs" action="viewlog" data-resourceType="ImageRelated" data-entityId="${related.imageRelatedId }">日志</a>
                	<c:if test="${related.status == 'rejected' || related.status == 'submitted' }">
                	<a class="btn btn-primary btn-xs" action="modify" data-resourceType="ImageRelated" data-entityId="${related.imageRelatedId }">修改</a>
                	<a class="btn btn-primary btn-xs" action="remove" data-entityId="${related.imageRelatedId }">删除</a>
                	</c:if>
                	
                	<c:if test="${related.status == 'ratified' }">
                	<a class="btn btn-primary btn-xs" action="publish" data-resourceType="ImageRelated" data-entityId="${related.imageRelatedId }">发布</a>
                	</c:if>
                </td>
              </tr>			  
              		<c:set var="rowIndex" value="${rowIndex + 1 }" />      
			    </c:forEach>
			</c:if>
            </tbody>
          </table>
          <a id="addImageRelated" class="btn btn-primary" data-toggle="modal" data-target="#imageRelatedModal">新建相关资料</a>
          <a id="addDocument" class="btn btn-primary" data-toggle="modal" data-target="#documentModal">新建文章</a>
        </div>
        <div class="col-md-3"></div>
    </div>

    <div class="modal fade" id="imageRelatedModal">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h4 class="modal-title">新建相关资料</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal" role="form" id="imageRelatedForm" action="relateds/add.html" enctype="multipart/form-data">
            	<input type="hidden" name="congressId" value="${imageMain.congressId }" />
            	<input type="hidden" name="imageMainId" value="${imageMain.imageMainId }" />
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="imageRelatedTitle" class="control-label">标题</label>
                </div>
                <div class="col-sm-8">
                  <input type="text" class="form-control" id="imageRelatedTitle" name="imageRelatedTitle" placeholder="标题" />
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="imageRelatedDescription" class="control-label">描述</label>
                </div>
                <div class="col-sm-8">
                  <input type="textarea" class="form-control" id="imageRelatedDescription" name="imageRelatedDescription" placeholder="描述" />
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="imageRelatedSequence" class="control-label">次序</label>
                </div>
                <div class="col-sm-8">
                  <input type="text" class="form-control" id="imageRelatedSequence" name="imageRelatedSequence" placeholder="次序" />
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="materialId" class="control-label">类型</label>
                </div>
                <div class="col-sm-8">
                	<select name="materialId" id="materialId" class="form-control">
                		<option value="1">图片</option>
                		<option value="2">视频</option>
                	</select>
                </div>
              </div>              
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="imageRelatedFile" class="control-label">文件</label>
                </div>
                <div class="col-sm-8">
                  <input type="file" class="form-control" id="imageRelatedFile" name="imageRelatedFile" accept="image/jpeg, image/png" />
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="imageMainFilePreview" class="control-label">图片预览</label>
                </div>
                <div class="col-sm-8" id="imageMainFilePreview">
                </div>
              </div>
              <div class="form-group video" style="display:none;">
                <div class="col-sm-2">
                  <label for="imageRelatedThumbFile" class="control-label">缩略图</label>
                </div>
                <div class="col-sm-8">
                  <input type="file" class="form-control" id="imageRelatedThumbFile" name="imageRelatedThumbFile" accept="image/jpeg, image/png" />
                </div>
              </div>
              <div class="form-group video" style="display:none;">
                <div class="col-sm-2">
                  <label for="imageRelatedThumbFilePreview" class="control-label">图片预览</label>
                </div>
                <div class="col-sm-8" id="imageRelatedThumbFilePreview">
                </div>
              </div>              
            </form>
          </div>
          <div class="modal-footer">
            <a class="btn btn-default" data-dismiss="modal">取消</a>
            <a class="btn btn-primary" action="addImageRelated">确定</a>
          </div>
        </div>
      </div>
	</div>
	
    <div class="modal fade" id="imageRelatedModifyModal">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h4 class="modal-title">修改相关资料</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal" role="form" id="imageRelatedModifyForm" action="relateds/modify.html" enctype="multipart/form-data">
            	<input type="hidden" name="imageRelatedId" value="${imageRelated.imageRelatedId }" />
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="imageRelatedTitle" class="control-label">标题</label>
                </div>
                <div class="col-sm-8">
                  <input type="text" class="form-control" name="imageRelatedTitle" placeholder="标题" />
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="imageRelatedDescription" class="control-label">描述</label>
                </div>
                <div class="col-sm-8">
                  <input type="textarea" class="form-control" name="imageRelatedDescription" placeholder="描述" />
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="imageRelatedSequence" class="control-label">次序</label>
                </div>
                <div class="col-sm-8">
                  <input type="text" class="form-control" name="imageRelatedSequence" placeholder="次序" />
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="materialId" class="control-label">类型</label>
                </div>
                <div class="col-sm-8">
                	<select name="materialId" class="form-control">
                		<option value="1">图片</option>
                		<option value="2">视频</option>
                	</select>
                </div>
              </div>              
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="imageRelatedFile" class="control-label">文件</label>
                </div>
                <div class="col-sm-8">
                  <input type="file" class="form-control" name="imageRelatedFile" accept="image/jpeg, image/png" />
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="imageMainFilePreview" class="control-label">图片预览</label>
                </div>
                <div class="col-sm-8" name="imageMainFilePreview">
                </div>
              </div>
              <div class="form-group video" style="display:none;">
                <div class="col-sm-2">
                  <label for="imageRelatedThumbFile" class="control-label">缩略图</label>
                </div>
                <div class="col-sm-8">
                  <input type="file" class="form-control" name="imageRelatedThumbFile" accept="image/jpeg, image/png" />
                </div>
              </div>
              <div class="form-group video" style="display:none;">
                <div class="col-sm-2">
                  <label for="imageRelatedThumbFilePreview" class="control-label">图片预览</label>
                </div>
                <div class="col-sm-8" name="imageRelatedThumbFilePreview">
                </div>
              </div>              
            </form>
          </div>
          <div class="modal-footer">
            <a class="btn btn-default" data-dismiss="modal">取消</a>
            <a class="btn btn-primary" action="modifyImageRelated">确定</a>
          </div>
        </div>
      </div>
	</div>	
	
    <div class="modal fade" id="documentModal">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h4 class="modal-title">新建文章</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal" role="form" id="documentForm" action="documents/add.html" enctype="multipart/form-data">
            	<input type="hidden" name="congressId" value="${imageMain.congressId }" />
            	<input type="hidden" name="imageMainId" value="${imageMain.imageMainId }" />
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="documentTitle" class="control-label">标题</label>
                </div>
                <div class="col-sm-8">
                  <input type="text" class="form-control" id="documentTitle" name="documentTitle" placeholder="标题" />
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="paragraphContents" class="control-label">段落</label>
                </div>
                <div class="col-sm-8">
                  <textarea class="form-control" rows="3" name="paragraphContents" placeholder="段落文字"></textarea>
                </div>
                <div class="col-sm-2">
                  <a class="btn btn-default" action="removeParagraph">移除段落</a>
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-12">
                  <a class="btn btn-default" action="addParagraph">增加段落</a>
                </div>
              </div>          
            </form>
            
              <div class="form-group" id="paragraphTemplate" style="display:none;">
                <div class="col-sm-2">
                  <label for="paragraphContents" class="control-label">段落</label>
                </div>
                <div class="col-sm-8">
                  <textarea class="form-control" rows="3" name="paragraphContents" placeholder="段落文字"></textarea>
                </div>
                <div class="col-sm-2">
                  <a class="btn btn-default" action="removeParagraph">移除段落</a>
                </div>
              </div>            
          </div>
          <div class="modal-footer">
            <a class="btn btn-default" data-dismiss="modal">取消</a>
            <a class="btn btn-primary" action="addDocument">确定</a>
          </div>
        </div>
      </div>
	</div>	
	
    <div class="modal fade" id="documentModifyModal">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h4 class="modal-title">修改文章</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal" role="form" id="documentModifyForm" action="documents/modify.html" enctype="multipart/form-data">
            	<input type="hidden" name="documentId" />
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="documentTitle" class="control-label">标题</label>
                </div>
                <div class="col-sm-8">
                  <input type="text" class="form-control" name="documentTitle" placeholder="标题" />
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-12">
                  <a class="btn btn-default" action="addParagraph">增加段落</a>
                </div>
              </div>          
            </form>            
          </div>
          <div class="modal-footer">
            <a class="btn btn-default" data-dismiss="modal">取消</a>
            <a class="btn btn-primary" action="modifyDocument">确定</a>
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
	<script type="text/javascript" src="${context }/js/common.js"></script>

	<script type="text/javascript">
	
		$(document).ready(function(){
			
			// 对话框隐藏时重置表单及验证提示
			$('.modal').on('hidden.bs.modal', function (e) {
				  $(".modal form")[0].reset();
				  $(".modal form label.error").remove();
				  $(".modal form label.valid").remove();
			});			
			
			// 新建相关资料表单的验证
			$('#imageRelatedForm').validate(
					{
						rules : {
							imageRelatedTitle : {
								required : true
							},
							imageRelatedSequence : {
								required : true
							},
							imageRelatedFile : {
								required : true
							}
						},
						highlight : function(element) {
							$(element)
									.closest('.control-group')
									.removeClass('success')
									.addClass('error');
						},
						success : function(element) {
							element.text('OK!').addClass(
									'valid').closest(
									'.control-group')
									.removeClass('error')
									.addClass('success');
						}
					});	
			
			// 新建相关资料
			$("[action=addImageRelated]").click(function(){
				if($('#imageRelatedForm').validate()) {
					
					var fd = new FormData();
					var ajax = new XMLHttpRequest();
					fd.append('congressId' , $("#imageRelatedForm [name=congressId]").val());
					fd.append('imageMainId' , $("#imageRelatedForm [name=imageMainId]").val());
					fd.append('imageRelatedTitle' , $("#imageRelatedTitle").val());
					fd.append('imageRelatedDescription' , $("#imageRelatedDescription").val());
					fd.append('imageRelatedSequence', $("#imageRelatedSequence").val());
					fd.append('materialId', $("#materialId").val());
					fd.append('imageRelatedFile' , $("#imageRelatedFile").get(0).files[0]);
					if($("#materialId").val() == 2) {
						fd.append('imageRelatedThumbFile' , $("#imageRelatedThumbFile").get(0).files[0]);
					}
					
					ajax.open("post", "relateds/add.html", true);

					ajax.onload = function () {
						console.log(ajax.responseText);
					};
					ajax.onreadystatechange = function() {
						if (ajax.readyState==4 && ajax.status==200) {
							var data = JSON.parse(ajax.responseText);
							
							$("#imageRelatedForm")[0].reset();
							
							if (null != data['msg']) {
								alert(data['msg']);
								return;
							}
							
							var imageRelated = data['imageRelated'];
							var opHtml = '<a class="btn btn-primary btn-xs" action="viewlog" data-resourceType="ImageRelated" data-entityId="' + imageRelated['imageRelatedId'] + '">日志</a>';
								opHtml += '\r\n<a class="btn btn-primary btn-xs" action="modify" data-entityId="' + imageRelated['imageRelatedId'] + '">修改</a>';
								opHtml += '\r\n<a class="btn btn-primary btn-xs" action="remove" data-entityId="' + imageRelated['imageRelatedId'] + '">删除</a>';
							
							$("<tr></tr>")
								.append($("<td></td>").text($(".table tbody tr").size() + 1))
								.append($("<td></td>").text(formatMaterialType(imageRelated['materialId'])))
								.append($("<td></td>").text(imageRelated['imageRelatedTitle']))
								.append($("<td></td>").text(imageRelated['imageRelatedSequence']))
								.append($("<td></td>").text(formatModelStatus(imageRelated['status'])))
								.append($("<td></td>").html(opHtml))
								.appendTo($(".table tbody"));						
							
							$("#imageRelatedModal").modal('hide');
					    }
					};

					ajax.send(fd);
				}
				
			});
			
			// 修改相关资料表单的验证
			$('#imageRelatedModifyForm').validate(
					{
						rules : {
							imageRelatedTitle : {
								required : true
							},
							imageRelatedSequence : {
								required : true
							}
						},
						highlight : function(element) {
							$(element)
									.closest('.control-group')
									.removeClass('success')
									.addClass('error');
						},
						success : function(element) {
							element.text('OK!').addClass(
									'valid').closest(
									'.control-group')
									.removeClass('error')
									.addClass('success');
						}
					});
			
			
			// 修改相关资料
			$("[action=modifyImageRelated]").click(function(){
				if($('#imageRelatedModifyForm').validate()) {
					
					var fd = new FormData();
					var ajax = new XMLHttpRequest();
					fd.append('imageRelatedId' , $("#imageRelatedModifyForm [name=imageRelatedId]").val());
					fd.append('imageRelatedTitle' , $("#imageRelatedModifyForm [name=imageRelatedTitle]").val());
					fd.append('imageRelatedDescription' , $("#imageRelatedModifyForm [name=imageRelatedDescription]").val());
					fd.append('imageRelatedSequence', $("#imageRelatedModifyForm [name=imageRelatedSequence]").val());
					fd.append('materialId', $("#imageRelatedModifyForm [name=materialId]").val());
					if ($("#imageRelatedModifyForm [name=imageRelatedFile]").val() != "") {
						fd.append('imageRelatedFile' , $("#imageRelatedFile").get(0).files[0]);
					}
					if($("#imageRelatedModifyForm [name=materialId]").val() == 2 && $("#imageRelatedModifyForm [name=imageRelatedThumbFile]").val() != "") {
						fd.append('imageRelatedThumbFile' , $("#imageRelatedModifyForm [name=imageRelatedThumbFile]").get(0).files[0]);
					}
					
					ajax.open("post", "relateds/modify.html", true);

					ajax.onload = function () {
						console.log(ajax.responseText);
					};
					ajax.onreadystatechange = function() {
						if (ajax.readyState==4 && ajax.status==200) {
							var data = JSON.parse(ajax.responseText);
							
							if (null != data['msg']) {
								alert(data['msg']);
								return;
							}
							
							$("#imageRelatedModifyModal").modal('hide');
							location.reload();
					    }
					};

					ajax.send(fd);
				}
				
			});			
			

							// 相关资料类型选择为图片时,隐藏缩略图选择框,否则显示缩略图选择框
							$("[name=materialId]").change(
									function(e) {
										var source = $(this);

										if (source.val() == 1) {
											source.parents("form").find(
													"div.video").hide();
										} else {
											source.parents("form").find(
													"div.video").show();
										}
									});

							// 相关资料图片预览
							$("input[name=imageRelatedFile]")
									.change(
											function(e) {
												var source = $(this);
												var form = source
														.parents("form");

												// 如果相关资料类型为图片
												if (form.find(
														"[name=materialId]")
														.val() == 1) {
													var file = e.target.files[0];
													var img = new Image(), url = img.src = URL
															.createObjectURL(file)
													var $img = $(img)
													img.style.width = '360px';
													img.style.height = '300px';
													img.onload = function() {
														URL
																.revokeObjectURL(url)
														form
																.find(
																		"[name=imageRelatedFilePreview]")
																.empty()
																.append($img)
													}
												}
											});

							// 相关资料缩略图图片预览
							$("input[name=imageRelatedThumbFile]")
									.change(
											function(e) {
												var source = $(this);
												var form = source
														.parents("form");
												var materialId = form
														.find("[name=materialId]");

												// 如果相关资料类型为图片
												if (materialId.val() == 2) {
													var file = e.target.files[0];
													var img = new Image(), url = img.src = URL
															.createObjectURL(file)
													var $img = $(img)
													img.style.width = '360px';
													img.style.height = '300px';
													img.onload = function() {
														URL
																.revokeObjectURL(url)
														form
																.find(
																		"[name=imageRelatedThumbFilePreview]")
																.empty()
																.append($img)
													}
												}
											});

							/** 文章相关代码 **/

							// 文章表单验证规则
							$('#documentForm').validate(
									{
										rules : {
											documentTitle : {
												required : true
											}
										},
										highlight : function(element) {
											$(element)
													.closest('.control-group')
													.removeClass('success')
													.addClass('error');
										},
										success : function(element) {
											element.text("OK!").addClass(
													"valid").closest(
													'.control-group')
													.removeClass('error')
													.addClass('success');
										}
									});

							// 移除段落按钮事件
							$("#documentForm")
									.on(
											"click",
											"a[action=removeParagraph]",
											function(e) {
												// 判断表单如果仅剩一个段落,则不允许移除
												if ($("#documentForm textarea")
														.size() <= 1) {
													return;
												}

												$(this).parents(".form-group")
														.remove();
											});

							// 增加段落按钮
							$("a[action=addParagraph]")
									.click(
											function(e) {
												$("#paragraphTemplate")
														.clone()
														.removeAttr("id")
														.css("display", "")
														.insertBefore(
																$(this)
																		.parents(
																				".form-group"));
											});

							// 新建文章
							$("a[action=addDocument]")
									.click(
											function(e) {
												$
														.ajax({
															type : "POST",
															url : 'documents/add.html',
															data : $(
																	"#documentForm")
																	.serialize(),
															success : function(
																	data,
																	textStatus,
																	jqXHR) {
																if (data['msg']) {
																	alert(data['msg']);
																	return;
																}

																var document = data['document'];
																var opHtml = '<a class="btn btn-primary btn-xs" action="viewlog" data-resourceType="Document" data-entityId="' + document['documentId'] + '">日志</a>';
																opHtml += '\r\n<a class="btn btn-primary btn-xs" action="modify" data-entityId="' + document['documentId'] + '">修改</a>';
																opHtml += '\r\n<a class="btn btn-primary btn-xs" action="remove" data-entityId="' + document['documentId'] + '">删除</a>';

																$("<tr></tr>")
																		.append(
																				$(
																						"<td></td>")
																						.text(
																								$(
																										".table tbody tr")
																										.size() + 1))
																		.append(
																				$(
																						"<td></td>")
																						.text(
																								formatMaterialType(document['materialId'])))
																		.append(
																				$(
																						"<td></td>")
																						.text(
																								document['documentTitle']))
																		.append(
																				$("<td></td>"))
																		.append(
																				$(
																						"<td></td>")
																						.text(
																								formatModelStatus(document['status'])))
																		.append(
																				$(
																						"<td></td>")
																						.html(
																								opHtml))
																		.appendTo(
																				$(".table tbody"));

																$(
																		"#documentModal")
																		.modal(
																				'hide');

															},
															dataType : 'json'
														});
											});
							
							// 修改文章表单验证规则
							$('#documentModifyForm').validate(
									{
										rules : {
											documentTitle : {
												required : true
											}
										},
										highlight : function(element) {
											$(element)
													.closest('.control-group')
													.removeClass('success')
													.addClass('error');
										},
										success : function(element) {
											element.text("OK!").addClass(
													"valid").closest(
													'.control-group')
													.removeClass('error')
													.addClass('success');
										}
									});

							// 移除段落按钮事件
							$("#documentModifyForm")
									.on(
											"click",
											"a[action=removeParagraph]",
											function(e) {
												// 判断表单如果仅剩一个段落,则不允许移除
												if ($(
														"#documentModifyForm textarea")
														.size() <= 1) {
													return;
												}

												$(this).parents(".form-group")
														.remove();
											});
							
							// 修改文章
							$("a[action=modifyDocument]").click(
									function(e) {
										$.ajax({
											type : "POST",
											url : 'documents/modify.html',
											data : $("#documentModifyForm")
													.serialize(),
											success : function(data,
													textStatus, jqXHR) {
												if (data['msg']) {
													alert(data['msg']);
													return;
												}

												$("#documentModifyModal")
														.modal('hide');
												location.reload();
											},
											dataType : 'json'
										});
									});							

							
							// 显示修改相关资料/文章对话框
							$(".table")
									.on(
											"click",
											"[action=modify]",
											function() {
												var source = $(this);
												var entityId = source
														.attr("data-entityId");
												var resourceType = source
														.attr("data-resourceType");

												if ('Document' == resourceType) {
													$
															.ajax({
																url : 'documents/'
																		+ entityId
																		+ '.html',
																success : function(
																		data,
																		textStatus,
																		jqXHR) {
																	$(
																			"#documentModifyForm [name=documentId]")
																			.val(
																					data['documentId']);
																	$(
																			"#documentModifyForm [name=documentTitle]")
																			.val(
																					data['documentTitle']);

																	var btnDiv = $("#documentModifyForm .form-group:last");
																	for ( var index in data['paragraphs']) {
																		var p = $(
																				"#paragraphTemplate")
																				.clone()
																				.removeAttr(
																						"id")
																				.css(
																						"display",
																						"");
																		p
																				.find(
																						"textarea")
																				.text(
																						data['paragraphs'][index]['paragraphContent']);
																		p
																				.insertBefore(btnDiv);
																	}

																	$(
																			"#documentModifyModal")
																			.modal(
																					'show');
																},
																dataType : 'json'
															});
												} else if ('ImageRelated' == resourceType) {
													$
															.ajax({
																url : 'relateds/'
																		+ entityId
																		+ '.html',
																success : function(
																		data,
																		textStatus,
																		jqXHR) {
																	$(
																			"#imageRelatedModifyForm [name=imageRelatedId]")
																			.val(
																					data['imageRelatedId']);
																	$(
																			"#imageRelatedModifyForm [name=imageRelatedTitle]")
																			.val(
																					data['imageRelatedTitle']);
																	$(
																			"#imageRelatedModifyForm [name=imageRelatedDescription]")
																			.val(
																					data['imageRelatedDescription']);
																	$(
																			"#imageRelatedModifyForm [name=imageRelatedSequence]")
																			.val(
																					data['imageRelatedSequence']);
																	$(
																			"#imageRelatedModifyForm [name=materialId]")
																			.val(
																					data['materialId']);

																	$(
																			"#imageRelatedModifyModal")
																			.modal(
																					'show');
																},
																dataType : 'json'
															});
												}

											});							
							
							// 发布相关资料和文章
							$("[action=publish]").click(
									function() {
										var source = $(this);
										var resourceId = source
												.attr('data-entityId');
										var resourceType = source
												.attr('data-resourceType');

										$.ajax({
											type : "POST",
											url : 'publish.html',
											data : 'resourceId=' + resourceId
													+ '&resourceType='
													+ resourceType,
											success : function(data,
													textStatus, jqXHR) {
												if (data['msg']) {
													alert(data['msg']);
													return;
												}

												source.parents("td").prev()
														.text('已发布');
												source.remove();
											},
											dataType : 'json'
										});
									});

							// 查看日志信息
							$(".table")
									.on(
											'click',
											'[action=viewlog]',
											function() {
												var resourceType = $(this)
														.attr(
																'data-resourceType');
												var resourceId = $(this).attr(
														'data-entityId');
												var url = 'auditlogs.html?resourceType='
														+ resourceType
														+ '&resourceId='
														+ resourceId;
												$("#logmodal .modal-body")
														.load(url, function() {
														});
												$("#logmodal").modal('show');
											});







						});
	</script>

</body>
</html>