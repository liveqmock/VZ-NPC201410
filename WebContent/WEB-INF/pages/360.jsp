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
<div id="container">
    <div id="flashContent">
        <object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" id="jinsedating_demo1"
                align="middle">
            <param name="movie" value="jinsedating_demo1.swf">
            <param name="quality" value="high">
            <param name="bgcolor" value="#ffffff">
            <param name="play" value="true">
            <param name="loop" value="true">
            <param name="wmode" value="window">
            <param name="scale" value="showall">
            <param name="menu" value="true">
            <param name="devicefont" value="false">
            <param name="salign" value="">
            <param name="allowScriptAccess" value="sameDomain">
            <!--[if !IE]>-->
            <object type="application/x-shockwave-flash" data="jinsedating_demo1.swf" align="middle">
                <param name="movie" value="jinsedating_demo1.swf">
                <param name="quality" value="high">
                <param name="bgcolor" value="#ffffff">
                <param name="play" value="true">
                <param name="loop" value="true">
                <param name="wmode" value="window">
                <param name="scale" value="showall">
                <param name="menu" value="true">
                <param name="devicefont" value="false">
                <param name="salign" value="">
                <param name="allowScriptAccess" value="sameDomain">
                <!--<![endif]-->
                <a href="http://www.adobe.com/go/getflash">
                    <img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif"
                         alt="获得 Adobe Flash Player">
                </a>
                <!--[if !IE]>-->
            </object>
            <!--<![endif]-->
        </object>
    </div>
    <%@ include file="script.jsp" %>
</body>
</html>