<!-- 设置项目根路径全局变量 -->
<#assign ctx=request.contextPath/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>C2C平台</title>
    <script src="${ctx}/static/js/jquery-1.3.2.min.js">
    </script>
    <script src="${ctx}/static/js/login/init_login.js"></script>
    <link rel="icon" href="${ctx}/static/img/page_icon.png">
    <link rel="stylesheet" href="${ctx}/static/css/login_and_register/login_back.css">
    <link rel="stylesheet" href="${ctx}/static/css/login_and_register/register_page.css">
    <link rel="stylesheet" href="${ctx}/static/css/login_and_register/confirm_password.css">
    <link rel="stylesheet" href="${ctx}/static/css/login_and_register/forget_password.css">
</head>
<body>
<!--顶级的content是用来充当3D旋转的容器-->
<div class=".stage">
    <div class="content">
        <!--登陆界面-->
        <div class="login">
            <div class="check_mark">
                <div class="left_mark">
                </div>
                <div class="right_mark">
                </div>
            </div>
            <form id="formlogin" method="post">
            <div class="login_form">
                <div class="login_username">
                    <!--svg就很溜-->
                    <svg class="login_icon" viewBox="0 0 20 20">
                        <path d="M0,20 a10,8 0 0,1 20,0z M10,0 a4,4 0 0,1 0,8 a4,4 0 0,1 0,-8"></path>
                    </svg>
                    <input type="text" placeholder="Username" class="input_username"id="loginname" name="username">
                </div>
                <div class="login_password">
                    <svg class="password_icon" viewBox="0 0 20 20">
                        <path d="M0,20 20,20 20,8 0,8z M10,13 10,16z M4,8 a6,8 0 0,1 12,0"></path>
                    </svg>
                    <input type="password" placeholder="Password" class="input_password"id="loginpwd" name="password">
                </div>
                <div class="go_forget_password">
                    <a class="go_to_forget" href="javascript:void(0)">忘记密码?</a>
                </div>
                <div class="login_button_div">
                    <input class="login_button" onclick="userLogin();" type="button" value="Sign in"/>
                </div>
                <div class="register_info">
                    <span>还没有账号 ? <a class="flip_to_register" href="javascript:void(0)">注册</a></span>
                </div>
            </div>
                </form>
        </div>
        <!--右侧的第二个页面-->
        <div class="register_page">
            <div class="top_icon">
                <svg class="circle_icon">
                    <circle cx="50%" cy="55%" r="25%" stroke="white" stroke-width="20" fill="none"/>
                </svg>
            </div>
            <div class="get_vcode">
                <div class="nickname_div">
                    <span class="n_span">N</span>
                    <input class="input_nickname" type="text" name="Nickname" id="Nickname" placeholder="Nickname">
                </div>
                <div class="register_phone_div">
                    <!--svg就很溜-->
                    <svg class="register_phone_svg" viewBox="0 0 20 20">
                        <path d="M0,20 a10,8 0 0,1 20,0z M10,0 a4,4 0 0,1 0,8 a4,4 0 0,1 0,-8"></path>
                    </svg>
                    <input type="text" placeholder="Username" name="username" id="username" class="register_input_phone">
                </div>
                <div class="register_vcode_div">
                    <input type="text" placeholder="验证码" class="register_input_vcode">
                    <button class="get_vcode_button">获取验证码</button>
                </div>
                <button class="go_enter_password_button">下一步</button>
                <div class="go_back_login_div">
                    <a class="go_back_login" href="javascript:void(0)">返回</a>
                </div>
            </div>
            <!--<p>12345679</p>-->
            <!--<button class="next_step">next</button>-->
        </div>
        <!--第三个页面-->
        <div class="enter_password">
            <!--            <p>12345679</p>
                        <button class="second">next</button>-->
            <div class="check_mark">
                <div class="left_mark">
                </div>
                <div class="right_mark">
                </div>
            </div>
            <div class="confirm_password_content">
                <div class="confirm_password_div">
                    <svg class="first_enter_password_icon" viewBox="0 0 20 20">
                        <path d="M0,20 20,20 20,8 0,8z M10,13 10,16z M4,8 a6,8 0 0,1 12,0"></path>
                    </svg>
                    <input type="password" placeholder="Password" id="password" name="password" class="first_enter_password_input">
                </div>
                <div class="confirm_password_div">
                    <svg class="confirm_password_icon" viewBox="0 0 20 20">
                        <path d="M0,20 20,20 20,8 0,8z M10,13 10,16z M4,8 a6,8 0 0,1 12,0"></path>
                    </svg>
                    <input type="password" placeholder="Confirm" id="confirmpassword" name="confirmpassword"class="confirm_password_input">
                </div>
                <div class="login_button_div">
                    <button class="confirm_register_button" onclick="userRegister();">Register</button>
                </div>
                <div class="go_back_up_div">
                    <a class="go_back_up" href="javascript:void(0)">上一步</a>
                </div>
            </div>
        </div>
        <!--第4个页面-->
        <div class="forget_password">
            <div class="top_icon">
                <svg class="circle_icon">
                    <circle cx="50%" cy="55%" r="25%" stroke="white" stroke-width="20" fill="none"/>
                </svg>
            </div>
            <div class="forget_get_vcode">
                <div class="forget_phone_div">
                    <!--svg就很溜-->
                    <svg class="register_phone_svg" viewBox="0 0 20 20">
                        <path d="M0,20 a10,8 0 0,1 20,0z M10,0 a4,4 0 0,1 0,8 a4,4 0 0,1 0,-8"></path>
                    </svg>
                    <input type="text" placeholder="Username" class="forget_input_phone">
                </div>
                <div class="forget_get_vcode_div">
                    <input type="text" placeholder="验证码" class="forget_input_vcode">
                    <button class="forget_get_vcode_button">获取验证码</button>
                </div>
                <button class="forget_password_button">下一步</button>
                <div class="go_back_login_div">
                    <a class="go_back_login_from_forget" href="javascript:void(0)">返回</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    // 用户登录
    function userLogin() {
        $.ajax({
            url: "${ctx}/user/login",
            type: "POST",
            data: $("#formlogin").serialize(),
            dataType: "JSON",
            success: function (result) {
                var result = JSON.parse(result);
                if (200 == result.code) {
                    // 如果存在重定向url则重定向至该url
                    if ($("#redirectUrl").val()) {
                        window.location.href = $("#redirectUrl").val();
                    }else {
                        console.log("dsffe")
                        window.location.href = "${ctx}/index";
                    }

                } else {
                    layer.msg("用户名或密码错误，请重新输入！");
                }
            },
            error: function () {
                layer.alert("亲，系统正在升级中，请稍后再试！");
            }
        });
    }

    //用户注册
    function userRegister() {

        $.ajax({
            url: "${ctx}/user/register",
            type: "POST",
            data: {Nickname:$("#Nickname").val(),
                    username:$("#username").val(),
                    password:$("#password").val(),
                    confirmpassword:$("#confirmpassword").val()},
            dataType: "JSON",
            success: function (result) {
                var result = JSON.parse(result);
                if (200 == result.code) {
                    // 如果存在重定向url则重定向至该url
                    if ($("#redirectUrl").val()) {
                        window.location.href = $("#redirectUrl").val();
                    }else {
                        window.location.href = "${ctx}/login";
                    }

                } else {
                    layer.msg("用户名或密码错误，请重新输入！");
                }
            },
            error: function () {
                layer.alert("亲，系统正在升级中，请稍后再试！");
            }
        });
    }
</script>
</body>
</html>