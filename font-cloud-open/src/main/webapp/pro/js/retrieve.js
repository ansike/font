/**
 * Created by Administrator on 2017/2/18 0018.
 */
function retrieve(data) {
    $.ajax({
        type: "post",
        url: "/user/forgetPassword",
        data: data,
        success: function(res) {
            console.log(res);
            if(res.code==0){
                if (data.step == 1) {
                    $(".step").eq(1).addClass("active").siblings().removeClass("active");
                    $(".check_id").hide();
                    $(".set_pwd").show();
                    $(".nextBtn").hide();
                    $(".retrieve_complete").show();
                } else if (data.step == 2) {
                    $("#toast-container").html("");
                    toastr.success("找回密码成功！");
                    setTimeout(function () {
                        window.location.href="/pro/index?retrieved";
                    },1000);

                }
            }else{
                $("#toast-container").html("");
                toastr.error(res.msg);
            }

        }
    })


}
$(".nextBtn").on("click", function() {
    var data = {};
    data.step = 1;
    data.mobile = $(".check_id").find(".phone").val().trim();
    data.randomCode = $(".check_id ").find(".pic_verification").val().trim();
    data.smsCode = $(".check_id ").find(".msg_verification").val().trim();
    data.templateCode = "FORGOT_PASSWORD";
    checkRegistered(data.mobile, $(".check_id ").find(".phone"));
    checkPicCode(data.randomCode, $(".check_id ").find(".pic_verification"));
    msg_notnull(data.smsCode, $(".check_id ").find(".msg_verification"))

    if(checkArr.phone==0){
        return;
    }
    if(checkArr.picCode==0){
        return;
    }
    if(checkArr.msgCode==0){
        return;
    }
    retrieve(data)
});
$(".retrieve_complete").on("click", function() {
    var data = {};
    data.step = 2;
    data.mobile = $(".check_id").find(".phone").val().trim();
    data.randomCode = $(".check_id").find(".pic_verification").val().trim();
    data.smsCode = $(".check_id").find(".msg_verification").val().trim();
    data.password = $(".set_pwd").find(".pwd").val().trim();
    data.confirmPassowrd = $(".set_pwd").find(".rePwd").val().trim();
    data.templateCode = "FORGOT_PASSWORD";

    checkePhone(data.mobile, $(".set_pwd").find(".phone"));
    checkPicCode(data.randomCode, $(".set_pwd").find(".pic_verification"));
    msg_notnull(data.smsCode, $(".set_pwd").find(".msg_verification"))
    checkPwd(data.password, $(".set_pwd").find(".pwd"));
    checkRePwd(data.confirmPassowrd, $(".set_pwd").find(".rePwd"));
    var n = getArrNum();
    if (n == 5) {
        retrieve(data)
    } else {
        console.log("信息填写不完善");
        return;
    }

});
