/**
 * NPC Scripts
 */

;

var context = '/npc';

// 相关资源类型
var materialTypes = ['article', 'image', 'video', 'article'];

// 将图片路径转为对应的大图的路径
function transImagePath(imagePath, type) {
	if(!imagePath) return '';
	
    var imageMainFilepath = imagePath;
    var lastIndex = imageMainFilepath.lastIndexOf('/') + 1;
    var fileName = imageMainFilepath.substring(lastIndex, imageMainFilepath.length);
    fileName = fileName.replace('.', '-' + type + '.');
    imageMainFilepath = imageMainFilepath.substring(0, lastIndex) + type + '/' + fileName;

    return imageMainFilepath;
}

$(document).ready(function () {

    if (window.PIE) {
        $('.c3').each(function () {
            PIE.attach(this);
        });
    }
    
    // 搜索
    $(".nav-search").on("click", function() {
		if($("#keyword").val())
			document.location.href=context + "/search.html?keyword=" + encodeURI($("#keyword").val());
	});

    var player = "";
    $("#index-main-content a.play").click(function (e) {
        e.preventDefault();
        if (!player) {
            $('#index-video-content').show();
            player = new MediaElement('index-video', {
                plugins: ['flash'],
                pluginPath: context + '/js/vendor/',
                flashName: 'flashmediaelement.swf',
                success: function (mediaElement, domObject) {
                    $(this).on('click', function () {
                        if (mediaElement.paused) {
                            mediaElement.play();
                        } else {
                            mediaElement.pause();
                        }
                    });
                    mediaElement.play();
                },
                error: function () {
                    if ($(".me-cannotplay").length > 0) {
                        $(".me-cannotplay").html('<h2>您的电脑没有安装flash播放器，无法观看视频。' +
                            '<a href="http://get.adobe.com/cn/flashplayer" target="_blank">点击下载安装</a></h2>')
                    } else {
                        alert("您的电脑没有安装flash播放器，无法观看视频。");
                    }
                }
            });
        } else {
            if (player.paused) {
                player.play();
            } else {
                player.pause();
            }
        }
    });

    $("#index-main-img").click(function () {
        window.location.href = window.location.origin + context + "/congress/0.html";
    });

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

        $("#detail-content .article").remove();
        $("#detail-content .media").html("").show();
        $("#jp_container_1").hide();

        var source = $(this);

        var materialType = source.prop('class');
        if (materialType.indexOf('video') != -1) {
            var imageSrc = source.find('img').prop('src').replace('/s/', '/b/').replace('-s.', '-b.');

            $("#detail-content h2").text($(this).attr('datatitle'));
            $("#detail-content p").html($(this).attr('datadescription') ? $(this).attr('datadescription') : '');

            //http://mediaelementjs.com/
            var _src = (context + "/" + source.attr('file'));
            var player = $('<video id="video" src="' + _src + '" width="720" height="576" controls="controls" preload="none"></video>');
            player.appendTo($("#detail-content .media"));
            var me = new MediaElement('video', {
                plugins: ['flash'],
                pluginPath: context + '/js/vendor/',
                flashName: 'flashmediaelement.swf',
                success: function (mediaElement, domObject) {
                    $(this).on('click', function () {
                        if (mediaElement.paused) {
                            mediaElement.play();
                        } else {
                            mediaElement.pause();
                        }
                    });
                    mediaElement.play();
                },
                error: function () {
                    if ($(".me-cannotplay").length > 0) {
                        $(".me-cannotplay").html('<h2>您的电脑没有安装flash播放器，无法观看视频。' +
                            '<a href="http://get.adobe.com/cn/flashplayer" target="_blank">点击下载安装</a></h2>')
                    } else {
                        alert("您的电脑没有安装flash播放器，无法观看视频。");
                    }
                }
            });

        } else if (materialType.indexOf('image') != -1) {
            var imageSrc = source.find('img').prop('src').replace('/s/', '/b/').replace('-s.', '-b.');
            $("#detail-content .media").html("<img src='" + imageSrc + "'></img>");
            $("#detail-content h2").text($(this).attr('datatitle'));
            $("#detail-content p").html($(this).attr('datadescription') ? $(this).attr('datadescription') : '');
        } else if (materialType.indexOf('article') != -1) {
            $("#detail-content h2").text("");
            $("#detail-content p").html("");
            $("#detail-content .media").hide();
            $("#detail-content").append($('<div class="article"><div><h2>' + $(this).attr('datatitle') + '</h2>' +
                ($(this).attr('datadescription') ? $(this).attr('datadescription') : '') + '</div></div>'));
        }

        $("#detail-content").show();

        $('body, html').animate({scrollTop: '0px'}, 500);
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






