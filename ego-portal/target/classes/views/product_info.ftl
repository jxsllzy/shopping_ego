<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>商品详情</title>
    <#include "common.ftl">
    <script>
        $(function () {
            $('.send_comment_button').click(function () {
                var value = $('.comment_textarea').val();

            });
            $('.buy_button').click(function () {

            });
        });
    </script>
    <style>
        body{
            font-family: 'SimSun';
        }
        .top_content {
            width: 99%;
            height: auto;
            /*background-color: aquamarine;*/
            position: absolute;
            display: block;
        }
        .publish_product_div {
            padding-top: 1%;
            padding-bottom: 5%;
            width: 70%;
            height: auto;
            margin-left: 15%;
            margin-top: 12%;
            margin-bottom: 10%;
            /*background-color: red;*/
            background-color: white;
            border-radius: 10px;
            box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.7);
            display: inline-block;
        }
        .product_img_div {
            width: 30%;
            height: auto;
            float: left;
            margin-left: 1%;
        }
        .product_img {
            width: 100%;
        }
        .product_info_div {
            width: 40%;
            display: block;
            float: left;
            margin-left: 5%;
            line-height: 2.1em;
            margin-bottom: 20%;
        }
        .product_info_div .what_info {
            font-size: 1.2em;
            font-weight: 600;
        }
        .publish_comment {
            clear: both;
            margin-left: 5%;
        }
        .publish_comment span {
            font-size: 1.3em;
            color: slateblue;
        }
        .publish_comment textarea {
            width: 90%;
            resize: none;
            color: rgba(0,0,0,0.78);
            border-radius: 5px;
            border: 1px solid slateblue;
            padding: 0.3em;
            box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.1);
            font-size: 1.3em;
            height: 6.5em;
        }
        .send_comment_button {
            width: 15%;
            padding: 0.3em;
            font-size: 1.5em;
            background-color: slateblue;
            color: white;
            border-radius: 5px;
            border: none;
            margin-top: 2em;
            margin-left: 75%;
        }
        .send_comment_button:hover {
            cursor: pointer;
        }
        .one_comment {
            border-bottom: 1px solid rgba(0, 0, 0, 0.33);
            width: 90%;
            margin-left: 5%;
            margin-top: 5%;
        }
        .one_comment .username {
            font-size: 1.3em;
            margin-right: 30%;
            color: rgba(0,0,0,0.82);
        }
        .one_comment .time {
            margin-left: 20%;
            font-size: 1.2em;
            color: rgba(0,0,0,0.62);
        }
        .one_comment .content {
            font-size: 1.3em;
            text-indent: 2em;
            color: rgba(0,0,0,0.62);
        }
        .buy_button {
            width: 40%;
            padding: 0.3em;
            font-size: 1.5em;
            background-color: slateblue;
            color: white;
            border-radius: 5px;
            border: none;
            margin-top: 5em;
            margin-left: 15%;
        }
        footer {
            width: 96%;
            background-color: slateblue;
            box-shadow: 4px 2px 10px #888888;
            border-radius: 10px;
            height: 80px;
            margin-left: 2%;
        }
        footer p {
            padding-top: 35px;
            vertical-align: middle;
            text-align: center;
            color: white;
            font-size: 1.2em;
        }
    </style>
</head>
<body>
<#include "head.ftl">
<div class="top_content">
    <div class="publish_product_div">
        <div class="product_img_div">
            <img src="${productInfo.image}" class="product_img">
        </div>
        <div class="product_info_div">
            <span class="what_info">商品名：</span>
            <span class="info_content">${productInfo.name}</span><br/>
            <span class="what_info">成色：</span>
            <span class="info_content">${productInfo.level}</span><br/>
            <span class="what_info">数量：</span>
            <span class="info_content">${productInfo.quantity}</span><br/>
            <span class="what_info">单价：</span>
            <span class="info_content">${productInfo.price}</span><br/>
            <span class="what_info">详情：</span>
            <span class="info_content">${productInfo.remark}</span><br/>
            <span class="what_info">分类：</span>
            <span class="info_content">${(productName.aname)!''}-${(productName.cname)!''}-${(productName.kname)!''}</span><br/>
            <button class="buy_button">加入购物车</button>
        </div>
        <div class="publish_comment">
            <span>留言：</span><br/><br/>
            <textarea class="comment_textarea" maxlength="122" placeholder="本次最多填写122个字" name="comment"></textarea>
            <button class="send_comment_button">发表</button>
        </div>
        <div class="comment_content">
            <#list shopContext as s>
                <div class="one_comment">
                    <span class="username">用户：${s.uname}</span>
                    <span class="time">发表于：${s.modified?string('yyyy-MM-dd HH:mm:ss')}</span>
                    <p class="content">${s.context}</p>
                </div>
            </#list>
        </div>
    </div>
    <footer style="top: 1722px; left:1%;">
        <p>Copyright © 2014.Company name All rights reserved.</p>
    </footer>
</div>
</body>
</html>