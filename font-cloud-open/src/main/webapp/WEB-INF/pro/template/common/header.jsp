<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.fd.font.cloud.base.FontCloudConstant" %>
<%@ page import="com.fd.font.cloud.developer.entity.Developer" %>
<%@page import="com.fd.font.cloud.util.HttpUtil" %>
<%@page import="java.util.Map.Entry" %>
<%@page import="java.util.Map" %>
<%@page import="org.nutz.json.Json" %>
<%@page import="com.fd.font.cloud.base.BaseController" %>
<%
    String uri = request.getRequestURI().toString();
    String active = uri.substring(8, uri.length() - 4);//活动的页面索引1首页，2字体中心，3资源中心等等
    Developer user = BaseController.getDeveloperBySessionAndRedis(request);
    boolean islogin = false;
    if (user == null) {
        islogin = false;
    } else {
        islogin = true;
    }
    String requestParams = "";
    Map<String, String> requestMap = HttpUtil.parseRequestParams(request);
    for (Entry<String, String> e : requestMap.entrySet()) {
        requestParams += e.getKey() + "=" + e.getValue() + "&";
    }
    String tagValue = requestMap.get("tagValue");
    String requestParamsJson = Json.toJson(requestMap).replaceAll("\n", "");
    request.setAttribute("fontcenter_tagValue", tagValue);
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader ("Expires", 0);
 	// 将过期日期设置为一个过去时间
    response.setHeader("Expires", "0");
    // 设置 HTTP/1.1 no-cache 头
    response.setHeader("Cache-Control", "no-store,no-cache,must-revalidate");
    // 设置 IE 扩展 HTTP/1.1 no-cache headers， 用户自己添加
    response.addHeader("Cache-Control", "post-check=0, pre-check=0");
    response.flushBuffer();
%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="keywords" content="字体下载,方正字体,个性字,创意字,手写体,趣味字">
    <meta name="description" content="方正手迹字体云开放平台提供各种个性字下载，包括个性字、创意字、趣味字">
    <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1,width=device-width,user-scalable=no">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="expires" content="0"/>
    <title>方正手迹字体云开放平台</title>
    <link rel="shortcut icon" href="/pro/img/favicons.png">
    <link rel="stylesheet" href="/pro/plugins/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="/pro/style/css/public.css?$date">
    <link rel="stylesheet" type="text/css" href="/pro/plugins/toastr/toastr.css">
    <script>
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?1edb9d7f8bd468e5d4947f3d4fcab51c";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>
    <script src="/pro/js/public/jquery-1.10.2.min.js"></script>
    <script src="/pro/plugins/bootstrap/bootstrap.js"></script>
    <script src="/pro/js/public/cookie.js"></script>
    <script type="text/javascript" src="/pro/plugins/toastr/toastr.min.js"></script>
    <script>
        //判断用户有没有登录
        var isLogined = <%=islogin%>;
        var tagValue = '<%=tagValue%>';
        var otherParams = '<%=requestParamsJson%>';
        var otherParamsObj = (JSON.parse(otherParams))?(JSON.parse(otherParams)):eval('(' + otherParams + ')');
        var active = '<%=active%>';

    </script>

</head>
<body>
<header class="header-wrapper">
    <div class="header clearfix">
        <div class="logo" title="手迹云开放平台"></div>
        <%if(!active.equalsIgnoreCase("/pro/template/register/index")){ %>
        <ul class="nav">
            <div onclick="goPage('/pro/index');">
                <li class="navChild <%=(active.equals("/pro/index")||active==null)?"checked":""%>">首页</li>
            </div>
            <div onclick="goPage('/font/fontCenter/fontcenter');">
                <li class="navChild <%=(active.equals("/pro/template/fontCenter/fontcenter")||active.equals("/pro/template/fontCenter/fontDetail"))?"checked":""%>">
                    字体中心
                </li>
            </div>
            <div onclick="goPage('/pro/template/resourceCenter/resource');">
                <li class="navChild <%=active.equals("/pro/template/resourceCenter/resource")?"checked":""%>">资源中心</li>
            </div>
            <div onclick="goPage('/pro/template/manageCenter/application',true);">
                <li class="navChild <%=(active.equals("/pro/template/manageCenter/application") || active.equals("/pro/template/manageCenter/createApp") || active.equals("/pro/template/manageCenter/applicationDetail"))?"checked":""%>">管理中心</li>
            </div>
            <div onclick="goPage('/pro/template/accountCenter/account',true);">
                <li class="navChild <%=active.equals("/pro/template/accountCenter/account")?"checked":""%>">账号中心</li>
            </div>
        </ul>
        <div class="loginAndRegister">
            <% if (user == null) { %>
            <a class=" login " data-toggle="modal" data-target="#myModal">登录</a>
            <b class="line">|</b>
            <a href="/pro/template/register/index" class="register">注册</a>
            <%} else { %>
            <a href="/pro/template/accountCenter/account"><%=user.getMobile() %>
            </a>
            <b class="line">|</b>
            <a id="logout">注销</a>
            <%} %>
        </div>
        <% } %>
        <!-- 登录 -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content login-content">
                    <div class="login_header clearfix">
                        <p class="close_box">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            </button>
                        </p>
                        <h4 class="login_tab">
                            <span class="pwd_login active">密码登录</span>
                        </h4>
                        <h4 class="login_tab">
                            <span class="msg_login">短信登录</span>
                        </h4>
                    </div>
                    <ul class="form_body account_pwd clearfix">
                        <li class="register_li">
                            <span class="iptLabel">手机号</span>
                            <input type="text" class="registerIpt phone" data-type="login"/>
                            <span class="error phone_number_empty" style="display: none">请输入手机号</span>
                            <span class="error phone_number_error" style="display: none">手机号格式错误</span>
                            <span class="error phone_number_notregistered" style="display: none">手机号未注册</span>
                            <span class="error accountError " style="display: none">账号或密码错误</span>
                        </li>
                        <li class="register_li">
                            <span class="iptLabel">密码</span>
                            <input type="password" name="password" class="registerIpt pwd"/>
                            <span class="eye" onclick="switchPsw(this);"></span>
                            <span class="error pwd_empty" style="display: none">请输入密码</span>
                        </li>
                        <li class="clearfix">
                            <p class="register_li pic margin_bottom_15">
                                <span class="iptLabel">验证码</span>
                                <input type="text" class="registerIpt verification pic_verification"/>
                                <span class="error pic_code_empty" style="display: none">请输入图片验证码</span>
                                <span class="error pic_code_error" style="display: none">图片验证码输入错误</span>
                            </p>
                            <img src="/user/getRandomCode" class="getCode picCode"/>
                        </li>
                    </ul>
                    <ul class="form_body account_msg clearfix" style="display: none;">
                        <li class="register_li">
                            <span class="iptLabel">手机号</span>
                            <input type="text" class="registerIpt phone" data-type="login"/>
                            <span class="error phone_number_empty" style="display: none">请输入手机号</span>
                            <span class="error phone_number_error" style="display: none">手机号格式错误</span>
                            <span class="error phone_number_notregistered" style="display: none">手机号未注册</span>

                        </li>
                        <li class="clearfix">
                            <p class="register_li pic">
                                <span class="iptLabel">验证码</span>
                                <input type="text" class="registerIpt verification pic_verification"/>
                                <span class="error pic_code_empty" style="display: none">请输入图片验证码</span>
                                <span class="error pic_code_error" style="display: none">图片验证码输入错误</span>
                            </p>
                            <img src="/user/getRandomCode" class="getCode picCode"/>
                        </li>
                        <li class="clearfix ">
                            <p class="register_li msg margin_bottom_15">
                                <span class="iptLabel">短信验证码</span>
                                <input type="text" class="registerIpt verification msg_verification"/>
                                <span class="error msg_code_empty" style="display: none">请输入手机验证码</span>
                                <span class="error msg_code_error" style="display: none">手机验证码输入错误</span>
                            </p>
                            <input type="button" class="getCode getMsgCode" value="获取验证码" data-type="MOBILE_LOGIN"
                                   disabled="disabled"/>
                        </li>
                    </ul>
                    <div class="footer">
                        <div class="agreementBox">
                            <input type="checkbox" id="remeber_account" class="check_box"/>
                            <label for="remeber_account">记住账号</label>
                        </div>
                        <div class="registerBtnBox">
                            <input type="button" class="loginBtn" value="登录" data="account_pwd">
                        </div>
                        <p class="footer_text">
                            <a class="get_pwd" href="/pro/template/retrievepwd/retrieve">忘记密码？</a>
                            <a class="go_register" href="/pro/template/register/index">立即注册？</a>
                        </p>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal -->
        </div>
        <!-- 普通弹窗 -->
        <div class="modal fade " id="normal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content normal normal_alert" name="">
                    <div class="normal_header">
                        你确定要取消使用吗？
                    </div>
                    <div class="normal_body">
                        取消使用后字体资源将不会出现在您的应用中！
                    </div>
                    <div class="normal_footer clearfix">
                        <button class="normal_btn cancle">取消</button>
                        <button class="normal_btn ok">确定</button>
                    </div>
                </div>
            </div>
        </div>
        <%--应用列表弹窗--%>
        <div class="modal fade " id="myApp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content myApp clearfix" name="">
                    <div class="app_header">
                        <h4 class="name">授权使用<span class="change_font">【方正有猫字体】</span>（可多选）</h4>
                    </div>
                    <div class="app_body clearfix">
                        <ul class="app_box">
                            <%--<li class='app_items active'>--%>
                                <%--<dl>--%>
                                    <%--<dt class="info_head"><img src="/pro/img/icon.png" /></dt>--%>
                                    <%--<dd class="info_body">--%>
                                        <%--<h4 class="app_name">应用</h4>--%>
                                        <%--<span class="possess_font">5款字体</span>--%>
                                    <%--</dd>--%>
                                <%--</dl>--%>
                            <%--</li>--%>
                        </ul>
                    </div>
                    <div class="app_footer clearfix">
                        <button class="normal_btn cancle">取消</button>
                        <button class="normal_btn ok">保存</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="turn_to_top">
        <span id="top"></span>
        <%--<span id="question">问</span>--%>
    </div>
</header>
</body>
</html>

