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
            <#if error??>
            swal('订单提交失败!','请核对信息','error');
            </#if>
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

            <div class="stepflow"><img src="${ctx}/static/images/cartinforstep.gif" width="980" height="32" alt=""/>
            </div>

            <div class="infotable">
                <table width="100%">
                    <tr>
                        <td class="tit">收货地址</td>
                        <td class="summary">
                            <table class="address" width="100%" style="font-size: 20px;">
                                <input type="hidden" value="${(orderForm.id)!}">
                                <tr>
                                    <td><span class="red">*</span>收货人：<span>${(orderForm.username)!''}</span></td>
                                </tr>
                                <tr>
                                    <td><span class="red">*</span>详细地址：<span>${(orderForm.address)!''} ${(orderForm.context)!''}</span></td>
                                </tr>

                                <tr>
                                    <td><span class="red">*</span>手机：<span>${(orderForm.phone)!''}</span></td>
                                </tr>
                                <tr>
                                    <td><span class="red">*</span>默认地址<span></td>
                                </tr>
                                <tr>
                                    <td colspan="2" height="10px"></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </div><!--infotable end-->

            <script type="text/javascript">
                $(function () {
                    $(".focutext00").focusText();
                    $(".focutext01").focusText();
                    $(".focutext02").focusText();
                    $(".focutext03").focusText();
                });
            </script>

            <div class="infotable">
                <table width="100%">
                    <tr>
                        <td class="tit">支付方式</td>
                        <td class="summary">
                            <table class="address" width="100%">
                                <tr>
                                    <td valign="top" width="10px"></td>
                                    <td valign="top" width="40px"><input type="radio" name="zf" id="df" value=""/></td>
                                    <td valign="top" width="110px"><label for="df">货到付款</label></td>
                                    <td valign="top">货到付款,<span class="red">EGO快递可刷卡</span>，免手续费；<span class="red">第三方EGO快递只能使用现金；可使用支票</span><br/>(1.支票抬头：上海EGO电子商务发展有限公司；2.支票限额：每个订单支票支付限额为4万；3.暂不接受非上海公司的支票)
                                    </td>
                                </tr>
                                <tr>
                                    <td height="10px"></td>
                                </tr>
                                <tr>
                                    <td height="10px"></td>
                                </tr>
                                <tr>
                                    <td valign="top" width="10px"></td>
                                    <td valign="top" width="40px"><input type="radio" name="zf" id="opay" value="" checked="checked"/>
                                    </td>
                                    <td valign="top"><label for="opay">在线支付</label></td>
                                    <td valign="top">
                                        通过网上银行或支付平台在线付款
                                        <table>
                                            <tr>
                                                <td><input type="radio" name="bank" id="bank14" value="" checked="checked"
                                                           class="bankicon"/><label for="bank14"><img
                                                                src="${ctx}/static/images/bank/bank14.gif" width="96"
                                                                height="32"
                                                                alt=""/></label></td>
                                                <td><input type="radio" name="bank" id="bank14" value=""
                                                           class="bankicon"/><label for="bank14"><img
                                                                src="${ctx}/static/images/bank/bank14.gif" width="96"
                                                                height="32"
                                                                alt=""/></label></td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td height="20px"></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </div><!--infotable end-->

            <div class="catinfotable">
                <table width="100%">
                    <tr>
                        <td class="tit">购物清单<br/><a style="font-size:12px;font-weight:normal;" class="blue"
                                                    href="#">更改</a></td>
                        <td class="summary">
                            <table class="catlist" width="100%">
                                <tr bgcolor="#f3f3f3">
                                    <th>购物车中的商品</th>
                                    <th>EGO价</th>
                                    <th>返现</th>
                                    <th>送积分</th>
                                    <th>购买数量</th>
                                    <th>发货时间</th>
                                </tr>
                                <#if (cartResult.cartList)?? && ((cartResult.cartList)?size > 0)>
                                    <#list cartResult.cartList as cart>
                                        <tr>
                                            <td><a href="#">${cart.name}</a><br/>商品编号：21-255-244</td>
                                            <td class="red">￥${cart.price}</td>
                                            <td>￥0.00</td>
                                            <td>0</td>
                                            <td>${cart.goodsNum}</td>
                                            <td name="cartType">${cart.context}</td>
                                        </tr>
                                    </#list>
                                </#if>
                            </table>
                        </td>
                    </tr>
                </table>
            </div>

            <div class="catinfotable">
                <table width="100%">
                    <tr>
                        <td class="tit">订单结算</td>
                        <td class="summary">
                            <div class="f-l" style="width:640px;">
                                <table class="ddbox" width="100%">
                                    <tr>
                                        <td><h1 style="margin-left: 30px; font-size: 30px;">马上你就能拥有他们了</h1></td>
                                    </tr>

                                </table>

                            </div>
                            <div class="f-r" style="width:170px;padding:0 15px;background:#f3f3f3;">
                                <form action="${ctx}/order/submitOrder<#if (addressNum)??>?addressNum=${(addressNum)!''}</#if>" method="post">
                                    <table class="totable" width="100%">
                                        <tr>
                                            <td width="80px">商品总重：</td>
                                            <td align="right">3.640千克</td>
                                        </tr>
                                        <tr>
                                            <td>商品总价：</td>
                                            <td align="right">￥${cartResult.totalPrice}</td>
                                        </tr>
                                        <tr>
                                            <td>运费金额：</td>
                                            <td align="right">￥0.00</td>
                                        </tr>
                                        <tr>
                                            <td>积分抵扣：</td>
                                            <td align="right">￥0.00</td>
                                        </tr>
                                        <tr>
                                            <td>返现优惠：</td>
                                            <td align="right">￥0.00</td>
                                        </tr>
                                        <tr>
                                            <td>手&nbsp;&nbsp;续&nbsp;&nbsp;费：</td>
                                            <td align="right">￥0.00</td>
                                        </tr>
                                        <tr>
                                            <td>优&nbsp;&nbsp;惠&nbsp;&nbsp;券：</td>
                                            <td align="right">￥0.00</td>
                                        </tr>
                                        <tr>
                                            <td>合&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;计：</td>
                                            <td align="right"><strong
                                                        style="font-size:18px;color:#dc0000;">￥${cartResult.totalPrice}</strong>
                                            </td>
                                        </tr>
                                        <tr>
                                            <#if (error?string('true', 'false'))=='true'>
                                                <td align="right" colspan="2"><input type="button" name="" id="" value="库存不足"/></td>
                                            <#else >
                                                    <td align="right" colspan="2"><input type="submit" name="" id="" value="" class="btnimg"/></td>
                                            </#if>
                                        </tr>
                                        <tr>
                                            <td height="10px"></td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                        </td>
                    </tr>
                </table>
            </div><!--catinfotable end-->

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
</body>
</html>
