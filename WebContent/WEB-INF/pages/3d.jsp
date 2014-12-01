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
                <!--<img alt="安装 Unity3d Web 播放器" src="http://webplayer.unity3d.com/installation/getunity.png"/>-->
                <p>您的电脑还未安装3D播放器，点击安装播放器</p></a>
        </div>
    </div>
</div>
<footer>
    <p><a href="#top" id="gotop"></a></p>
</footer>

<%@ include file="script.jsp" %>
<script>
    function OpenNPC(val){
        //alert("参数：" + window.location.href.replace("360", "congress/" + val));
        window.location = window.location.href.replace("3d", "congress/" + val);
    }
</script>
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
            //unityObject.embedUnity("unityPlayer", "Img/renda2.unity3d", width, parseInt(width / 1.9), { logoimage: 'image/unity/zi.png', disableContextMenu: 'true' });
            var params = {
                backgroundcolor: "A0A0A0",  <!--背景色-->
                bordercolor: "000000",  <!--边线色-->
                textcolor: "FFFFFF",   <!--字体色-->
                logoimage: "image/unity/zi.png"  <!--LOGO图片名-->
                //progressbarimage: "image/unity/zi.png", <!--加载条-->
                //progressframeimage: "image/unity/zi.png"  <!--加载条外框-->
            };
            unityObject.embedUnity("unityPlayer", "Img/renda2.unity3d", width, parseInt(width / 1.9), params);
        }
    });
</script>
</body>
</html>