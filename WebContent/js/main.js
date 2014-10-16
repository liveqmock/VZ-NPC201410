/**
 * NPC Scripts
 */

;

var context = '/npc';

$(document).ready(function () {

    //首页届别浮动文字
    $("#session-nav li a").on("mouseenter", function () {
        $(this).find("span").show();
    }).on("mouseleave", function () {
        $(this).find("span").hide();
    });
    
    //二级页面全部图片浮动文字
    $("#gallery-content li a").on("mouseenter", function () {
        $(this).find("span").show();
    }).on("mouseleave", function () {
        $(this).find("span").hide();
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
        if(materialType == 'video') {
        	var player = $("<div class='video'></div>");
        	player.appendTo($("#detail-content .media"));
        	player.jPlayer({
        		   ready: function () {
        			    $(this).jPlayer("setMedia", {
        			   		// flv:"00-04-2002.flv"
        			   		flv :  context + "/" + source.attr('file'),
             			   poster: imageSrc
        			    });
        			   },
        			   swfPath: context + "/jPlayer/Jplayer.swf",
        			   supplied: "flv"
        			  });
        	
        	$("#jp_container_1").show();
        } else if(materialType == 'image') {
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
});






