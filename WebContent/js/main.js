/**
 * NPC Scripts
 */

;
$(document).ready(function () {

    //首页届别浮动文字
    $("#session-nav li a").on("mouseenter", function () {
        $(this).find("span").show();
    }).on("mouseleave", function () {
        $(this).find("span").hide();
    });

    //子页面相关信息显示隐藏
    $("#relate-content .media").on('click', 'a', function (e) {
        e.preventDefault();
        $("#relate-content, #main-content").hide();
        
        $("#detail-content .media").html(this.innerHTML);
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






