/**
 * NPC Scripts
 */

;

var context = '/npc';

$(document).ready(function () {

    if (window.PIE) {
        $('.c3').each(function () {
            PIE.attach(this);
        });
    }

    //首页届别浮动文字
    $("#session-nav li a").on("mouseenter", function () {
        $(this).find("span").show();
        $(this).find("img").hide();
    }).on("mouseleave", function () {
        $(this).find("span").hide();
        $(this).find("img").show();
    });

    //二级页面全部图片浮动文字
    $("#gallery-content li a").on("mouseenter", function () {
        var t = $(this);
        t.find("span").width(t.width()).height(t.height()).show();
    }).on("mouseleave", function () {
        var t = $(this);
        t.find("span").hide();
    });

    //子页面相关信息显示隐藏
    $("#relate-content .media").on('click', 'a', function (e) {
        e.preventDefault();
        $("#relate-content, #main-content").hide();

        $("#detail-content .media").html("");
        $("#jp_container_1").hide();

        var source = $(this);

        var imageSrc = source.find('img').prop('src').replace('/s/', '/b/').replace('-s.', '-b.');
        var materialType = source.prop('class');
        if (materialType == 'video') {
            //http://mediaelementjs.com/
            var _src = (context + "/" + source.attr('file'));
            var player = $('<video id="video" src="' + _src + '" width="720" height="576" controls="controls" preload="none">' +
                '<source type="video/flv" src="' + _src + '" />' +
                '<object width="720" height="576" type="application/x-shockwave-flash" data="' + context + '/js/vendor/flashmediaelement.swf">' +
                '<param name="movie" value="' + context + '/js/vendor/flashmediaelement.swf" />' +
                '<param name="flashvars" value="controls=true&file=' + _src + '" />' +
                '<img src="' + imageSrc + '" width="720" height="576" />' +
                '</object>' +
                '</video>');
            player.appendTo($("#detail-content .media"));
            $('#detail-content .media video').mediaelementplayer();

        } else if (materialType == 'image') {
            $("#detail-content .media").html("<img src='" + imageSrc + "'></img>");
        }

        $("#detail-content h2").text($(this).attr('datatitle'));
        $("#detail-content p").text($(this).attr('datadescrition'));

        $("#detail-content").show();
    });
    $("#detail-content .close").click(function (e) {
        e.preventDefault();
        $("#relate-content, #main-content").show();
        $("#detail-content").hide();
    });
    $("#main-content a.gallery").click(function (e) {
        e.preventDefault();
        $("#relate-content, #main-content").hide();
        $("#gallery-content").show();
    });
    $("#gallery-content").on('click', 'a', function (e) {
        e.preventDefault();

        showImageMain($(this).attr('dataImageMainId'));

        $("#relate-content, #main-content").show();
        $("#gallery-content").hide();
    });

    $("#gotop").click(function (e) {
        e.preventDefault();
        $('body, html').animate({scrollTop: '0px'}, 500);
    });
});






