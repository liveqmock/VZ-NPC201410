<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="common.jsp" %>

<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>人大60周年展</title>
    <link rel="stylesheet" href="css/style.css"/>
    <link rel="stylesheet" href="css/media.css"/>
    <!--[if lt IE 9]>
    <script src="js/vendor/html5.min.js"></script>
    <script src="js/vendor/respond.min.js"></script>
    <![endif]-->
    <!--[if lt IE 7]>
    <script src="js/vendor/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>
        DD_belatedPNG.fix('.png_bg');
    </script>
    <![endif]-->
</head>
<body>
	<%@ include file="header.jsp" %>
<div id="container">
    <div id="index-main-content">
        <div class="wrapper" title="进入序言">
            <img src="image/index_main.jpg" alt="进入序言" id="index-main-img"/>
        </div>
    </div>
    <div id="session-nav">
        <ul>
            <c:if test="${!empty congresses && fn:length(congresses) > 0}">
                <c:set var="rowIndex" value="1"></c:set>
                <c:forEach var="congress" items="${congresses}" varStatus="row">
                    <c:if test="${congress.congressId > 0 }">
                        <li><a href="congress/${congress.congressId }.html"> <span>
										    <h3>第${congress.congressId }届全国人民代表大会<br/>
                                                <strong>SESSION ${congress.congressId } OF THE NATIONAL PEOPLE'S
                                                    CONGRESS</strong></h3>
										    <c:if
                                                    test="${!empty congress.congressResumes && fn:length(congress.congressResumes) > 0 }">
                                                <c:forEach var="congressResume"
                                                           items="${congress.congressResumes }">
                                                    <p>${congressResume.resume }</p>
                                                </c:forEach>
                                            </c:if></span>

                            <div><img src="image/index_s${congress.congressId }.jpg" alt=""/></div>
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

<script src="js/vendor/jquery.min.js"></script>
<!--[if lt IE 7]>
<script>
    $(document).ready(function () {
        $("li, span, a").hover(function () {
            $(this).toggleClass("hover");
        });
    });
</script>
<![endif]-->
<script src="js/main.js"></script>
</body>
</html>