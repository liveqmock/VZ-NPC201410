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

<div id="indexPlayerClose" title="关闭"></div>
<div id="indexPlayer"><img src="image/index_main_b.jpg"/></div>
<div id="container">
    <div id="index-main-content">
        <div class="wrapper">
            <img src="image/index_main3.jpg" alt="全国人大成立60周年网上纪念展"/>
        </div>
    </div>
    <div id="session-nav">
        <ul>
            <li><a href="congress/0.html">
                <span>
                    <h3>序——历史的选择 ${congress.congressDescription } <strong>（1954-2014）</strong></h3>
                    <p>“60年的实践充分证明，人民代表大会制度是符合中国国情和实际、体现社会主义国家性质、
                        保证人民当家作主、保障实现中华民族伟大复兴的好制度。” ——摘自“庆祝全国人大成立60周年”习近平总书记讲话</p>
                </span>

                <div><img src="image/index_s0.jpg" alt="" class=""/></div>
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

                            <div><img src="image/index_s${congress.congressId }.jpg" alt="" class=""/>
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
<script>
    $(document).ready(function () {

        function hideAd() {
            $("#indexPlayerClose").hide();
            $("#indexPlayer").animate({
                top: -dheight
            }, 1000, function () {
                $(this).hide();
                $("body").css("overflow", "auto");
            });
        }

        var dwidth = $(document).width();
        var dheight = document.documentElement.clientHeight;

        $("#indexPlayer").css({
            width: dheight * 2.8286,
            height: dheight,
            "margin-left": -dheight * 2.8286 / 2
        }).show();

        //把关闭按钮始终定位在右上角
        $("#indexPlayerClose").css("left", dwidth / 2 + ($("#indexPlayer img").width() > dwidth ? dwidth / 2 : $("#indexPlayer img").width() / 2) - 60)
                .show().click(function () {
                    hideAd();
                });


        $("body").css("overflow-x", "hidden");

		if(window.location.href.indexOf("style2") > 0) {
		    $("#index-main-content img").attr("src", "image/index_main4.jpg");
			
		    $("#session-nav li a").each(function(){
				var t = $(this);
				var img = t.find("img")
			    img.attr("src", img.attr("src").replace("index_s", "index2_s"));
				t.css("background", "#b28951");
				t.find("span").css("background", "#b28951");
			});

			
		}



        setTimeout(hideAd, 5000);
    });
</script>
</body>
</html>