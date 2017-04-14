//用来存放全局使用的 应用详情信息
var appDetail={
    appKey:"",
};
var authArr=[];//存放授权字体id
$(function() {
    fixedBottom();//固定列表底部（方法在public.js文件中）
    appDetail.appKey = $("#appKeyVal").val();
    var formObj = {//判断输入表单是否合法
        nameValid: true,
        imgValid: true
    };
    getAppDetail(appDetail.appKey);//获取应用信息详情
    getAppTypes("#appTypeEdit");//获取应用所属类型集合
    $('#appNameEdit').on('input', function () {//input输入内容时清除按钮出现
        var str=$(this).val();
        if (str== "") {
            $(".clear_input").hide();
        } else {
            $(".clear_input").show();
        }
    });
    $(".clear_input").click(function () {//点击清除按钮清空输入框
        $('#appNameEdit').val("");
        $(this).hide();
    });
    $('#appNameEdit').on("blur", function () {//失去焦点时对应用名称做校验
        if ($(this).val() == "") {
            $("#nameError").html("请输入应用名称");
            $(this).css("border-color", "#f00");
            formObj.nameValid = false;
        } else {
            formObj.nameValid = verifyName();
        }
    });
    $("#fileBtn").change(function () {//上传应用图标
        if (this.files[0].type != "image/jpeg" && this.files[0].type != "image/png") {//校验格式
            $("#imgError").html("图片格式错误");
            formObj.imgValid = false;
            return;
        }else if(this.files[0].size>307200){//校验文件大小
            $("#imgError").html("图片不超过300KB");
            formObj.imgValid=false;
            return;
        } else {//预览图片
            var formData = new FormData($("#uploadForm")[0]);
            $.ajax({
                url: '/file/uploadpic',
                type: 'post',
                data: formData,
                cache: false,
                contentType: false,
                processData: false,
                success: function (response) {
                    if (response.code == 0) {
                        var imgData = response.data;
                        $("#iconPre").attr("src", imgData.url);
                        $("#delImg").show();
                        $("#iconPre").show();
                        $("#uploadBtns").hide();
                        $("#imgError").html("");
                        formObj.imgValid = true;
                    } else {
                        $("#toast-container").html("");
                        toastr.error("上传失败，请稍后再试！");
                        formObj.imgValid = false;
                    }
                },
                error: function () {
                    $("#toast-container").html("");
                    toastr.error("错误，请稍后再试！");
                }
            });
        }
    });
    $("#delImg").click(function () {//删除预览图片
        $(this).hide();
        $("#iconPre").hide();
        $("#uploadBtns").show();
        $("#iconPre").attr("src", "");
        $("#imgError").html("请上传应用ICON");
        formObj.imgValid=false;
    });
    $(".form-control").focus(function () {//输入框获得焦点后，错误提示信息消失（用于所有输入框）
        $(this).css("border-color", "");
        $(this).siblings(".error_tip").html("");
    });

    $("#updateApp").click(function () {//提交表单更新应用
        for (var i in formObj) {
            if (formObj[i] == false) {
                return;
            }
        }
        updateApp();
    });

    //点击字体示例图查看放大图片
    $("#fontList").delegate(".font_img","click",function(){
        var src=$(this).attr("src");
        enlargeImg(src);
    });
    $(".close_img").click(function(){//关闭放大图片
        $("#largeImgBox").hide();
    });
});
//加载应用详情
function getAppDetail(appKey){
    $.ajax({
        type: "get",
        url: "/app/appDetail?"+Math.random(),
        data:{appKey:appKey},
        success: function(response) {
            if (response.code == 0) {
                var app=response.data;
                appDefault(app);//应用信息初始化
                appFontInit();//字体列表初始化
            } else if(response.code == 203){
                window.location.href="/pro/index"
            }
        },
        error:function(){
            $("#toast-container").html("");
            toastr.error("错误，请稍后再试！");
        }
    })
}
//确认删除应用
function confirmDel(){
    $.ajax({
        type: "post",
        url: "/app/deleteApp",
        data: {
            appid: $("#appId").val()
        },
        success: function (response) {
            if (response.code == 0) {
                $("#toast-container").html("");
                toastr.success("删除应用成功！");
                $("#delAppDialog").modal("hide");//关闭弹窗
                window.location.href = "/pro/template/manageCenter/application";
            } else {
                $("#toast-container").html("");
                toastr.error("删除应用失败！");
            }
        },
        error: function () {
            $("#toast-container").html("");
            toastr.error("错误，请稍后再试！");
        }
    })
}
//应用信息初始化
function appDefault(app){
    $("#appId").val(app.appId);
    $("#appName").html(app.appName);
    $("#appType").val(app.appType);
    $("#appTypeValue").html(app.appTypeValue);
    $(".app_key").html(app.appKey);
    $(".app_secret").html(app.appSecret);
    $("#icon").attr("src",app.iconUrl);
    if(app.updateFrequency==0){
        $("#appNameEdit").attr("disabled",true);
        $(".clear_input").hide();
    }
    linkCancel();
}
//应用字体列表初始化
function appFontInit() {
    var appId = $("#appId").val();
    $.ajax({
        type: "post",
        url: "/appfont/findAppFonts?"+Math.random(),
        data: {
            appid: appId
        },
        success: function (response) {
            if (response.code == 0) {
                var fonts=response.data;
                var L = fonts .length;
                var html = "";
                for (var i = 0; i < L; i++) {
                    var authzDate = formatDate(new Date(fonts[i].authzDate));
                    var unauthzDate = formatDate(new Date(fonts[i].unauthzDate));
                    html += "<ul class='row font_info'>" +
                        " <li class='col-sm-3'>" +
                        "<a href='/font/fontCenter/fontDetail?fontId="+fonts[i].fontId+"'>" + fonts[i].name + "</a>" +
                        "</li>" +
                        "<li class='col-sm-3'>" +
                        "<img class='font_img' src=" + fonts[i].picUrl + ">" +
                        "</li>" +
                        "<li class='col-sm-3 date'>" + authzDate+"<br>至<br>" + unauthzDate + "</li>" +
                        "<li class='col-sm-3 font_status' onclick='reAuthorize("+fonts[i].fontId+",this);'>" + (fonts[i].status==1?"使用中":"重新授权") + "</li>" +
                        "</ul>";
                    if(fonts[i].status != 1){//筛选出未授权字体
                        authArr.push(fonts[i].fontId);
                    }
                }
                $("#fontList").html(html);
                $("#authAll").show();//显示一键授权按钮
                //重新授权高亮显示
                $(".font_status").each(function(){
                    var text=$(this).html();
                    if(text=="重新授权"){
                        $(this).addClass("status_light");
                    }
                });
                if(authArr.length!=0){
                    $("#authAll").removeClass("btn_disabled");//检测到有未授权字体时候，一键授权按钮取消置灰
                }else{
                    $("#authAll").addClass("btn_disabled");
                }
                fixedTop();//固定列表头部
                fixedBottom();
            }else{
                $("#toast-container").html("");
                toastr.error(response.msg);
            }
        },
        error: function () {
            $("#toast-container").html("");
            toastr.error("错误，请稍后再试！");
        }
    })
}
//编辑应用界面初始化
function editInit(){
    $("#appNameEdit").val( $("#appName").html());
    $("#appTypeEdit").val($("#appType").val());
    $("#iconPre").attr("src",$("#icon").attr("src"));
    $("#iconPre").show();
    $("#delImg").show();
    $("#uploadBtns").hide();
}
//更新应用（点击保存更新应用）
function updateApp(){
    $.ajax({
        type: "post",
        url: "/app?"+Math.random(),
        data: {
            appId: $("#appId").val(),
            appName: $.trim($("#appNameEdit").val()),
            appType: $("#appTypeEdit").val(),
            iconUrl: $("#iconPre").attr("src")
        },
        success: function (response) {
            if (response.code == 0) {
                $("#toast-container").html("");
                toastr.success("更新应用成功！");
                setTimeout(function(){
                    getAppDetail(appDetail.appKey);
                },1100);
            }else if(response.code == 208){
                $("#toast-container").html("");
                toastr.error("应用名称最多修改三次！");
                $("#appNameEdit").attr("disabled",true);
                $(".clear_input").hide();
            } else {
                $("#toast-container").html("");
                toastr.error(response.msg);
            }
        },
        error: function () {
            $("#toast-container").html("");
            toastr.error("错误，请稍后再试！");
        }
    })
}
//一键授权
function authorAll(){
    var L=authArr.length;
    if(L==0){
        return;
    }else{
        var fontStr=authArr.join();
        $.ajax({
            type: "post",
            url: "/appfont/reAuth?"+Math.random(),
            data:{
                appid:$("#appId").val(),
                fontids:fontStr,
            },
            success: function(response) {
                if(response.code==0){
                    $("#toast-container").html("");
                    toastr.success("授权使用成功！");
                    authArr=[];
                    appFontInit();
                }else{
                    $("#toast-container").html("");
                    toastr.warning("授权使用失败！");
                }
            },
            error:function(){
                $("#toast-container").html("");
                toastr.error("错误，请稍后再试！");
            }
        })
    }
}
//重新授权字体
function reAuthorize(id,obj){
    var text=$(obj).html();
    if(text=="重新授权"){
        $.ajax({
            type: "post",
            url: "/appfont/reAuth?"+Math.random(),
            data:{
                appid:$("#appId").val(),
                fontids:id
            },
            success: function(response) {
                if(response.code==0){
                    $("#toast-container").html("");
                    toastr.success("授权使用成功！");
                    $(obj).html("使用中");
                    $(obj).removeClass("status_light");
                    var authzDate = formatDate(new Date(response.data[0].authzDate));
                    var unauthzDate = formatDate(new Date(response.data[0].unauthzDate));
                    var dateInner=authzDate+"<br>至<br>" + unauthzDate;
                    $(obj).siblings(".date").html(dateInner);
                    authArr.remove(response.data[0].fontId);
                    if(authArr.length==0){
                        $("#authAll").addClass("btn_disabled");
                    }
                }
            },
            error:function(){
                $("#toast-container").html("");
                toastr.error("错误，请稍后再试！");
            }
        })
    }else{
        return;
    }
}

//点击修改按钮触发事件
function linkEdit() {
    $("#appEdit").show();
    $("#appInfo").hide();
    editInit();
}
//取消修改应用操作
function linkCancel(){
    $("#appEdit").hide();
    $("#appInfo").show();
    $("#appEdit .error_tip").html("");
    $("#appEdit input[type=text]").css("border-color","");
}
//校验应用名称
function verifyName(){
    var imgValid;
    var regName=/^[\u4e00-\u9fa5a-zA-Z0-9_]+$/; //应用名称校验
    var appName=$.trim($('#appNameEdit').val());
    var L=appName.length;
    if(L>10 && !regName.test(appName)){
        $("#nameError").html("仅支持汉字、数字、英文、下划线  最多输入10个字");
        $('#appNameEdit').css("border-color","#f00");
        imgValid=false;
    }else if(L>10){
        $("#nameError").html("最多输入10个字");
        $('#appNameEdit').css("border-color","#f00");
        imgValid=false;
    }else if(!regName.test(appName)){
        $("#nameError").html("仅支持汉字、数字、英文、下划线");
        $('#appNameEdit').css("border-color","#f00");
        imgValid=false;
    }else{
        $("#nameError").html("");
        $('#appNameEdit').css("border-color","");
        imgValid=true;
    };
    return imgValid;
}
//应用字体示例图放大效果
function enlargeImg(src){
    $("#largeImgBox").show();
    $("#largeImg").attr("src",src);
}
/*格式化日期*/
function formatDate(date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? '0' + m : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    return y + '年' + m + '月' + d+'日';
};
// 数组删除某个元素
Array.prototype.remove = function(val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};



