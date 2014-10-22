<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="npc" uri="http://weizhen.com/tags/npc" %>

<c:set var="context" value="/npc">
</c:set>

<c:set var="personIds" value="-1">
</c:set>
<c:if test="${!empty persons && fn:length(persons) > 0}">
    <c:forEach var="person" items="${persons}" varStatus="row">
        <c:set var="personIds"
               value="${personIds},${person.personId}"></c:set>
    </c:forEach>
</c:if>

<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>人大60周年展 - 人物专题</title>
    <link rel="stylesheet" href="${context }/css/style.css"/>
    <link rel="stylesheet" href="${context }/css/media.css"/>
    <link rel="stylesheet" href="${context }/js/vendor/mediaelementplayer.min.css"/>
    <!--[if lt IE 9]>
    <script src="${context }/js/vendor/html5.min.js"></script>
    <script src="${context }/js/vendor/respond.min.js"></script>
    <![endif]-->
    <!--[if lt IE 7]>
    <script src="${context }/js/vendor/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>
        DD_belatedPNG.fix('.png_bg');
    </script>
    <![endif]-->
</head>
<body>
<header>
    <div id="header-content">
        <div id="logo">
            <a href="${context }/index.html" title="回到首页"></a>
        </div>
        <nav>
            <ul>
                <li class="nav-3d"><a href="#"></a></li>
                <li class="nav-location"><a href="#"></a></li>
                <li class="nav-input"><input type="text" name="keyword" id="keyword" class="png_bg"/></li>
                <li class="nav-search"><a href="javascript:void(0)"></a></li>
                <li class="nav-session"><a href="${context }/index.html"></a>
                    <ul>
                        <c:if test="${!empty congresses && fn:length(congresses) > 0}">
                            <c:forEach var="congress" items="${congresses}" varStatus="row">
                                <li class="nav-session-${congress.congressId }"><a
                                        href="${context }/congress/${congress.congressId }.html"></a></li>
                            </c:forEach>
                        </c:if>
                    </ul>
                </li>
                <li class="nav-discovery"><a href="javascript:void(0);"></a></li>
            </ul>
            <div class="clearfix"></div>
        </nav>
    </div>
</header>
<div id="container">
    <div id="main-content">
        <div class="wrapper">
            <img src="" alt=""/>
        </div>
        <nav>
            <ul>
                <li class="gallery"><a href="javascript:void(0)" title="所有图片" class="png_bg"></a></li>
                <li class="previous"><a href="javascript:void(0)" title="上一张" class="png_bg"></a></li>
                <li class="next"><a href="javascript:void(0)" title="下一张" class="png_bg"></a></li>
            </ul>
        </nav>
        <span class="tbar"></span>
        <span class="info"></span> <span class="share"> <a
            href="javascript:void(0)">赞</a> <a href="javascript:void(0)">分享</a>
			</span>
    </div>
    <div id="relate-content">
        <div class="text">
            <h2></h2>

            <p></p>
        </div>
        <div class="media">
            <ul>
            </ul>
        </div>
        <div class="clearfix"></div>
    </div>
    <div id="gallery-content">
        <ul>
            <c:if test="${!empty persons && fn:length(persons) > 0}">
                <c:forEach var="person" items="${persons}" varStatus="row">
                    <li><a dataImageMainId="${person.personId }" href="javascript:void(0)">
								<span style="display:none;">
									<h3>${person.personName }</h3><br/>
									<p>${person.personResume }</p>
								</span>
                        <img src="${context }/${npc:transImagePath(person.personImage, 'm')}" alt=""/>
                    </a>
                    </li>
                </c:forEach>
            </c:if>
        </ul>
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
    <p>
        <a href="#top" id="gotop"></a>
    </p>
</footer>

<script src="${context }/js/vendor/jquery.min.js"></script>
<script src="${context }/js/vendor/mediaelement-and-player.min.js"></script>
<!--[if lt IE 7]>
<script>
    $(document).ready(function () {
        $("li, span, a").hover(function () {
            $(this).toggleClass("hover");
        });
    });
</script>
<![endif]-->
<script src="${context }/js/main.js"></script>

<script>
    var personIds = [${personIds}];
    var currentIndex = 1;
    var currentPersonId = personIds[currentIndex];
    var materialTypes = ['article', 'image', 'video', 'article'];

    // 将图片路径转为对应的大图的路径
    function transImagePath(imagePath, type) {
    	if(!imagePath) return '';
    	
        var imageMainFilepath = imagePath;
        var lastIndex = imageMainFilepath.lastIndexOf('/') + 1;
        var fileName = imageMainFilepath.substring(lastIndex, imageMainFilepath.length);
        fileName = fileName.replace('.', '-' + type + '.');
        imageMainFilepath = imageMainFilepath.substring(0, lastIndex) + type + '/' + fileName;

        return imageMainFilepath;
    }

    function showImageMain(personId) {
    	personId = personId * 1;

        $.ajax("${context}/person/" + personId + ".html", {
            type: 'get',
            dataType: 'json',
            async: true,
            success: function (data, textStatus, jqXHR) {
                currentPersonId = personId;
                currentIndex = $.inArray(currentPersonId, personIds);
                
                var person = data['person'];
                

                $("#main-content img")[0].src = '${context}/' + transImagePath(person['personImage'], 'b');
                $("#main-content .info").text(currentIndex + " / " + (personIds.length - 1));

                $("#relate-content .text h2").text(person['personName']);
                $("#relate-content .text p").text(person['personResume'] || '');

                $("#relate-content .media ul").html('');
                
                // 主题
                for (var index in data['imageMains']) {
                	var imageMain = data['imageMains'][index];
                	var materialType = 'image';
                	
                	var title = imageMain['imageMainTitle'];
                	var description = imageMain['imageMainDescription'] || '';
                	var file = imageMain['imageMainFilepath'];
                	var imgSrc = '${context}/' + transImagePath(imageMain['imageMainFilepath'], 's')
                	
                	
                	
                	var tHtml = "<li><a href='javascript:void(0)' datatitle='" + title + "' datadescription='" + description + "' file='" + file + "' "
                		+ "class='" + materialType + "' title='" + title + "'><img src='" + imgSrc + "' alt='" + title + "'></img></a></li>";
           			 $(tHtml).appendTo($("#relate-content .media ul"));
                }

                // 相关图片和视频
                for (var index in data['imageRelateds']) {
                    var relate = data['imageRelateds'][index];

                    var materialType = materialTypes[relate['materialId']];
                    var imgSrc = "default.png";
                    if (materialType != 'article')
                        imgSrc = '${context}/' + transImagePath(relate['imageRelatedThumbFilepath'], 's');

                    var content = relate['imageRelatedDescription'] || '';

                    var tHtml = "<li><a href='javascript:void(0)' datatitle='" + relate['imageRelatedTitle'] + "' datadescription='" + content + "' file='" + relate['imageRelatedFilepath'] + "' " +
                            "class='" + materialType + "' title='" + relate['imageRelatedTitle'] + "'><img src='" + imgSrc + "' alt='" + relate['imageRelatedTitle'] + "'></img></a></li>";
                    $(tHtml).appendTo($("#relate-content .media ul"));
                }

                // 相关文章
                for (var index in data['documents']) {
                    var document = data['documents'][index];
                    var paragraphs = document['paragraphs'];
                    var content = '';
                    if (paragraphs && paragraphs.length > 0) {
                        content = '<p>' + paragraphs[0]['paragraphContent'] + "</p>";
                        for (var i = 1; i < paragraphs.length; i++) {
                            content += '<p>' + paragraphs[i]['paragraphContent'] + "</p>";
                        }
                    }

                    $("<li></li>").append(
                            $("<a href='javascript:void(0)' class='article png_bg'></a>")
                                    .attr('datatitle', document['documentTitle'])
                                    .attr('datadescription', content)
                                    .attr('title', document['documentTitle'])
                    ).appendTo($("#relate-content .media ul"));

                    //var tHtml = "<li><a href='javascript:void(0)' datatitle='" + document['documentTitle'] + "' datadescription='" + content + "' class='article'></a></li>";
                    //$(tHtml).appendTo($("#relate-content .media ul"));
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("获取内容失败,请稍后重试");
            }
        });
    }
    $(document).ready(function () {
        $("#main-content li.gallery a").click(function (e) {
            $("#gallery-content").show().siblings().hide();
        });


        $("#main-content li.previous a").click(function (e) {
            if (currentIndex && currentIndex > 1) {
                showImageMain(personIds[currentIndex - 1])
            }
        });

        $("#main-content li.next a").click(function (e) {
            if (currentIndex && currentIndex < personIds.length - 1) {
                showImageMain(personIds[currentIndex + 1])
            }
        });

       	// showImageMain(personIds[1]);
       	$("#main-content li.gallery a").click();
        
        $(".nav-search").on("click", function() {
			if($("#keyword").val())
				document.location.href="search.html?keyword=" + encodeURI($("#keyword").val());
		});
    });
</script>
</body>
</html>