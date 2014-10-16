<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="npc" uri="http://weizhen.com/tags/npc" %>

<c:set var="context" value="/npc">
</c:set>

<c:set var="imageMainIds" value="-1">
</c:set>
<c:if test="${!empty imageMains && fn:length(imageMains) > 0}">
	<c:forEach var="imageMain" items="${imageMains}" varStatus="row">
		<c:set var="imageMainIds"
			value="${imageMainIds},${imageMain.imageMainId}"></c:set>
	</c:forEach>
</c:if>

<!DOCTYPE html>
<html>
<head lang="zh-cn">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>人大60周年展</title>
<link rel="stylesheet" href="${context }/css/style.css" />
<!--[if lt IE 9]>
    <link rel="stylesheet" href="css/ie.css"/>
    <![endif]-->

<script src="${context }/js/vendor/jquery.min.js"></script>
<script src="${context }/js/main.js"></script>
<script src="${context }/jPlayer/jquery.jplayer.min.js"></script>

<script type="text/javascript">
    	var currentImageMainId = ${firstImageMain.imageMainId};
    	var imageMainIds = [${imageMainIds}];
    	
    	var currentIndex = imageMainIds.indexOf(currentImageMainId);
    	
    	$(document).ready(function(){
    	    $("#main-content li.gallery a").click(function (e) {
    	        $("#gallery-content").show().siblings().hide();
    	    });
    	    
    	    $("#main-content li.previous a").click(function (e) {
    	        if (currentIndex && currentIndex > 1) {
    	        	showImageMain(imageMainIds[currentIndex - 1])
    	        }
    	    });
    	    
    	    $("#main-content li.next a").click(function (e) {
    	        if (currentIndex && currentIndex < imageMainIds.length - 1) {
    	        	showImageMain(imageMainIds[currentIndex + 1])
    	        }
    	    });
    	    
    	    $("#main-content .info").text("${congress.congressTitle} / (" + currentIndex + ")");
    	    
    	    
    	    showImageMain('${firstImageMain.imageMainId}');
    	});
    	
    	
    	// 将图片路径转为对应的大图的路径
    	function transImagePath(imagePath, type) {
			  var imageMainFilepath = imagePath;
			  var lastIndex = imageMainFilepath.lastIndexOf('/') + 1;
			  var fileName = imageMainFilepath.substring(lastIndex, imageMainFilepath.length);
			  fileName = fileName.replace('.', '-'+type+'.');
			  imageMainFilepath = imageMainFilepath.substring(0, lastIndex) + type + '/' + fileName;
    		
			  return imageMainFilepath;
    	}
    	
    	function showImageMain(imageMainId) {
    		imageMainId = imageMainId * 1;
    		
    		$.ajax("${context}/imagemain/" + imageMainId + ".do", {
				  type: 'get',
				  dataType: 'json',
				  async: true,
				  success: function(data, textStatus, jqXHR) {
					  currentImageMainId = imageMainId;
					  currentIndex = imageMainIds.indexOf(currentImageMainId);
					  
					  $("#main-content img")[0].src = '${context}/' + transImagePath(data['imageMainFilepath'], 'b');
					  $("#main-content .info").text("${congress.congressTitle} / (" + currentIndex + ")");
					  
					  $("#relate-content .text h2").text(data['imageMainTitle']);
					  $("#relate-content .text p").text(data['imageMainDescription']);
					  
					  $("#relate-content .media ul").html('');
					  
					  for(var index in data['imageRelateds']) {
						  var relate = data['imageRelateds'][index];
						  
						  var materialType = materialTypes[relate['materialId']];
						  var imgSrc = "default.png";
						  if (materialType != 'article')
							  imgSrc = '${context}/' + transImagePath(relate['imageRelatedThumbFilepath'], 's');
						  
						  var tHtml = "<li><a href='javascript:void(0)' datatitle='" + relate['imageRelatedTitle'] + "' datadescription='" + relate['imageRelatedDescription'] + "' file='" + relate['imageRelatedFilepath'] + "' class='" + materialType + "'><img src='" + imgSrc + "'></img></a></li>";
						  $(tHtml).appendTo($("#relate-content .media ul"));
					  }
				  },
				  error: function(jqXHR, textStatus, errorThrown) {
					  //alert("获取内容失败,请稍后重试");
				  }
			  });	
    	}
    	
    
    	var materialTypes = ['article', 'image', 'video', 'article'];
    </script>
</head>
<body>
	<header>
		<div>
			<div id="logo">
				<a href="${context }/index.do" title="回到首页"></a>
			</div>
			<nav>
				<ul>
					<li class="nav-3d"><a href="#"></a></li>
					<li class="nav-location"><a href="#"></a></li>
					<li class="nav-input"><input type="text" /></li>
					<li class="nav-search"><a href="#"></a></li>
					<li class="nav-session"><a href="#"></a>
						<ul>
							<c:if test="${!empty congresses && fn:length(congresses) > 0}">
								<c:forEach var="congress" items="${congresses}" varStatus="row">
									<c:if test="${congress.congressId > 0 }">
										<li class="nav-session-${congress.congressId }"><a
											href="congress/${congress.congressId }.do"></a></li>
									</c:if>
								</c:forEach>
							</c:if>
						</ul></li>
					<li class="nav-discovery"><a href="javascript:void(0);"></a></li>
				</ul>
				<div class="clearfix"></div>
			</nav>
		</div>
	</header>
	<div id="container">
		<div id="main-content">
			
			<img src="${context }/${npc:transImagePath(firstImageMain.imageMainFilepath, 'b')}" alt="" />
			<nav>
				<ul>
					<li class="gallery"><a href="javascript:void(0)" title="所有图片"></a></li>
					<li class="previous"><a href="javascript:void(0)" title="上一张"></a></li>
					<li class="next"><a href="javascript:void(0)" title="下一张"></a></li>
				</ul>
			</nav>
			<span class="tbar"></span>
			<span class="info"></span> <span class="share"> <a
				href="javascript:void(0)">赞</a> <a href="javascript:void(0)">分享</a>
			</span>
		</div>
		<div id="relate-content">
			<div class="text">
				<h2>${firstImageMain.imageMainTitle }</h2>
				<p>${firstImageMain.imageMainDescription }</p>
			</div>
			<div class="media">
				<ul>
					<li><a href="javascript:void(0)" class="article"></a></li>
					<li><a href="javascript:void(0)" class="article"></a></li>
					<li><a href="javascript:void(0)" class="image"></a></li>
					<li><a href="javascript:void(0)" class="image"></a></li>
					<li><a href="javascript:void(0)" class="image"></a></li>
					<li><a href="javascript:void(0)" class="image"></a></li>
					<li><a href="javascript:void(0)" class="video"></a></li>
					<li><a href="javascript:void(0)" class="video"></a></li>
					<li><a href="javascript:void(0)" class="video"></a></li>
				</ul>
			</div>
			<div class="clearfix"></div>
		</div>
		<div id="gallery-content">
			<ul>
				<c:if test="${!empty imageMains && fn:length(imageMains) > 0}">
					<c:forEach var="imageMain" items="${imageMains}" varStatus="row">
						<li><a dataImageMainId="${imageMain.imageMainId }" href="javascript:void(0)">
								<span style="display:none;">
									<h3>${imageMain.imageMainTitle }</h3><br/>
									<p>${imageMain.imageMainDescription }</p>
								</span>
								<img src="${context }/${npc:transImagePath(imageMain.imageMainFilepath, 'm')}" alt="" />
							</a>
					    </li>
					</c:forEach>
				</c:if>
			</ul>
			<div class="clearfix"></div>
		</div>
		<div id="detail-content">
			<a href="javascript:void(0)" class="close"></a>

			<div class="media">
				<img
					src="data:image/gif;base64,R0lGODlhBAABAIABAMLBwfLx8SH5BAEAAAEALAAAAAAEAAEAAAICRF4AOw=="
					alt="" />
					
				
			</div>
			
			<div id="jp_container_1">
				 <a href="javascript:void(0);" class="jp-play">Play</a>
				 <a href="javascript:void(0);" class="jp-pause">Pause</a>
			</div>
			
			<h2></h2>

			<p></p>
		</div>
	</div>
	<footer>
		<p>
			<a href="#top" class="gotop"></a>
		</p>
	</footer>
</body>
</html>