
$(function(){
    getUser();//初始化获取用户账号信息
    var isEmailError=false;//判断邮箱格式是否正确，默认正确
    $("#email").blur(function(){//校验邮箱格式
        var reg=/^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
        if($(this).val()!="" && !reg.test($(this).val())){
            $(this).css("border-color","#f00");
            $("#emailTip").html("邮箱格式不正确");
            isEmailError=true;
        }
    });
    $("#email").focus(function(){
        $(this).css("border-color","");
        $(this).parent().siblings(".error_tip").html("");
        isEmailError=false;
    });
    $("#address").on("input",function(){
        var L=100-$(this).val().length;
        $(this).siblings("span").html("还能输入"+L+"字");
    });
    $("#updateUser").click(function(){//保存用户账号修改
        if(!isEmailError){//邮箱格式正确
            updateUser();
        }else{
            return;
        }
    });

    var imgCodeValid=false;
    $("#imgCode").on("input",function(){
        var L=$.trim($(this).val()).length;
        if(L==4){
            verifyImgCode();
        }
    });
    $("#imgCode").blur(function(){
        var L=$.trim($(this).val()).length;
        if($(this).val()==""){
            $('#imgTip').html("请输入图片验证码");
            $(this).css("border-color","#f00");
            imgCodeValid=false;
            return;
        }else if(L!=4){
            $('#imgTip').html("图片验证码错误");
            $(this).css("border-color","#f00");
            imgCodeValid=false;
            return;
        }else{
            verifyImgCode();
        }
    });
    $("#codeImg").click(function(){
        updateImgCode();
    });
    function verifyImgCode(){
        $.ajax({
            type: "get",
            url: "/user/webCheckRandomCode",
            data: {
                randomCode: $.trim($("#imgCode").val())
            },
            success: function(response) {
                if (response.code == 0) {
                    imgCodeValid=true;
                    $('#imgTip').html("");
                    $('#imgCode').css("border-color","");
                } else if (response.code == 104) {
                    $('#imgTip').html("图片验证码错误");
                    $('#imgCode').css("border-color","#f00");
                    imgCodeValid=false;
                    return;
                }
            }
        })
    }
    $("#getSmsCode").click(function(){//获取短信验证码
        if($("#imgCode").val()==""){
            $('#imgTip').html("请输入图片验证码");
            $("#imgCode").css("border-color","#f00");
            imgCodeValid=false;
            return;
        }else{
            verifyImgCode();
        }
        if(imgCodeValid){
            var config={
                mobile: $("#mobile").html(),
                templateCode: "MODIFY_PASSWORD",
                randomCode: $("#imgCode").val()
            };
            getSmsCode(config,"#getSmsCode");
        }else{
            return;
        }
    });

    $("#nextPsw").click(function(){//修改密码第一步
        if(imgCodeValid){
            if($("#smsCode").val()==""){
                $("#smsTip").html("请输入验证码");
                $("#smsCode").css("border-color","#f00");
                return;
            }else {
                checkSmsCode();
            }
        }else{
            return;
        }
    });

    $("#updatePsw").click(function(){//修改密码第二步
        var regSymbol=/^[^\w\s]+$/;
        var regNum=/^\d{6,20}$/;
        var regLetter=/^[A-Za-z]+$/;
        var pswStr=$("#psw").val();
        var confirmStr=$("#confirmPsw").val();
        if(pswStr==""){//校验密码
            $("#pswTip").html("新密码不能为空");
            $("#psw").css("border-color","#f00");
            return;
        }else if(pswStr.length<6 || pswStr.length>20){
            $("#pswTip").html("密码应为6-20位字符");
            $("#psw").css("border-color","#f00");
            return;
        } else if(regSymbol.test(pswStr)){
            $("#pswTip").html("密码不能全为符号");
            $("#psw").css("border-color","#f00");
            return;
        }else if(regNum.test(pswStr)){
            $("#pswTip").html("密码不能全为数字");
            $("#psw").css("border-color","#f00");
            return;
        }else if(regLetter.test(pswStr)){
            $("#pswTip").html("密码不能全为字母");
            $("#psw").css("border-color","#f00");
            return;
        }else if(confirmStr==""){
            $("#confirmTip").html("确认密码不能为空");
            $("#confirmPsw").css("border-color","#f00");
            return;
        }else if(confirmStr!=pswStr){
            $("#confirmTip").html("两次密码输入不一致");
            $("#confirmPsw").css("border-color","#f00");
            return;
        }else{
            resetPsw();//提交保存
        }
    });
    $(".form-control").focus(function(){//输入框获得焦点后错误提示消失
        $(this).siblings(".error_tip").html("");
        $(this).css("border-color","");
    });
    $("input[type=password]").on("input",function(){
        var str=$(this).val();
        if(str.indexOf(" ")>-1){
            str=$.trim(str);
            str = str.replace(/\s/g,"");
            $(this).val(str);
        }
    });
});
function getUser(){//获取用户账号信息
    $.ajax({
        type: "post",
        url: "/user/developerInfo?"+Math.random(),
        success: function (response) {
            if(response.code==0){
                var user=response.data;
                window.fontCloudUser=user;
                $(".mobile").html(user.mobile);
                $(".email").html(user.email==""?"<span style='color:#bababa;'>未设置</span>":user.email);
                $(".address").html(user.address==""?"<span style='color:#bababa;'>未设置</span>":user.address);
                $("#userId").val(user.developerId);
            }else{
                $('#myModal').modal('show');
            }
        }
    });
};
function updateUser(){//保存账号信息修改
    $.ajax({
        type: "post",
        url: "/user/updateDeveloper",
        data:{
            id: $("#userId").val(),
            mobile:$("#mobile").html(),
            email:$("#email").val(),
            address:$("#address").val()
        },
        success: function (response) {
            if(response.code==0){
                $("#toast-container").html("");
                toastr.success("保存成功！");
                getUser();
                setTimeout(showUserInfo,2000);
            }else{
                $("#toast-container").html("");
                toastr.error(response.msg);
            }
        },
        error:function(){
            $("#toast-container").html("");
            toastr.error("错误，请稍后再试！");
        }
    });
}
var timer=null;//定时器
function setTimer(trigger) {//获取短信验证码倒计时
    var sec=60;
    timer = setInterval(function() {
        if (sec < 1) {
            clearInterval(timer);
            $(trigger).val("获取验证码");
            sec = 60;
            $(trigger).prop("disabled", false);
            return;
        }
        $(trigger).val(sec + "s后重新获取");
        sec--;
        $(trigger).prop("disabled", true);
    }, 1000);
};
function clearTimer(trigger){//清除定时器
    clearInterval(timer);
    $(trigger).val("获取验证码");
    $(trigger).prop("disabled", false);
}
function getSmsCode(configObj,trigger){//获取短信验证码
    $.ajax({
        type: "post",
        url: "/user/sendSmsCode",
        data: configObj,
        success: function (response) {
            if (response.code == 0) {
                setTimer(trigger);
            } else if(response.code != 104){
                $("#toast-container").html("");
                toastr.warning(response.msg);
            }
        },
        error: function () {
            $("#toast-container").html("");
            toastr.error("获取验证码失败");
        }
    })

}
function checkSmsCode(){//修改密码第一步 下一步操作（校验短信验证码是否正确）
    $.ajax({
        type: "post",
        url: "/user/updatePassword",
        data:{
            step:1,
            randomCode:$("#imgCode").val(),
            mobile:$("#mobile").html(),
            smsCode:$("#smsCode").val()
        },
        success: function (response) {
            if(response.code==0){
                clearTimer("#getSmsCode");
                $("#step2 :input").each(function () {
                    $(this).val("");
                });
                $("#step2").show();
                $("#step1").hide();
            }if(response.code==104){
                $("#imgTip").html(response.msg);
                $("#imgCode").css("border-color","#f00");
            }else{
                $("#smsTip").html(response.msg);
                $("#smsCode").css("border-color","#f00");
            }
        }
    });
}
function resetPsw(){//修改密码第二步 保存操作
    $.ajax({
        type: "post",
        url: "/user/updatePassword",
        data:{
            step:2,
            mobile:$("#mobile").html(),
            password:$("#psw").val(),
            confirmPassword:$("#confirmPsw").val()
        },
        success: function (response) {
            if(response.code==0){
                $("#toast-container").html("");
                toastr.success("保存成功！");
                logOut();
            }else{
                $("#toast-container").html("");
                toastr.error("保存失败！");
            }
        },
        error:function(){
            $("#toast-container").html("");
            toastr.error("错误，请稍后再试！");
        }
    });
}
function logOut(){//退出登录
    $.ajax({
        type: "post",
        url: "/user/logout",
        success: function (response) {
            if(response.code==0){
                window.location.href="/pro/index";
            }
        },
        error:function(){
            $("#toast-container").html("");
            toastr.error("错误，请稍后再试！");
        }
    });
}
function editUserInfo(){//编辑用户信息
    $("#accountDis").hide();
    $("#infoUpdate").show();
    $("#updateLink").hide();
    userEditInit();
}
function userEditInit(){//初始化 用户编辑界面
    $("#email").val($(".email").text()=="未设置"?"":$(".email").html());
    $("#address").val($(".address").text()=="未设置"?"":$(".address").html());
}
function showUserInfo(){//显示用户信息
    $("#infoUpdate :input").each(function () {
        $(this).val("");
    });
    $("#infoUpdate textarea").val("");
    $("#textareaTip").html("联系地址不能超过100个字");
    $("#accountDis").show();
    $("#infoUpdate").hide();
    $("#updateLink").show();
}
function toResetPsw(){//点击修改密码按钮事件
    $("#resetTip").hide();
    $("#pswStep").show();
    $("#step1 :input[type='text']").each(function () {
        $(this).val("");
    });
    clearTimer("#getSmsCode");
    updateImgCode();
}
function cancelReset(){//取消修改密码
    clearTimer("#getSmsCode");//清除定时器
    $("#step1 :input[type='text']").each(function () {
        $(this).val("");
        $(this).css("border-color","");
        $(".error_tip").html("");
    });
    $("#step2 :input").each(function () {
        $(this).val("");
        $(this).css("border-color","");
        $(".error_tip").html("");
    });
    $("#step2").hide();
    $("#step1").show();
    $("#pswStep").hide();
    $("#resetTip").show();
}
function updateImgCode() {
    $("#codeImg").attr("src", "/user/getRandomCode?" + Math.random());//获取图片验证码
}
