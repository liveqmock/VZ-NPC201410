<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="npc" uri="http://weizhen.com/tags/npc" %>

<%@ include file="common.jsp" %>

<%@ include file="doctype.jsp" %>
<body>
<%@ include file="header.jsp" %>

<div id="container">

    <div id="search-box">
        <input type="text" class="text" id="_keyword" value="${keyword }" placeholder="请输入搜索关键字..."/>
        <input type="submit" value="搜 索" class="btn"/>
    </div>

    <div id="nodata" style="display:none;text-align: center;">

    </div>

    <div id="gallery-content" style="display: block" class="for-search">
        <ul style="clear:both">
            <c:if test="${!empty imageMains.records && fn:length(imageMains.records) > 0}">
                <c:forEach var="imageMain" items="${imageMains.records}" varStatus="row">

                    <li>
                        <a href='javascript:void(0)' datatitle='${imageMain.imageMainTitle }'
                           datadescription='${imageMain.imageMainDescription }'
                           file='${imageMain.imageMainFilepath }'
                           class='image'
                           title='${imageMain.imageMainTitle }'>
                            <span style="display:none;">
									<h3>${fn:replace(imageMain.imageMainTitle, '　', '<br>') }</h3><br/>
									<p>
                                        ${row.index < 2 ? fn:replace(congress.congressResumeContent,'/n','<br/>') : imageMain.imageMainDescription }</p>
								</span>
                            <img src="${context }/${npc:transImagePath(imageMain.imageMainFilepath, 'm')}"
                                 alt='${imageMain.imageMainTitle }'></img>
                            <i><h4>${fn:replace(imageMain.imageMainTitle, '　', '') }</h4></i>
                        </a>
                    </li>

                </c:forEach>
            </c:if>
        </ul>
        <div class="clearfix"></div>
    </div>


    <div id="relate-content" style="display: block;width: 100%;">

        <hr/>
        <div class="media" style="width: 100%;">
            <ul>
                <c:if test="${!empty imageRelateds.records && fn:length(imageRelateds.records) > 0}">
                    <c:forEach var="imageRelated" items="${imageRelateds.records}" varStatus="row">

                        <li>
                            <a href='javascript:void(0)' datatitle='${imageRelated.imageRelatedTitle }'
                               datadescription='${imageRelated.imageRelatedDescription }'
                               file='${imageRelated.imageRelatedFilepath }'
                               class='${npc:transMaterialType(imageRelated.materialId) }'
                               title='${imageRelated.imageRelatedTitle }'>
                                <img src="${context }/${npc:transImagePath(imageRelated.imageRelatedThumbFilepath, 's')}"
                                     alt='${imageRelated.imageRelatedTitle }'></img>
                                <span><h4>${imageRelated.imageRelatedTitle }</h4></span>
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
                                <span><h4>${document.documentTitle }</h4></span>
                            </a>
                        </li>

                    </c:forEach>
                </c:if>

            </ul>
        </div>
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
    <p><a href="#top" id="gotop"></a></p>
</footer>

<%@ include file="script.jsp" %>

<script>
    $(document).ready(function () {

        var dataSize = $("#gallery-content li").size() + $("#relate-content li").size();
        if ($("#_keyword").val() && dataSize == 0) {
            $("#nodata").html("<h3>没有找到您要搜索的信息</h3>").show();
        }

        $("#search-box .btn").click(function (e) {
            window.location.href = window.location.origin + context + "/search.html?page=1&pageSize=10000&keyword=" + encodeURI($("#_keyword").val());

            return false;
        });

        $("#gallery-content a").click(function (e) {
            e.preventDefault();
            e.stopPropagation();
            showDetail($(this));
        });

    });
</script>

</body>
</html>