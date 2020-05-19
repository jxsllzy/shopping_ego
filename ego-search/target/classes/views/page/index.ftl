<header>
    <span class="short_nav"></span>
    <div class="home_icon">
        <a href="${ctx}/index.html" style="text-decoration: none;color: white">C2C</a>
    </div>
    <input type="text" placeholder="Search" name="search" class="nav_search_input" value="${searchStr!}">
    <span class="search_icon"></span>
    <span class="user_icon"></span>
    <span class="login_or_register_string">
        <#if admin??>
            <a href="page/personal/personal_info.html" class="user_name_a">这是一个用户名这是一个用户名这是一个用户名</a>
            <div class="personal_nav">
                <ul>
                    <li><a href="personal_info.do">个人信息</a></li>
                    <li><a href="${ctx}/publish_product.do">发布商品</a></li>
                    <li><a href="${ctx}/require_product.do">发布求购信息</a></li>
                    <li><a href="${ctx}/shopping_cart.do">我的购物车</a></li>
                    <li><a href="${ctx}/my_publish_product_page.do">我发布的商品</a></li>
                    <li><a href="${ctx}/my_require_product_page.do">我求购的商品</a></li>
                    <li><a class="login_out" href="${ctx}/logout.do">退出</a></li>
                </ul>
            </div>
            <#else >
            <a href="login_page.html">登陆</a> ， <a href="login_page.html">注册</a>
        </#if>
    </span>
</header>