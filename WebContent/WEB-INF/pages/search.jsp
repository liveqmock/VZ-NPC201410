<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="npc" uri="http://weizhen.com/tags/npc" %>

<%@ include file="common.jsp" %>

<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>人大60周年展</title>
    <link rel="stylesheet" href="css/style.css"/>
    <link rel="stylesheet" href="css/media.css"/>
    <!--[if lt IE 9]>
    <script src="js/vendor/html5.min.js"></script>
    <script src="js/vendor/respond.min.js"></script>
    <![endif]-->
    <!--[if lt IE 7]>
    <script src="js/vendor/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>
        DD_belatedPNG.fix('.png_bg');
    </script>
    <![endif]-->
</head>
<body>
	
	<%@ include file="header.jsp" %>

<div id="container">	
	<p>相关主图</p>
    <div id="relate-content">
        <div class="media">
            <ul>
            
            <c:if test="${!empty imageMains.records && fn:length(imageMains.records) > 0}">
                <c:forEach var="imageMain" items="${imageMains.records}" varStatus="row">
            	
            	<li>
            		<a href='javascript:void(0)' datatitle='${imageMain.imageMainTitle }'
            			 datadescription='${imageMain.imageMainDescription }'
            			 file='${imageMain.imageMainFilepath }'
            			 class='image'
            			 title='${imageMain.imageMainTitle }'>
            			<img src="${context }/${npc:transImagePath(imageMain.imageMainFilepath, 'm')}" alt='${imageMain.imageMainTitle }'></img>
            		</a>
            	</li>
            	
            	</c:forEach>
            </c:if>
            
            </ul>
        </div>
        <div class="clearfix"></div>
    </div>	
	
	
	<p>相关资料</p>
    <div id="relate-content">
        <div class="media">
            <ul>
            
            <c:if test="${!empty imageRelateds.records && fn:length(imageRelateds.records) > 0}">
                <c:forEach var="imageRelated" items="${imageRelateds.records}" varStatus="row">
            	
            	<li>
            		<a href='javascript:void(0)' datatitle='${imageRelated.imageRelatedTitle }'
            			 datadescription='${imageRelated.imageRelatedDescription }'
            			 file='${imageRelated.imageRelatedFilepath }'
            			 class='${npc:transMaterialType(imageRelated.materialId) }'
            			 title='${imageRelated.imageRelatedTitle }'>
            			<img src="${context }/${npc:transImagePath(imageRelated.imageRelatedFilepath, 's')}" alt='${imageRelated.imageRelatedTitle }'></img>
            		</a>
            	</li>
            	
            	</c:forEach>
            </c:if>
            
           	<c:if test="${!empty documents.records && fn:length(documents.records) > 0}">
                <c:forEach var="document" items="${documents.records}" varStatus="row">
                <c:set var="content" value=""></c:set>
                <c:if test="${!empty document.paragraphs && fn:length(document.paragraphs) > 0}">
                	<c:forEach var="paragraph" items="${document.paragraphs}" varStatus="paragraphRow">
                		<c:set var="content" value="${content }<p>${paragraph.paragraphContent }</p>"></c:set>
                	</c:forEach>
                </c:if>
            	
            	<li>
            		<a href='javascript:void(0)' datatitle='${document.documentTitle }'
            			 datadescription='${content }'
            			 class='article png_bg'
            			 title='${document.documentTitle }'>
            		</a>
            	</li>
            	
            	</c:forEach>
            </c:if>            
            
            </ul>
        </div>
        <div class="clearfix"></div>
    </div>
    
	<div id="detail-content">
        <a href="javascript:void(0)" class="close png_bg"></a>

        <div class="media">
            <img src="data:image/gif;base64,R0lGODlhBAABAIABAMLBwfLx8SH5BAEAAAEALAAAAAAEAAEAAAICRF4AOw==" alt=""/>
        </div>

        <h2></h2>

        <p></p>
    </div>
</div>

<footer>
    <p><a href="#top" id="gotop"></a></p>
</footer>

<script src="js/vendor/jquery.min.js"></script>
<script src="js/vendor/mediaelement-and-player.min.js"></script>
<!--[if lt IE 7]>
<script>
    $(document).ready(function () {
        $("li, span, a").hover(function () {
            $(this).toggleClass("hover");
        });
    });
</script>
<![endif]-->
<script src="js/main.js"></script>
</body>
</html>