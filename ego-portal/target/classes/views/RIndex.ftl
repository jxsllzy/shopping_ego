<!-- 设置项目根路径全局变量 -->
<#assign ctx=request.contextPath/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>发布商品</title>
    <link href="${ctx}/css/home_page/header_and_nav.css" rel="stylesheet">
    <link rel="icon" href="${ctx}/img/page_icon.png">
    <link href="${ctx}/css/product/publish_product.css" rel="stylesheet">
    <script src="${ctx}/js/jquery-3.4.1.js"></script>
    <script src="${ctx}/js/home/header_model_js.js"></script>
    <link rel="stylesheet" href="${ctx}/css/fileinput.min.css"/>
    <script type="text/javascript" src="${ctx}/js/fileinput.js"></script>
    <!-- 对中文的支持 -->
    <script type="text/javascript" src="${ctx}/js/fileinput_locale_zh.js"></script>
    <script src="${ctx}/bootstrap/js/bootstrap.min.js"></script>
    <link href="${ctx}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<header>
    <span class="short_nav"></span>
    <div class="home_icon">
        <a href="../index.html" style="text-decoration: none;color: white">C2C</a>
    </div>
    <input type="text" placeholder="Search" name="search" class="nav_search_input">
    <span class="search_icon"></span>
    <span class="user_icon"></span>
    <!--修改为用户名-->
    <span class="login_or_register_string">
        <a href="login_page.html">登陆</a> ， <a href="login_page.html">注册</a>
        <!--<a href="page/personal/personal_info.html" class="user_name_a">这是一个用户名这是一个用户名这是一个用户名</a>-->
    </span>
</header>
<div class="short_nav_show">
    <ul>
        <li><a href="../index.html">首页</a></li>
        <li><a href="mall_page.html">商城</a></li>
        <li><a href="javascript:void(0)">发布商品</a></li>
        <li><a href="require_product.html">求购商品</a></li>
        <li><a href="shopping_cart.html">我的购物车</a></li>
        <li><a href="javascript:void(0)">反馈与意见</a></li>
        <li><a href="javascript:void(0)">联系我们</a></li>
    </ul>
</div>
<nav class="my_nav">
    <ul>
        <li><a href="../index.html">首页</a></li>
        <li><a href="mall_page.html">商城</a></li>
        <li><a href="javascript:void(0)">发布商品</a></li>
        <li><a href="require_product.html">求购商品</a></li>
        <li><a href="shopping_cart.html">我的购物车</a></li>
        <li><a href="javascript:void(0)">反馈与意见</a></li>
        <li><a href="javascript:void(0)">联系我们</a></li>
    </ul>
</nav>
<div class="top_content">
    <div class="publish_product_div">
        <!--表单 --->
        <form id="category_form" method="post">
            <div class="publish_one_par">
                <div class="publish_title">
                    <h3>商品名</h3>
                </div>
                <div class="publish_content">
                    <input type="text" class="title_input" name="name" required>
                </div>
            </div>
            <div class="publish_one_par">
                <div class="publish_title">
                    <h3>成色</h3>
                </div>
                <div class="publish_content">
                    <select class="choose_chengse" name="level">
                        <option value="3">三成</option>
                        <option value="4">四成</option>
                        <option value="5">五成</option>
                        <option value="6">六成</option>
                        <option value="7" selected >七成</option>
                        <option value="8">八成</option>
                        <option value="9">九成</option>
                        <option value="10">全新</option>
                    </select>
                </div>
            </div>
            <div class="publish_one_par">
                <div class="publish_title">
                    <h3>单价</h3>
                </div>
                <div class="publish_content cost_content">
                    <input type="text" class="cost_input" name="price" required><span>  RMB(.00)</span>
                </div>
            </div>
            <div class="publish_one_par">
                <div class="publish_title">
                    <h3>数量</h3>
                </div>
                <div class="publish_content">
                    <input type="text" class="count_input" name="quantity" required>
                </div>
            </div>
            <div class="publish_one_par">
                <div class="publish_title">
                    <h3>详情</h3>
                </div>
                <div class="publish_content">
                    <textarea class="detail_textarea" maxlength="122"
                              placeholder="留下联系方式,方便买家联系" name="remark" required></textarea>
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
            <div class="form-group">
                <div class="publish_title">
                    <h3>图片</h3>
                </div>
                <input type="text" id="image" name="image"/>
                <form enctype="multipart/form-data">
                    <input id="file-goods-category" class="file" name="file"
                           type="file"
                           data-min-file-count="1">
                </form>
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
        $.ajax({
            type: "POST",
            url: "${ctx}/release/save",
            data: $("#category_form").serialize(),// 你的formid
            dataType: "JSON",
            error: function (request) {
                alert("服务器繁忙, 请联系管理员!");
            },
            success: function (result) {
                console.log(result)
                if (200 == result.code) {
                    alert("保存成功");
                } else {
                    alert("保存失败");
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
    /**
     * 初始设置
     * language指定语言
     * uploadUrl指定文件上传的后台地址
     * allowedPreviewTypes允许上传文件的类型
     */
    $('#file-goods-category').fileinput({
        language: 'zh',
        uploadUrl: '${ctx}/fileUpload/upload',
        allowedPreviewTypes: ['image', 'html', 'text', 'video', 'audio',
            'flash']
    });
    /**
     * 上传文件失败后 调用方法（回调函数）
     */
    $('#file-goods-category').on('fileuploaderror', function (event,
                                                              data, previewId, index) {
        var form = data.form,
            files = data.files, e
        xtra = data.extra,
            response = data.response,
            reader = data.reader;
        console.log(data);
        console.log('File upload error');
    });
    /**
     * 文件错误 比如文件类型错误 调用方法（回调函数）
     */
    $('#file-goods-category').on('fileerror', function (event, data) {
        console.log(data.id);
        console.log(data.index);
        console.log(data.file);
        console.log(data.reader);
        console.log(data.files);
    });
    /**
     * 文件上传成功后 调用方法（回调函数）
     */
    $('#file-goods-category').on('fileuploaded', function (event, data,
                                                           previewId, index) {
        var form = data.form,
            files = data.files,
            extra = data.extra,
            response = data.response,
            reader = data.reader;
        // 服务器文件地址
        // alert(data.response.fileUrl);
        // 将服务器文件地址设置至隐藏域
        $("#image").val(data.response.fileUrl);
        console.log('File uploaded triggered');
    });
    //#####################商品图片上传end########################
</script>
</body>
</html>