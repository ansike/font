

$(function () {
    var appVerify={//判断表单是否合法
        nameValid:false,
        selValid:false,
        imgValid:false
    };
    //初始化应用类型选项
    getAppTypes("#selectApp");
    //输入验证
    $('#appNameEdit').on('input', function() {//input输入内容时清除按钮出现
        var str=$(this).val();
        if (str=="") {
            $(".clear_input").hide();
        } else {
            $(".clear_input").show();
        }
    });
    $(".clear_input").click(function(){//点击清除按钮清空输入框
        $('#appNameEdit').val("");
        $(this).hide();
    });
    $('#appNameEdit').on("blur",function(){//失去焦点时对应用名称做校验
        if($(this).val()==""){
            $("#nameError").html("请输入应用名称");
            $("#nameError").addClass("error_tip");
            $(this).css("border-color","#f00");
            appVerify.nameValid=false;
        }else{
            appVerify.nameValid= verifyName();
        }
    });
    $("#selectApp").on("change",function(){//校验下拉框是否为空
        if($(this).val()=="null"){
            $("#selError").html("请选择应用类型");
            $(this).css("border-color","#f00");
            appVerify.selValid=false;
        }else{
            $("#selError").html("");
            $(this).css("border-color","");
            appVerify.selValid=true;
        }
    });
    //上传预览图片
    $("#fileBtn").change(function(){
        if( this.files[0].type!="image/jpeg" && this.files[0].type!="image/png" ){//校验格式
            $("#imgError").html("图片格式错误");
            appVerify.imgValid=false;
            return;
        }else if(this.files[0].size>307200){//校验文件大小
            $("#imgError").html("图片不超过300KB");
            appVerify.imgValid=false;
            return;
        }else{//预览图片
            var formData = new FormData($("#uploadForm")[0]);
            $.ajax({//获取图片预览地址
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
                        appVerify.imgValid=true;
                    } else {
                        $("#toast-container").html("");
                        toastr.error("上传失败，请稍后再试！");
                        appVerify.imgValid=false;
                    }
                },
                error: function () {
                    $("#toast-container").html("");
                    toastr.error("错误，请稍后再试！");
                }
            });
        }
    }) ;
    $("#delImg").click(function(){//删除预览图片
        $(this).hide();
        $("#iconPre").attr("src","");
        $("#imgError").html("请上传应用ICON");
        appVerify.imgValid=false;
        $("#iconPre").hide();
        $("#uploadBtns").show();
    });

    //勾选同意协议后，错误提示语消失
    $("#agree").change(function(){
        if($("#agree").is(":checked")){
            $("#checkTip").html("");
        }
    });
    //取消创建
    $("#cancel").click(function(){
        window.location.href="/pro/template/manageCenter/application";
    });
    //提交表单创建应用
    $("#submit").on("click",function(){
        //校验输入内容
        if($("#appNameEdit").val()==""){
            $("#nameError").html("请输入应用名称");
            $("#appNameEdit").css("border-color","#f00");
            $("#nameError").addClass("error_tip");
            return;
        }else if($("#selectApp").val()=="null"){
            $("#selError").html("请选择应用类型");
            $("#selectApp").css("border-color","#f00");
            return;
        }else if($("#iconPre").attr("src")==""){
            $("#imgError").html("请上传应用ICON");
            return;
        }
        for(var i in appVerify){
            if(appVerify[i]==false){
                return;
            }
        }
        if($("#agree").is(":checked")){//是否选中协议
            var　data={
                appName : $.trim($("#appNameEdit").val()),
                appType :　$("#selectApp").val(),
                iconUrl : $("#iconPre").attr("src")
            };
            $('#submit').attr('disabled',true)
            $.ajax({
                type: "post",
                url: "/app ",
                data: $.param(data),
                success: function(response) {
                    if (response.code == 0) {
                        $("#toast-container").html("");
                        toastr.success("创建应用成功！");
                        window.location="/pro/template/manageCenter/application";
                    }else if(response.code == 207){
                        $("#nameError").html("应用名称已存在");
                        $("#nameError").addClass("error_tip");
                        $('#submit').attr('disabled',false)
                        $("#appNameEdit").css("border-color","#f00");
                    }else{
                        $("#toast-container").html("");
                        toastr.error("创建应用失败！");
                        $('#submit').attr('disabled',false)
                    }
                },
                error:function(){
                    $("#toast-container").html("");
                    toastr.error("错误，请稍后再试！");
                    $('#submit').attr('disabled',false)
                }
            })
        }else{
            $("#checkTip").html("请同意手迹云服务条款");
            return;
        }
    });
    //输入框获得焦点后，错误提示信息消失
    $(".form-control").focus(function(){
        $(this).css("border-color","");
        $(this).siblings(".error_tip").html("");
    });
});

function verifyName(){//校验应用名称
    var imgValid;
    var regName=/^[\u4e00-\u9fa5a-zA-Z0-9_]+$/; //应用名称校验
    var appName=$.trim($('#appNameEdit').val());
    var L=appName.length;
    if(L>10 && !regName.test(appName)){
        $("#nameError").html("仅支持汉字、数字、英文、下划线  最多输入10个字");
        imgValid=false;
    }else if(L>10){
        $("#nameError").html("最多输入10个字");
        imgValid=false;
    }else if(!regName.test(appName)){
        $("#nameError").html("仅支持汉字、数字、英文、下划线");
        imgValid=false;
    }else{
        $("#nameError").html("");
        imgValid=true;
    };
    if(imgValid){
        $('#appNameEdit').css("border-color","");
        $("#nameError").removeClass("error_tip");
    }else{
        $('#appNameEdit').css("border-color","#f00");
        $("#nameError").addClass("error_tip");
    }
    return imgValid;
}
