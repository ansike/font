/**
 * Created by Administrator on 2017/2/23 0023.
 */
//字体ID
var fontId = otherParamsObj["fontId"];
function download() {
	$.ajax({
        type: "post",
        url: "/font/downFontAction",
        data: {
            fontId: fontId
        },
        success: function (res) {
        	if(res.code==0){
        		window.location.href = res.data;
        	}else if(res.code==203){
        		$('#myModal').modal('show');
        	}else{
        		toastr.error(res.data==null?res.msg:res.data);
        	}
        }
    });
}

$("#authorize").on("click", function () {
    if (isLogined) {
        $.ajax({
            type: "get",
            url: "/font/apps?"+Math.random(),
            data: {
                fontId: fontId
            },
            success: function (res) {
                var data = res.data;
                /**
                 * 按钮的状态
                 *  1---已经授权，可以取消授权
                 *  0---未授权，可以授权
                 *  其他---
                 * */
                if (res.code == 0) {//ajax调用成功

                    if (status >= 1) {//已授权
                        var num = data.authApp.length;
                        if (num == 1 && data.apps.length == 1) {
                            /**
                             * (用户仅有一个应用，授权之后显示)点击取消授权：如果用户仅存在一个应用，弹出确认取消弹出框，
                             * 点击确定后直接取消该应用授权，点击后toast提示：取消使用成功，3s后消失，按钮切换成授权使用
                             * */
                                //-----------------删除字体弹框基本配置
                            var checkdelete = {
                                    title: "您确定要取消使用吗？",
                                    content: "取消使用后字体资源将不会出现在您的应用中！",
                                    func: "delete_font"//必写
                                };
                            $(".normal_header").css({"background":"#DF4D47 url('/pro/img/delete.png') no-repeat 20px center","background-size":"20px 20px"});
                            createmodal(checkdelete);
                            $('#normal').modal('show');
                            $(".normal[name='delete_font'] .normal_btn").off("click");
                            $(".normal[name='delete_font'] .normal_btn").on("click", function () {
                                if ($(this).hasClass("ok")) {
                                    $('#normal').modal('hide');
                                    var datas = {
                                        status: "0",
                                        appids: data.authApp[0].appId,
                                        fontids: fontId
                                    }
                                    authorizeFun(datas);
                                } else {
                                    $('#normal').modal('hide');
                                }
                            })

                        } else {
                            /**
                             * (用户有多个应用(应用>1)，授权之后显示)点击修改授权：点击之后弹出应用框，复显用户之前选择的应用，
                             * 可选择及取消多个应用授权；若用户全部取消，点击确定后关闭应用框，弹出确认取消弹出框，点击确定后toast提示：
                             * 取消使用成功，3s后消失，按钮切换成授权使用；若用户仅进行了修改，点击确定后关闭应用框，
                             * toast提示：修改授权成功，3s后消失，按钮依然为修改授权；点击取消，关闭按钮，
                             * 弹框外其它地方不进行保存直接关闭弹窗。
                             * */
                            $('.myApp').addClass("recycle");
                            $('.myApp .app_header .name').text("修改授权【" + fontName + "】（可多选）");
                            $('.myApp').attr("name", "recycle")
                            var appList = data["apps"];//全部应用
                            var authList = [];//已经授权应用数组
                            $.each(data["authApp"], function (i, obj) {
                                authList.push(obj.appId + "");
                            })
                            var possessList = [];
                            var html = "";
                            for (var i = 0; i < appList.length; i++) {
                                var name = appList[i]['appName'].length > 3 ? appList[i]['appName'].substr(0, 3) : appList[i]['appName'];
                                if (authList.indexOf(appList[i]['appId'] + "") != -1) {
                                    possessList.push(appList[i]['appId'] + "");
                                    html += " <li class='app_items active' data-appId='" + appList[i]['appId'] + "'><dl>" +
                                        "<dt class='info_head'><img src='" + appList[i].iconUrl + "' /></dt>" +
                                        "<dd class='info_body'>" +
                                        "<h4 class='app_name'>" + name + "</h4>" +
                                        "<span class='possess_font'><span class='possess_number'>"+(res.data.appNums[appList[i]['appId']]==null?0:res.data.appNums[appList[i]['appId']])+"</span>款字体</span></dd></dl></li>";
                                } else {
                                    html += " <li class='app_items ' data-appId='" + appList[i]['appId'] + "'><dl>" +
                                        "<dt class='info_head'><img src='" + appList[i].iconUrl + "' /></dt>" +
                                        "<dd class='info_body'>" +
                                        "<h4 class='app_name'>" + name + "</h4>" +
                                        "<span class='possess_font'><span class='possess_number'>"+(res.data.appNums[appList[i]['appId']]==null?0:res.data.appNums[appList[i]['appId']])+"</span>款字体</span></dd></dl></li>";
                                }

                            }
                            $(".app_box").html(html);

                            $('#myApp').modal('show');

                            $(".recycle .app_items").on("click", function () {

                                var number=parseInt($(this).find(".possess_number").text());
                                if ($.inArray($(this).attr("data-appId"), possessList) != -1) {
                                    possessList.splice($.inArray($(this).attr("data-appId"), possessList), 1);
                                    $(this).find(".possess_number").text(number-1);
                                } else {
                                	if(number>=20){
                                        $("#toast-container").html("");
                                    	toastr.warning("当前应用字体数超过上限!");
                                    	return;
                                    }
                                    possessList.push($(this).attr("data-appId"));
                                    $(this).find(".possess_number").text(number+1);
                                }
                                $(this).toggleClass("active");
                            });
                            $(".recycle .normal_btn").off("click");
                            $(".recycle .normal_btn").on("click", function () {
                                if ($(this).hasClass("ok")) {
                                    var datas = {
                                        status: "1",
                                        appids: possessList.join(","),
                                        fontid: fontId
                                    }
                                    if (possessList.length == 0) {
                                        var datas = {
                                            status: "0",
                                            appids: "cleanAll",
                                            fontid: fontId
                                        }
                                        $('#myApp').modal('hide');
                                        var checkdelete = {
                                            title: "您确定要取消使用吗？",
                                            content: "取消使用后字体资源将不会出现在您的应用中！",
                                            func: "delete_all"//必写
                                        };
                                        $(".normal_header").css({"background":"#DF4D47 url('/pro/img/delete.png') no-repeat 20px center","background-size":"20px 20px"});
                                        createmodal(checkdelete);
                                        $('#normal').modal('show');
                                        $(".normal[name='delete_all']").delegate(".normal_btn", "click", function () {
                                            if ($(this).hasClass("ok")) {
                                                //--------------------------------接口
                                                authFontToApps(datas);
                                                $('#normal').modal('hide');
                                            } else {
                                                $('#normal').modal('hide');
                                            }
                                        })
                                    }  else {
                                        //--------------------------------接口
                                        authFontToApps(datas);
                                        $('#myApp').modal('hide');
                                    }
                                } else {
                                    $('#myApp').modal('hide');
                                }
                            })
                        }

                    } else if (status == 0 || status == null || status == '' || status == undefined) {//未授权
                        var num = data.apps.length;
                        if (num == 0) {
                            /**
                             * 若用户没有应用，则点击授权使用，登录后若没有创建应用点击加入列表弹框提示：您还未创建应用，
                             * 左侧：再看看(关闭弹出框)，右侧：去创建(跳转到创建应用页面)
                             * */
                            var appIsNull = {
                                title: "请先创建应用",
                                content: "未创建应用将无法授权并使用字体！",
                                func: "appIsNull",//必写
                                cancle: "再看看",
                                ok: "去创建"
                            };
                            createmodal(appIsNull);
                            $(".modal-content").removeClass("normal_alert");
                            $(".modal-content").addClass("appIsNull");
                            $('#normal').modal('show');
                            $(".normal[name='appIsNull'] .normal_btn").off("click");
                            $(".normal[name='appIsNull'] .normal_btn").on("click", function () {
                                if ($(this).hasClass("ok")) {
                                    window.location.href = "/pro/template/manageCenter/createApp";
                                } else {
                                    $('#normal').modal('hide');
                                }
                            });
                        } else if (num == 1) {
                            /**
                             * 点击授权使用：如果用户仅存在一个应用，则直接在该应用中授权使用，点击后toast提示：
                             * 授权使用成功，3s后消失；授权使用按钮切换成取消使用；
                             * */
                            var number=res.data.appNums[res.data.apps[0]['appId']]==null?0:res.data.appNums[res.data.apps[0]['appId']];
                            if(number>=20){
                                toastr.warning("当前应用字体数超过上限!");
                                return;
                            }
                            var datas = {
                                status: "1",
                                appids: data.unauthApp[0].appId,
                                fontids: fontId
                            }
                            authorizeFun(datas)


                        } else if (num > 1) {
                            /**
                             * 如果用户存在多个应用，则弹出应用框，让用户进行选择，可选择多个应用授权，点击确定后toast提示：
                             * 授权使用成功，3s后消失；点击取消，关闭按钮，弹框外其它地方不进行保存直接关闭弹窗。
                             * */
                            $('.myApp').addClass("authorize");
                            $('#myApp').modal('show');
                            var appList = data["apps"];//新增应用数组
                            var authorizeList = [];
                            var html = "";
                            for (var i = 0; i < appList.length; i++) {
                                var name = appList[i]['appName'].length > 3 ? appList[i]['appName'].substr(0, 3) : appList[i]['appName'];
                                html += " <li class='app_items' data-appId='" + appList[i]['appId'] + "'><dl>" +
                                    "<dt class='info_head'><img src='" + appList[i].iconUrl + "' /></dt>" +
                                    "<dd class='info_body'>" +
                                    "<h4 class='app_name'>" + name + "</h4>" +
                                    "<span class='possess_font'><span class='possess_number'>"+(res.data.appNums[appList[i]['appId']]==null?0:res.data.appNums[appList[i]['appId']])+"</span>款字体</span></dd></dl></li>";
                            }
                            $(".app_box").html(html);

                            $(".app_box .app_items").on("click", function () {
                                var number=parseInt($(this).find(".possess_number").text());
                                if ($.inArray($(this).attr("data-appId"), authorizeList) != -1) {
                                    authorizeList.splice($.inArray($(this).attr("data-appId"), authorizeList), 1);
                                    $(this).find(".possess_number").html(number-1);
                                } else {
                                    if(number>=20){
                                        $("#toast-container").html("");
                                        toastr.warning("当前应用字体数超过上限!");
                                        return;
                                    }
                                    authorizeList.push($(this).attr("data-appId"));
                                    $(this).find(".possess_number").html(number+1);
                                }
                                $(this).toggleClass("active");
                            });
                            $(".authorize .normal_btn").off("click");
                            $(".authorize .normal_btn").on("click", function () {
                                if ($(this).hasClass("ok")) {
                                    if (authorizeList.length == 0) {
                                        $('#myApp').modal('hide');
                                    } else {
                                        var datas = {
                                            status: "1",
                                            appids: authorizeList.join(","),
                                            fontid: fontId
                                        }
                                        authFontToApps(datas);
                                        $('#myApp').modal('hide');
                                    }
                                } else {
                                    $('#myApp').modal('hide');
                                }
                            })
                        }
                    } else {
                    }

                } else {
                    toastr.warning(res.msg);
                }
            }
        })
    } else {
        //未登录手动启动模态框
        $('#myModal').modal('show')
    }
})
/**
 * 授权和取消授权接口
 * data={
 *  status=1,(1授权，0取消授权)
 *  appids=,
 *  fontids=
 * }
 * */
function authorizeFun(data) {
    $.ajax({
        type: "post",
        url: "/appfont/authOrUnFont",
        data: data,
        success: function (res) {
            console.log(res);
            if (res.code == 0) {
                if (data.status == 1) {
                    toastr.success("授权成功");
                } else {
                    toastr.success("取消授权成功");
                }
                setTimeout(function () {
                    window.location.reload();
                },500)
            }
        }
    })
}
function authFontToApps(data) {
    $.ajax({
        type: "post",
        url: "/appfont/authFontToApps",
        data: data,
        success: function (res) {
            console.log(res);
            if (res.code == 0) {
                    toastr.success("修改成功");
                setTimeout(function () {
                    window.location.reload();
                },500)
            }
        }
    })
}
// $('#myApp').on('hidden.bs.modal', function () {
//     if($(".myApp").hasClass("recycle")){
//         $(".myApp").removeClass("recycle");
//     }
//     if($(".myApp").hasClass("authorize")){
//         $(".myApp").removeClass("authorize");
//     }
//     $(".app_box").html("");
// });