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
        debug: function (msg) {
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
    $('body, html').stop().animate({scrollTop: '0px'}, 500);
};

/**
 * 时间对象的格式化
 */

Date.prototype.format = function (format) {
    /*
     * format="yyyy-MM-dd hh:mm:ss";
     */
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    }

    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    }

    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
                : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}


// 用来存放当前显示的相关资料的类型和ID
var currentContentType;
var currentContentId;



$(document).ready(function () {

    $("img.lazy").lazyload();

    // 搜索
    $(".nav-search").click(function () {
        window.location.href = window.location.origin + context + "/search.html?page=1&pageSize=10000&keyword=" + encodeURI($("#keyword").val());

        return false;
    });

    if ($('#index-video-content').length > 0) {
        var player = "";
        $("#main-content a.play").click(function (e) {
            e.stopPropagation();
            e.preventDefault();
            if (!player) {
                $('#index-video-content').show();
                player = new MediaElement('index-video', {
                    plugins: ['flash'],
                    pluginPath: context + '/js/vendor/',
                    pauseOtherPlayers: true,
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

//    $("#index-main-img").click(function () {
//        window.location.href = window.location.origin + context + "/congress/0.html";
//    });

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
        $("#relate-content, #main-content, .for-search").hide();

        $("#detail-content .article").remove();
        $("#detail-content .media").html("").show();
        $("#jp_container_1").hide();

        var source = $(this);
        
        currentContentType = source.attr('data-contentType');
        currentContentId = source.attr('data-contentId');
        currentContentVid = source.attr('data-vid');

        var materialType = source.prop('class');

        //如果是视频
        if (materialType.indexOf('video') != -1) {
            var imageSrc = source.find('img').prop('src')
                .replace('/s/', '/b/').replace('-s.', '-b.')
                .replace('/m/', '/b/').replace('-m.', '-b.');

            $("#detail-content h2").text($(this).attr('datatitle'));
            $("#detail-content p").html($(this).attr('datadescription') ? $(this).attr('datadescription') : '');

            var _media = $("#detail-content .media");
            if(currentContentVid){
                //腾讯视频分享地址
                var _mediaHtml = '<embed src="http://static.video.qq.com/TPout.swf?vid=' + currentContentVid+'&auto=1" ' +
                    'allowFullScreen="true" quality="high" width="720" height="576" ' +
                    'align="middle" allowScriptAccess="always" type="application/x-shockwave-flash"></embed>';
                $(_mediaHtml).appendTo(_media);
            }else {
                //http://mediaelementjs.com/
                var _src = (context + "/" + source.attr('file'));
                var player = $('<video id="video" src="' + _src + '" width="720" height="576" controls="controls" preload="none"></video>');
                player.appendTo(_media);
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
            }

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

        $("#detail-content").data("source_element", source);
        $("#detail-content").show();
        goToPageTop();
        
        // 三级页面点赞恢复初始值
        $("#detail-content a[data-action=fav]").text("赞");
    });


    $("#detail-content .close a").click(function (e) {
        e.preventDefault();
        $("#relate-content, #main-content, .for-search").show();
        $("#detail-content").hide();
        goToPageTop();
    });

    $("#detail-content .previous a").click(function (e) {
        e.preventDefault();

        var sourceElement = $("#detail-content").data("source_element");
        if (sourceElement) {
            var prev = sourceElement.parent("li").prev("li");
            if (prev) {
                prev.children("a").click();
            }
        }
    });

    $("#detail-content .next a").click(function (e) {
        e.preventDefault();

        var sourceElement = $("#detail-content").data("source_element");
        if (sourceElement) {
            var prev = sourceElement.parent("li").next("li");
            if (prev) {
                prev.children("a").click();
            }
        }
    });
    
    
    // 三级页面点赞及分享
    $("#detail-content a[data-action=fav]").click(function(e){
        e.preventDefault();
        var fav = $(this).siblings(".favtip");
        var source = $(this);

        if (currentContentId) {
            $.ajax({
                type: "GET",
                url: context + "/fav.html",
                data: {
                    resourceType: currentContentType,
                    resourceId: currentContentId
                },
                success: function (data) {
                    if (data.success) {
                    	source.text("赞(" + data.data + ")");
                    	fav.text("点赞成功");
                    } else {
                    	source.text("赞(" + data.data + ")");
                    	fav.text(data.msg);
                    }
                    
                    fav.show();
                    setTimeout(function () {
                        fav.hide();
                    }, 2000);
                }
            });        	
        }    	
    });
    $("#detail-content a[data-action=share]").click(function (e) {
        e.preventDefault();
        try {
        	var entry = {
        		title: "全国人民代表大会成立60周年网上纪念展",
        		summary: $("#detail-content h2").text()
        	};
        	
        	if ($("#detail-content div.media img").size() > 0) {
        		entry['pic'] = $("#detail-content div.media img").attr("src");
        	}
        	
            bShare.addEntry(entry);
            bShare.more(event);
        } catch (e) {
        }
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

    $("#share").click(function (e) {
        e.preventDefault();
        try {
            bShare.addEntry({
                title: "全国人民代表大会成立60周年网上纪念展",
                summary: $("#relate-content .text #imageMainTitle").html(),
                pic: window.location.origin + "/" + $("img.imageMainBig").attr("src")
            });
            bShare.more(event);
        } catch (e) {
        }
    });

    $("#fav").click(function (e) {
        e.preventDefault();
        var fav = $(this).siblings(".favtip");
        var source = $(this);
        
        if (currentImageMainId) {
            $.ajax({
                type: "GET",
                url: context + "/fav.html",
                data: {
                    resourceType: 'ImageMain',
                    resourceId: currentImageMainId
                },
                success: function (data) {
                    if (data.success) {
                    	source.text("赞(" + data.data + ")");
                    	fav.text("点赞成功");
                    } else {
                    	source.text("赞(" + data.data + ")");
                    	fav.text(data.msg);
                    }
                    
                    fav.show();
                    setTimeout(function () {
                        fav.hide();
                    }, 2000);
                }
            });        	
        }
    });

});


function showDetail(source) {
    $("#relate-content, #main-content, .for-search").hide();

    $("#detail-content .article").remove();
    $("#detail-content .media").html("").show();
    $("#jp_container_1").hide();

    var materialType = source.prop('class');
    if (materialType.indexOf('video') != -1) {
        var imageSrc = source.find('img').prop('src')
            .replace('/s/', '/b/').replace('-s.', '-b.')
            .replace('/m/', '/b/').replace('-m.', '-b.');

        $("#detail-content h2").html(source.attr('datatitle').replace('　', ''));
        $("#detail-content p").html(source.attr('datadescription') || '');

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
        $("#detail-content h2").html(source.attr('datatitle').replace('　', ''));
        $("#detail-content p").html(source.attr('datadescription')  || '');
    } else if (materialType.indexOf('article') != -1) {
        $("#detail-content h2").text("");
        $("#detail-content p").html("");
        $("#detail-content .media").hide();
        $("#detail-content").append($('<div class="article"><div><h2>' + $(this).attr('datatitle') + '</h2>' +
            ($(this).attr('datadescription') ? $(this).attr('datadescription') : '') + '</div></div>'));
    }

    $("#detail-content").data("source_element", source);

    $("#detail-content").show();
    goToPageTop();
}





