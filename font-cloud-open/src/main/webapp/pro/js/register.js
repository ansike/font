/**
 * Created by Administrator on 2017/2/10 0010.
 */
//------------------------------------注册
$(".registerBtn").on("click", function() {
    //勾选注册协议
    if ($("#agreement").prop("checked")) {
        $(".agreement_error").hide();

        var data = {};
        data.mobile = $(".register_content").find(".phone").val().trim();
        data.randomCode = $(".register_content").find(".pic_verification").val().trim();
        data.smsCode = $(".register_content").find(".msg_verification").val().trim();
        data.password = $(".register_content").find(".pwd").val().trim();
        data.confirmPassword = $(".register_content").find(".rePwd").val().trim();

        checkePhone(data.mobile, $(".register_content").find(".phone"));
        checkPicCode(data.randomCode, $(".register_content").find(".pic_verification"));
        msg_notnull(data.smsCode, $(".register_content").find(".msg_verification"))
        checkPwd(data.password, $(".register_content").find(".pwd"));
        checkRePwd(data.confirmPassword, $(".register_content").find(".rePwd"));

        var n=getArrNum();
        if (n == 5) {
            register(data);
        } else {
            console.log("信息填写不完善");
            return;
        }
    } else {
        $(".agreement_error").show();
    }

});

function register(data) {
    $.ajax({
        type: "post",
        url: "/user/register?random="+Math.random(),
        data: data,
        success: function(res) {
            if (res.code == 0) {
                var index=3;
                $("#toast-container").html("");
                var ttt=toastr.success("注册成功！"+index+"秒后进行登录。","",{timeOut: 5000});
                var t=setInterval(function () {
                    if(index<=0){
                        clearInterval(t);
                        data.tag = "password";
                        toastr.clear(ttt);
                        login(data);
                        setTimeout(function () {
                            window.location.href="/font/fontCenter/fontcenter";
                        },500)
                    }
                    ttt.setText("注册成功！"+index+"秒后进行登录。");
                    index--;
                },1000);

            } else if (res.code == 407) {
                $("#toast-container").html("");
                toastr.warning("发送验证码次数过多，请稍后再试");
            }else{
                $("#toast-container").html("");
                toastr.error(res.msg);
            }
        }
    })
}
$(".register_content .agreementBox").on("click",function () {
    if($("#agreement").prop("checked")){
        $(".register_content .agreement_error").hide();
    }else{
        $(".register_content .agreement_error").show();
    }
})
$(".relogin").on("click",function () {
    $('#myModal').modal('show');
})
