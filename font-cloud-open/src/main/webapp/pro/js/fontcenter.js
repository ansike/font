/**
 * Created by Administrator on 2017/2/13 0013.
 */
//------------------------------------------联想查询
$("#search_ipt").on("keyup", function () {
    $(".localhistory").hide();
    var text = $(this).val().trim();
    if (text == "") {
        $(".search_result").hide();
    } else {
        $.ajax({
            type: "get",
            url: "/font/findThink",
            data: {
                search: text
            },
            success: function (res) {
                var data = res.data;
                if (data.names.length == 0 && data.authors.length == 0) {
                    $(".search_result").hide();
                } else {
                    var html = "";
                    for (key in data) {
                        //{key:[{}]}
                        var datas = data[key];
                        var nLen = datas.length;
                        if (nLen > 0) {
                            $(".search_result").show();
                            html += "<dl class='result_box font_name'>" +
                                "<dt class='dl_title'>" + (key == 'names' ? "字体名" : (key == "authors" ? "作者名" : "未知")) + "</dt>" +
                                "<dd class='dl_body'>";
                            nLen = nLen > 4 ? 4 : nLen;
                            for (var i = 0; i < nLen; i++) {
                                html += "<p class='res_items' data-key=" + key + " data-content='" + datas[i] + "' >"+datas[i].substr(0,datas[i].indexOf(text))+"<span class='ipted'>" + text + "</span>" + datas[i].substr(datas[i].lastIndexOf(text)+text.length) + "</p>";
                            }
                            html += "</dd></dl>";
                        }
                    }
                    $(".search_result").html(html);
                    //点击联想出的结果进行搜索
                    $(".res_items").on("click", function () {
                        var key = $(this).attr("data-key");
                        var value = $(this).attr("data-content");
                        set_search_history(value);
                        window.location.href = "/font/fontCenter/fontcenter?searchKey=" + key + "&searchValue=" + encodeURI(value);

                    })
                }


            }
        })
    }

});
$(document).on("click", function () {
    $(".localhistory").hide();
    $(".search_result").hide();
});
//----------------------------------------------搜索框的双击事件
$("#search_ipt").on("dblclick", function () {
    if (get_search_history()) {
        $(".localhistory").show();
    }
});
//-----------------------------------------------搜索框的回车事件
$("#search_ipt").keydown(function (event) {
    var value = $(this).val().trim();
    if (value == "") {
        return;
    } else {
        if (event.keyCode == "13") {
            set_search_history(value);
            window.location.href = "/font/fontCenter/fontcenter?searchKey=search&searchValue=" + encodeURI(value);
        }
    }
});
//-------------------------------------------------搜索按钮事件
$(".searchBtn").on("click", function () {
    var value = $("#search_ipt").val().trim();
    if (value == "") {
        window.location.href = "/font/fontCenter/fontcenter";
        return;
    } else {
        set_search_history(value);
        window.location.href = "/font/fontCenter/fontcenter?searchKey=search&searchValue=" + encodeURIComponent(value);
    }
});
//---------------------------------------初始化索搜历史（5个）
function addContent() {
    var history = get_search_history();
    var html = "";
    if (history) {
        for (var i = 0; i < history.length; i++) {
            html += "<li class='res_items'>" + history[i] + "</li>";
        }
    }
    $(".localhistory").html(html);
    $(".localhistory .res_items").on("click", function () {
        var value = $(this).text().trim();
        set_search_history(value);
        window.location.href = "/font/fontCenter/fontcenter?searchKey=search&searchValue=" + encodeURIComponent(value);
    })
}
addContent();
//--------------------------------------设置cookie索搜历史
function set_search_history(value) {
    if (cookie("localhistory")) {
        var history = get_search_history();
        var len = history.length > 4 ? 4 : history.length;
        var str = value;
        for (var i = 0; i < len; i++) {
            str += "#" + history[i];
        }
        cookie("localhistory", str, 7);
    } else {
        cookie("localhistory", value, 7);
    }
}
//---------------------------------获取cookie中的搜索历史
function get_search_history() {
    if (cookie("localhistory")) {
        var history = cookie("localhistory");
        return history[0];
    } else {
        return false;
    }

}
function isInteger(obj) {
    return obj % 1 == 0
}
//---------------------------------------------回车跳页
$(".pageIpt").on("keydown", function (event) {
    var pageNum = $(this).val().trim();
    if (event.keyCode == 13) {
        if (isInteger(pageNum) && pageNum >= 1 && pageNum <= pages) {
            turnToPage(parseInt(pageNum));
        } else {
            turnToPage(1);
        }
    }
});
//----------------------------------------------按钮跳页
$("#turn_page").on("click", function () {
    var pageNum = $(".pageIpt").val().trim();
    if (isInteger(pageNum) && pageNum >= 1 && pageNum <= pages) {
        turnToPage(parseInt(pageNum));
    } else {
        turnToPage(1);
    }
});
//----------------------------------------------上一页
$(".previous").on("click", function () {
    var pageNum = otherParamsObj["pageNum"];
    if (pageNum && pageNum > 1) {
        pageNum--;
        turnToPage(pageNum);
    }
})
//----------------------------------------------下一页
$(".next").on("click", function () {
    var pageNum = otherParamsObj["pageNum"];
    if (pageNum == null) {
        pageNum = 1;
    }
    if (pageNum && pageNum < pages) {
        pageNum++;
        turnToPage(pageNum);
    }
})
$(".previous").on("mouseover",function () {
    if($(".pageNumBox span:first-child").hasClass("active")){
        $(this).css("cursor","default")
    }
})
$(".next").on("mouseover",function () {
    if($(".pageNumBox span:last-child").hasClass("active")){
        $(this).css("cursor","default")
    }
})
//next跳页方法
function turnToPage(pageNum) {
    if (pageNum > pages) {
        return;
    }
    otherParamsObj["pageNum"] = pageNum;
    togo();
}

//修改标签样式
change_fontLabel_style();
function change_fontLabel_style() {
    var params = tagValue;
    var labels = $(".fontLabel");
    for (var i = 0; i < labels.length; i++) {
        var label = labels[i];
        if (params.indexOf(label.dataset.value) > -1) {
            label.classList.add("active");
        }
    }
}
//添加标签点击事件
$(".hotLabel .fontLabel").on("click", function (event) {
    if (tagValue.indexOf(this.dataset.value) > -1) {
        //已经选中的标签
    } else {
        //没有选中的标签，添加参数
        otherParamsObj["tagValue"] = (otherParamsObj["tagValue"] == null ? "" : otherParamsObj["tagValue"] + ",") + this.dataset.value;
        removePage();
        togo();
    }
});
//删除标签
function removeLabel(_this) {
    tagValue = otherParamsObj["tagValue"].replace(_this.dataset.value, "");
    if (tagValue.substr(0,1)==',') {
        tagValue = tagValue.substr(1);
    } else if (tagValue.substr(tagValue.length-1,1)==",") {
        tagValue = tagValue.substr(0, tagValue.length - 1);
    }
    tagValue = tagValue.replace(",,", ",");
    otherParamsObj['tagValue'] = tagValue;
    removePage();
    togo();
}
//移除页面参数
function removePage() {
    otherParamsObj["pageNum"] = null;
}
//进行跳转
function togo() {
    window.location.href = location.href.substr(0, location.href.indexOf("?")) + parseParams();
}
//参数对象解析成url参数
function parseParams() {
    var params = "";
    for (key in otherParamsObj) {
        var value = otherParamsObj[key];
        if (value != null && value != '' && value != 'null') {
            params += key + "=" + otherParamsObj[key] + "&";
        }
    }
    if (params.length > 0) {
        params = params.substr(0, params.length - 1);
    }
    if (params == null || params == '' || params == 'null')return "";
    return "?" + encodeURI(params);
}
