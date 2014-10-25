/**
 * NPC Scripts
 */

;

var context = '/npc';

// 相关资源类型
var materialTypes = ['article', 'image', 'video', 'article'];

// 解决IE不支持window.location.origin问题
if (!window.location.origin)
    window.location.origin = window.location.protocol + "//" + window.location.host;

if (!window.console) {
    window.console = {
    	debug: function(msg) {
    	},
        log: function () {
        },
        error: function () {
        },
        info: function () {
        },
        warn: function () {
        }
    };
}

// 将图片路径转为对应的大图的路径
function transImagePath(imagePath, type) {
    if (!imagePath) return '';

    var imageMainFilepath = imagePath;
    var lastIndex = imageMainFilepath.lastIndexOf('/') + 1;
    var fileName = imageMainFilepath.substring(lastIndex, imageMainFilepath.length);
    fileName = fileName.replace('.', '-' + type + '.');
    imageMainFilepath = imageMainFilepath.substring(0, lastIndex) + type + '/' + fileName;

    return imageMainFilepath;
}

// 截断中英文字符串,中文算2个字符
var subStr = function (str, len) {
    var str_length = 0;
    var str_len = 0;
    str_cut = new String();
    str_len = str.length;
    for (var i = 0; i < str_len; i++) {
        a = str.charAt(i);
        str_length++;
        if (escape(a).length > 4) {
            //中文字符的长度经编码之后大于4
            str_length++;
        }
        str_cut = str_cut.concat(a);
        if (str_length >= len) {
            str_cut = str_cut.concat("...");
            return str_cut;
        }
    }
    //如果给定字符串小于指定长度，则返回源字符串；
    if (str_length < len) {
        return  str;
    }
};

var goToPageTop = function () {
    $('body, html').animate({scrollTop: '0px'}, 500);
};

$(document).ready(function () {

    if (window.PIE) {
        $('.c3').each(function () {
            PIE.attach(this);
        });
    }

    // 搜索
    $(".nav-search").click(function () {
        if ($("#keyword").val()) {
        	window.location.href = window.location.origin + context + "/search.html?keyword=" + encodeURI($("#keyword").val());
        }
        
        return false;
    });

    if ($('#index-video-content').length > 0) {
        console.log(1)
        var player = "";
        $("#main-content a.play").click(function (e) {
            e.stopPropagation();
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

        //点击关闭视频
        $("#main-content").click(function () {
            $('#index-video-content .me-plugin').remove();
            player = "";
        });
    }

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
        t.find("span").width(t.width()).height(310).show();
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
            var imageSrc = source.find('img').prop('src')
                .replace('/s/', '/b/').replace('-s.', '-b.')
                .replace('/m/', '/b/').replace('-m.', '-b.');

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
            var imageSrc = source.find('img').prop('src')
                .replace('/s/', '/b/').replace('-s.', '-b.')
                .replace('/m/', '/b/').replace('-m.', '-b.');

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
        goToPageTop();
    });
    $("#detail-content .close a").click(function (e) {
        e.preventDefault();
        $("#relate-content, #main-content").show();
        $("#detail-content").hide();
        goToPageTop();
    });
    $("#main-content a.gallery").click(function (e) {
        e.preventDefault();
        $("#relate-content, #main-content").hide();
        $("#gallery-content").show();
        goToPageTop();
    });
    $("#gallery-content").on('click', 'a', function (e) {
        e.preventDefault();

        showImageMain($(this).attr('dataImageMainId'));

        $("#relate-content, #main-content").show();
        $("#gallery-content").hide();
        goToPageTop();
    });

    $("#gotop").click(function (e) {
        e.preventDefault();
        goToPageTop();
    });
});






