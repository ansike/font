$(function () {
    //获取应用列表
    getAppList();
    // 添加应用
    $("#addLink").click(function(){
        var appNum=parseInt($("#toolTip").find("span").html());
        if(appNum==0){
            return;
        }else{
            window.location.href="/pro/template/manageCenter/createApp";
        }
    })
});

function getAppList(){//初始化获取应用列表
    $.ajax({
        type: "get",
        url: "/apps?"+Math.random(),
        success: function (response) {
            if (response.code == 0) {
                var fonts=response.data;
                var L=fonts.length;
                $("#toolTip").html("还可创建"+"<span>"+(10-L)+"</span>"+"个应用");
                if(L==0){
                    $("#nullMsg").show();
                    $("#appList").hide();
                    return;
                }else{
                    $("#nullMsg").hide();
                    $("#appList").show();
                }
                if(L==10){
                    $("#addLink").addClass("btn_disabled");
                }else{
                    $("#addLink").removeClass("btn_disabled");
                }
                var html="";
                for(var i=0;i<L;i++){
                    html+="<tr>" +
                        "<td>"+fonts[i].appName+"</td>" +
                            "<td>" +
                            "<img src='"+fonts[i].iconUrl+"' width='60' height='60'>" +
                            "</td>" +
                            "<td>"+fonts[i].appKey+"</td>" +
                            "<td>" +
                            "<a href="+"/pro/template/manageCenter/applicationDetail?appKey="+fonts[i].appKey+" class='link_a'>&nbsp;&nbsp;查看&nbsp;&nbsp;</a>" +
                            " </td>" +
                        "</tr>" ;
                }
                $("#tbody").append(html);

            }else if(response.code == 203){
                $("#myModal").modal("show");
            }
        },
        error: function () {
            $("#toast-container").html("");
            toastr.error("错误，请稍后再试！");
        }
    })
}
