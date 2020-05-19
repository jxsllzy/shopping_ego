<header>
    <span class="short_nav"></span>
    <div class="home_icon">
        <a href="${ctx}/index.html" style="text-decoration: none;color: white">C2C</a>
    </div>
    <input type="text" placeholder="Search" name="search" class="nav_search_input" value="${searchStr!}">
    <span class="search_icon"></span>
    <span class="user_icon"></span>
    <span class="login_or_register_string">
        <#if user??>
            <span class="user_name_a">${(user.username)!'admin'}</span>
            <#else >
            <a href="${ctx}/login">登陆</a> ， <a href="${ctx}/login">注册</a>
        </#if>
    </span>
</header>
<div class="short_nav_show">
    <ul>
        <li><a href="${ctx}/index">首页</a></li>
        <li><a href="${ctx}/mall_page">商城</a></li>
        <li><a href="${ctx}/userwant/userlist">求购商品</a></li>
        <li><a href="javascript:void(0)">反馈与意见</a></li>
        <li><a href="javascript:void(0)">联系我们</a></li>
    </ul>
</div>
<nav class="my_nav">
    <ul>
        <li><a href="${ctx}/index">首页</a></li>
        <li><a href="${ctx}/mall_page">商城</a></li>
        <li><a href="${ctx}/userwant/userlist">求购商品</a></li>
        <li><a href="javascript:void(0)">反馈与意见</a></li>
        <li><a href="javascript:void(0)">联系我们</a></li>
    </ul>
</nav>
<div class="personal_nav">
    <ul>
        <li><a href="${ctx}/user/info">个人信息</a></li>
        <li><a href="${ctx}/release/RIndex">发布商品</a></li>
        <li><a href='${ctx}/userwant/updateview'>发布求购信息</a></li>
        <li><a href="${searchUrl}/order/">我的购物车</a></li>
        <li><a href="${ctx}/issue/index">我发布的商品</a></li>
        <li><a href="${ctx}/userwant/userlist">我求购的商品</a></li>
        <li><a class="login_out" href="${ctx}/user/logout">退出</a></li>
    </ul>
</div>
<script type="text/javascript">
    $('.search_icon').click(function () {
        var name = $('.nav_search_input').val();
        if (name==null||name.trim()==''){
            return;
        }
        window.location.href = '${searchUrl}/findShopByName?name=' + name;
    });

    function addOrder(id) {

        <#if !user??>
            window.location.href = "${ctx}/login";
            return;
        </#if>

        if (id==null||id<0){
            return;
        }
        $.ajax({
            url:'${searchUrl}/order/insertGoodsCar',
            dataType:"jsonp",
            jsonp:'callbacks',
            data:{id:id,goodsNum:1},
            success:function (data) {
                var result = JSON.parse(data);
                if (200 == result.code){
                    layer.msg("加入购物车成功");
                } else {
                    layer.msg("加入购物车失败");
                }
            },
            error: function (result) {
                console.log(result);
                layer.msg("加入购物车成功");
            }
        })
    }
</script>