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

<script src="js/vendor/jquery.min.js"></script>
<script src="js/vendor/UnityObject.js"></script>
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