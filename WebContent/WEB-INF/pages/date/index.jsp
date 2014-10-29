<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="npc" uri="http://weizhen.com/tags/npc" %>

<%@ include file="../common.jsp" %>


<%@ include file="../doctype.jsp" %>
<body>
<%@ include file="../header.jsp" %>

<div id="container">
    <div id="dateContainer">

        <c:if test="${!empty dateVOs && fn:length(dateVOs) > 0 }">
            <c:set var="currentYear" value="${fn:substring(dateVOs[0].publishDate, 0, 4) }"></c:set>

            <div class="year" class="png_bg">
                <span name="year" class="png_bg">${currentYear }年</span>
                <ul>

                    <c:forEach var="dateVO" items="${dateVOs}" varStatus="row">

                        <c:if test="${currentYear != fn:substring(dateVO.publishDate,0,4) }">
                            <c:set var="currentYear" value="${fn:substring(dateVO.publishDate,0,4) }"></c:set>

                            <div class="clearfix"></div>
                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="year" class="png_bg">
                <span name="year" class="png_bg">${currentYear }年</span>
                <ul>
        </c:if>

        <li class="png_bg"><a href="#">${fn:substring(dateVO.publishDate,4,6) }月</a>
            <ul class="${row.index % 2 == 0 ? 'right' : 'left' }">

                <c:if test="${!empty dateVO.imageMains && fn:length(dateVO.imageMains) > 0 }">
                    <c:forEach var="imageMain" items="${dateVO.imageMains }" varStatus="imageMainRow">

                        <li><a href="javascript:void(0)" datatitle="${imageMain.imageMainTitle }"
                               datadescription="${imageMain.imageMainDescription }"
                               file="${imageMain.imageMainFilepath }" class="image"
                               title="${imageMain.imageMainTitle }"><span><h4>
                            ${imageMain.imageMainTitle }</h4></span><img
                                data-original="${context }/${npc:transImagePath(imageMain.imageMainFilepath, 's')}"
                                alt="${imageMain.imageMainTitle }" class="lazy"></a></li>

                    </c:forEach>
                </c:if>

                <c:if test="${!empty dateVO.imageRelateds && fn:length(dateVO.imageRelateds) > 0 }">
                    <c:forEach var="imageRelated" items="${dateVO.imageRelateds }" varStatus="imageRelatedRow">

                        <li><a href="javascript:void(0)" datatitle="${imageRelated.imageRelatedTitle }"
                               datadescription="${imageRelated.imageRelatedDescription }"
                               file="${imageRelated.imageRelatedFilepath }"
                               class="${npc:transMaterialType(imageRelated.materialId) }"
                               title="${imageRelated.imageRelatedTitle }"><span><h4>
                            ${imageRelated.imageRelatedTitle }</h4></span><img
                                data-original="${context }/${npc:transImagePath(imageRelated.imageRelatedThumbFilepath, 's')}"
                                alt="${imageRelated.imageRelatedTitle }" class="lazy"></a></li>

                    </c:forEach>
                </c:if>

                <c:if test="${!empty dateVO.documents && fn:length(dateVO.documents) > 0 }">
                    <c:forEach var="document" items="${dateVO.documents }" varStatus="documentRow">

                        <c:set var="paragraphContent" value=""></c:set>
                        <c:if test="${!empty document.paragraphs && fn:length(document.paragraphs) > 0 }">
                            <c:forEach var="paragraph" items="${document.paragraphs }">
                                <c:set var="paragraphContent"
                                       value="${paragraphContent }<p>${paragraph.paragraphContent }</p>"></c:set>
                            </c:forEach>
                        </c:if>

                        <li><a href="javascript:void(0)" class="article"
                               datatitle="${document.documentTitle }"
                               datadescription="${paragraphContent} "
                               title="${document.documentTitle }"><span><h4>${document.documentTitle }</h4></span></a>
                        </li>
                    </c:forEach>
                </c:if>

            </ul>
            <div class="clearfix"></div>
        </li>

        </c:forEach>

        </li>
        <div class="clearfix"></div>
        </ul>
        <div class="clearfix"></div>
    </div>

    </c:if>

</div>

<div class="modal">
    <div id="detail-content" style="display: block;">

        <nav class="control">
            <ul>
                <li class="close"><a href="javascript:void(0)" title="关闭" class="png_bg"></a></li>
            </ul>
        </nav>

        <div class="media">
            <img src="data:image/gif;base64,R0lGODlhBAABAIABAMLBwfLx8SH5BAEAAAEALAAAAAAEAAEAAAICRF4AOw==" alt=""/>
        </div>

        <h2></h2>

        <p></p>
    </div>
</div>
</div>

<footer>
    <p>
        <a href="#top" id="gotop"></a>
    </p>
</footer>

<%@ include file="../script.jsp" %>
<script src="${context }/js/vendor/jquery.lazyload.min.js"></script>
<script>

    function showDetail(source) {

        //$("#dateContainer").hide();

        $("#detail-content .article").remove();
        $("#detail-content .media").html("").show();
        $("#jp_container_1").hide();

        var materialType = source.prop('class');
        if (materialType.indexOf('video') != -1) {
            var imageSrc = source.find('img').prop('src')
                    .replace('/s/', '/b/').replace('-s.', '-b.')
                    .replace('/m/', '/b/').replace('-m.', '-b.');

            $("#detail-content h2").text(source.attr('datatitle'));
            $("#detail-content p").html(source.attr('datadescription') ? source.attr('datadescription') : '');

            //http://mediaelementjs.com/
            var _src = (context + "/" + source.attr('file'));
            var player = $('<video id="video" src="' + _src + '" width="720" height="576" controls="controls" preload="none"></video>');
            player.appendTo($("#detail-content .media"));
            var me = new MediaElement('video', {
                plugins: ['flash'],
                pluginPath: context + '/js/vendor/',
                flashName: 'flashmediaelement.swf',
                success: function (mediaElement, domObject) {
                    $(this).on('click', function () {
                        if (mediaElement.paused) {
                            mediaElement.play();
                        } else {
                            mediaElement.pause();
                        }
                    });
                    mediaElement.play();
                },
                error: function () {
                    if ($(".me-cannotplay").length > 0) {
                        $(".me-cannotplay").html('<h2>您的电脑没有安装flash播放器，无法观看视频。' +
                                '<a href="http://get.adobe.com/cn/flashplayer" target="_blank">点击下载安装</a></h2>')
                    } else {
                        alert("您的电脑没有安装flash播放器，无法观看视频。");
                    }
                }
            });

        } else if (materialType.indexOf('image') != -1) {
            var imageSrc = source.find('img').prop('src')
                    .replace('/s/', '/b/').replace('-s.', '-b.')
                    .replace('/m/', '/b/').replace('-m.', '-b.');

            $("#detail-content .media").html("<img src='" + imageSrc + "'></img>");
            $("#detail-content h2").text(source.attr('datatitle'));
            $("#detail-content p").html(source.attr('datadescription') ? source.attr('datadescription') : '');
        } else if (materialType.indexOf('article') != -1) {
            $("#detail-content h2").text("");
            $("#detail-content p").html("");
            $("#detail-content .media").hide();
            $("#detail-content").append($('<div class="article"><div><h2>' + source.attr('datatitle') + '</h2>' +
                    (source.attr('datadescription') ? source.attr('datadescription') : '') + '</div></div>'));
        }
        $("#detail-content").css("top", $(document).scrollTop());
        console.log(source.offset().top)
        $(".modal").show();
    }


    $(document).ready(function () {

        $("img.lazy").lazyload();

        $(".year li a").click(function (e) {
            e.preventDefault();
            showDetail($(this));
        });

        $("#detail-content .close a").unbind().click(function (e) {
            e.preventDefault();
            //$("#dateContainer").show();
            $(".modal").hide();
        });

        $("#container .modal").click(function (e) {
            e.preventDefault();
            //$("#dateContainer").show();
            $(".modal").hide();
        });

        $(".modal").height($(document.body).height());
        $(".modal").width($('body, html').width());

    });
</script>
</body>
</html>