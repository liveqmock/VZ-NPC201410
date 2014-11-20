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
             <label for="imageMainTitle" class="control-label">标题</label>
         </div>
         <div class="col-sm-8">
         	<p class="form-control-static">${imageMain.imageMainTitle }</p>
         </div>
     </div>
     <div class="form-group form-group-sm">
         <div class="col-sm-2 text-right">
             <label for="imageMainDescription" class="control-label">描述</label>
         </div>
         <div class="col-sm-8">
             <p class="form-control-static">${imageMain.imageMainDescription }</p>
         </div>
     </div>
     <div class="form-group form-group-sm">
         <div class="col-sm-2 text-right">
             <label for="imageMainSequence" class="control-label">次序</label>
         </div>
         <div class="col-sm-8">
         	<p class="form-control-static">${imageMain.imageMainSequence }</p>
         </div>
     </div>
     <div class="form-group">
         <div class="col-sm-2 text-right">
             <label for="imageMainFilePreview" class="control-label text-right">文件</label>
         </div>
         <div class="col-sm-8" id="imageMainFilePreview" name="imageMainFilePreview">
         	<img width="320" alt="文件" title="文件" src="${context }/${imageMain.imageMainFilepath}">
         </div>     
     </div>
<!--  </form> -->