<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../common.jsp" %>

<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>人大60周年展 - 地点主题</title>
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
    <div id="main-content" class="location-content">
        <div class="wrapper">
            <span id="mapContainer"></span>
        </div>
        <ul id="location-list" style="overflow-y:scroll;">
            <c:forEach var="location" items="${locations}" varStatus="row">
                <li class="locationitem">
                    <a href="javascript:void(0)" title="${location.locationName }" class="png_bg"
                       data-locationId="${location.locationId }"
                       data-locationLng="${location.locationLng }"
                       data-locationLnt="${location.locationLat }"
                       data-locationName="${location.locationName }"
                       data-locationTitle="${location.locationTitle }"
                       data-locationResume="${location.locationResume }"
                       data-detail-detailType="${location.detail.detailType }"
                       data-detail-id="${location.detail.id }"
                       data-detail-materialType="${location.detail.materialType }"
                       data-detail-title="${location.detail.title }"
                       data-detail-description="${location.detail.description }"
                       data-detail-filepath="${location.detail.filepath }"
                       data-detail-thumbFilepath="${location.detail.thumbFilepath }">${location.detail.title }</a>
                </li>
            </c:forEach>
        </ul>
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
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=KKEEWToQh6mD7cCkpGX3iolH"></script>
<script src="${context }/js/convert.js"></script>

<script>

    // 调用百度地图API显示地图
    function markLocationInMap(source, showInfoWindow) {
    	var _showInfoWindow = showInfoWindow || false;
    	
        var lng = source.attr('data-locationLng');
        var lnt = source.attr('data-locationLnt');
        var gpsPoint = new BMap.Point(lng, lnt);

        BMap.Convertor.translate(gpsPoint, 0, function (point) {
            map.centerAndZoom(point, 5);

            var marker = new BMap.Marker(point);
            map.addOverlay(marker);

            var opts = {width: 300, height:250};
            var infoHtml = "<div style='margin-top:10px' onclick='showLocationDetail(" + source.attr("data-locationId") + ")'>";
            infoHtml += "<p>" + source.attr('data-detail-title') + "<br/>[" + source.attr('data-locationName') + "]</p>"
            infoHtml += "<img src='" + context + '/' + transImagePath(source.attr('data-detail-thumbFilepath'), 's') + "'></img>"
            infoHtml += "</div>";
            var infoWindow = new BMap.InfoWindow(infoHtml, opts);
            marker.addEventListener('click', function () {
                marker.openInfoWindow(infoWindow);
            });
            
            markers[source.attr('data-locationId')] = {
            	"marker" : marker,
            	"infoWindow" : infoWindow
            };
        });
    }

    // 显示位置详情
    function showLocationDetail(locationId) {
        //$("#dateContainer").hide();
        var source = $(".locationitem a[data-locationId=" + locationId + "]");

        $("#detail-content .article").remove();
        $("#detail-content .media").html("").show();
        $("#jp_container_1").hide();

        var materialType = source.attr('data-detail-materialType');
        if (materialType.indexOf('video') != -1) {
            $("#detail-content h2").text(source.attr('data-detail-title'));
            $("#detail-content p").html(source.attr('data-detail-description') ? source.attr('data-detail-description') : '');

            //http://mediaelementjs.com/
            var _src = (context + "/" + source.attr('data-detail-filepath'));
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
            var imageSrc = context + '/' + transImagePath(source.attr('data-detail-filepath'), 'b');

            $("#detail-content .media").html("<img src='" + imageSrc + "'></img>");
            $("#detail-content h2").text(source.attr('data-detail-title'));
            $("#detail-content p").html(source.attr('data-detail-description') ? source.attr('data-detail-description') : '');
        } else if (materialType.indexOf('article') != -1) {
            $("#detail-content h2").text("");
            $("#detail-content p").html("");
            $("#detail-content .media").hide();
            $("#detail-content").append($('<div class="article"><div><h2>' + source.attr('data-detail-title') + '</h2>' +
                    (source.attr('data-detail-description') ? source.attr('data-detail-description') : '') + '</div></div>'));
        }
        $("#detail-content").css("top", $(document).scrollTop());
        console.log(source.offset().top)
        $(".modal").show();    	
    }
    
    
    // 百度地图调用
    var map;
    var markers = {};

    $(document).ready(function () {
        map = new BMap.Map("mapContainer");
        map.enableScrollWheelZoom();

        $("#main-content li.locationitem a").click(function (e) {
            // showLocation($(this));
           	// markLocationInMap($(this));
            var t = markers[$(this).attr('data-locationId')];
            t['marker'].openInfoWindow(t['infoWindow']);
        });

        $("#main-content li.locationitem a").each(function (index, element) {
            markLocationInMap($(element));
        });
        
        //$("#main-content li.locationitem a:first").click();
        
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