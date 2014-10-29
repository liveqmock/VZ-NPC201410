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
    <div id="unityPlayer">
        <div class="missing">
            <a href="http://unity3d.com/cn/webplayer" title="安装 Unity3d Web 播放器">
                <img alt="安装 Unity3d Web 播放器" src="http://webplayer.unity3d.com/installation/getunity.png"/>

                <p>您的电脑还未安装3D播放器，点击安装播放器</p></a>
        </div>
    </div>
</div>
<footer>
    <p><a href="#top" id="gotop"></a></p>
</footer>

<%@ include file="script.jsp" %>
<script src="js/vendor/UnityObject.js"></script>
<script>
    $(document).ready(function () {
        $("li, span, a").hover(function () {
            $(this).toggleClass("hover");
        });

        function GetUnity() {
            if (typeof unityObject != "undefined") {
                return unityObject.getObjectById("unityPlayer");
            }
            return null;
        }

        var width = $("#unityPlayer").width();

        if (typeof unityObject != "undefined") {
            unityObject.embedUnity("unityPlayer", "Img/renda2.unity3d", width, parseInt(width / 1.9), { logoimage: 'images/unity/zi.png', disableContextMenu: 'true' });
        }

        function OpenNPC(id) {
            if (id >= 0 && id <= 12) {
                window.location.href = "congress/" + id + ".html";
            }
        }
    });
</script>
</body>
</html>