<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="context" value="/npc"> </c:set>

<c:set var="imageMainIds" value="-1"> </c:set>
<c:if test="${!empty imageMains && fn:length(imageMains) > 0}">
	<c:forEach var="imageMain" items="${imageMains}" varStatus="row">
		<c:set var="imageMainIds" value="${imageMainIds},${imageMain.imageMainId}"></c:set>
    </c:forEach>
</c:if>



<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>人大60周年展</title>
    <link rel="stylesheet" href="${context }/css/style.css"/>
    <!--[if lt IE 9]>
    <link rel="stylesheet" href="css/ie.css"/>
    <![endif]-->
    
	<script src="${context }/js/vendor/jquery.min.js"></script>
	<script src="${context }/js/main.js"></script>
    
    <script type="text/javascript">
    	var currentImageMainId = ${firstImageMain.imageMainId};
    	var imageMainIds = [${imageMainIds}];
    	
    	var currentIndex = imageMainIds.indexOf(currentImageMainId);
    	
    	$(document).ready(function(){
    	    $("#main-content a.previous").click(function (e) {
    	        if (currentIndex && currentIndex > 1) {
    	        	showImageMain(imageMainIds[currentIndex - 1])
    	        }
    	    });
    	    
    	    $("#main-content a.next").click(function (e) {
    	        if (currentIndex && currentIndex < imageMainIds.length - 1) {
    	        	showImageMain(imageMainIds[currentIndex + 1])
    	        }
    	    });
    	    
    	    $("#main-content .info").text("${congress.congressTitle} 第" + currentIndex + "张");
    	    
    	    
    	    showImageMain('${firstImageMain.imageMainId}');
    	});
    	
    	function showImageMain(imageMainId) {
    		imageMainId = imageMainId * 1;
    		
    		$.ajax("${context}/imagemain/" + imageMainId + ".do", {
				  type: 'get',
				  dataType: 'json',
				  async: true,
				  success: function(data, textStatus, jqXHR) {
					  currentImageMainId = imageMainId;
					  currentIndex = imageMainIds.indexOf(currentImageMainId);
					  
					  $("#main-content img")[0].src = '${context}/' + data['imageMainFilepath'];
					  $("#main-content .info").text("${congress.congressTitle} 第" + currentIndex + "张");
					  
					  $("#relate-content .text h2").text(data['imageMainTitle']);
					  $("#relate-content .text p").text(data['imageMainDescription']);
					  
					  $("#relate-content .media ul").html('');
					  
					  for(var index in data['imageRelateds']) {
						  var relate = data['imageRelateds'][index];
						  
						  var materialType = materialTypes[relate['materialId']];
						  var imgSrc = "default.png";
						  if (materialType != 'article')
							  imgSrc = '${context}/' + relate['imageRelatedThumbFilepath'];
						  
						  var tHtml = "<li><a href='javascript:void(0)' datatitle='" + relate['imageRelatedTitle'] + "' datadescription='" + relate['imageRelatedDescription'] + "' class='" + materialType + "'><img height=80 width=80 src='" + imgSrc + "'></img></a></li>";
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
                <li>
                    <input type="text"/>
                    <button>搜索</button>
                </li>
                <li><a href="#">届别</a>
                    <ul>
 		<c:if test="${!empty congresses && fn:length(congresses) > 0}">
	            <c:forEach var="congress" items="${congresses}" varStatus="row">
	            	<c:if test="${congress.congressId > 0 }">
						<li><a href="congress/${congress.congressId }.do">${congress.congressTitle }</a></li>
		          	</c:if>
	            </c:forEach>
	    </c:if>
                    </ul>
                </li>
                <li><a href="#">发现</a></li>
            </ul>
            <div class="clearfix"></div>
        </nav>
    </div>
</header>
<div id="container">
    <div id="main-content">
        <img src="${context }/${firstImageMain.imageMainFilepath}" alt=""/>
        <nav>
            <ul>
                <li><a href="javascript:void(0)" class="gallery">所有图片</a></li>
                <li><a href="javascript:void(0)" class="previous">上一张</a></li>
                <li><a href="javascript:void(0)" class="next">下一张</a></li>
            </ul>
            <span class="info">第1届 第1张</span>
            <span class="share">
                <a href="javascript:void(0)">赞</a>
                <a href="javascript:void(0)">分享</a>
            </span>
        </nav>
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
					<li><a dataImageMainId="${imageMain.imageMainId }" href="javascript:void(0)"><img src="${context }/${imageMain.imageMainFilepath}" alt=""/></a></li>
	            </c:forEach>
	    </c:if>
        </ul>
        <div class="clearfix"></div>
    </div>
    <div id="detail-content">
        <a href="javascript:void(0)" class="close">关闭</a>

        <div class="media">
            <img src="data:image/gif;base64,R0lGODlhBAABAIABAMLBwfLx8SH5BAEAAAEALAAAAAAEAAEAAAICRF4AOw==" alt=""/>
        </div>
        <h2></h2>

        <p></p>
    </div>
</div>
<footer>
    <p>Copyright 2014</p>
</footer>
</body>
</html>