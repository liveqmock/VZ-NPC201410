<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="common.jsp" %>

<%@ include file="doctype.jsp" %>
<body>
<%@ include file="header.jsp" %>

<!-- <div id="indexPlayerClose" title="关闭"></div>
<div id="indexPlayer"><img src="image/index_main3.jpg"/></div> -->
<div id="container">
    <div id="index-main-content">
        <div id="indexPlayerClose" title="关闭"></div>
        <div class="wrapper">
            <img src="image/index_main5.jpg" alt="全国人大成立60周年网上纪念展"/>
        </div>
        <div id="index-video-content">
            <video id="index-video" src="${context }/Img/indexIntro.flv"></video>
        </div>
        <a href="#" class="play png_bg"></a>
    </div>
    <div id="session-nav">
        <ul>
            <li><a href="congress/0.html">
                <span>
                    <h3>序——历史的选择</h3>
                    <p>“60年的实践充分证明，人民代表大会制度是符合中国国情和实际、体现社会主义国家性质、
                        保证人民当家作主、保障实现中华民族伟大复兴的好制度。” ——摘自“庆祝全国人大成立60周年”习近平总书记讲话</p>
                </span>

                <div><img src="image/index2_s0.jpg" alt="" class=""/></div>
            </a></li>
            <c:if test="${!empty congresses && fn:length(congresses) > 0}">
                <c:set var="rowIndex" value="1"></c:set>
                <c:forEach var="congress" items="${congresses}" varStatus="row">
                    <c:if test="${congress.congressId > 0 }">
                        <li><a href="congress/${congress.congressId }.html">
                            <span>
                                <h3>${congress.congressTitle }全国人民代表大会 <strong>${congress.congressDescription }</strong>
                                </h3>
								<c:if test="${!empty congress.congressResumes && fn:length(congress.congressResumes) > 0 }">
                                    <c:forEach var="congressResume" items="${congress.congressResumes }">
                                        <p>${congressResume.resume }</p>
                                    </c:forEach>
                                </c:if>
                            </span>

                            <div><img src="image/index2_s${congress.congressId }.jpg" alt="" class=""/>
                            </div>
                        </a></li>
                        <c:set var="rowIndex" value="${rowIndex+1 }"></c:set>
                    </c:if>
                </c:forEach>
            </c:if>
        </ul>
        <div class="clearfix"></div>
    </div>
</div>
<footer>
    <p><a href="#top" id="gotop"></a></p>
</footer>

<%@ include file="script.jsp" %>
<script src="js/vendor/jquery.cookie.js"></script>
<script>
    $(document).ready(function () {

        var _width = $("#index-main-content").width();
        var _rate = 5;
        var _rate2 = 19;

        if (!$.cookie('npc60init') || window.location.href.indexOf("nocache") > 0) {

            function hideAd() {
                //$("#indexPlayerClose").hide();
                $("#index-main-content").animate({
                    height: _width / _rate
                }, 1000);
                $("#index-main-content img").animate({
                    "margin-top": -_width / _rate2
                }, 1000);
            }

            $("#container #index-main-content").css({
                height: _width / 1.8
            });

            //关闭横幅
            $("#indexPlayerClose").show().click(function () {
                $(this).hide();
                $("#index-main-content").animate({
                    height: 0
                }, 500, function () {
                    $(this).hide();
                });
            });

            //$("body").css("overflow-x", "hidden");

            var st = setTimeout(hideAd, 5000);

            $.cookie('npc60init', 'true', {expires: 1});
        } else {

            $("#container #index-main-content").css({
                height: _width / _rate
            });

            $("#index-main-content img").css({
                "margin-top": -_width / _rate2
            });

            //关闭横幅
            $("#indexPlayerClose").show().click(function () {
                $(this).hide();
                $("#index-main-content").animate({
                    height: 0
                }, 500, function () {
                    $(this).hide();
                });
            });
        }

        var player = "";
        $("#index-main-content a.play").click(function (e) {
            e.stopPropagation();
            e.preventDefault();
            if (!player) {
                var _width = $(document).width() < 1200 ? ($(document).width() - 40) : 1280;
                var _height = $(document).width() < 1200 ? ($(document).width() * 9 / 16) : 720;
                $('#index-video-content').show().css("margin-left", -_width / 2);
                player = new MediaElement('index-video', {
                    plugins: ['flash'],
                    defaultVideoWidth: _width,
                    defaultVideoHeight: _height,
                    pluginPath: context + '/js/vendor/',
                    pauseOtherPlayers: true,
                    flashName: 'flashmediaelement.swf',
                    success: function (mediaElement, domObject) {
                        $(this).on('click', function () {
                            if (mediaElement.paused) {
                                mediaElement.play();
                            } else {
                                mediaElement.pause();
                            }
                        });
                        $("#index-main-content").animate({
                            height: _height
                        }, 500, function () {
                            mediaElement.play();
                            $('#index-main-content .wrapper').hide();
                        });
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
            } else {
                if (player.paused) {
                    player.play();
                } else {
                    player.pause();
                }
            }
        });

        //var dwidth = $(document).width();
        //var dheight = document.documentElement.clientHeight;

    });
</script>
</body>
</html>