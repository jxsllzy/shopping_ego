<!-- 设置项目根路径全局变量 -->
<#assign ctx=request.contextPath/>
<!DOCTYPE html >
<html>
<head>
    <#include "../head.ftl">
    <script type="text/javascript">
        var timeout = 500;
        var closetimer = 0;
        var ddmenuitem = 0;

        $(document).ready(function () {
            $('.cat_item').mousemove(function () {
                $(this).addClass('cat_item_on');
            });
            $('.cat_item').mouseleave(function () {
                $(this).removeClass('cat_item_on');
            });
            $('#slideshow').imgSlideShow({itemclass: 'i'})
            $("#slide-qg").switchTab({titCell: "dt a", trigger: "mouseover", delayTime: 0});
            $("#s_cart_nums1").hover(function () {
                mcancelclosetime();
                if (ddmenuitem) ddmenuitem.hide();
                ddmenuitem = $(document).find("#s_cartbox");
                ddmenuitem.fadeIn();
            }, function () {
                mclosetime();
            });
            $("#s_cart_nums2").hover(function () {
                mcancelclosetime();
                if (ddmenuitem) ddmenuitem.hide();
                ddmenuitem = $(document).find("#s_cartbox");
                ddmenuitem.fadeIn();
            }, function () {
                mclosetime();
            });
            $("#s_cartbox").hover(function () {
                mcancelclosetime();
            }, function () {
                mclosetime();
            });
            var $cur = 1;
            var $i = 4;
            var $len = $('.hot_list>ul>li').length;
            var $pages = Math.ceil($len / $i);
            var $w = $('.hotp').width() - 66;

            var $showbox = $('.hot_list');

            var $pre = $('div.left_icon');
            var $next = $('div.rgt_icon');

            $pre.click(function () {
                if (!$showbox.is(':animated')) {
                    if ($cur == 1) {
                        $showbox.animate({
                            left: '-=' + $w * ($pages - 1)
                        }, 500);
                        $cur = $pages;
                    } else {
                        $showbox.animate({
                            left: '+=' + $w
                        }, 500);
                        $cur--;
                    }

                }
            });

            $next.click(function () {
                if (!$showbox.is(':animated')) {
                    if ($cur == $pages) {
                        $showbox.animate({
                            left: 0
                        }, 500);
                        $cur = 1;
                    } else {
                        $showbox.animate({
                            left: '-=' + $w
                        }, 500);
                        $cur++;
                    }

                }
            });

        });

        function mclose() {
            if (ddmenuitem) ddmenuitem.hide();
        }

        function mclosetime() {
            closetimer = window.setTimeout(mclose, timeout);
        }

        function mcancelclosetime() {
            if (closetimer) {
                window.clearTimeout(closetimer);
                closetimer = null;
            }
        }
    </script>
</head>

<body>
<div id="doc">

    <div id="s_hdw">
        <div class="mmenu">
            <div class="s_hd">

                <div id="s_cartbox" class="s_cartbox">您的购物车中暂无商品，赶快选择心爱的商品吧！</div>

                <script type="text/javascript">
                    $(document).ready(function () {
                        $("#s_cats").hoverClass("current");
                    });
                </script>
            </div>
        </div><!--mmenu end-->

    </div><!--s_hdw end-->

    <link type="text/css" href="${ctx}/static/css/info.css" rel="stylesheet"/>

    <div id="s_bdw">
        <div id="s_bd">

            <div class="stepflow"><img src="${ctx}/static/images/stepend.gif" width="980" height="33" alt=""/></div>
            <form id="paymentForm" action="${ctx}/order/payment" method="post">
                <input type="hidden" name="orderSn" value="${goodCarSno!}"/>
                <div class="osuccess">
                    <strong class="tit">您的订单提交成功！</strong>
                    <p>我们讲为您保留订单2日，如果2日后EGO网仍未收到您的付款，我们将自动取消此订单。<br/>商品预计到达时间：2011年11月06日，星期日，下午14:00-18:00，请您注意查收。</p>
                    <div class="ddinfo">
                        订单编号：${goodCarSno!} <a class="blue" href="#">查看订单详情</a><br/>付款方式：在线支持 支付宝<br/>应付金额：<strong
                                class="red">￥${totalPrice}</strong>
                    </div>
                    <p class="gopay"><a href="javascript:void(0);" onclick="payment()"><img
                                    src="${ctx}/static/images/gopay.gif" width="88" height="36" alt=""/></a></p>

                    <div class="nowgo">您现在还可以：<br/><a class="blue" href="#">返回首页</a><a class="blue" href="#">继续购物</a><a
                                class="blue" href="#">查看订单</a><a class="blue" href="#">订单中心</a></div>
                </div><!--osuccess end-->
            </form>

            <input type="hidden" value="${goodCarSno!''}" id="ordersno">
            <input type="button" onclick="orderFrom()" value="手动提交">

            <dl style="margin-top:10px;">
                <dt>注意事项：</dt>
                <dd>1.“订单提交成功”仅表明EGO网收到了您的订单，只有您的订单通过审核后，才代表订单正式生效；<br/>2.选择货到付款/EGO快递的客户，请您务必认真检查所收货物，如有不符，您可以拒收；<br/>3.选择其他方式的客户，请您认真检查外包装。如有明显损坏迹象，您可以拒收该货品，并及时通知我们；<br/>4.建议在购买的15天内保留商品的全套包装、附件、发票等所有随货物品，以便后续的保修处理。
                </dd>
            </dl>

        </div><!--s_bd end-->
    </div><!--s_bdw end-->

    <div id="s_ftw">

        <div class="ft_quicklinks">
            <div class="ftql cf">
                <ul>
                    <li class="ftql_s">
                        <h3>购物指南</h3>
                        <ul>
                            <li><a href="">怎样购物</a></li>
                            <li><a href="">会员制</a></li>
                            <li><a href="">积分制度</a></li>
                            <li><a href="">优惠券介绍</a></li>
                            <li><a href="">订单状态说明</a></li>
                        </ul>
                    </li>
                    <li class="ftql_s">
                        <h3>服务条款</h3>
                        <ul>
                            <li><a href="">售后条款</a></li>
                            <li><a href="">退换货说明</a></li>
                            <li><a href="">联系客服</a></li>
                        </ul>
                    </li>
                    <li class="ftql_s">
                        <h3>配送方式</h3>
                        <ul>
                            <li><a href="">上门自提</a></li>
                            <li><a href="">快递运输</a></li>
                            <li><a href="">特快专递（EMS）</a></li>
                            <li><a href="">如何送礼</a></li>
                        </ul>
                    </li>
                    <li class="ftql_s">
                        <h3>支付帮助</h3>
                        <ul>
                            <li><a href="">货到付款</a></li>
                            <li><a href="">在线支付</a></li>
                            <li><a href="">邮政汇款</a></li>
                            <li><a href="">银行转账</a></li>
                            <li><a href="">发票说明</a></li>
                        </ul>
                    </li>
                    <li class="ftql_s">
                        <h3>关于EGO商城</h3>
                        <ul>
                            <li><a href="">EGO商城介绍</a></li>
                            <li><a href="">团队</a></li>
                            <li><a href="">媒体报道</a></li>
                            <li><a href="">招纳贤士</a></li>
                            <li><a href="">公告</a></li>
                        </ul>
                    </li>
                    <li class="ftql_s">
                        <div class="ftql_d">
                            <div class="str">客服中心信箱：</div>
                            <div class="val"><a href="mailto:service@shunkelong.com">sxt@bjsxt.com</a></div>
                        </div>
                        <div class="ftql_d">
                            <div class="str">客服中心热线：</div>
                            <div class="val stel">400-009-1906</div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>

        <div id="s_ft">
            <div class="ft_suggest pt100">请帮助我们提高！<a href="#">商城首页意见反馈</a></div>
            <div class="ft_banners1 tac pbt10">
                <ul>
                    <li><a href="#"><img src="${ctx}/static/images/ft_1.gif" border="0"/></a></li>
                    <li><a href="#"><img src="${ctx}/static/images/ft_2.gif" border="0"/></a></li>
                    <li><a href="#"><img src="${ctx}/static/images/ft_3.gif" border="0"/></a></li>
                </ul>
            </div>
            <div class="copyright tac pbt10">版权所有 Copyright&copy; EGO商城 All Rights Reserved 版权所有</div>
            <div class="ft_banners2 tac">
                <ul>
                    <li><a href="#"><img src="${ctx}/static/images/u255.png" border="0"/></a></li>
                    <li><a href="#"><img src="${ctx}/static/images/u257.png" border="0"/></a></li>
                    <li><a href="#"><img src="${ctx}/static/images/u259.png" border="0"/></a></li>
                    <li><a href="#"><img src="${ctx}/static/images/u261.png" border="0"/></a></li>
                </ul>
            </div>
        </div>

    </div><!--s_ftw end-->

</div>

<script type="text/javascript">
    // 去付款
    function payment() {
        $("#paymentForm").submit();
    }
    function orderFrom() {
        var sno = $("#ordersno").val();
        if(sno!=null&&sno.trim()!=''){
            window.location.href = "${ctx}/form/saveOrderForm<#if addressNum??>?addressNum=${addressNum}</#if>";
        }
    }
</script>
</body>
</html>
