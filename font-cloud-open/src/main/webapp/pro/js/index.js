/**
 * Created by Administrator on 2017/2/17 0017.
 */
// $(".getMsgCode").on("click", function () {
//     $(".getMsgCode").attr("disabled", true);
//     var picCode = $(".pic_verification").val();
//     var phoneNomber = $(".phone").val();
//     $.ajax({
//         type: "post",
//         url: "/user/sendSmsCode",
//         data: {
//             mobile: phoneNomber,
//             templateCode: "REGISTER_ACCOUNT",
//             randomCode: picCode
//         },
//         success: function (data) {
//             console.log(data);
//             if (data.code == 0) {
//                 console.log("成功");
//                 Countdown();
//             } else if (data.code == 407) {
//                 console.log("频繁的请求数据");
//             } else if (data.code == 106) {
//                 console.log(data.msg);
//                 $(".phone_number_registered").show();
//                 testaArr.phone = 0;
//             }
//         }
//         ,
//         error: function () {
//             console.log("网络异常！")
//         }
//     })
// });

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
