<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<header>
    <div id="header-content">
        <div id="logo">
            <a href="${context }/index.html" title="回到首页"></a>
        </div>
        <nav>
            <ul>
                <li class="nav-3d"><a href="${context }/3d.html"></a></li>
                <li class="nav-location"><a href="${context }/360.html"></a></li>
                <li class="nav-input"><input name="keyword" id="keyword" type="text"/></li>
                <li class="nav-search"><a href="javascript:void(0)"></a></li>
                <li class="nav-session"><a href="${context }/index.html"></a>
                    <ul>
                        <c:if test="${!empty congresses && fn:length(congresses) > 0}">
                            <c:forEach var="congress" items="${congresses}" varStatus="row">
                                <li class="nav-session-${congress.congressId }">
                                    <a href="${context }/congress/${congress.congressId }.html"></a></li>
                            </c:forEach>
                        </c:if>
                    </ul>
                </li>
                <li class="nav-discovery"><a href="#"></a>
                    <ul>
                        <li class="nav-discovery-1"><a href="#"></a></li>
                        <li class="nav-discovery-2"><a href="#"></a></li>
                        <li class="nav-discovery-3"><a href="#"></a></li>
                    </ul>
                </li>
            </ul>
            <div class="clearfix"></div>
        </nav>
    </div>
</header>