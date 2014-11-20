<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common.jsp" %>

 <!-- <form class="form-horizontal" role="form"> -->
	<style>
		div.form-group {
			margin-bottom : 2px;
		}
	</style>

     <div class="form-group">
     	<div class="col-sm-2 text-right">
     		<label class="control-label">标题</label>
         </div>
         <div class="col-sm-8">
         	<p class="form-control-static"><b>${document.documentTitle }</b></p>
         </div>
     </div>
     <c:if test="${!empty document.paragraphs && fn:length(document.paragraphs) > 0 }">
     	
     <div class="form-group form-group-sm">
     	<div class="col-sm-2 text-right">
     		<label class="control-label">段落</label>
         </div>
         <div class="col-sm-8">
         	<c:forEach  var="paragraph" items="${document.paragraphs}" varStatus="row">
             <p class="form-control-static">${paragraph.paragraphContent }</p>
            </c:forEach>
         </div>
     </div>    	
     	
     </c:if>

<!--  </form> -->