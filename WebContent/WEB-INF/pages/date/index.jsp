<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="npc" uri="http://weizhen.com/tags/npc" %>

<%@ include file="../common.jsp" %>

<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>人大60周年展 - 时间专题</title>
    <link rel="stylesheet" href="${context }/css/style.css"/>
    <link rel="stylesheet" href="${context }/css/media.css"/>
    <link rel="stylesheet" href="${context }/js/vendor/mediaelementplayer.min.css"/>
    <!--[if lt IE 9]>
    <script src="${context }/js/vendor/html5.min.js"></script>
    <script src="${context }/js/vendor/respond.min.js"></script>
    <![endif]-->
    <!--[if lt IE 7]>
    <script src="${context }/js/vendor/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>
        DD_belatedPNG.fix('.png_bg');
    </script>
    <![endif]-->
</head>
<body>
<%@ include file="../header.jsp" %>

<div id="container">
    <div id="dateContainer">

        <div class="year" class="png_bg">
            <span name="year" class="png_bg">1500年</span>
            <ul>
                <li class="png_bg"><a href="#">2月</a>
                    <ul class="right">
                        <li><a href="javascript:void(0)" datatitle="筹备召开第一届全国人民代表大会，成立宪法起草委员会" datadescription=""
                               file="Img/Image_Related/01-22-2001.flv" class="video"
                               title="筹备召开第一届全国人民代表大会，成立宪法起草委员会"><span><h4>筹备召开第一届全国人民代表大会，成立宪法起草委员会</h4></span><img
                                src="/npc/Img/Image_Related/s/01-22-2001-s.jpg" alt="筹备召开第一届全国人民代表大会，成立宪法起草委员会"></a>
                        </li>
                        <li><a href="javascript:void(0)" datatitle="新中国成立以来第一次普选基层人大代表" datadescription=""
                               file="Img/Image_Related/01-22-2002.flv" class="video"
                               title="新中国成立以来第一次普选基层人大代表"><span><h4>新中国成立以来第一次普选基层人大代表</h4></span><img
                                src="/npc/Img/Image_Related/s/01-22-2002-s.jpg" alt="新中国成立以来第一次普选基层人大代表"></a></li>
                        <li><a href="javascript:void(0)" datatitle="毛泽东、朱德、刘少奇、周恩来等党和国家领导参加投票选举基层人大代表"
                               datadescription="" file="Img/Image_Related/01-22-2003.flv" class="video"
                               title="毛泽东、朱德、刘少奇、周恩来等党和国家领导参加投票选举基层人大代表"><span><h4>毛泽东、朱德、刘少奇、周恩来...</h4></span><img
                                src="/npc/Img/Image_Related/s/01-22-2003-s.jpg" alt="毛泽东、朱德、刘少奇、周恩来等党和国家领导参加投票选举基层人大代表"></a>
                        </li>
                        <li><a href="javascript:void(0)" datatitle="第一届全国人民代表大会开幕-代表入场" datadescription=""
                               file="Img/Image_Related/01-22-2004.flv" class="video"
                               title="第一届全国人民代表大会开幕-代表入场"><span><h4>第一届全国人民代表大会开幕-代表入场</h4></span><img
                                src="/npc/Img/Image_Related/s/01-22-2004-s.jpg" alt="第一届全国人民代表大会开幕-代表入场"></a></li>
                        <li><a href="javascript:void(0)" datatitle="周恩来在第一届全国人民代表大会第一次会议上作政府工作报告" datadescription=""
                               file="Img/Image_Related/01-22-2005.flv" class="video"
                               title="周恩来在第一届全国人民代表大会第一次会议上作政府工作报告"><span><h4>
                            周恩来在第一届全国人民代表大会第一次会议上作政府工作报告</h4></span><img src="/npc/Img/Image_Related/s/01-22-2005-s.jpg"
                                                                         alt="周恩来在第一届全国人民代表大会第一次会议上作政府工作报告"></a></li>
                        <li><a href="javascript:void(0)" datatitle="蔡畅在一届全国人大一次会议上发言。" datadescription=""
                               file="Img/Image_Related/01-22-1001.jpg" class="image"
                               title="蔡畅在一届全国人大一次会议上发言。"><span><h4>蔡畅在一届全国人大一次会议上发言。</h4></span><img
                                src="/npc/Img/Image_Related/s/01-22-1001-s.jpg" alt="蔡畅在一届全国人大一次会议上发言。"></a></li>
                        <li><a href="javascript:void(0)"
                               datatitle="出席一届全国人大一次会议的几位女代表。（从左到右第二人起）：邓颖超　（汉族）、赛力玛・塔力甫瓦（维吾尔族）、黄莲辉（僮族）、蒙素芬（布依族）、李桂英（彝族）、黎明（僮族）。"
                               datadescription="" file="Img/Image_Related/01-22-1002.jpg" class="image"
                               title="出席一届全国人大一次会议的几位女代表。（从左到右第二人起）：邓颖超　（汉族）、赛力玛・塔力甫瓦（维吾尔族）、黄莲辉（僮族）、蒙素芬（布依族）、李桂英（彝族）、黎明（僮族）。"><span><h4>
                            出席一届全国人大一次会议的几...</h4></span><img src="/npc/Img/Image_Related/s/01-22-1002-s.jpg"
                                                              alt="出席一届全国人大一次会议的几位女代表。（从左到右第二人起）：邓颖超　（汉族）、赛力玛・塔力甫瓦（维吾尔族）、黄莲辉（僮族）、蒙素芬（布依族）、李桂英（彝族）、黎明（僮族）。"></a>
                        </li>
                        <li><a href="javascript:void(0)" datatitle="出席一届全国人大一次会议的代表  （左起）华罗庚、老舍、梁思成、梅兰芳在会议休息时交谈。"
                               datadescription="" file="Img/Image_Related/01-22-1003.jpg" class="image"
                               title="出席一届全国人大一次会议的代表  （左起）华罗庚、老舍、梁思成、梅兰芳在会议休息时交谈。"><span><h4>
                            出席一届全国人大一次会议的代...</h4></span><img src="/npc/Img/Image_Related/s/01-22-1003-s.jpg"
                                                              alt="出席一届全国人大一次会议的代表  （左起）华罗庚、老舍、梁思成、梅兰芳在会议休息时交谈。"></a>
                        </li>
                        <li><a href="javascript:void(0)" datatitle="出席一届全国人大一次会议的上海市工商界代表在会议休息时交谈。自左至右：汤蒂因、荣毅仁、胡厥文、王志莘。"
                               datadescription="" file="Img/Image_Related/01-22-1004.jpg" class="image"
                               title="出席一届全国人大一次会议的上海市工商界代表在会议休息时交谈。自左至右：汤蒂因、荣毅仁、胡厥文、王志莘。"><span><h4>
                            出席一届全国人大一次会议的上...</h4></span><img src="/npc/Img/Image_Related/s/01-22-1004-s.jpg"
                                                              alt="出席一届全国人大一次会议的上海市工商界代表在会议休息时交谈。自左至右：汤蒂因、荣毅仁、胡厥文、王志莘。"></a>
                        </li>
                        <li><a href="javascript:void(0)"
                               datatitle="出席一届全国人大一次会议的几位女代表。自左至右：胡文秀、吴志珍（苗族）、蒙素芬（布依族）、常香玉、绕西・泽仁卓玛（藏族）、申纪兰、裔式娟。"
                               datadescription="" file="Img/Image_Related/01-22-1005.jpg" class="image"
                               title="出席一届全国人大一次会议的几位女代表。自左至右：胡文秀、吴志珍（苗族）、蒙素芬（布依族）、常香玉、绕西・泽仁卓玛（藏族）、申纪兰、裔式娟。"><span><h4>
                            出席一届全国人大一次会议的几...</h4></span><img src="/npc/Img/Image_Related/s/01-22-1005-s.jpg"
                                                              alt="出席一届全国人大一次会议的几位女代表。自左至右：胡文秀、吴志珍（苗族）、蒙素芬（布依族）、常香玉、绕西・泽仁卓玛（藏族）、申纪兰、裔式娟。"></a>
                        </li>
                        <li><a href="javascript:void(0)" datatitle="出席一届全国人大一次会议的几位农民代表。从右到左：梁军、李顺达、邓国章、吴春安、苏殿选。"
                               datadescription="" file="Img/Image_Related/01-22-1006.jpg" class="image"
                               title="出席一届全国人大一次会议的几位农民代表。从右到左：梁军、李顺达、邓国章、吴春安、苏殿选。"><span><h4>
                            出席一届全国人大一次会议的几...</h4></span><img src="/npc/Img/Image_Related/s/01-22-1006-s.jpg"
                                                              alt="出席一届全国人大一次会议的几位农民代表。从右到左：梁军、李顺达、邓国章、吴春安、苏殿选。"></a>
                        </li>
                        <li><a href="javascript:void(0)" datatitle="1954年9月，全国人大代表、内蒙古自治区人民政府主席乌兰夫（右二）深入牧区访问。"
                               datadescription="" file="Img/Image_Related/01-22-1007.jpg" class="image"
                               title="1954年9月，全国人大代表、内蒙古自治区人民政府主席乌兰夫（右二）深入牧区访问。"><span><h4>
                            1954年9月，全国人大代表、内蒙...</h4></span><img src="/npc/Img/Image_Related/s/01-22-1007-s.jpg"
                                                                 alt="1954年9月，全国人大代表、内蒙古自治区人民政府主席乌兰夫（右二）深入牧区访问。"></a>
                        </li>
                        <li><a href="javascript:void(0)" datatitle="一届全国人民代表大会一次会议出席证" datadescription=""
                               file="Img/Image_Related/01-22-1008.jpg" class="image"
                               title="一届全国人民代表大会一次会议出席证"><span><h4>一届全国人民代表大会一次会议出席证</h4></span><img
                                src="/npc/Img/Image_Related/s/01-22-1008-s.jpg" alt="一届全国人民代表大会一次会议出席证"></a></li>
                        <li><a href="javascript:void(0)" datatitle="张澜（右）、郭沫若（左）在一届全国人大一次会议上。" datadescription=""
                               file="Img/Image_Related/01-26-1002.jpg" class="image"
                               title="张澜（右）、郭沫若（左）在一届全国人大一次会议上。"><span><h4>张澜（右）、郭沫若（左）在一届全国人大一次会议上。</h4></span><img
                                src="/npc/Img/Image_Related/s/01-26-1002-s.jpg" alt="张澜（右）、郭沫若（左）在一届全国人大一次会议上。"></a>
                        </li>
                        <li><a href="javascript:void(0)" class="article png_bg" datatitle="一届全国人大一次会议隆重开幕"
                               datadescription="<p>9月15日―28日一届全国人大一次会议举行。</p><p>15日，一届全国人大一次会议开幕。代表总数1226人，实到代表1141人。中央人民政府主席毛泽东主持开幕式并致开幕词。毛泽东指出，我们这次会议具有伟大的历史意义。这次会议是标志着我国人民从1949年建国以来的新胜利和新发展的里程碑，这次会议所制定的宪法将大大地促进我国的社会主义事业。我们的总任务是团结全国人民，争取一切国际朋友的支援，为了建设一个伟大的社会主义国家而奋斗，为了保卫国际和平和发展人类进步事业而奋斗。我国人民应当努力工作，努力学习苏联和各兄弟国家的先进经验，老老实实，勤勤恳恳，互勉互助，力戒任何的虚夸和骄傲，准备在几个五年计划之内，将我们现在这样一个经济上文化上落后的国家，建设成为一个工业化的具有高度现代文化程度的伟大的国家。毛泽东强调，领导我们事业的核心力量是中国共产党，指导我们思想的理论基础是马克思列宁主义。</p><p>会议选举产生由97人组成的大会主席团和秘书长。主席团成员有：毛泽东、王崇伦、司徒美堂、朱德、朱德海、何香凝、吴玉章、吴耀宗、宋庆龄、李四光、李先念、李顺达、李德全、李济深、李烛尘、沈雁冰、沈钧儒、周文江、周恩来、林巧稚、林伯渠、林彪、林枫、果基木古、竺可桢、柳亚子、胡子昂、胡耀邦、范永、韦国清、徐向前、徐特立、桑吉悦希、乌兰夫、班禅额尔德尼・确吉坚赞、马叙伦、马寅初、高崇民、崔建功、张治中、张奚若、张闻天、张难先、张澜、梁希、梅兰芳、章伯钧、习仲勋、许广平、许德珩、郭沫若、陈少敏、陈叔通、陈绍宽、陈云、陈经畲、陈嘉庚、陈毅、傅作义、嵇文甫、彭真、彭德怀、彭泽民、盛丕华、程潜、华罗庚、贺龙、黄长水、黄炎培、杨明轩、叶剑英、董必武、达赖喇嘛・丹增嘉措、荣毅仁、熊克武、刘少奇、刘伯承、刘格平、刘鸿生、刘澜涛、欧百川、蔡廷锴、蔡畅、邓子恢、刘芳芝、邓宝珊、巩天民、赖若愚、龙云、薄一波、谢觉哉、赛福鼎、韩恩、韩望尘、聂荣臻、罗荣桓、谭平山，李维汉为秘书长。</p><p>会议通过一届全国人大一次会议议程。</p><p>同日，一届全国人大一次会议主席团举行第一次会议。会议推选毛泽东、刘少奇、周恩来、宋庆龄、李济深、张澜、黄炎培、郭沫若、陈叔通为主席团常务主席，决定余心清、吴克坚、汪锋、辛志超、邢西萍、屈武、孙起孟、张苏、许广平、杨静仁、齐燕铭为大会副秘书长。</p><p>16日，一届全国人大一次会议举行全体会议。会议通过一届全国人大代表资格审查委员会主任委员、委员名单。代表资格审查委员会由19人组成，马明方为主任委员；委员有：王维舟、平杰三、朱蕴山、吴芝圃、成柏仁、李永、李澄之、车向忱、周素园、胡厥文、徐立清、涂长望、庄希泉、陈汝棠、杨之华、杨静仁、郑振铎、罗叔章。</p><p>会议还通过一届全国人大一次会议提案审查委员会主任委员、委员名单。提案审查委员会由25人组成，习仲勋为主任委员；委员有：丁西林、王任重、朱学范、吴有训、吴克坚、吴耀宗、李国伟、李德全、李颉伯、周钦岳、邵力子、孙志远、高崇民、章乃器、郭棣活、陈此生、陈劭先、陈其瑗、乔传珏、曾希圣、杨秀峰、杨显东、包尔汉、薄一波。</p><p>16日―26日，会议就各项主要议程进行分组审议和大会发言。共有164名代表在大会上发言。</p><p>18日，一届全国人大一次会议举行全体会议。会议听取并通过代表资格审查委员会主任委员马明方关于代表资格的审查报告。报告确认本届代表大会1226名代表的代表资格有效。</p>"
                               title="一届全国人大一次会议隆重开幕"><span><h4>一届全国人大一次会议隆重开幕</h4></span></a></li>
                    </ul>
                </li>
                <div class="clearfix"></div>
                <li class="png_bg"><a href="#">4月</a>

                    <ul class="left">
                        <li><a href="javascript:void(0)" datatitle="筹备召开第一届全国人民代表大会，成立宪法起草委员会" datadescription=""
                               file="Img/Image_Related/01-22-2001.flv" class="video"
                               title="筹备召开第一届全国人民代表大会，成立宪法起草委员会"><span><h4>筹备召开第一届全国人民代表大会，成立宪法起草委员会</h4></span><img
                                src="/npc/Img/Image_Related/s/01-22-2001-s.jpg" alt="筹备召开第一届全国人民代表大会，成立宪法起草委员会"></a>
                        </li>
                        <li><a href="javascript:void(0)" datatitle="新中国成立以来第一次普选基层人大代表" datadescription=""
                               file="Img/Image_Related/01-22-2002.flv" class="video"
                               title="新中国成立以来第一次普选基层人大代表"><span><h4>新中国成立以来第一次普选基层人大代表</h4></span><img
                                src="/npc/Img/Image_Related/s/01-22-2002-s.jpg" alt="新中国成立以来第一次普选基层人大代表"></a></li>
                        <li><a href="javascript:void(0)" datatitle="毛泽东、朱德、刘少奇、周恩来等党和国家领导参加投票选举基层人大代表"
                               datadescription="" file="Img/Image_Related/01-22-2003.flv" class="video"
                               title="毛泽东、朱德、刘少奇、周恩来等党和国家领导参加投票选举基层人大代表"><span><h4>毛泽东、朱德、刘少奇、周恩来...</h4></span><img
                                src="/npc/Img/Image_Related/s/01-22-2003-s.jpg" alt="毛泽东、朱德、刘少奇、周恩来等党和国家领导参加投票选举基层人大代表"></a>
                        </li>
                        <li><a href="javascript:void(0)" datatitle="第一届全国人民代表大会开幕-代表入场" datadescription=""
                               file="Img/Image_Related/01-22-2004.flv" class="video"
                               title="第一届全国人民代表大会开幕-代表入场"><span><h4>第一届全国人民代表大会开幕-代表入场</h4></span><img
                                src="/npc/Img/Image_Related/s/01-22-2004-s.jpg" alt="第一届全国人民代表大会开幕-代表入场"></a></li>
                        <li><a href="javascript:void(0)" datatitle="周恩来在第一届全国人民代表大会第一次会议上作政府工作报告" datadescription=""
                               file="Img/Image_Related/01-22-2005.flv" class="video"
                               title="周恩来在第一届全国人民代表大会第一次会议上作政府工作报告"><span><h4>
                            周恩来在第一届全国人民代表大会第一次会议上作政府工作报告</h4></span><img src="/npc/Img/Image_Related/s/01-22-2005-s.jpg"
                                                                         alt="周恩来在第一届全国人民代表大会第一次会议上作政府工作报告"></a></li>
                        <li><a href="javascript:void(0)" datatitle="蔡畅在一届全国人大一次会议上发言。" datadescription=""
                               file="Img/Image_Related/01-22-1001.jpg" class="image"
                               title="蔡畅在一届全国人大一次会议上发言。"><span><h4>蔡畅在一届全国人大一次会议上发言。</h4></span><img
                                src="/npc/Img/Image_Related/s/01-22-1001-s.jpg" alt="蔡畅在一届全国人大一次会议上发言。"></a></li>
                        <li><a href="javascript:void(0)"
                               datatitle="出席一届全国人大一次会议的几位女代表。（从左到右第二人起）：邓颖超　（汉族）、赛力玛・塔力甫瓦（维吾尔族）、黄莲辉（僮族）、蒙素芬（布依族）、李桂英（彝族）、黎明（僮族）。"
                               datadescription="" file="Img/Image_Related/01-22-1002.jpg" class="image"
                               title="出席一届全国人大一次会议的几位女代表。（从左到右第二人起）：邓颖超　（汉族）、赛力玛・塔力甫瓦（维吾尔族）、黄莲辉（僮族）、蒙素芬（布依族）、李桂英（彝族）、黎明（僮族）。"><span><h4>
                            出席一届全国人大一次会议的几...</h4></span><img src="/npc/Img/Image_Related/s/01-22-1002-s.jpg"
                                                              alt="出席一届全国人大一次会议的几位女代表。（从左到右第二人起）：邓颖超　（汉族）、赛力玛・塔力甫瓦（维吾尔族）、黄莲辉（僮族）、蒙素芬（布依族）、李桂英（彝族）、黎明（僮族）。"></a>
                        </li>
                        <li><a href="javascript:void(0)" datatitle="出席一届全国人大一次会议的代表  （左起）华罗庚、老舍、梁思成、梅兰芳在会议休息时交谈。"
                               datadescription="" file="Img/Image_Related/01-22-1003.jpg" class="image"
                               title="出席一届全国人大一次会议的代表  （左起）华罗庚、老舍、梁思成、梅兰芳在会议休息时交谈。"><span><h4>
                            出席一届全国人大一次会议的代...</h4></span><img src="/npc/Img/Image_Related/s/01-22-1003-s.jpg"
                                                              alt="出席一届全国人大一次会议的代表  （左起）华罗庚、老舍、梁思成、梅兰芳在会议休息时交谈。"></a>
                        </li>
                        <li><a href="javascript:void(0)" datatitle="出席一届全国人大一次会议的上海市工商界代表在会议休息时交谈。自左至右：汤蒂因、荣毅仁、胡厥文、王志莘。"
                               datadescription="" file="Img/Image_Related/01-22-1004.jpg" class="image"
                               title="出席一届全国人大一次会议的上海市工商界代表在会议休息时交谈。自左至右：汤蒂因、荣毅仁、胡厥文、王志莘。"><span><h4>
                            出席一届全国人大一次会议的上...</h4></span><img src="/npc/Img/Image_Related/s/01-22-1004-s.jpg"
                                                              alt="出席一届全国人大一次会议的上海市工商界代表在会议休息时交谈。自左至右：汤蒂因、荣毅仁、胡厥文、王志莘。"></a>
                        </li>
                        <li><a href="javascript:void(0)"
                               datatitle="出席一届全国人大一次会议的几位女代表。自左至右：胡文秀、吴志珍（苗族）、蒙素芬（布依族）、常香玉、绕西・泽仁卓玛（藏族）、申纪兰、裔式娟。"
                               datadescription="" file="Img/Image_Related/01-22-1005.jpg" class="image"
                               title="出席一届全国人大一次会议的几位女代表。自左至右：胡文秀、吴志珍（苗族）、蒙素芬（布依族）、常香玉、绕西・泽仁卓玛（藏族）、申纪兰、裔式娟。"><span><h4>
                            出席一届全国人大一次会议的几...</h4></span><img src="/npc/Img/Image_Related/s/01-22-1005-s.jpg"
                                                              alt="出席一届全国人大一次会议的几位女代表。自左至右：胡文秀、吴志珍（苗族）、蒙素芬（布依族）、常香玉、绕西・泽仁卓玛（藏族）、申纪兰、裔式娟。"></a>
                        </li>
                        <li><a href="javascript:void(0)" datatitle="出席一届全国人大一次会议的几位农民代表。从右到左：梁军、李顺达、邓国章、吴春安、苏殿选。"
                               datadescription="" file="Img/Image_Related/01-22-1006.jpg" class="image"
                               title="出席一届全国人大一次会议的几位农民代表。从右到左：梁军、李顺达、邓国章、吴春安、苏殿选。"><span><h4>
                            出席一届全国人大一次会议的几...</h4></span><img src="/npc/Img/Image_Related/s/01-22-1006-s.jpg"
                                                              alt="出席一届全国人大一次会议的几位农民代表。从右到左：梁军、李顺达、邓国章、吴春安、苏殿选。"></a>
                        </li>
                        <li><a href="javascript:void(0)" datatitle="1954年9月，全国人大代表、内蒙古自治区人民政府主席乌兰夫（右二）深入牧区访问。"
                               datadescription="" file="Img/Image_Related/01-22-1007.jpg" class="image"
                               title="1954年9月，全国人大代表、内蒙古自治区人民政府主席乌兰夫（右二）深入牧区访问。"><span><h4>
                            1954年9月，全国人大代表、内蒙...</h4></span><img src="/npc/Img/Image_Related/s/01-22-1007-s.jpg"
                                                                 alt="1954年9月，全国人大代表、内蒙古自治区人民政府主席乌兰夫（右二）深入牧区访问。"></a>
                        </li>
                        <li><a href="javascript:void(0)" datatitle="一届全国人民代表大会一次会议出席证" datadescription=""
                               file="Img/Image_Related/01-22-1008.jpg" class="image"
                               title="一届全国人民代表大会一次会议出席证"><span><h4>一届全国人民代表大会一次会议出席证</h4></span><img
                                src="/npc/Img/Image_Related/s/01-22-1008-s.jpg" alt="一届全国人民代表大会一次会议出席证"></a></li>
                        <li><a href="javascript:void(0)" datatitle="张澜（右）、郭沫若（左）在一届全国人大一次会议上。" datadescription=""
                               file="Img/Image_Related/01-26-1002.jpg" class="image"
                               title="张澜（右）、郭沫若（左）在一届全国人大一次会议上。"><span><h4>张澜（右）、郭沫若（左）在一届全国人大一次会议上。</h4></span><img
                                src="/npc/Img/Image_Related/s/01-26-1002-s.jpg" alt="张澜（右）、郭沫若（左）在一届全国人大一次会议上。"></a>
                        </li>
                        <li><a href="javascript:void(0)" class="article png_bg" datatitle="一届全国人大一次会议隆重开幕"
                               datadescription="<p>9月15日―28日一届全国人大一次会议举行。</p><p>15日，一届全国人大一次会议开幕。代表总数1226人，实到代表1141人。中央人民政府主席毛泽东主持开幕式并致开幕词。毛泽东指出，我们这次会议具有伟大的历史意义。这次会议是标志着我国人民从1949年建国以来的新胜利和新发展的里程碑，这次会议所制定的宪法将大大地促进我国的社会主义事业。我们的总任务是团结全国人民，争取一切国际朋友的支援，为了建设一个伟大的社会主义国家而奋斗，为了保卫国际和平和发展人类进步事业而奋斗。我国人民应当努力工作，努力学习苏联和各兄弟国家的先进经验，老老实实，勤勤恳恳，互勉互助，力戒任何的虚夸和骄傲，准备在几个五年计划之内，将我们现在这样一个经济上文化上落后的国家，建设成为一个工业化的具有高度现代文化程度的伟大的国家。毛泽东强调，领导我们事业的核心力量是中国共产党，指导我们思想的理论基础是马克思列宁主义。</p><p>会议选举产生由97人组成的大会主席团和秘书长。主席团成员有：毛泽东、王崇伦、司徒美堂、朱德、朱德海、何香凝、吴玉章、吴耀宗、宋庆龄、李四光、李先念、李顺达、李德全、李济深、李烛尘、沈雁冰、沈钧儒、周文江、周恩来、林巧稚、林伯渠、林彪、林枫、果基木古、竺可桢、柳亚子、胡子昂、胡耀邦、范永、韦国清、徐向前、徐特立、桑吉悦希、乌兰夫、班禅额尔德尼・确吉坚赞、马叙伦、马寅初、高崇民、崔建功、张治中、张奚若、张闻天、张难先、张澜、梁希、梅兰芳、章伯钧、习仲勋、许广平、许德珩、郭沫若、陈少敏、陈叔通、陈绍宽、陈云、陈经畲、陈嘉庚、陈毅、傅作义、嵇文甫、彭真、彭德怀、彭泽民、盛丕华、程潜、华罗庚、贺龙、黄长水、黄炎培、杨明轩、叶剑英、董必武、达赖喇嘛・丹增嘉措、荣毅仁、熊克武、刘少奇、刘伯承、刘格平、刘鸿生、刘澜涛、欧百川、蔡廷锴、蔡畅、邓子恢、刘芳芝、邓宝珊、巩天民、赖若愚、龙云、薄一波、谢觉哉、赛福鼎、韩恩、韩望尘、聂荣臻、罗荣桓、谭平山，李维汉为秘书长。</p><p>会议通过一届全国人大一次会议议程。</p><p>同日，一届全国人大一次会议主席团举行第一次会议。会议推选毛泽东、刘少奇、周恩来、宋庆龄、李济深、张澜、黄炎培、郭沫若、陈叔通为主席团常务主席，决定余心清、吴克坚、汪锋、辛志超、邢西萍、屈武、孙起孟、张苏、许广平、杨静仁、齐燕铭为大会副秘书长。</p><p>16日，一届全国人大一次会议举行全体会议。会议通过一届全国人大代表资格审查委员会主任委员、委员名单。代表资格审查委员会由19人组成，马明方为主任委员；委员有：王维舟、平杰三、朱蕴山、吴芝圃、成柏仁、李永、李澄之、车向忱、周素园、胡厥文、徐立清、涂长望、庄希泉、陈汝棠、杨之华、杨静仁、郑振铎、罗叔章。</p><p>会议还通过一届全国人大一次会议提案审查委员会主任委员、委员名单。提案审查委员会由25人组成，习仲勋为主任委员；委员有：丁西林、王任重、朱学范、吴有训、吴克坚、吴耀宗、李国伟、李德全、李颉伯、周钦岳、邵力子、孙志远、高崇民、章乃器、郭棣活、陈此生、陈劭先、陈其瑗、乔传珏、曾希圣、杨秀峰、杨显东、包尔汉、薄一波。</p><p>16日―26日，会议就各项主要议程进行分组审议和大会发言。共有164名代表在大会上发言。</p><p>18日，一届全国人大一次会议举行全体会议。会议听取并通过代表资格审查委员会主任委员马明方关于代表资格的审查报告。报告确认本届代表大会1226名代表的代表资格有效。</p>"
                               title="一届全国人大一次会议隆重开幕"><span><h4>一届全国人大一次会议隆重开幕</h4></span></a></li>
                    </ul>
                </li>
                <div class="clearfix"></div>
            </ul>
            <div class="clearfix"></div>
        </div>

        <c:if test="${!empty publishDates && fn:length(publishDates) > 0}">
            <c:set var="currentYear" value="${fn:substring(publishDates[0],0,4) }"></c:set>
            <div class="year">
                <span name="year">${currentYear }年</span>
                <ul>

                    <c:forEach var="publishDate" items="${publishDates}" varStatus="row">
                        <c:if test="${currentYear != fn:substring(publishDate,0,4) }">
                            <c:set var="currentYear" value="${fn:substring(publishDate,0,4) }"></c:set>
                </ul>
            </div>
            <div class="year">
                <span name="year">${currentYear }年</span>
                <ul>
        </c:if>

        <li><a href="javascript:void(0);" data="${publishDate }">${fn:substring(publishDate,4,6) * 1 }月</a></li>
        </c:forEach>

        </ul>
    </div>
    </c:if>

</div>
<div id="relate-content">
    <div class="media">
        <ul>
        </ul>
    </div>
    <div class="clearfix"></div>
</div>
<div id="detail-content">
    <a href="javascript:void(0)" class="close png_bg"></a>

    <div class="media">
        <img src="data:image/gif;base64,R0lGODlhBAABAIABAMLBwfLx8SH5BAEAAAEALAAAAAAEAAEAAAICRF4AOw==" alt=""/>
    </div>

    <h2></h2>

    <p></p>
</div>
</div>
<footer>
    <p>
        <a href="#top" id="gotop"></a>
    </p>
</footer>

<script src="${context }/js/vendor/jquery.min.js"></script>
<script src="${context }/js/vendor/mediaelement-and-player.min.js"></script>
<!--[if lt IE 7]>
<script>
    $(document).ready(function () {
        $("li, span, a").hover(function () {
            $(this).toggleClass("hover");
        });
    });
</script>
<![endif]-->
<script src="${context }/js/main.js"></script>

<script>

    function showImageMain(publishDate) {

        $.ajax("${context}/date/" + publishDate + ".html", {
            type: 'get',
            dataType: 'json',
            async: true,
            success: function (data, textStatus, jqXHR) {

                $("#relate-content .media ul").html('');

                // 主题
                for (var index in data['imageMains']) {
                    var imageMain = data['imageMains'][index];
                    var materialType = 'image';

                    var title = imageMain['imageMainTitle'];
                    var description = imageMain['imageMainDescription'] || '';
                    var file = imageMain['imageMainFilepath'];
                    var imgSrc = '${context}/' + transImagePath(imageMain['imageMainFilepath'], 's')

                    var tHtml = "<li><a href='javascript:void(0)' datatitle='" + title + "' datadescription='" + description + "' file='" + file + "' "
                            + "class='" + materialType + "' title='" + title + "'><img src='" + imgSrc + "' alt='" + title + "'></img></a></li>";
                    $(tHtml).appendTo($("#relate-content .media ul"));
                }

                // 相关图片和视频
                for (var index in data['imageRelateds']) {
                    var relate = data['imageRelateds'][index];

                    var materialType = materialTypes[relate['materialId']];
                    var imgSrc = "default.png";
                    if (materialType != 'article')
                        imgSrc = '${context}/' + transImagePath(relate['imageRelatedThumbFilepath'], 's');

                    var content = relate['imageRelatedDescription'] || '';

                    var tHtml = "<li><a href='javascript:void(0)' datatitle='" + relate['imageRelatedTitle'] + "' datadescription='" + content + "' file='" + relate['imageRelatedFilepath'] + "' " +
                            "class='" + materialType + "' title='" + relate['imageRelatedTitle'] + "'><img src='" + imgSrc + "' alt='" + relate['imageRelatedTitle'] + "'></img></a></li>";
                    $(tHtml).appendTo($("#relate-content .media ul"));
                }

                // 相关文章
                for (var index in data['documents']) {
                    var document = data['documents'][index];
                    var paragraphs = document['paragraphs'];
                    var content = '';
                    if (paragraphs && paragraphs.length > 0) {
                        content = '<p>' + paragraphs[0]['paragraphContent'] + "</p>";
                        for (var i = 1; i < paragraphs.length; i++) {
                            content += '<p>' + paragraphs[i]['paragraphContent'] + "</p>";
                        }
                    }

                    $("<li></li>").append(
                            $("<a href='javascript:void(0)' class='article png_bg'></a>")
                                    .attr('datatitle', document['documentTitle'])
                                    .attr('datadescription', content)
                                    .attr('title', document['documentTitle'])
                    ).appendTo($("#relate-content .media ul"));

                    //var tHtml = "<li><a href='javascript:void(0)' datatitle='" + document['documentTitle'] + "' datadescription='" + content + "' class='article'></a></li>";
                    //$(tHtml).appendTo($("#relate-content .media ul"));
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("获取内容失败,请稍后重试");
            }
        });
    }

    $(document).ready(function () {
        $(".year a").click(function () {
            showImageMain($(this).attr('data'));
        });
    });
</script>
</body>
</html>