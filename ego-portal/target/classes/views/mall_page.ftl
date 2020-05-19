<!-- 设置项目根路径全局变量 -->
<#assign ctx=request.contextPath/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>商城</title>
    <script src="${ctx}/static/js/jquery-1.3.2.min.js"></script>
    <script src="${ctx}/static/js/home/header_model_js.js"></script>
    <script src="${ctx}/static/js/mall/type_list_obj.js"></script>
    <script src="${ctx}/static/js/mall/left_nav_on_goods_page.js"></script>
    <link rel="icon" href="${ctx}/static/img/page_icon.png">
    <link href="${ctx}/static/css/home_page/header_and_nav.css" rel="stylesheet">
    <link href="${ctx}/static/css/mall_page/mall_page_other.css" rel="stylesheet">
    <!-- 引入 doT.js -->
    <script type="text/javascript" src="${ctx}/static/js/doT.min.js"></script>
    <script src="${ctx}/static/js/layer/layer-min.js"></script>
</head>
<body>
<#include "head.ftl">
<div class="my_type_div">
    <#--<ul>
        <li class="type_1">数码科技</li>
        <li class="type_2">影音家电</li>
        <li class="type_3">鞋服配饰</li>
        <li class="type_4">运动代步</li>
        <li class="type_5">书籍文具</li>
        <li class="type_6">其他</li>
    </ul>-->
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
    <input type="hidden" id="name" name="name"/>
    <div class="pagination_div" onmouseleave="hideParticular()" style="align-content: center">

    </div>
    <footer>
        <p>Copyright © 2014.Company name All rights reserved.</p>
    </footer>
</div>

<!-- 编写商品模板 -->
<script type="template" id="shopInfoTemplate">
    {{ for(var i = 0; i < it.length; i++){ }}
    <div class="detail_product">
        <img src="{{=it[i].image}}" height="196px" style="width: 186px;height: 196px"><br>
        <span class="detail_product_name">{{=it[i].name}}</span><br>
        <span class="detail_product_cost">{{=it[i].price}}</span><br>
        <span class="detail_buy product_{{=it[i].id}}" onclick="addOrder({{=it[i].id}})">加入购物车</span>
    </div>
    {{ } }}
</script>



<!-- 编写模板 -->
<script type="template" id="goodsKindsTemplate">

    {{ for(var i = 0; i < it.length; i++){ }}
    <li class="cat_item">
        <h4 class="cat_tit"><a href="#" class="cat_tit_link">{{=it[i].name}}</a></h4>
        <div class="cat_cont">
            <div class="cat_cont_lft">
                {{ for(var j = 0; j < it[i].childrenList.length; j++){ }}
                <dl class="cf">
                    <dt><a href="#">{{=it[i].childrenList[j].name}}</a></dt>
                    <dd>
                        <ul>
                            {{ for(var k = 0; k < it[i].childrenList[j].childrenList.length; k++){ }}
                            <li class="first"><a href="javascript:ajax_get_table(1)" id="name_{{=it[i].childrenList[j].childrenList[k].id}}" onclick="getName({{=it[i].childrenList[j].childrenList[k].id}})">{{=it[i].childrenList[j].childrenList[k].name}}</a></li>
                            {{ } }}
                        </ul>
                    </dd>
                </dl>
                {{ } }}

            </div>
        </div>
        {{ } }}
</script>

<script type="text/javascript">

    //商品分类点击查询
    function getName(id){
        var name = $("#name_"+id).html();
        console.log(name);
        $("#name").val(name);
    }

    $(document).ready(function () {
        // ajax 加载商品列表
        var page = $("#pageNum").val();
        ajax_get_table(page);

        //获取商品分类
        selectKindsList();
    });
    //ajax 抓取页面 page 为当前第几页
    function ajax_get_table(page) {
        $.ajax({
            url: "${ctx}/shopInformation/list",
            type: "POST",
            data: {
                name: $("#name").val(),
                pageNum: page,
                pageSize: $("#pageSize").val()
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

                        /*//获取分页模板
                        var pageTemp = doT.template($("#pageTemplate").text());
                        //填充数据
                        $(".pagination_div").html(pageTemp(result.pageInfo));*/

                        pageInfoBar(result.pageInfo,"pagination_div")

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

    function selectKindsList() {
        $.ajax({
            url: "${ctx}/shopInformation/kinds",
            type: "GET",
            dataType: "JSON",
            success: function (result) {
                var result = JSON.parse(result);
                if (result.length > 0) {
                    // 调用模板
                    var templ = doT.template($("#goodsKindsTemplate").text());
                    // 填充内容
                    $(".my_type_div").html(templ(result));
                    /* ----------鼠标移入移出事件---begin------- */
                    $('.cat_item').mousemove(function () {
                        $(this).addClass('cat_item_on');
                    });
                    $('.cat_item').mouseleave(function () {
                        $(this).removeClass('cat_item_on');
                    });
                    /* ----------鼠标移入移出事件-----end------- */
                } else {
                    layer.msg("亲，系统正在升级中，请稍后再试！");
                }
            },
            error: function (result) {
                console.log(result);
                layer.msg("亲，系统正在升级中，请稍后再试！");
            }
        });
    }


    function pageInfoBar(pageInfo, barDivId) {
        var barDiv = $("." + barDivId);
        /*var context = "<span>当前页：" + pageInfo.pageNum + "&nbsp;总页数："
            + pageInfo.pages + "&nbsp;&nbsp;总记录数："+pageInfo.total+"</span>";*/
        var context = "<div class='query-content-page-btn'><ul>";
        if (pageInfo.pageNum > 1) {
            context += "<li class='prev-next' onclick=prePage('"
                + pageInfo.prePage + "')><</li>";
        }
        for (var i = 0; i < pageInfo.navigatepageNums.length; i++) {
            if (pageInfo.pageNum == pageInfo.navigatepageNums[i]) {
                context += "<li class='current_page' onclick=numPage('"
                    + pageInfo.navigatepageNums[i]
                    + "')>"
                    + pageInfo.navigatepageNums[i] + "</li>"
            } else {
                context += "<li onclick=numPage('"
                    + pageInfo.navigatepageNums[i] + "')>"
                    + pageInfo.navigatepageNums[i] + "</li>"
            }

        }

        if (pageInfo.pageNum < pageInfo.pages) {
            context += "<li class='bus-border-right prev-next' onclick=nextPage('"
                + pageInfo.nextPage + "')>></li>";
        }
        context += "</ul></div>";
        barDiv.html(context);
    }

    // 下一页
    function nextPage() {
        // 获取当前页的值 加一 然后重新赋值给当前页
        var page = parseInt($("#pageNum").val()) + 1;
        $("#pageNum").val(page);
        // 调用搜索函数
        ajax_get_table(page);
    }

    //上一页
    function prePage() {
        // 获取当前页的值 减一 然后重新赋值给当前页
        var page = parseInt($("#pageNum").val()) - 1;
        $("#pageNum").val(page);
        // 调用搜索函数
        ajax_get_table(page);
    }


    // 第几页
    function numPage(num) {
        // 获取点击的按钮值 然后重新赋值给当前页
        $("#pageNum").val(num);

        // 调用搜索函数
        ajax_get_table(num);
    }
</script>

</body>
</html>