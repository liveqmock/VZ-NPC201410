<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common.jsp" %>

 <!-- <form class="form-horizontal" role="form"> -->
	<style>
		div.form-group {
			margin-bottom : 2px;
		}
	</style>

     <div class="form-group form-group-sm">
         <div class="col-sm-2 text-right">
             <label for="imageRelatedTitle" class="control-label">标题</label>
         </div>
         <div class="col-sm-8">
         	<p class="form-control-static">${imageRelated.imageRelatedTitle }</p>
         </div>
     </div>
     <div class="form-group form-group-sm">
         <div class="col-sm-2 text-right">
             <label for="imageRelatedDescription" class="control-label">描述</label>
         </div>
         <div class="col-sm-8">
             <p class="form-control-static">${imageRelated.imageRelatedDescription }</p>
         </div>
     </div>
     <div class="form-group form-group-sm">
         <div class="col-sm-2 text-right">
             <label for="imageRelatedSequence" class="control-label">次序</label>
         </div>
         <div class="col-sm-8">
         	<p class="form-control-static">${imageRelated.imageRelatedSequence }</p>
         </div>
     </div>
     <div class="form-group form-group-sm">
         <div class="col-sm-2 text-right">
             <label for="materialId" class="control-label">类型</label>
         </div>
         <div class="col-sm-8">
         	<p class="form-control-static">${npc:formatMaterialType(imageRelated.materialId) }</p>
         </div>
     </div>
     <div class="form-group">
         <div class="col-sm-2 text-right">
             <label for="imageRelatedFilePreview" class="control-label text-right">文件</label>
         </div>
         <div class="col-sm-5" id="imageRelatedFilePreview" name="imageRelatedFilePreview">
         	<c:if test="${imageRelated.materialId == 1 }">
         		<img width="320" alt="文件" title="文件" src="${context }/${imageRelated.imageRelatedFilepath}">
         	</c:if>
         	<c:if test="${imageRelated.materialId == 2 }">
         		<video id="video" alt="文件" title="文件" src="${context }/${imageRelated.imageRelatedFilepath}" width="320" height="256" controls="controls" preload="none"></video>
         	</c:if>
         </div>
         <div class="col-sm-5" id="imageRelatedThumbFilePreview" name="imageRelatedThumbFilePreview">
         	<img width="320" alt="缩略图" title="缩略图" src="${context }/${imageRelated.imageRelatedThumbFilepath}">
         </div>         
     </div>
<!--  </form> -->