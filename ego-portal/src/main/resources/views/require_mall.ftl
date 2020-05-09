<!-- 设置项目根路径全局变量 -->
<#assign ctx=request.contextPath/>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <title>求购商城</title>
    <script src="${ctx}/js/jquery-1.8.2.min.js"></script>
    <script src="${ctx}/js/home/header_model_js.js"></script>
    <script src="${ctx}/js/mall/type_list_obj.js"></script>
    <script src="${ctx}/mall/left_nav_on_goods_page.js"></script>
    <link rel="icon" href="${ctx}/images/page_icon.png"/>
    <script>
        //        $(function () {
        //            $('.require_one_part').click(function () {
        //                var which = $(this).attr("id");
        //                alert(which);
        //            });
        //        });
    </script>
    <style>
        .my_type_div {
            position: fixed;
            /*width: 55%;*/
            width: 13.75%;
            z-index: 2;
            margin-top: 15%;
            margin-left: 5%;
            float: left;
            background-color: white;
            box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.5);
            text-align: center;
            border-radius: 5px;
        }

        .my_type_div ul {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        .my_type_div ul li {
            padding: 5%;
            border-bottom: 0.1px solid rgba(0, 0, 0, 0.06);
        }

        .my_type_div ul li:hover {
            cursor: pointer;
            background-color: rgba(0, 0, 0, 0.06);
        }

        .particular_type_div {
            background-color: white;
            box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.5);
            position: fixed;
            width: 40%;
            z-index: 20;
            margin-top: 15%;
            margin-left: 19.25%;
            border-radius: 5px;
            display: none;
        }

        .one_part {
            width: 45%;
            margin: 2.5%;
            height: auto;
            float: left;
            padding-bottom: 5%;
        }

        .type_title_div {
            display: inline;
        }

        .one_part .type_border_span {
            float: left;
            margin-left: 8%;
            margin-top: 8%;
            margin-right: 8%;
            background-color: blueviolet;
            color: blueviolet;
        }

        .type_title_div h3 {
            color: rgba(0, 0, 0, 0.71);
        }

        .one_part .type_goods_list {
            margin-left: 4%;
        }

        .one_part .type_goods_list a {
            font-size: 1em;
            text-decoration: none;
            color: rgba(0, 0, 0, 0.62);
            padding-right: 4%;
        }

        .one_part .type_goods_list a:hover {
            color: blueviolet;
        }

        .temp_content {
            width: 99%;
            min-width: 800px;
            position: absolute;
            display: inline-block;
            top: 20%;
            padding: 0;
        }

        .require_content_div {
            width: 75%;
            min-height: 20em;
            margin-top: 3%;
            margin-left: 25%;
            display: block;
            z-index: 25;
        }

        .require_one_part {
            width: 23%;
            float: left;
            margin: 5% 2%;
            background-color: white;
            box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.7);
            border-radius: 5px;
            padding: 2%;
            line-height: 2.7em;
            text-align: left;
        }

        .require_one_part:hover {
            cursor: pointer;
        }

        .require_one_part .what_info {
            font-size: 1.1em;
        }

        div.pagination_div {
            display: block;
            width: 40%;
            height: 36px;
            clear: both;
            margin-bottom: 5%;
            margin-left: 42%;
        }

        .pagination_div ul {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        .pagination_div ul li {
            float: left;
        }

        .pagination_div .pagination_lt, .pagination_div .pagination_gt {
            float: left;
        }

        .pagination_div .pagination_lt, .pagination_div ul li,
        .pagination_div .pagination_gt {
            width: 6%;
            height: 36px;
            background-color: white;
            margin-left: 4%;
            font-size: 1.5em;
            color: rgba(0, 0, 0, 0.54);
            text-align: center;
            vertical-align: middle;
            padding-top: 0.3em;
            border: 1px solid rgba(0, 0, 0, 0.3);
            border-radius: 5px;
            box-shadow: 1px 2px 5px rgba(0, 0, 0, 0.3);
        }

        .all_info {
            height: 3em;
            overflow: hidden;
        }

        .pagination_div .pagination_lt:hover, .pagination_div ul li:hover,
        .pagination_div .pagination_gt:hover {
            cursor: pointer;
            background-color: slateblue;
        }

        footer {
            width: 96%;
            background-color: slateblue;
            box-shadow: 4px 2px 10px #888888;
            border-radius: 10px;
            height: 80px;
            margin-left: 2%;
        }

        .shop_sort {
            cursor: pointer;
            padding: 0 5px;
        }

        .shop_sort:hover {
            color: red;
        }

        footer p {
            padding-top: 35px;
            vertical-align: middle;
            text-align: center;
            color: white;
            font-size: 1.2em;
        }

        /*new*/
        .pagination_div li.current_page {
            background-color: slateblue;
        }
    </style>
</head>
<body>
<header th:include="header :: copy"></header>
<div th:include="short_nav_show :: short_nav_show" class="short_nav_show"></div>
<nav th:include="my_nav :: my_nav" class="my_nav"></nav>
<div th:include="personal_nav :: personal_nav" class="personal_nav"></div>
<div class="my_type_div">
    <ul>
        <li class="type_1">数码科技</li>
        <li class="type_2">影音家电</li>
        <li class="type_3">鞋服配饰</li>
        <li class="type_4">运动代步</li>
        <li class="type_5">书籍文具</li>
        <li class="type_6">其他</li>
    </ul>
</div>
<div class="particular_type_div" onmouseleave="hideParticular()">
    <div class="one_part">
    </div>
    <div class="one_part">
    </div>
</div>
<!--求购详情，加多一个多余的div，然分页按钮可以显示在内容之下-->
<#--<div class="temp_content" onmousedown="hideParticular()">-->
<#--    <!--注意需要加一个br，不然会在同一行显示&ndash;&gt;-->
<#--    <div class="require_content_div">-->
<#--        <div class="require_one_part" id="1" th:each="o:${list}">-->
<#--            <span class="what_info">商品名：</span>-->
<#--            <span class="info_content" th:text="${o.name}">苹果6s</span><br/>-->
<#--            <span class="what_info">数量：</span>-->
<#--            <span class="info_content" th:text="${o.quantity}">3</span><br/>-->
<#--            <span class="what_info">单价：</span>-->
<#--            <span class="info_content" th:text="'￥'+${o.price}+'元'">20.00</span><br/>-->
<#--            <span class="what_info" style="float: left">详情：</span>-->

<#--            <textarea readonly="readonly" class="info_content" style="resize: none;width: 250px;height: 100px"-->
<#--                      th:text="${o.remark}">这是一哈哈哈哈哈哈哈哈哈打撒打撒个商品，联系电话1111111111</textarea><br/>-->

<#--            <span class="what_info">分类：</span>-->
<#--            <span class="info_content" th:text="${o.sort}">数码科技-手机-苹果</span><br/>-->
<#--        </div>-->
<#--    </div>-->
<#--    <div class="pagination_div" onmouseleave="hideParticular()">-->
<#--        <a class="pagination_lt">&lt;</a>-->
<#--        <ul>-->
<#--            <!--new&ndash;&gt;-->
<#--            <li class="current_page"><a>1</a></li>-->
<#--            <li><a>2</a></li>-->
<#--            <li><a>3</a></li>-->
<#--            <li><a>4</a></li>-->
<#--            <li><a>5</a></li>-->
<#--        </ul>-->
<#--        <a class="pagination_gt">&gt;</a>-->
<#--    </div>-->
<#--    <footer>-->
<#--        <p>Copyright © 2017.Company name All rights reserved.<a href="/"></a></p>-->
<#--    </footer>-->
<#--</div>-->
</body>
</html>