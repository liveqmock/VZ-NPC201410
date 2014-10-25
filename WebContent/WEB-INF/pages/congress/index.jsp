<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="npc" uri="http://weizhen.com/tags/npc" %>

<%@ include file="../common.jsp" %>

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
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>人大60周年展</title>
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

<%@ include file="../header.jsp" %>


<div id="container">
    <div id="main-content">
        <div class="wrapper">
            <c:if test="${congress.congressId == 0}">
                <div id="index-video-content">
                    <video id="index-video" src="${context }/Img/xi.mp4" width="625" height="500"></video>
                </div>
                <a class="play png_bg"></a>
            </c:if>
            <img src="${context }/${npc:transImagePath(firstImageMain.imageMainFilepath, 'b')}" alt=""/>
        </div>
        <nav class="control">
            <ul>
                <li class="gallery"><a href="javascript:void(0)" title="所有图片" class="png_bg"></a></li>
                <li class="previous"><a href="javascript:void(0)" title="上一个" class="png_bg"></a></li>
                <li class="next"><a href="javascript:void(0)" title="下一个" class="png_bg"></a></li>
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
									<p>
                                        ${row.index < 2 ? fn:replace(congress.congressResumeContent,'/n','<br/>') : imageMain.imageMainDescription }</p>
								</span>
                        <img src="${context }/${npc:transImagePath(imageMain.imageMainFilepath, 'm')}" alt=""/>
                        <i><h4>${imageMain.imageMainTitle }</h4></i>
                    </a>
                    </li>
                </c:forEach>
            </c:if>
        </ul>
        <div class="clearfix"></div>
    </div>
    <div id="detail-content">

        <nav class="control">
            <ul>
                <li class="close"><a href="javascript:void(0)" title="关闭" class="png_bg"></a></li>
                <li class="previous"><a href="javascript:void(0)" title="上一个" class="png_bg"></a></li>
                <li class="next"><a href="javascript:void(0)" title="下一个" class="png_bg"></a></li>
            </ul>
        </nav>

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
    var currentImageMainId = ${firstImageMain.imageMainId};
    var imageMainIds = [${imageMainIds}];
    var currentIndex = $.inArray(currentImageMainId, imageMainIds);

    function showImageMain(imageMainId) {

//        console.log(currentIndex);
//        if (currentIndex && currentIndex <= 1) {
//            $("#main-content li.previous").hide();
//        } else {
//            $("#main-content li.previous").show();
//        }
//        if (currentIndex && currentIndex >= imageMainIds.length - 1) {
//            $("#main-content li.next").hide();
//        } else {
//            $("#main-content li.next").show();
//        }

        imageMainId = imageMainId * 1;

        $.ajax("${context}/imagemain/" + imageMainId + ".html", {
            type: 'get',
            dataType: 'json',
            async: true,
            success: function (data, textStatus, jqXHR) {
                currentImageMainId = imageMainId;
                currentIndex = $.inArray(currentImageMainId, imageMainIds);

                $("#main-content img")[0].src = '${context}/' + transImagePath(data['imageMainFilepath'], 'b');
                $("#main-content .info").text("${congress.congressTitle} / (" + currentIndex + ")");

                $("#relate-content .text h2").text(data['imageMainTitle']);

                var description = currentIndex == 1 ? '${congress.congressResumeContent}' : data['imageMainDescription'];
                $("#relate-content .text p")
                        .html($("<span></span>").text(description).html().replace(new RegExp('/n', 'g'), '<br/><br/>'));

                $("#relate-content .media ul").html('');

                for (var index in data['imageRelateds']) {
                    var relate = data['imageRelateds'][index];

                    var materialType = materialTypes[relate['materialId']];
                    var imgSrc = "default.png";
                    if (materialType != 'article')
                        imgSrc = '${context}/' + transImagePath(relate['imageRelatedThumbFilepath'], 's');

                    var title = relate['imageRelatedTitle'] || '';
                    var content = relate['imageRelatedDescription'] || '';

                    var _title = title;
                    if (title.length > 28) {
                        _title = subStr(title, 28);
                    }

                    var tHtml = "<li><a href='javascript:void(0)' datatitle='" + title + "' datadescription='" + content + "' file='" + relate['imageRelatedFilepath'] + "' " +
                            "class='" + materialType + "' title='" + title + "'><span><h4>" + _title + "</h4></span><img src='" + imgSrc + "' alt='" + title + "'></img>" +
                            "</a></li>";
                    $(tHtml).appendTo($("#relate-content .media ul"));
                }

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
                            $("<a href='javascript:void(0)' class='article png_bg'><span><h4>" + document['documentTitle'] + "</h4></span></a>")
                                    .attr('datatitle', document['documentTitle'])
                                    .attr('datadescription', content)
                                    .attr('title', document['documentTitle'])
                    ).appendTo($("#relate-content .media ul"));

                    //var tHtml = "<li><a href='javascript:void(0)' datatitle='" + document['documentTitle'] + "' datadescription='" + content + "' class='article'></a></li>";
                    //$(tHtml).appendTo($("#relate-content .media ul"));
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                //alert("获取内容失败,请稍后重试");
            }
        });
    }
    $(document).ready(function () {

        $("#main-content li.gallery a").click(function (e) {
            $("#gallery-content").show().siblings().hide();
            goToPageTop();
        });


        $("#main-content li.previous a").click(function (e) {
            if (currentIndex && currentIndex > 1) {
                showImageMain(imageMainIds[currentIndex - 1]);
                goToPageTop();
            }
        });

        $("#main-content li.next a").click(function (e) {
            if (currentIndex && currentIndex < imageMainIds.length - 1) {
                showImageMain(imageMainIds[currentIndex + 1]);
                goToPageTop();
            }
        });

        $("#main-content .info").text("${congress.congressTitle} / (" + currentIndex + ")");

        showImageMain('${firstImageMain.imageMainId}');
    });
</script>
</body>
</html>