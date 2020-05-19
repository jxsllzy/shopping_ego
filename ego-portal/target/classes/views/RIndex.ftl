<!-- 设置项目根路径全局变量 -->
<#assign ctx=request.contextPath/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>发布商品</title>
    <link href="${ctx}/static/css/home_page/header_and_nav.css" rel="stylesheet">
    <link rel="icon" href="${ctx}/static/img/page_icon.png">
    <link href="${ctx}/static/css/product/publish_product.css" rel="stylesheet">
    <script src="${ctx}/static/js/jquery-3.4.1.js"></script>
    <script src="${ctx}/static/js/home/header_model_js.js"></script>
    <!-- 引入 doT.js -->
    <script type="text/javascript" src="${ctx}/static/js/doT.min.js"></script>
    <script src="${ctx}/static/js/layer/layer-min.js"></script>
    <link href="${ctx}/static/layui/css/layui.css" rel="stylesheet" type="text/css"/>
    <script src="${ctx}/static/layui/layui.js" type="text/javascript"></script>
</head>
<body>
<#include "head.ftl">
<div class="top_content">
    <div class="publish_product_div">
        <!--表单 --->
        <form id="category_form" method="post">
            <#--储存当前商品得id-->
            <#if Aid??>
                <input id="Aid" type="hidden" value="${Aid}">
                <#else >
                <input type="hidden" id="Aid">
            </#if>

            <div class="publish_one_par">
                <div class="publish_title">
                    <h3>商品名</h3>
                </div>
                <div class="publish_content">
                    <#if (shops.name)??>
                        <input type="text" class="title_input" id="name" name="name" required value="${shops.name}">
                        <#else>
                        <input type="text" class="title_input" id="name" name="name" required>
                    </#if>

                </div>
            </div>
            <div class="publish_one_par">
                <div class="publish_title">
                    <h3>成色</h3>
                </div>
                <div class="publish_content">
                    <select class="choose_chengse" name="level">
                        <#if (shops.level)??>
                            <#if shops.level==1>
                                <option value="3" selected >三成</option>
                                <option value="4">四成</option>
                                <option value="5">五成</option>
                                <option value="6">六成</option>
                                <option value="7" >七成</option>
                                <option value="8">八成</option>
                                <option value="9">九成</option>
                                <option value="10">全新</option>
                                <#elseif shops.level==4>
                                    <option value="3">三成</option>
                                    <option value="4"selected>四成</option>
                                    <option value="5">五成</option>
                                    <option value="6">六成</option>
                                    <option value="7" >七成</option>
                                    <option value="8">八成</option>
                                    <option value="9">九成</option>
                                    <option value="10">全新</option>
                                <#elseif shops.level==5>
                                <option value="3">三成</option>
                                <option value="4">四成</option>
                                <option value="5"selected>五成</option>
                                <option value="6">六成</option>
                                <option value="7" >七成</option>
                                <option value="8">八成</option>
                                <option value="9">九成</option>
                                <option value="10">全新</option>
                                <#elseif shops.level==6>
                                <option value="3">三成</option>
                                <option value="4">四成</option>
                                <option value="5">五成</option>
                                <option value="6" selected>六成</option>
                                <option value="7" >七成</option>
                                <option value="8">八成</option>
                                <option value="9">九成</option>
                                <option value="10">全新</option>
                                <#elseif shops.level==7>
                                <option value="3">三成</option>
                                <option value="4">四成</option>
                                <option value="5">五成</option>
                                <option value="6" >六成</option>
                                <option value="7" selected>七成</option>
                                <option value="8">八成</option>
                                <option value="9">九成</option>
                                <option value="10">全新</option>
                            <#elseif shops.level==8>
                                <option value="3">三成</option>
                                <option value="4">四成</option>
                                <option value="5">五成</option>
                                <option value="6" >六成</option>
                                <option value="7" >七成</option>
                                <option value="8" selected>八成</option>
                                <option value="9">九成</option>
                                <option value="10">全新</option>
                            <#elseif shops.level==9>
                                <option value="3">三成</option>
                                <option value="4">四成</option>
                                <option value="5">五成</option>
                                <option value="6" >六成</option>
                                <option value="7" >七成</option>
                                <option value="8" >八成</option>
                                <option value="9" selected>九成</option>
                                <option value="10">全新</option>
                            <#elseif shops.level==10>
                                <option value="3">三成</option>
                                <option value="4">四成</option>
                                <option value="5">五成</option>
                                <option value="6" >六成</option>
                                <option value="7" >七成</option>
                                <option value="8" >八成</option>
                                <option value="9" >九成</option>
                                <option value="10" selected>全新</option>
                            </#if>

                        <#else>
                            <option value="3">三成</option>
                            <option value="4">四成</option>
                            <option value="5">五成</option>
                            <option value="6">六成</option>
                            <option value="7" selected >七成</option>
                            <option value="8">八成</option>
                            <option value="9">九成</option>
                            <option value="10">全新</option>
                        </#if>
                    </select>
                </div>
            </div>
            <div class="publish_one_par">
                <div class="publish_title">
                    <h3>单价</h3>
                </div>
                <div class="publish_content cost_content">
                    <#if (shops.price)??>
                        <input type="text" class="cost_input" id="price" name="price" value="${shops.price}" required><span>  RMB(.00)</span>
                        <#else >
                        <input type="text" class="cost_input" id="price"  name="price" required><span>  RMB(.00)</span>
                    </#if>

                </div>
            </div>
            <div class="publish_one_par">
                <div class="publish_title">
                    <h3>数量</h3>
                </div>
                <div class="publish_content">
                    <#if (shops.quantity)??>
                        <input type="text" id="quantity" class="count_input" name="quantity" value="${shops.quantity}" required>
                        <#else >
                            <input type="text" id="quantity" class="count_input" name="quantity" required>
                    </#if>

                </div>
            </div>
            <div class="publish_one_par">
                <div class="publish_title">
                    <h3>详情</h3>
                </div>
                <div class="publish_content">
                    <#if (shops.remark)??>
                        <textarea class="detail_textarea" maxlength="122"
                                   name="remark" required>${shops.remark}</textarea>
                        <#else >
                        <textarea class="detail_textarea" maxlength="122"
                                  placeholder="留下联系方式,方便买家联系" name="remark" required></textarea>
                    </#if>

                </div>
            </div>
            <div class="publish_one_par">
                <div class="publish_title">
                    <h3>分类</h3>
                </div>
                <div class="publish_content">
                    <input type="hidden" name="sort" id="sort" value="0">
                    <select class="choose_first_type" name="sort" id="cat_id"
                            onchange="getCategory(this.value,'cat_id_2','0','sort');">
                        <option value="0">请选择商品分类</option>
                        <#list gcList as gc>
                            <option value="${gc.id}">${gc.name}</option>
                        </#list>
                    </select><span class="to_left">--</span>
                    <select class="choose_second_type" name="cat_id_2" id="cat_id_2"
                            onchange="getCategoryT(this.value,'cat_id_3','1','sort');" >
                        <option value="0">请选择商品分类</option>
                    </select><span class="to_left">--</span>
                    <select class="choose_third_type" name="cat_id_3" id="cat_id_3"
                            onchange="setHiddenValue(this.value,'sort');">
                        <option value="0">请选择商品分类</option>
                    </select>
                </div>
            </div>
            <div class="publish_one_par">
                <div class="publish_title">
                    <h3>图片</h3>
                </div>
                <div class="layui-input-block">
                    <!-- 隐藏的input,一个隐藏的input（用于保存文件url） -->
                    <input type="hidden" name="image" id="img_url"/>
                    　　　　<!-- 上传按钮 -->
                    　　　　<button type="button" class="layui-btn" id="uploadPic"><i class="layui-icon">&#xe67c;</i>选择图片</button>
                    　　　　<button type="button" class="layui-btn layui-btn-warm" id="uploadPicBtn">开始上传</button>
                    　　　　<!-- 预览区域 -->
                    　　　　<div class="layui-upload-list">
                        　　　　　　<img class="layui-upload-img" width="100px" height="80px" id="preShow"/>
                        　　　　　　<p id="demoText"></p>
                        　　　　</div>
                    　　</div>
            </div>
            <div class="submit_content_div">
                <input type="button" onclick="ajaxSubmit();" value="保存" class="submit_input">
            </div>
        </form>
    </div>
    <footer>
        <p>Copyright © 2014.Company name All rights reserved.</p>
    </footer>
</div>
<script type="text/javascript">

    /**
     * ajax 提交表单 到后台去验证然后回到前台提示错误
     * 验证通过后,再通过 form 自动提交
     * form_id:表单id
     * submit_url:提交的url
     * add_url:继续新增的url
     * list_url:返回列表的url
     * hiddenId:隐藏域id
     */
    function ajaxSubmit() {
        //前台验证
        var name=$("#name").val();
        if(name =="" && name.length<1){
            layer.alert("请填写用户名");
            return;
        }

        var imgUrl=$("#img_url").val();
        if(imgUrl =="" && imgUrl.length<1){
            layer.alert("请上传图片");
            return;
        }
        var quantity=$("#quantity").val();
        if(quantity =="" && quantity.length<1){
            layer.alert("请填写数量");
            return;
        }
        var price=$("#price").val();
        if(price =="" && price.length<1){
            layer.alert("前填写价格");
            return;
        }



        var Aid=$("#Aid").val();
        var url=null;
        if(Aid!=null&&Aid.trim()!=''){
            url="${ctx}/release/save?AId="+Aid;
        }else{
            url="${ctx}/release/save"
        }
        $.ajax({
            type: "POST",
            url:url,
            data: $("#category_form").serialize(),// 你的formid
            dataType: "JSON",
            error: function (request) {
                alert("服务器繁忙, 请联系管理员!");
            },
            success: function (result) {
                console.log(result)
                if (200 == result.code) {
                    layer.alert("保存成功");
                    //清空表单信息
                    $("#category_form")[0].reset();
                    //重定向到发布商品页面
                    window.location.href="${ctx}/issue/index";

                }else if(result){

                } else {

                    layer.alert("保存失败");
                }
            }
        });
    }

    /**
     * 二级
     * 获取多级联动的商品分类
     * id:当前选择框的值
     * next：下级选择框显示的内容
     * select_id:level
     */
    function getCategory(id, next, select_id) {
        var url = '${ctx}/release/' + id;
        // 用户重新选择顶级分类时，重置下级分类为：请选择商品分类，且清空下级分类信息
        var htmlStr = "<option value='0'>请选择商品分类</option>";
        //修改parentId
        $("#parentId").val(id);
        if (0 == id) {
            $("#" + next).html(htmlStr);
            //修改level
            $("#level").val(1);
            return;
        }
        $.ajax({
            type: "GET",
            url: url,
            error: function (request) {
                layer.alert("获取子分类失败！");
            },
            success: function (result) {

                if (result.length > 0) {
                    for (i = 0; i < result.length; i++) {
                        htmlStr += "<option value='" + result[i].id + "'>" + result[i].name + "</option>"
                    }
                    $("#" + next).html(htmlStr);
                    $("#level").val(2);
                } else {
                    layer.alert("获取子分类失败！");
                }
            }
        });
    }

    /**
     * 三级
     * 获取多级联动的商品分类
     * id:当前选择框的值
     * next：下级选择框显示的内容
     * select_id:level
     */
    function getCategoryT(id, next, select_id) {
        var url = '${ctx}/release/three/' + id;
        // 用户重新选择顶级分类时，重置下级分类为：请选择商品分类，且清空下级分类信息
        var htmlStr = "<option value='0'>请选择商品分类</option>";
        //修改parentId
        $("#parentId").val(id);
        if (0 == id) {
            $("#" + next).html(htmlStr);
            //修改level
            $("#level").val(1);
            return;
        }
        $.ajax({
            type: "GET",
            url: url,
            error: function (request) {
                layer.alert("获取子分类失败！");
            },
            success: function (result) {
                if (result.length > 0) {
                    for (i = 0; i < result.length; i++) {
                        htmlStr += "<option value='" + result[i].id + "'>" + result[i].name + "</option>"
                    }
                    $("#" + next).html(htmlStr);
                    $("#level").val(2);
                } else {
                    layer.alert("获取子分类失败！");
                }
            }
        });
    }

    /**
     * 设置商品分类的隐藏域的值
     * @param value
     * @param hiddenId
     */
    function setHiddenValue(value, hiddenId) {
        $("#" + hiddenId).val(value);
    }

    //#####################商品图片上传begin########################
    layui.use('upload', function(){
        var upload = layui.upload , $ = layui.jquery;
        //上传图片
        var uploadInst = upload.render({
            elem: '#uploadPic' //绑定元素
            , url: '${ctx}/ar/uploadFile' //上传接口 [[@{/upload/img}]]
            , auto: false
            , bindAction: '#uploadPicBtn'
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#preShow').attr('src', result); //图片链接（base64）
                });
            }
            , done: function (res) {
                //如果上传失败
                if (res.code > 0) {
                    alert("上传失败" + res.data.src);
                    return layer.msg('上传失败');
                }
                //上传成功
                console.log("上传成功" + res.data.src);
                document.getElementById("img_url").value = res.data.src;
                return layer.msg('上传成功');
            }
            , error: function () {
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
            }
        })
    });
    //#####################商品图片上传end########################
</script>
</body>
</html>