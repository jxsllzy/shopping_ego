<!-- 设置项目根路径全局变量 -->
<#assign ctx=request.contextPath/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>商城</title>
    <#include "head.ftl">
    <!-- 引入 doT.js -->
    <script type="text/javascript" src="${ctx}/js/doT.min.js"></script>
    <script src="${ctx}/js/layer/layer-min.js"></script>

</head>
<body>
<header>
    <span class="short_nav"></span>
    <div class="home_icon">
        <a href="${ctx}/index.html" style="text-decoration: none;color: white">C2C</a>
    </div>
    <input type="text" placeholder="Search" name="search" class="nav_search_input">
    <span class="search_icon"></span>
    <span class="user_icon"></span>
    <span class="login_or_register_string">
        <a href="login_page.html">登陆</a> ， <a href="login_page.html">注册</a>
        <!--<a href="page/personal/personal_info.html" class="user_name_a">这是一个用户名这是一个用户名这是一个用户名</a>-->
    </span>
</header>
<div class="short_nav_show">
    <ul>
        <li><a href="${ctx}index.html">首页</a></li>
        <li><a href="javascript:void(0)">商城</a></li>
        <li><a href="publish_product.html">发布商品</a></li>
        <li><a href="require_product.html">求购商品</a></li>
        <li><a href="shopping_cart.html">我的购物车</a></li>
        <li><a href="javascript:void(0)">反馈与意见</a></li>
        <li><a href="javascript:void(0)">联系我们</a></li>
    </ul>
</div>
<nav class="my_nav">
    <ul>
        <li><a href="../index.html">首页</a></li>
        <li><a href="javascript:void(0)">商城</a></li>
        <li><a href="publish_product.html">发布商品</a></li>
        <li><a href="require_product.html">求购商品</a></li>
        <li><a href="shopping_cart.html">我的购物车</a></li>
        <li><a href="javascript:void(0)">反馈与意见</a></li>
        <li><a href="javascript:void(0)">联系我们</a></li>
    </ul>
</nav>
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
        <div class="type_title_div">
               <span class="type_border_span">1
               </span>
            <h3>手机</h3>
        </div>
        <div class="type_goods_list">
            <a>11111</a>
            <a>2222</a>
            <a>3333</a>
        </div>
    </div>
    <div class="one_part">
        <div class="type_title_div">
               <span class="type_border_span">1
               </span>
            <h3>手机</h3>
        </div>
        <div class="type_goods_list">
            <a>11111</a>
            <a>2222</a>
            <a>3333</a>
        </div>
    </div>
</div>
<!--商品详情，加多一个多余的div，然分页按钮可以显示在内容之下-->
<div class="temp_content" onmousedown="hideParticular()">
    <!--注意需要加一个br，不然会在同一行显示-->
    <div class="product_content_div" id="content">

    </div>

    <input type="hidden" id="pageNum" name="pageNum" value="1"/>
    <input type="hidden" id="pageSize" name="pageSize" value="6"/>
    <div class="pagination_div" onmouseleave="hideParticular()" style="align-content: center">
       <#-- <a class="pagination_lt">&lt;</a>
        <ul>
            <!--new&ndash;&gt;
            <li class="current_page"><a>1</a></li>
            <li><a>2</a></li>
            <li ><a>3</a></li>
            <li><a>4</a></li>
            <li><a>5</a></li>
        </ul>
        <a class="pagination_gt">&gt;</a>-->
    </div>
    <footer>
        <p>Copyright © 2014.Company name All rights reserved.</p>
    </footer>
</div>

<!-- 编写商品模板 -->
<script type="template" id="shopInfoTemplate">
    {{ for(var i = 0; i < it.length; i++){ }}
        <div class="detail_product">
            <img src="{{=it[i].image}}" height="196px">
            <span class="detail_product_name">{{=it[i].name}}</span><br>
            <span class="detail_product_cost">{{=it[i].price}}</span><br>
            <span class="detail_buy product_{{=it[i].id}}">加入购物车</span>
        </div>
    {{ } }}
</script>

<!-- 编写分页模板 -->
<script type="template" id="pageTemplate">
    {{ if(it.hasPreviousPage){ }}
    <a class="pagination_lt" href="javascript:ajax_get_table('{{=it.prePage}}');" style="text-decoration: none"><</a>
    {{ } }}

        {{ for(var i = 1; i <= it.navigatepageNums.length; i++){ }}
        <ul>
        <li
                {{ if(i == it.pageNum){ }}
                class="current_page"
                {{ } }}>
            <a href="javascript:ajax_get_table('{{=i}}');" style="text-decoration: none">{{=i}}</a>
        </li>
        </ul>
        {{ } }}

    {{ if(it.hasNextPage){ }}
    <a class="pagination_gt" href="javascript:ajax_get_table('{{=it.nextPage}}');" style="text-decoration: none">></a>
    {{ } }}
</script>


<!-- 编写模板 -->
<script type="template" id="goodsKindsTemplate">

    {{ for(var i = 0; i < it.length; i++){ }}
    <li class="cat_item">
        <h4 class="cat_tit"><a href="#" class="cat_tit_link">{{=it[i].name}}</a><span
                    class="s_arrow">></span></h4>
        <div class="cat_cont">
            <div class="cat_cont_lft">
                {{ for(var j = 0; j < it[i].childrenList.length; j++){ }}
                <dl class="cf">
                    <dt><a href="#">{{=it[i].childrenList[j].name}}</a></dt>
                    <dd>
                        <ul>
                            {{ for(var k = 0; k < it[i].childrenList[j].childrenList.length; k++){ }}
                            <li class="first"><a href="#">{{=it[i].childrenList[j].childrenList[k].name}}</a></li>
                            {{ } }}
                        </ul>
                    </dd>
                </dl>
                {{ } }}

            </div>
        </div>
    {{ } }}
</script>

<script>

    $(document).ready(function () {
        // ajax 加载商品列表
        ajax_get_table(1);
    });

    $('.search_icon').click(function () {
        var name = $('.nav_search_input').val();
        window.location.href = '${ctx}/findShopByName?name=' + name;
    });


    //ajax 抓取页面 page 为当前第几页
    function ajax_get_table(page) {
        $.ajax({
            url: "${ctx}/shopInformation/list",
            type: "POST",
            data: {
                name: $(".nav_search_input").val(),
                pageNum: page,
                pageSize: 6
            },
            dataType: "JSON",
            success: function (result) {
                var result = JSON.parse(result);
                if (200 == result.code) {

                    if (result.pageInfo.list.length > 0) {
                        //获取商品列表模板
                        var goodsTemp = doT.template($("#shopInfoTemplate").text());
                        //填充数据
                        $("#content").html(goodsTemp(result.pageInfo.list));

                        //获取分页模板
                        var pageTemp = doT.template($("#pageTemplate").text());
                        //填充数据
                        $(".pagination_div").html(pageTemp(result.pageInfo));

                    } else {
                        layer.msg("该分类或品牌暂无商品信息！");
                    }
                } else {
                    layer.msg("该分类或品牌暂无商品信息！");
                }
            },
            error: function (result) {
                console.log(result)
            }
        });
    }

/*function getpageInfo(pageInfo) {
    $(".pagination_div").html("");
    var str='<a class="pagination_lt" href="javascript:ajax_get_table('+pageInfo.pageNum+');" style="text-decoration: none"><</a>';
        for(var i = 1; i <= pageInfo.pages; i++){
            str+='<ul><li><a href="javascript:ajax_get_table('+i+');" style="text-decoration: none">'+i+'</a></li></ul>'
        }
        str +='<a class="pagination_gt" href="javascript:ajax_get_table('+pageInfo.pageNum+');" style="text-decoration: none">></a>';
    $(".pagination_div").html(str);
}*/

</script>

</body>
</html>