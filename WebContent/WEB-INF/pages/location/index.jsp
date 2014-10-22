<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="context" value="/npc">
</c:set>

<!DOCTYPE html>
<html>
<head lang="zh-cn">
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>地点主题</title>
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
       		<span style="display:block; width:700px;height:460px;border:1px solid gray; margin:2px auto;" id="mapContainer"</span>
        </div>
        <nav>
            <ul>
			<c:forEach var="location" items="${locations}" varStatus="row">
				<li class="locationitem">
					<a href="javascript:void(0)" title="${location.locationName }" class="png_bg"
						data-locationId="${location.locationId }" 
						data-locationLng="${location.locationLng }"
						data-locationLnt="${location.locationLat }"
						data-locationName="${location.locationName }"
						data-locationTitle="${location.locationTitle }"
						data-locationResume="${location.locationResume }"></a>
				</li>
			</c:forEach>
            </ul>
        </nav>
        <span class="tbar"></span>
        <span class="info"></span> 
    </div>
    <div id="relate-content">
        <div class="text">
            <h2 name="title">${firstImageMain.imageMainTitle }</h2>
            <p name="description">${firstImageMain.imageMainDescription }</p>
        </div>
        <div class="media">
            <ul>
            </ul>
        </div>
        <div class="clearfix"></div>
    </div>
    <div id="detail-content">
        <a href="javascript:void(0)" class="close png_bg"></a>

        <div class="media">
            <img src="data:image/gif;base64,R0lGODlhBAABAIABAMLBwfLx8SH5BAEAAAEALAAAAAAEAAEAAAICRF4AOw==" alt=""/>
        </div>

        <h2 name="title"></h2>
        <p name="description"></p>
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
    var materialTypes = ['article', 'image', 'video', 'article'];

    // 将图片路径转为对应的大图的路径
    function transImagePath(imagePath, type) {
        var imageMainFilepath = imagePath;
        var lastIndex = imageMainFilepath.lastIndexOf('/') + 1;
        var fileName = imageMainFilepath.substring(lastIndex, imageMainFilepath.length);
        fileName = fileName.replace('.', '-' + type + '.');
        imageMainFilepath = imageMainFilepath.substring(0, lastIndex) + type + '/' + fileName;

        return imageMainFilepath;
    }

    function showLocation(locationInfo) {
    	var locationId = locationInfo['locationId'] * 1;

        $.ajax("${context}/location/" + locationId + ".html", {
            type: 'get',
            dataType: 'json',
            async: true,
            success: function (data, textStatus, jqXHR) {
                $("#relate-content .text h2").text(locationInfo['locationTitle']);
                $("#relate-content .text p").text(locationInfo['locationResume']);
                
                $("#relate-content .media ul").html('');

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
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                //alert("获取内容失败,请稍后重试");
            }
        });
    }
    
    // 百度地图调用
    var map; 
    
    $(document).ready(function () {
    	map = new BMap.Map("mapContainer"); 
    	map.enableScrollWheelZoom();
    	
        $("#main-content li.locationitem a").click(function (e) {
        	var source = $(this);	
        	
           	// 调用百度地图API显示地图
        	var lng = source.attr('data-locationLng');
        	var lnt = source.attr('data-locationLnt');
        	var gpsPoint = new BMap.Point(lng,lnt);
        	
        	BMap.Convertor.translate(gpsPoint,0,function(point){
        		map.centerAndZoom(point, 5);
            	
            	var marker=new BMap.Marker(point);  
            	map.addOverlay(marker); 
            	
            	var opts = {width: 300};
            	var infoWindow = new BMap.InfoWindow("<b>" + source.attr('data-locationName') + "</b>", opts); 
           	    marker.addEventListener('click',function(){
           	        marker.openInfoWindow(infoWindow);
           	    });
        	});
        	
        	// 显示地点主题相关信息
        	var locatinInfo = {
        		"locationId" : source.attr('data-locationId'),
        		"locationTitle" : source.attr('data-locationTitle'),
        		"locationResume" : source.attr('data-locationResume')
        	};
        	showLocation(locatinInfo);
        });


        $("#main-content li.locationitem a:first").click();
    });
</script>

</body>
</html>