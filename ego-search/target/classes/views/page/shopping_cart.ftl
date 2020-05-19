<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <title>我的购物车</title>
    <#include "../head.ftl">
    <script src="${ctx}/static/js/shopping_cart/shopping_cart.js"></script>
    <link rel="stylesheet" href="${ctx}/static/css/shopping_cart/shopping_cart.css"/>
</head>
<body>
<#include "header.ftl">
<div class="top_content">
    <div class="publish_product_div">
        <#if addressList?? && (addressList?size > 0)>
            <#list addressList as orderForm>
                <div class="shipping_address" id="${(orderForm_index)!}">
                    <span class="name_info">${(orderForm.username)!''}</span><img src="${ctx}/static/img/pencil.jpg" class="pencil_icon"/><br/>
                    <span class="address_info">${(orderForm.address)!} </span><br/><br/>
                    <span class="phone_info">${(orderForm.context)!}</span><br/>
                    <span class="is_default">默认地址</span><img src="${ctx}/static/img/delete_icon.jpg" class="delete_icon"/>
                </div>
            </#list>
        </#if>
        <div class="shipping_address" id="${(orderForm.id)!}">
            <input type="button" onclick="addAddress()" value="添加"/>
        </div>
    </div>
    <div class="cart_content">
        <table>
            <tr class="table_head">
                <th colspan="4">商品信息</th>
                <th>商品金额</th>
                <th>商品数量</th>
                <th>总金额</th>
                <th>编辑</th>
            </tr>
            <form method="post" action="${ctx}/order/toPreOrder">
                <#if (cartResult.cartList)?? && ((cartResult.cartList)?size > 0)>
                    <#list cartResult.cartList as cart>
                        <tr class="table_content" id="div_${cart.id}">
                            <td class="input_checkbox">
                                <input id="1" type="hidden" checked="checked" name="checkbox" value="${(cart.id)?c}"/>
                            </td>
                            <td class="show_img">
                                <img src="${cart.image}"/></td>
                            <td class="title" colspan="2"><span>${cart.name}</span></td>
                            <td class="cost">￥<span>${(cart.price)?c}</span>元</td>
                            <td class="count">
                                <span class="minus">-</span>
                                <span class="number">${cart.goodsNum}</span>
                                <span class="add">+</span>
                            </td>
                            <td class="per_sum">￥<span th:text="sum(${cart.price},${cart.goodsNum})">${(cart.price*cart.goodsNum)?c}</span>元</td>
                            <td class="delete_img"><img src="${ctx}/static/img/delete_icon.jpg" th:value="${cart.id}"
                                                        onclick="deleteShopCar(${cart.id})"/></td>
                        </tr>
                    </#list>
                </#if>


                <tr class="end_pay">
                    <td class="is_all"><input id="all" checked="checked" type="hidden"/></td>
                    <td class="space" colspan="3"></td>
                    <td colspan="2" class="all_sum">总价：￥<span>500.00</span>元</td>
                    <td colspan="3" class="pay_button_div">
                        <input type="hidden" name="toPreOrderNum" value="0">

                        <button class="pay_button">结算</button>
                    </td>
                </tr>
            </form>
        </table>
    </div>
    <footer>
        <p>Copyright © 2017.Company name All rights reserved.<a href="/"></a></p>
    </footer>
</div>
<script type="text/javascript">
    function sum(price, goodsNum) {
        return price * goodsNum;
    }
    
    var ctx = '${ctx}';
    
    function addAddress() {
        $.ajax({
            url: '${ctx}/order/addAddress',
            type: 'post',
            dataType: 'JSON',
            success: function (data) {
                var result = JSON.parse(data);
                if (200 == result.code) {
                    layer.msg("添加地址成功");
                    window.location.href="http://localhost:9094/ego-search/order/";
                } else {
                    layer.msg("添加地址失败");
                }
            }
        });
    }

    function deleteShopCar(id) {
        str = String(id)
        id = str.replace(",", "");
        if (id == null || id.trim() == '' || id < 0) {
            return;
        }
        $.ajax({
            url: '${ctx}/order/deleteGoodsCar',
            type: 'post',
            dataType: 'JSON',
            data: {id: id},
            success: function (data) {
                var result = JSON.parse(data);
                if (200 == result.code) {
                    layer.msg("删除购物车成功");
                    $("#div_" + id).remove();
                } else {
                    layer.msg("删除购物车失败");
                }
            }
        });
    }

</script>
</body>
</html>

