<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="npc" uri="http://weizhen.com/tags/npc" %>

<%@ include file="../common.jsp" %>

<c:set var="personIds" value="-1">
</c:set>
<c:if test="${!empty persons && fn:length(persons) > 0}">
    <c:forEach var="person" items="${persons}" varStatus="row">
        <c:set var="personIds"
               value="${personIds},${person.personId}"></c:set>
    </c:forEach>
</c:if>

<%@ include file="../doctype.jsp" %>
<body>
<%@ include file="../header.jsp" %>
<div id="container">
    <div id="main-content">
        <div class="wrapper">
            <img src="" alt=""/>
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
                        <i><h4>${person.personName }</h4></i>
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
    var personIds = [${personIds}];
    var currentIndex = 1;
    var currentPersonId = personIds[currentIndex];

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
                //$("#main-content .info").text(currentIndex + " / " + (personIds.length - 1));

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

                    var _title = title;
                    if (title.length > 28) {
                        _title = subStr(title, 28);
                    }

                    var tHtml = "<li><a href='javascript:void(0)' datatitle='" + title + "' datadescription='" + description + "' file='" + file + "' "
                            + "class='" + materialType + "' title='" + title + "'><span><h4>" + _title + "</h4></span><img src='" + imgSrc + "' alt='" + title + "'></img></a></li>";
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

                    var _title = relate['imageRelatedTitle'] || '';
                    if (_title.length > 28) {
                        _title = subStr(_title, 28);
                    }

                    var tHtml = "<li><a href='javascript:void(0)' datatitle='" + relate['imageRelatedTitle'] + "' datadescription='" + content + "' file='" + relate['imageRelatedFilepath'] + "' " +
                            "class='" + materialType + "' title='" + relate['imageRelatedTitle'] + "'><span><h4>" + _title + "</h4></span><img src='" + imgSrc + "' alt='" + relate['imageRelatedTitle'] + "'></img></a></li>";
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
    });
</script>
</body>
</html>