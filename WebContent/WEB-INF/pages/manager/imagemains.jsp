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
      
     <style type="text/css">
		label.valid {
		  width: 24px;
		  height: 24px;
		  background: url(../static/images/valid.png) center center no-repeat;
		  display: inline-block;
		  text-indent: -9999px;
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
		<div class="row">
			<div class="navbar">
				<div class="navbar-inner">
					<div class="container-fluid">
						 <a data-target=".navbar-responsive-collapse" data-toggle="collapse" class="btn btn-navbar"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></a> <a href="#" class="brand">后台管理</a>
						<div class="nav-collapse collapse navbar-responsive-collapse">
							<ul class="nav">
								<li class="dropdown open">
								</li>
								
								<ul class="dropdown-menu">
								</ul>
							</ul>
							<ul class="nav pull-right">
								<li>
									<a href="javascript:void(0);">请登录</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>

     <div class="row">
        <div class="col-md-2">
			<ul class="nav nav-pills nav-stacked">
				<li>
					<a href="users.html">用户管理</a>
				</li>
				<li class="active">
					<a href="content.html">内容管理</a>
				</li>
				<li>
					<a href="auditing.html">内容审核</a>
				</li>
				<li>
					<a href="statics.html">信息统计</a>
				</li>
				<!-- 
				<li class="disabled">
					<a href="#">信息</a>
				</li>
				 -->
			</ul>        
        </div>
        <div class="col-md-7">
          <table class="table table-hover">
            <thead>
              <tr>
                <th>序号</th>
                <th>标题</th>
                <th>描述</th>
                <th>次序</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
			<c:if test="${!empty imageMains && fn:length(imageMains) > 0}">
			    <c:forEach var="imageMain" items="${imageMains}" varStatus="row">
              <tr>
                <td>${row.index + 1 }</td>
                <td>${imageMain.imageMainTitle }</td>
                <td>${imageMain.imageMainDescription }</td>
                <td>${imageMain.imageMainSequence }</td>
                <td><a class="btn btn-primary" data-imageMainId="${imageMain.imageMainId }">编辑</a></td>
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
            <form class="form-horizontal" role="form" id="imageMainForm" action="imagemains/add.html" enctype="multipart/form-data">
            	<input type="hidden" id="congressId" name="congressId" value="${congressId }" />
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="imageMainTitle" class="control-label">标题</label>
                </div>
                <div class="col-sm-8">
                  <input type="text" class="form-control" id="imageMainTitle" name="imageMainTitle" placeholder="标题" />
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="imageMainDescription" class="control-label">描述</label>
                </div>
                <div class="col-sm-8">
                  <input type="textarea" class="form-control" id="imageMainDescription" name="imageMainDescription" placeholder="描述" />
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="imageMainSequence" class="control-label">次序</label>
                </div>
                <div class="col-sm-8">
                  <input type="text" class="form-control" id="imageMainSequence" name="imageMainSequence" placeholder="次序" />
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="imageMainFile" class="control-label">图片</label>
                </div>
                <div class="col-sm-8">
                  <input type="file" class="form-control" id="imageMainFile" name="imageMainFile" accept="image/jpeg, image/png" />
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

	<script type="text/javascript" src="/npc/js/vendor/jquery.min.js"></script>
	<script type="text/javascript" src="/npc/static/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/npc/static/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript" src="/npc/static/jquery-validation/additional-methods.min.js"></script>
	<script type="text/javascript" src="/npc/static/jquery/jquery-dateFormat.js"></script>

	<script type="text/javascript">
	
		$(document).ready(function(){
			
			$("[action=addImageMain]").click(function(){
				if($('#imageMainForm').validate()) {
					
					var fd = new FormData();
					var ajax = new XMLHttpRequest();
					fd.append('congressId' , $("#congressId").val());
					fd.append('imageMainTitle' , $("#imageMainTitle").val());
					fd.append('imageMainDescription' , $("#imageMainDescription").val());
					fd.append('imageMainSequence', $("#imageMainSequence").val());
					fd.append('imageMainFile' , $("#imageMainFile").get(0).files[0]);
					ajax.open("post", "imagemains/add.html", true);

					ajax.onload = function () {
						console.log(ajax.responseText);
					};
					ajax.onreadystatechange = function() {
						if (ajax.readyState==4 && ajax.status==200) {
							var data = JSON.parse(ajax.responseText);
							
							$("#imageMainForm")[0].reset();
							
							if (null != data['errorMessage']) {
								alert(data['errorMessage']);
								return;
							}
							
							var imageMain = data['imageMain'];
							$("<tr></tr>")
								.append($("<td></td>").text($(".table tbody tr").size() + 1))
								.append($("<td></td>").text(imageMain['imageMainTitle']))
								.append($("<td></td>").text(user['imageMainDescription']))
								.append($("<td></td>").text(user['imageMainSequence']))
								.append($("<td></td>").html('<a class="btn btn-primary" data-imageMainId="' + imageMain['imageMainId'] + '">编辑</a>'))
								.appendTo($(".table tbody"));						
							
							$("#imageMainModal").modal('hide');
					    }
					};

					ajax.send(fd);
					
					
					/*
					$.ajax("imagemains/add.html",{
						contentType:'multipart/form-data',
						data:{
							'congressId' : $("#congressId").val(),
							'imageMainTitle' : $("#imageMainTitle").val(),
							'imageMainDescription' : $("#imageMainDescription").val(),
							'imageMainSequence': $("#imageMainSequence").val(),
							'imageMainFile' : $("#imageMainFile").get(0).files[0]
						},
						dataType:'json',
						error: function(jqXHR, textStatus, errorThrown){
							alert(errorThrown);
						},
						processData : false,
						success: function(data, textStatus, jqXHR) {
							$("#imageMainForm")[0].reset();
							
							if (null != data['errorMessage']) {
								alert(data['errorMessage']);
								return;
							}
							
							var imageMain = data['imageMain'];
							$("<tr></tr>")
								.append($("<td></td>").text($(".table tbody tr").size() + 1))
								.append($("<td></td>").text(imageMain['imageMainTitle']))
								.append($("<td></td>").text(user['imageMainDescription']))
								.append($("<td></td>").text(user['imageMainSequence']))
								.append($("<td></td>").html('<a class="btn btn-primary" data-imageMainId="' + imageMain['imageMainId'] + '">编辑</a>'))
								.appendTo($(".table tbody"));						
							
							$("#imageMainModal").modal('hide');
						},
						type:'post'
					});
					*/
				
				}
				
			});
			
			$('#imageMainModal').on('hidden.bs.modal', function (e) {
				  $("#imageMainForm")[0].reset();
				  $("#imageMainForm label.error").remove();
				  $("#imageMainForm label.valid").remove();
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
					    	required:true
					    }
					  },
					  highlight: function(element) {
					    $(element).closest('.control-group').removeClass('success').addClass('error');
					  },
					  success: function(element) {
					    element
					    .text('OK!').addClass('valid')
					    .closest('.control-group').removeClass('error').addClass('success');
					  }
					 });	
			
			// 图片预览
			$("#imageMainFile").change(function(e){
				var file = e.target.files[0];
				var img = new Image(), url = img.src = URL.createObjectURL(file)
	            var $img = $(img)
	            img.style.width = '360px';
				img.style.height ='300px';
	            img.onload = function() {
	                URL.revokeObjectURL(url)
	                $('#imageMainFilePreview').empty().append($img)
	            }
			});
		});
	
	</script>

</body>
</html>