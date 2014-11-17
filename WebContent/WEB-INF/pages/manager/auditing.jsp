<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../common.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>内容审核</title>
	<link href="${context }/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
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
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
            
            <c:set var="rowIndex" value="1" />
            
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
                
              	  <c:set var="rowIndex" value="${rowIndex + 1 }" />       
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
                
              	  <c:set var="rowIndex" value="${rowIndex + 1 }" />       
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
                
              	  <c:set var="rowIndex" value="${rowIndex + 1 }" />       
			    </c:forEach>            	
            </c:if>         

            </tbody>
          </table>
        </div>
        <div class="col-md-2"></div>
    </div>

    <div class="modal fade" id="auditingModal">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h4 class="modal-title">审批</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal" role="form" id="auditingForm" action="resource/audit.html">
            	<input type="hidden" name="resourceType" id="resourceType" />
            	<input type="hidden" name="resourceId" id="resourceId" />
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="contentType" class="control-label">内容类型</label>
                </div>
                <div class="col-sm-8">
                  <p id="contentType"></p>
                </div>
              </div>            	
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="title" class="control-label">标题</label>
                </div>
                <div class="col-sm-8">
                  <p id="title"></p>
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="auditLog" class="control-label">审核信息</label>
                </div>
                <div class="col-sm-8" name="auditLog" id="auditLog">
                </div>
              </div>              
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="note" class="control-label">审核意见</label>
                </div>
                <div class="col-sm-8">
                  <textarea class="form-control" id="note" name="note" placeholder="审核意见"></textarea>
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

	<script type="text/javascript" src="${context }/js/vendor/jquery.min.js"></script>
	<script type="text/javascript" src="${context }/static/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${context }/static/jquery/jquery-dateFormat.js"></script>
	<script type="text/javascript" src="${context }/js/common.js"></script>

	<script type="text/javascript">
		$(document).ready(function(){
			$("[action=audit]").click(function(){
				var resourceType = $(this).attr('data-resourceType');
				var resourceId = $(this).attr('data-entityId');
				
				$("#resourceType").val(resourceType);
				$("#resourceId").val(resourceId);
				
				$("#contentType").text(resourceTypeEnum[resourceType]);
				$("#title").text($(this).parents("td").prev().text());
				
				// 加载审核日志
				var url = "auditlogs.html?resourceType=" + resourceType + "&resourceId=" + resourceId;
				$("#auditLog").load(url, function(){});
				
				$("#auditingModal").modal('show');
			});
			
			$("[action=reject],[action=ratify]").click(function(){
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
					  success: function(data, textStatus, jqXHR){
						  if(data['msg']) {
							  alert(data['msg']);
							  return;
						  }

					  	$("a[data-entityId="+ $("#resourceId").val() + "][data-resourceType="+$("#resourceType").val() + "]").parents("tr").remove();
						$("#auditingModal").modal('hide');
						$("#auditingForm")[0].reset();
					  },
					  dataType: 'json'
					});				
			});	
			
			// 查看日志信息
			$(".table").on("click", "[action=viewlog]", function(){
				var resourceId = $(this).attr("data-entityId");
				var resourceType = $(this).attr("data-resourceType");
				
				var url = 'auditlogs.html?resourceType='+ resourceType + '&resourceId=' + resourceId;
				$("#logmodal .modal-body").load(url, function(){});	
				$("#logmodal").modal('show');
			});			
		});
	
	</script>

</body>
</html>