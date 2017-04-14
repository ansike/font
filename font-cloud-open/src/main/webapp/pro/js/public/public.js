/**
 * Created by Administrator on 2017/2/8 0008.
 *  // toastr.success("注册成功！"); // toastr.info("注册成功！"); //
 * toastr.error("注册成功！"); // toastr.warning("注册成功！");
 */
var sec = 60; // 获取验证码秒数
var checkArr = {
    phone: 0,
    picCode: 0,
    msgCode: 0,
    pwd: 0,
    rePwd: 0
};
var FONT_VERSION = "";// Math.random();
function goPage(url, checkLogin) {
    if (checkLogin) {
        if (isLogined) {
            window.location.href = url;
        } else {
            if (cookie("account")) {
                $(".account_pwd .phone").val(cookie("account")[0][1]);
                var pwd = cookie("account")[1][1];
                $(".account_pwd .pwd").val(pwd);
            }
            $('#myModal').modal({show: true});
            $('#myModal').attr("data-url", url);
        }
    } else {
        window.location.href = url;
    }
}
//--------------------------------------------新手指引----------------------------
$(".step_box .step_child").on("click", function () {
    var index = $(this).index();
    switch (index) {
        case 0:
            if (isLogined) {
                window.location.href = "/font/fontCenter/fontcenter";
                break;

            } else {
                window.location.href = "/pro/template/register/index";
                break;
            }
        case 1:
            goPage("/pro/template/manageCenter/application", true)
            break;
        case 2:
            window.location.href = "/pro/template/resourceCenter/resource?id=item3";
            break;
        default:
            break;

    }
})
// 计时器
var timer = null;
// toast基本配置
toastr.options = {
    "closeButton": false, // 是否显示关闭按钮
    "debug": false, // 是否使用debug模式
    "positionClass": "toast-center-half-width", // 弹出窗的位置
    "showDuration": "10", // 显示的动画时间
    "hideDuration": "10", // 消失的动画时间
    "timeOut": "1000", // 展现时间
    "extendedTimeOut": "100", // 加长展示时间
    "showEasing": "swing", // 显示时的动画缓冲方式
    "hideEasing": "linear", // 消失时的动画缓冲方式
    "showMethod": "fadeIn", // 显示时的动画方式
    "hideMethod": "fadeOut" // 消失时的动画方式
};

// -------------------------------------------------查看密码
function switchPsw(obj) {
    if ($(obj).siblings("input[name=password]").attr("type") == "password") {
        $(obj).siblings("input[name=password]").attr("type", "text");
        $(obj).css("background-image", "url(/pro/img/eye.png)");
    } else {
        $(obj).siblings("input[name=password]").attr("type", "password");
        $(obj).css("background-image", "url(/pro/img/eye2.png)");
    }
}
/*
 * $(".eye").on("click", function () { if
 * ($(this).siblings("input[name=password]").attr("type") == "password") {
 * $(this).siblings("input[name=password]").attr("type", "text");
 * $(this).css("background-image", "url(/pro/img/eye.png)"); } else {
 * $(this).siblings("input[name=password]").attr("type", "password");
 * $(this).css("background-image", "url(/pro/img/eye2.png)"); } });
 */
// -------------------------------------------------改变图片公共方法
function changeImgCode($this) {
    $this.attr("src", "/user/getRandomCode?" + Math.random());
}
// -------------------------------------------------图片验证码
$(".picCode").on("click", function () {
    var $this = $(this);
    $this.prev(".register_li").find(".pic_verification").val("");
    $this.siblings(".success").hide();
    changeImgCode($this);
})
// ---------------------------------------------输入框的聚焦事件
$(".registerIpt ").on(
    "focus",
    function () {
        $(this).parent(".register_li").addClass("border_focus")
            .removeClass("border_error");
        $(this).siblings(".error").hide();
    })


$(".registerIpt ").on("blur", function () {
    $(this).parent(".register_li").removeClass("border_focus");
});
// 手机号正则
var reg = /^(13[0-9]|14[579]|15[0-9]|17[0-9]|18[0-9])[0-9]{8}$/;
// -----------------------------------------------1检查手机号
function checkePhone(text, $this) {
    if (text == "") {
        $this.siblings(".phone_number_empty").show();
        $this.parent(".register_li").children(".success").hide();
        $this.parent(".register_li").addClass("border_error");
        checkArr.phone == 0
    } else {
        if (reg.test(text)) {
            $this.parent(".register_li").removeClass("border_error");
            $this.parent(".register_li").children(".error").hide();
            checkArr.phone = 1;
        } else {
            $this.parent(".register_li").addClass("border_error");
            $this.siblings(".phone_number_error").show();
            $this.parent().children(".success").hide();
            checkArr.phone == 0
        }
    }
}
function checkRegistered(text, $this) {
    if (text == "") {
        $this.siblings(".phone_number_empty").show();
        $this.parent(".register_li").children(".success").hide();
        $this.parent(".register_li").addClass("border_error");
        checkArr.phone == 0;
    } else {
        $.ajax({
            type: "get",
            url: "/user/webCheck?random=" + Math.random(),
            data: {
                type: "MOBILE",
                mobile: text
            },
            success: function (res) {
                if ($this.attr("data-type") == "register") {
                    if (res.code == 0) {
                        $this.parent(".register_li").addClass("border_error");
                        $this.parent(".register_li").children(".success")
                            .hide();
                        $this.siblings(".phone_number_registered").show();
                        checkArr.phone = 0
                    } else {
                        $this.parent(".register_li")
                            .removeClass("border_error");
                        $this.siblings(".phone_number_registered").hide();
                        $this.parent(".register_li").children(".success")
                            .show();
                        checkePhone(text, $this);
                    }
                } else {
                    if (res.code == 0) {
                        $this.parent(".register_li")
                            .removeClass("border_error");
                        $this.siblings(".phone_number_registered").hide();
                        $this.parent(".register_li").children(".success")
                            .show();
                        checkePhone(text, $this);
                    } else if (res.code == 108) {
                        $this.parent(".register_li").addClass("border_error");
                        $this.siblings(".phone_number_error").show();
                        $this.parent().children(".success").hide();
                        checkArr.phone == 0
                    } else {
                        $this.parent(".register_li").addClass("border_error");
                        $this.parent(".register_li").children(".success")
                            .hide();
                        $this.siblings(".phone_number_notregistered").show();
                        checkArr.phone = 0
                    }
                }

            }
        })
    }

}
// -----------------------------------------------2检查图片验证码
function checkPicCode(code, $this) {

    if (code == "") {
        $this.siblings(".pic_code_empty").show();
        $this.parents("li").children(".success").hide();
        $this.parent(".register_li").addClass("border_error");
        checkArr.picCode = 0;
    } else {
        $.ajax({
            type: "get",
            url: "/user/webCheckRandomCode?random=" + Math.random(),
            data: {
                randomCode: code
            },
            success: function (data) {
                if (data.code == 0) {
                    // code==0验证通过
                    $this.parent(".register_li").removeClass("border_error");
                    if (sec == 60) {
                        $(".getMsgCode").attr("disabled", false);
                    }
                    $this.siblings(".pic_code_error").hide();
                    $this.parents("li").children(".success").show();
                    checkArr.picCode = 1;
                } else if (data.code == 104) {
                    // 验证码错误
                    checkArr.picCode = 0;
                    $this.parent(".register_li").addClass("border_error");
                    $this.siblings(".pic_code_error").show();
                    $(".getMsgCode").attr("disabled", true);
                    $this.parents("li").children(".success").hide();
                } else {
                    $("#toast-container").html("");
                    toastr.error(data.msg);
                }
            },
            error: function () {
                $("#toast-container").html("");
                toastr.error("网络错误！");
            }
        })
    }

}

// ------------------------------------------------3.1获取短信验证码
function getMsgCode(data, $this) {
    $.ajax({
        type: "post",
        url: "/user/sendSmsCode?random=" + Math.random(),
        data: data,
        success: function (data) {
            if (data.code == 0) {
                checkArr.msgCode = 1;
                Countdown($this);
            } else {
                $("#toast-container").html("");
                toastr.warning(data.msg);
                if ((location.href).substr((location.href).length - 34) == "/pro/template/retrievepwd/retrieve") {
                    setTimeout(function () {
                        window.location.reload();
                    }, 2000)
                }
            }
        },
        error: function () {
            toastr.error("网络异常！");
            window.location.reload();
        }
    })
}

// ------------------------------------------------3.2短信验证码非空
function msg_notnull(code, $this) {
    if (code == "") {
        $this.siblings(".msg_code_empty").show();
        $this.parent(".register_li").addClass("border_error");
        checkArr.msgCode = 0;
    } else {
        $this.siblings(".msg_code_empty").hide();
        $this.parent(".register_li").removeClass("border_error");
        checkArr.msgCode = 1;
    }
}

// ------------------------------------------------4检查密码
function checkPwd(code, $this) {
    if (code.length == 0) {
        $this.siblings(".pwd_empty").show();
        $this.parent(".register_li").addClass("border_error");
        $this.parent().children(".success").hide();
    } else if (!$this.parents().hasClass("login-content")) {
        $this.siblings(".pwd_empty").hide();
        if (code.length < 6 || code.length > 20) {
            $this.siblings(".pwd_length").show();
            $this.parent(".register_li").addClass("border_error");
            checkArr.pwd = 0;
            $this.parent().children(".success").hide();
            return;
        } else {
            $this.siblings(".pwd_length").hide();
            if (t1.test(code)) {
                $this.siblings(".pwd_allnumber").show();
                $this.parent(".register_li").addClass("border_error");
                checkArr.pwd = 0;
                $this.parent().children(".success").hide();
                return;
            }
            if (t2.test(code)) {
                $this.siblings(".pwd_allcharacter").show();
                $this.parent(".register_li").addClass("border_error");
                checkArr.pwd = 0;
                $this.parent().children(".success").hide();
                return;
            }
            if (t3.test(code)) {
                $this.siblings(".pwd_allsymbol").show();
                $this.parent(".register_li").addClass("border_error");
                checkArr.pwd = 0;
                $this.parent().children(".success").hide();
                return;
            }
            if (t4.test(code)) {
                $this.siblings(".pwd_nbsp").show();
                $this.parent(".register_li").addClass("border_error");
                checkArr.pwd = 0;
                $this.parent().children(".success").hide();
                return;
            }
            $this.parent(".register_li").removeClass("border_error");
            checkArr.pwd = 1;
            $this.parent().children(".success").show();
        }
    }
}

// -------------------------------------------------5检查重复密码
function checkRePwd(code, $this) {
    var pwd = $this.parents(".form_body").find(".pwd").val().trim();
    if (code == "") {
        $(".repwd_empty").show();
        $this.parent(".register_li").addClass("border_error");
        $this.parent().children(".success").hide();
        checkArr.rePwd = 0;
    } else {
        $(".repwd_empty").hide();
        if (code == pwd) {
            $(".repwd_error").hide();
            $this.parent(".register_li").removeClass("border_error");
            checkArr.rePwd = 1;
            $this.parent().children(".success").show();
            $(".registerBtn").attr("disabled", false);
        } else {
            $(".repwd_error").show();
            $this.parent(".register_li").addClass("border_error");
            checkArr.rePwd = 0;
            $this.parent().children(".success").hide();
        }
    }
}

// --------------------------------------------------60s处理
function Countdown($this) {
    $this.attr("disabled", true);
    sec = 60;
    clearInterval(timer);
    $(".getMsgCode").val("获取验证码");
    timer = setInterval(function () {
        if (sec < 1) {
            clearInterval(timer);
            $this.val("获取验证码");
            $this.attr("disabled", false);
            sec = 60;
            return;
        }
        $this.val(sec + "s后重新获取");
        sec--;
    }, 1000);
}

// --------------------------------------------------
function getArrNum() {
    var n = 0;
    $.each(checkArr, function (i, obj) {
        n += obj;
    })
    return n;
}

/*******************************************************************************
 * --------------------------------------------------登录接口 ---密码登录 data.tag =
 * "password"; data.mobile data.randomCode data.password ---短信验证码登录 data.tag =
 * "mobile"; data.mobile data.randomCode data.smsCode
 ******************************************************************************/
function login(data) {
    $.ajax({
        type: "post",
        url: "/user/login?random=" + Math.random(),
        data: data,
        success: function (res) {
            if (res.code == 0) {
                $(".accountError").hide();
                $("#toast-container").html("");
                toastr.success(res.msg);
                if ($("#remeber_account").prop("checked")) {
                    var phone = $(".account_pwd .phone").val().trim();
                    var pwd = $(".account_pwd .pwd").val().trim();
                    // pwd = $.base64.encode(pwd);
                    var msg = "phone#" + phone + "|pwd#" + pwd;
                    cookie("account", msg, 7);
                }
                setTimeout(function () {
                    if (active == "/pro/template/register/index") {
                        window.location.href = "/font/fontCenter/fontcenter";
                    } else if (active == "/pro/template/retrievepwd/retrieve") {
                        window.location.href = "/pro/index";
                    }
                    else {
                        if ($('#myModal')[0].dataset.url != null) {
                            window.location.href = $('#myModal')[0].dataset.url;
                        } else {
                            window.location.reload();
                        }
                    }
                }, 1000)
            } else if (res.code == 204) {
                $(".accountError").show();
            }
            else {
                $("#toast-container").html("");
                toastr.error(res.msg);
            }

        },
        error: function (res) {
            toastr.error(res);
        }
    })
}
$(".phone").on("keyup", function () {
    if ($(this).val() != "") {
        $(this).siblings(".phone_clear").show();
    } else {
        $(this).siblings(".phone_clear").hide();
    }
})
$(".phone").on("blur", function () {
    var text = $(this).val().trim();
    var $this = $(this);
    checkRegistered(text, $this);
});
// -------------------------------图片验证码

$(".pic_verification").on("blur", function () {
    var code = $(this).val().trim();
    var $this = $(this);
    if (code == "") {
        checkArr.picCode = 0;
        $this.siblings(".pic_code_empty").show();
        $this.parent(".register_li").addClass("border_error");
    } else {
        checkPicCode(code, $this);
    }
});
$(".pic_verification").on("keyup", function () {
    var code = $(this).val().trim();
    var $this = $(this);
    if (code.length == 4) {
        checkPicCode(code, $this);
    }
});
// ---------------------------短信验证码
$(".getMsgCode").on(
    "click",
    function () {
        var $this = $(this);
        var data = {};
        data.randomCode = $(this).parents(".form_body").find(
            ".pic_verification").val().trim();
        data.mobile = $(this).parents(".form_body").find(".phone").val()
            .trim();
        data.templateCode = $(this).attr("data-type");
        checkRegistered(data.mobile, $(this).parents(".form_body").find(
            ".phone"));
        if (checkArr.phone == 0) {
            return;
        }
        getMsgCode(data, $this);
    });

$(".msg_verification").on("blur", function () {
    var code = $(this).val().trim();
    var $this = $(this);
    msg_notnull(code, $this);
});

// -------------------------密码
var t1 = /^\d+$/; // 只能是数字
var t2 = /^[a-z]+$/i; // 只能是英文字母
var t3 = /^[?!.*?[\~\`\·\！\!@\#\￥\$%\……\^&;\*\(\)\（\）\_\-\——\+\=\【\】\[\]\{\}\|\、<>\\\</>：\:\;\；\"\”\“\’\'\'\&lt;\&gt;\《\》\,\，\。\.\?\？\/]+$/; //只能是特殊字符
var t4 = /[ ]/g;// 中间有空格
$(".pwd").on("blur", function () {
    var code = $(this).val().trim();
    var $this = $(this);
    checkPwd(code, $this);
});

$(".rePwd").on("blur", function () {
    var code = $(this).val().trim();
    var $this = $(this);
    checkRePwd(code, $this);
})
// -----------------------------------登陆tab切换
$(".login_tab").on("click", function () {
    $(this).children().addClass("active");
    $(this).siblings().children().removeClass("active");
    changeImgCode($(this).find(".picCode"));
    if ($(this).children().attr("class").indexOf("msg_login") != -1) {
        $(".account_pwd").hide();
        $(".account_msg").show();
        $(".login-content .agreementBox").css("visibility", "hidden");
        $(".loginBtn").attr("data", "account_msg");
    } else {
        $(".account_msg").hide();
        $(".account_pwd").show();
        $(".login-content .agreementBox").css("visibility", "visible");
        $(".loginBtn").attr("data", "account_pwd");
    }
})
// 清除验证码
$(".login").on("click", function () {
    $(".verification").val("");
    changeImgCode($(this).find(".picCode"));
})
// ----------------------------记住用户名和密码
$(".loginBtn").on('click', function () {
    check_login();
});
$("#myModal").on("keydown", function (event) {
    if (event.keyCode == 13) {
        check_login();
    }
})
function check_login() {
    // 用户名密码登录
    var data = {};
    if ($("#remeber_account").prop("checked")) {
        data.rememberMe = true;
    }
    if ($(".loginBtn").attr("data") == "account_pwd") {
        data.tag = "password";
        data.mobile = $(".account_pwd").find(".phone").val().trim();
        data.randomCode = $(".account_pwd").find(".pic_verification").val()
            .trim();
        data.password = $(".account_pwd").find(".pwd").val().trim();

        checkePhone(data.mobile, $(".account_pwd ").find(".phone"));
        checkPicCode(data.randomCode, $(".account_pwd").find(
            ".pic_verification"));
        checkPwd(data.password, $(".account_pwd").find(".pwd"));
        if (checkArr.phone == 0) {
            return;
        }
        if (checkArr.picCode == 0) {
            return;
        }

        login(data);
        // 手机号短信登录
    } else {
        data.tag = "mobile";
        data.mobile = $(".account_msg").find(".phone").val().trim();
        data.randomCode = $(".account_msg").find(".pic_verification").val()
            .trim();
        data.smsCode = $(".account_msg").find(".msg_verification").val().trim();

        checkePhone(data.mobile, $(".account_msg").find(".phone"));
        checkPicCode(data.randomCode, $(".account_msg").find(
            ".pic_verification"));
        msg_notnull(data.smsCode, $(".account_msg").find(".msg_verification"));

        if (checkArr.phone == 0) {
            return;
        }
        if (checkArr.picCode == 0) {
            return;
        }
        if (checkArr.msgCode == 0) {
            return;
        }

        login(data);
    }
}
if (cookie("account")) {
    $(".account_pwd .phone").val(cookie("account")[0][1]);
    var pwd = cookie("account")[1][1];
    $(".account_pwd .pwd").val(pwd);
}
$(".phone_clear").on("click", function () {
    $(this).siblings(".phone")[0].value = "";
})
// ---------------------------------注销
$("#logout").on("click", function () {
    var url = location.href;
    $.ajax({
        type: "get",
        url: "/user/logout?random=" + Math.random(),
        success: function (res) {
            if (res.code == 0) {
                if (url.indexOf('manageCenter') > -1) {
                    window.location.href = "/pro/index?random=" + Math.random();
                } else if (url.indexOf('accountCenter') > -1) {
                    window.location.href = "/pro/index?random=" + Math.random();
                } else {
                    if (url.indexOf("?") > -1) {
                        window.location.href = url + "&random=" + Math.random();
                    } else {
                        window.location.href = url + "?random=" + Math.random();
                    }
                }
            } else {
                $("#toast-container").html("");
                toastr.error(res.msg);
            }
        }
    })
});
// -----------------------------------------模态框关闭的回掉
$('#myModal').on(
    'hidden.bs.modal',
    function () {
        changeImgCode($(".picCode"));
        if (active == "/pro/template/manageCenter/application"
            || active == "/pro/template/accountCenter/account") {
            if (!isLogined) {
                window.history.back();
            }
        }
        if (active == "/pro/template/register/index") {
            window.location.reload();
        }
        var urs = (location.href).substr((location.href).length - 20);
        if (urs == "/pro/index?retrieved") {
            window.location.href = "/pro/index";
            return;
        }
        for (var i = 0; i < $(".registerIpt").length; i++) {
            var $this = $(".registerIpt").eq(i);
            (function ($this) {
                $this.parent(".register_li").removeClass("border_error");
                $this.siblings(".error").hide();
            })($this)
        }
        $(".account_msg .phone").val("");
        $("#remeber_account").prop("checked",false);
        if(!$("#remeber_account").prop("checked")){
            $(".phone").val("");
            $(".pwd").val("");
        }
    });
// -----------------------------------------模态框显示的回掉
$('#myModal').on(
    'show.bs.modal',
    function () {
        changeImgCode($(this).find(".picCode"));
    });
// createmodal(appIsNull);
function createmodal(option) {
    var title = option["title"] ? option["title"] : "标题";
    var content = option["content"] ? option["content"] : "内容";
    var func = option["func"] ? option["func"] : "normal";
    var cancle = option["cancle"] ? option["cancle"] : "  取消";
    var ok = option["ok"] ? option["ok"] : "  确定";
    $("#normal .normal").attr("name", func);
    $("#normal .normal_header").text(title);
    $("#normal .normal_body").text(content);
    $("#normal .normal_footer .cancle").text(cancle);
    $("#normal .normal_footer .ok").text(ok);
}

// 获取应用类型列表(管理中心)
function getAppTypes(selectWrap) {
    $.ajax({
        type: "get",
        url: "/app/appTypes?random=" + Math.random(),
        success: function (response) {
            if (response.code == 0) {
                var appTypes = response.data;
                var L = appTypes.length;
                for (var i = 0; i < L; i++) {
                    $(selectWrap).append(
                        "<option value=" + appTypes[i].key + ">"
                        + appTypes[i].value + "</option>");
                }
            }
        },
        error: function () {
            $("#toast-container").html("");
            toastr.error("错误，请稍后再试！");
        }
    })
}

// ----------------------------------------回到顶部
/*$("#authorizedBottom").addClass("fix_bottom");
setTimeout(function () {
    if (!$("body").scrollTop() > 0) {
        $("#authorizedBottom").removeClass("fix_bottom");
    }
},1000)*/
$(document).on("scroll", function () {
    if (active == "/pro/template/manageCenter/applicationDetail"){
        fixedTop();
    }
    var documentTop = document.body.clientHeight;
    var scrollTop = $(document).scrollTop();
    if (scrollTop > 500) {
        $("#turn_to_top").show();
    } else {
        $("#turn_to_top").hide();
    }
})
$("#top").on("click", function () {
    $('body,html').animate({scrollTop: '0px'}, 1000)
    // $('body').scrollTop()?$('body,html').animate({scrollTop: '0px'}, 1000):(document.body.scrollTop =0,document.documentElement.scrollTop=0	);
})
// 找回密码回到首页打开登录弹窗
var url = (document.URL).substr((document.URL).length - 9);
if (url == "retrieved" && !isLogined) {
    $("#myModal").modal('show');
}
$(".logo").on("click", function () {
    window.location.href = "/pro/index";
})

function fixedBottom(){
    if (!$(document).scrollTop() > 0) {
        $("#authorizedBottom").removeClass("fix_bottom");
    }else{
        $("#authorizedBottom").addClass("fix_bottom");
    }
}
function fixedTop(){
    $("#table_top").removeClass("fix_top");
    if ($(document).scrollTop() > 0) {
        $("#authorizedBottom").addClass("fix_bottom");
        var h1 = $("#fontList").offset().top;
        var h2 = $(document).scrollTop();
        if (h1 - h2 < 40) {
            $("#table_top").addClass("fix_top");
        } else {
            $("#table_top").removeClass("fix_top");
        }
    }
}