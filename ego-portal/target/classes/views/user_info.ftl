<!-- 设置项目根路径全局变量 -->
<#assign ctx=request.contextPath/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人信息</title>
    <link rel="stylesheet" href="${ctx}/static/css/jquery-ui.css">
    <link rel="icon" href="${ctx}/static/img/page_icon.png">
    <link rel="stylesheet" href="${ctx}/static/css/home_page/header_and_nav.css">
    <link rel="stylesheet" href="${ctx}/static/css/personal/personal_info.css">
    <script src="${ctx}/static/js/jquery-3.4.1.js"></script>
    <script src="${ctx}/static/js/jquery-ui.js"></script>
    <script src="${ctx}/static/js/home/header_model_js.js"></script>
    <script>

        $( function() {
            $( "#accordion" ).accordion();
            $('.update_button').click(function () {
                var type = $(this).siblings(".first_info").children("input").attr("type");
                var className = $(this).siblings(".first_info").children("input").attr("class");
                if (type=="radio") {
//
                }else {
                    var val = $(this).siblings(".first_info").children("input").val();
                    if (val==undefined||val=='') {
                        $(this).siblings(".first_info").children(".reqiure_enter").show(0);
                    }else{
//
                    }
                }
            });
//            实时监听输入框的输入变化，当有输入值的时候，隐藏必须填写字段
            $('.first_info input').bind("input propertychange change",function () {
                var val = $(this).val();
                if (val!=undefined&&val!='') {
                    $(this).siblings(".reqiure_enter").hide(0);
                }
            });
        } );
    </script>
</head>
<body>
<#include "head.ftl">
<div class="top_content">
    <div class="publish_product_div">
        <div id="accordion">
            <h3>用户名：<span class="username_span">
                    <#if user.username??>
                        ${user.username}
                    </#if>
                </span>
            </h3>
            <div class="info_content">
                <div class="first_info">
                    <span>用户名：</span><input type="text" class="username" id="userName">
                    <span class="reqiure_enter"> 必须填写此字段</span>
                </div>
                <button class="update_button" onclick="updateName(${user.id})">确认修改</button>
            </div>
            <h3>真实姓名：<span class="realname_span">
                     <#if user.realname??>
                         ${user.realname}
                     </#if>
                </span></h3>
            <div class="info_content">
                <div class="first_info">
                    <span>真实姓名：</span><input type="text" class="realName" id="realName">
                    <span class="reqiure_enter"> 必须填写此字段</span>
                </div>
                <button class="update_button" onclick="updateRealName(${user.id})">确认修改</button>
            </div>
            <h3>性别：<span class="sex_span">
                    <#if user.gender??>
                        ${user.gender}
                    </#if>
                </span></h3>
            <div class="info_content">
                <div class="first_info sex_info">
                    <span>性别：</span>
                    <#if (user.gender)??>
                        <#if user.gender=="男">
                            <input class="gender" type="radio" name="gender" value="1" checked>男
                            <input class="gender" type="radio" name="gender" value="2">女
                            <#else >
                                <input class="gender" type="radio" name="gender" value="1" >男
                                <input class="gender" type="radio" name="gender" value="2" checked>女
                        </#if>
                        <#else >
                            <input class="gender" type="radio" name="gender" value="1" checked>男
                            <input class="gender" type="radio" name="gender" value="2">女
                    </#if>

                </div>
                <button class="update_button" onclick="updateGender(${user.id})">确认修改</button>
            </div>
            <h3>学号：<span class="sno_span">
                    <#if user.sno??>
                         ${user.sno}
                     </#if>
                </span></h3>
            <div class="info_content">
                <div class="first_info">
                    <span>学号：</span><input type="text" class="sno" id="sno">
                    <span class="reqiure_enter"> 必须填写此字段</span>
                </div>
                <button class="update_button" onclick="updateSno(${user.id})">确认修改</button>
            </div>
            <h3>宿舍号：<span class="dormitory_span">
                    <#if user.dormitory??>
                        ${user.dormitory}
                    </#if>
                </span></h3>
            <div class="info_content">
                <div class="first_info">
                    <span>宿舍号：</span><input type="text" class="dormitory" id="dormitory">
                    <span class="reqiure_enter"> 必须填写此字段</span>
                </div>
                <button class="update_button" onclick="updateDormitory(${user.id})" >确认修改</button>
            </div>
            <h3>电话：<span class="email_span">
                    <#if user.phone??>
                        ${user.phone}
                    </#if>
                </span></h3>
            <div class="info_content">
                <div class="first_info">
                    <span>电话：</span><input type="text" class="email" id="phone">
                    <span class="reqiure_enter"> 必须填写此字段</span>
                </div>
                <button class="update_button" onclick="updatePhone(${user.id})">确认修改</button>
            </div>
        </div>
    </div>
    <footer>
        <p>Copyright © 2014.Company name All rights reserved.</p>
    </footer>
</div>

<script type="text/javascript">
    function updateName(id) {
        var userName=$("#userName").val();
        if(userName=="" && userName.length<1){
            layer.alert("请输入用户名");
            return ;
        }
        $.ajax({
            type:"post",
            url:"${ctx}/user/userName?userName="+userName+"&id="+id,
            success:function (result) {
                if(result.code==200){
                    console.log("修改成功")
                    location.reload([true])
                }else {
                    layer.alert("修改失败！！")
                }
            }
        })
    }

    function updateRealName(id) {
        var realName=$("#realName").val();
        if(realName=="" && realName.length<1){
            layer.alert("请输入真实名字");
            return ;
        }
        $.ajax({
            type:"post",
            url:"${ctx}/user/realName?realName="+realName+"&id="+id,
            success:function (result) {
                if(result.code==200){
                    console.log("修改成功")
                    location.reload([true])
                }else {
                    layer.alert("修改失败！！")
                }
            }
        })
    }

    function updateGender(id) {
        var gender=$('input[type=radio]:checked').val();
        $.ajax({
            type:"post",
            url:"${ctx}/user/gender?gender="+gender+"&id="+id,
            success:function (result) {
                if(result.code==200){
                    console.log("修改成功")
                    location.reload([true])
                }else {
                    layer.alert("修改失败！！")
                }
            }
        })
    }

    function updateSno(id) {
        var sno=$("#sno").val();
        if(sno=="" && sno.length<1){
            layer.alert("请输入学号");
            return ;
        }
        $.ajax({
            type:"post",
            url:"${ctx}/user/sno?sno="+sno+"&id="+id,
            success:function (result) {
                if(result.code==200){
                    console.log("修改成功")
                    location.reload([true])
                }else {
                    layer.alert("修改失败！！")
                }
            }
        })
    }

    function updateDormitory(id) {
        var dormitory=$("#dormitory").val();
        if(dormitory=="" && dormitory.length<1){
            layer.alert("请输入宿舍号");
            return ;
        }
        $.ajax({
            type:"post",
            url:"${ctx}/user/dormitory?dormitory="+dormitory+"&id="+id,
            success:function (result) {
                if(result.code==200){
                    console.log("修改成功")
                    location.reload([true])
                }else {
                    layer.alert("修改失败！！")
                }
            }
        })
    }
    function updatePhone(id) {
        var phone=$("#phone").val();
        if(phone=="" && phone.length<1){
            layer.alert("请输入电话号码");
            return ;
        }
        $.ajax({
            type:"post",
            url:"${ctx}/user/phone?phone="+phone+"&id="+id,
            success:function (result) {
                if(result.code==200){
                    console.log("修改成功")
                    location.reload([true])
                }else {
                    layer.alert("修改失败！！")
                }
            }
        })

    }
</script>
</body>
</html>