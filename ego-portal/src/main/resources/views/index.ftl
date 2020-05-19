<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>C2C二手交易平台</title>
    <#include "common.ftl">
</head>
<body>
<#include "head.ftl">
<#--轮播图-->
<div class="my_slider">
    <#--商品-->
    <#list shop as s>
        <#if s_index<4>
            <div class="my_slide">
                <h1>${s.name}</h1>
                <p>${s.remark}</p>
                <div class="slide_img">
                    <a href="${ctx}/productInfo?id=${s.id}&sort=${s.sort}"><img src="${s.image}" style="width:400px;height: 270px"></a>
                </div>
            </div>
            <#else >
                <div class="my_slide current">
                    <h1>${s.name}</h1>
                    <p>${s.remark}</p>
                    <div class="slide_img">
                        <a href="${ctx}/productInfo?id=${s.id}&sort=${s.sort}"><img src="${s.image}" style="width:400px;height: 270px"></a>
                    </div>
                </div>
        </#if>
    </#list>
    <#--箭头-->
    <div class="left_border">
        <span class="left_turn"></span>
    </div>
    <div class="right_border">
        <span class="right_turn"></span>
    </div>
</div>

<div class="featured_products" style="height: 520px">
    <h2>精选商品</h2>
    <#--<#list shops as s>
        <div class="product">
            <img src="${s.image}" width="100%" height="45%">
            <span class="product_name">${s.name}</span><br>
            <span class="product_cost">￥${s.price}</span><br><br>
            <span class="buy product_1">加入购物车</span>
        </div>
    </#list>-->

    <#--分页查询-->
    <div id="goodsContent" style="height: 350px"></div>
    <div class="pagination_div" style="margin-left: 37%">
        <#--<a class="pagination_lt">&lt;</a>
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
</div>

<div class="home_page_info">
    <div class="bottom_info">
        <h2>
            关于我们
        </h2>
        <p>
            该网站成立与2017年，是仲恺农业工程学院最大的校内二手交易网站。拥有2000万注册用户和10万注册企业和合作伙伴。日常在线活动用户约90万。交易金额最大可达1亿/日。
        </p>
    </div>
    <div class="bottom_info">
        <h2>
            团队信息
        </h2>
        <p>该网站由WSK和XYF协力完成。有疑问可以发送问题至邮箱 wushukai1103@gmail.com
        </p>
    </div>
    <div class="bottom_info">
        <h2>
            版权声明
        </h2>
        <p>本网站社区发布的所有信息，都视为发布者同意本网站免费予以使用；同时本网站认为其发布的信息有侵权、违法等行为的，有权予以修改、删除或通知发布者予以纠正的权利，发布者不得阻止或拒绝。
        </p>
    </div>
</div>
<footer>
    <p style="line-height: 5%">Copyright © 2017-2200.Company name All rights reserved.<a href="/"></a></p>
</footer>
</body>


<!-- 编写商品模板 -->
<script type="template" id="goodsTemplate">
    {{ for(var i = 0; i < it.length; i++){ }}
        <div class="product">
           <a href="${ctx}/productInfo?id={{=it[i].id}}&sort={{=it[i].sort}}"><img src="{{=it[i].image}}"></a>
            <span class="product_name">{{=it[i].name}}</span><br>
            <span class="product_cost">￥{{=it[i].price}}</span><br><br>
            <span class="buy product_1" onclick="addOrder({{=it[i].id}})">加入购物车</span>
        </div>
    {{ } }}
</script>

<!-- 编写分页模板 -->
<#--<script type="template" id="pageTemplate">

    {{ if(it.hasPreviousPage){ }}&lt;#&ndash;hasPreviousPage当前页&ndash;&gt;
    <a class="pagination_lt" href="javascript:ajax_get_table('{{=it.prePage}}');" style="text-decoration: none"><</a>
    {{ } }}

    {{ for(var i = 1; i <= it.navigatepageNums.length; i++){ }}&lt;#&ndash;navigatepageNums显示页数&ndash;&gt;
    <ul>
        <li {{ if(i == it.pageNum){ }} class="current_page" {{ } }}>
            <a href="javascript:ajax_get_table('{{=i}}');" style="text-decoration: none">{{=i}}</a>
        </li>
    </ul>
    {{ } }}

    {{ if(it.hasNextPage){ }}
        <a class="pagination_gt" href="javascript:ajax_get_table('{{=it.nextPage}}');" style="text-decoration: none">></a>
    {{ } }}
</script>-->

<script type="text/javascript">


    $(document).ready(function () {
        // ajax 加载商品列表
        ajax_get_table(1);

    });

    //ajax抓取页面 page为当前第几页
    function ajax_get_table(page) {
        $.ajax({
            url: "${ctx}/listForPage",
            type: "POST",
            data: {
                pageNum: page,
                pageSize: 4
            },
            dataType: "JSON",
            success: function (result) {
                if (200 == result.code) {
                    if (result.pageInfo.list.length > 0) {
                        console.log(result.pageInfo.list);
                        //获取商品列表模板
                        var goodsTemp = doT.template($("#goodsTemplate").text());
                        //填充数据
                        $("#goodsContent").html(goodsTemp(result.pageInfo.list));
                        //获取分页模板
                        // var pageTemp = doT.template($("#pageTemplate").text());
                         pageInfoBar(result.pageInfo,"pagination_div");
                        //填充数据
                        //$(".pagination_div").html(pageTemp(result.pageInfo));
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
</script>

<#--分页功能-->
<script>
    function pageInfoBar(pageInfo, barDivId) {
        var barDiv = $("." + barDivId);
        /*var context = "<span>当前页：" + pageInfo.pageNum + "&nbsp;总页数："
            + pageInfo.pages + "&nbsp;&nbsp;总记录数："+pageInfo.total+"</span>";*/
        var context = "<div class='query-content-page-btn'><ul>";
        if (pageInfo.pageNum > 1) {
            context += "<li class='prev-next' onclick='prePage()'><</li>";
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
            context += "<li class='bus-border-right prev-next' onclick='nextPage()'>></li>";
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
        console.log(page);
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
</html>