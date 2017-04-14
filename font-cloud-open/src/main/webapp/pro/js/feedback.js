/**
 * Created by Administrator on 2017/2/13 0013.
 */
$.ajax({
    type: "post",
    url: "/fof/h/d/query.json?code=answer_type",
    success: function (data) {
        var data = data.data;
        console.log(data);
        var html = "<option value=''>问题类型</option>";
        for (var i = 0; i < data.length; i++) {
            var str=data[i].name.substr(0,12);
            html+="<option >"+str+"</option>";
        }
        $(".form-control").html(html);
    }
})