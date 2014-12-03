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

<%@ include file="../doctype.jsp" %>
<body>
<%@ include file="../header.jsp" %>
<div id="container">
    <div id="main-content">
        <div class="wrapper">
            <c:if test="${congress.congressId == 0 }">
                <div id="index-video-content">
                    <video id="index-video" src="${context }/Img/xi.flv" width="668" height="500"></video>
                </div>
                <a class="play png_bg"></a>
            </c:if>
            <img src="${context }/${npc:transImagePath(firstImageMain.imageMainFilepath, 'b')}" alt=""
                 class="imageMainBig"/>
        </div>
        <nav class="control">
            <ul>
                <li class="gallery"><a href="javascript:void(0)" title="所有图片" class="png_bg"></a></li>
                <li class="previousCon"><a
                        href="${context }/congress/${congress.congressId-1<0?0:congress.congressId-1 }.html" title="上一届"
                        class="png_bg"></a></li>
                <li class="nextCon"><a
                        href="${context }/congress/${congress.congressId+1>12?12:congress.congressId+1 }.html"
                        title="下一届" class="png_bg"></a></li>
                <li class="home"><a href="${context }/index.html" title="回到首页" class="png_bg"></a></li>
                <li class="previous"><a href="javascript:void(0)" title="上一个" class="png_bg"></a></li>
                <li class="next"><a href="javascript:void(0)" title="下一个" class="png_bg"></a></li>
            </ul>
        </nav>
        <span class="tbar"></span>
        <span class="info"></span>
        <span class="share">
        	<a href="javascript:void(0)" id="fav" data-id="">赞</a>
            <span class="favtip">点赞成功</span>
        	<a href="javascript:void(0)" id="share">分享</a>
		</span>
    </div>

    <div id="relate-content">
        <div class="text">
            <span id="imageMainId" class="hidden">${firstImageMain.imageMainId }</span>

            <h2 id="imageMainTitle">${fn:replace(firstImageMain.imageMainTitle, '　', '') }</h2>

            <p>${firstImageMain.imageMainDescription }</p>
        </div>
        <div class="media">
            <ul>
            </ul>
        </div>
        <div class="clearfix"></div>
    </div>

    <div id="gallery-content">
        <h2 class="con-title"><img src="${context }/image/title2_s${congress.congressId}.png" alt=""/></h2>
        <ul>
            <c:if test="${!empty imageMains && fn:length(imageMains) > 0}">
                <c:forEach var="imageMain" items="${imageMains}" varStatus="row">
                    <li><a dataImageMainId="${imageMain.imageMainId }" href="javascript:void(0)">
								<span style="display:none;">
									<h3>${fn:replace(imageMain.imageMainTitle, '　', '<br>') }</h3><br/>
									<p>
                                        ${row.index < 2 ? fn:replace(congress.congressResumeContent,'/n','<br/>') : imageMain.imageMainDescription }</p>
								</span>
                        <img data-original="${context }/${npc:transImagePath(imageMain.imageMainFilepath, 'm')}" alt=""
                             class="lazy"/>
                        <i><h4>${fn:replace(imageMain.imageMainTitle, '　', '') }</h4></i>
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

<%@ include file="../script.jsp" %>
<script>
    var currentImageMainId = ${firstImageMain.imageMainId};
    var imageMainIds = [${imageMainIds}];
    var currentIndex = $.inArray(currentImageMainId, imageMainIds);

    function showImageMain(imageMainId) {

        //只有习主席才显示视频播放
        if ($("#index-video-content").length > 0) {
            if (imageMainId == 1)
                $("a.play").show();
            else
                $("a.play").hide();
        }

        imageMainId = imageMainId * 1;

        $.ajax("${context}/imagemain/" + imageMainId + ".html", {
            type: 'get',
            dataType: 'json',
            async: true,
            success: function (data, textStatus, jqXHR) {
                currentImageMainId = imageMainId;
                currentIndex = $.inArray(currentImageMainId, imageMainIds);

                // 浏览到第一张的时候,隐藏"上一张"箭头
                if (currentIndex <= 1) {
                    $("#main-content li.previous").addClass("disable");
                } else {
                    $("#main-content li.previous").removeClass("disable");
                }

                if (currentIndex >= imageMainIds.length - 1) {
                    $("#main-content li.next").addClass("disable");
                } else {
                    $("#main-content li.next").removeClass("disable");
                }

                $("#main-content img")[0].src = '${context}/' + transImagePath(data['imageMainFilepath'], 'b');
                $("#main-content .info").text("${congress.congressTitle} " + currentIndex + "/" + (imageMainIds.length - 1));

                $("#relate-content .text h2").text(data['imageMainTitle'].replace('　', ''));
                $("#relate-content .text #imageMainId").html(data['imageMainId']);

                var description = data['imageMainDescription'];
                $("#relate-content .text p").html($("<span></span>").text(description).html());
                //.replace(new RegExp('/n', 'g'), '<br/><br/>'));

                $("#relate-content .media ul").html('');

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
                }

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

        //showImageMain(currentImageMainId);
    });
</script>

<script type="text/javascript" charset="utf-8"
        src="http://static.bshare.cn/b/buttonLite.js#style=-1&lang=zh"></script>
</body>
</html>