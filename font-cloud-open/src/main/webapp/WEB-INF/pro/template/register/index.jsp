<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>注册</title>
    <%@include file="/WEB-INF/pro/template/common/header.jsp" %>
    <link rel="stylesheet" href="/pro/style/css/register.css?$date">
</head>
<body>
<div class="wrapper">
    <div class="container">
        <div class="register">
            <div class="title_box clearfix">
                <h4 class="title">欢迎注册</h4>
                <p class="loginText">已有账号，直接<a href="#" class="relogin">登录</a></p>
            </div>
            <dl class="content clearfix">
                <dt class="register_pic ">
                    <img src="/pro/img/register2.png" style="width: 100%"/>
                </dt>
                <dd class="register_content">
                    <ul class="form_body clearfix">
                        <li class="register_li">
                            <span class="iptLabel" >手机号</span>
                            <input type="text" class="registerIpt phone register_phone" data-type="register"  />
                            <%--<span class="phone_clear" >&times;</span>--%>
                            <span class="error phone_number_empty" style="display: none">请输入手机号</span>
                            <span class="error phone_number_error" style="display: none">手机号格式错误</span>
                            <span class="error phone_number_registered" style="display: none">手机号已注册</span>
                            <span class="success" style="display: none"></span>
                        </li>
                        <li class="clearfix">
                            <p class="register_li pic">
                                <span class="iptLabel">验证码</span>
                                <input type="text" class="registerIpt verification pic_verification"  />
                                <span class="error pic_code_empty" style="display: none">请输入图片验证码</span>
                                <span class="error pic_code_error" style="display: none">图片验证码错误</span>
                            </p>
                            <img src="/user/getRandomCode"  class="getCode picCode"  />
                            <span class="success" style="display: none"></span>
                        </li>
                        <li class="clearfix ">
                            <p class="register_li msg">
                                <span class="iptLabel">短信验证码</span>
                                <input type="text" class="registerIpt verification msg_verification" />
                                <span class="error msg_code_empty" style="display: none">请输入手机验证码</span>
                                <span class="error msg_code_error" style="display: none">手机验证码输入错误</span>
                            </p>
                            <input type="button" class="getCode getMsgCode" value="获取验证码" data-type="REGISTER_ACCOUNT" disabled="disabled"/>
                            <span class="success" style="display: none"></span>
                        </li>
                        <li class="register_li">
                            <span class="iptLabel">密码</span>
                            <input type="password" name="password" class="registerIpt pwd" />
                            <span class="eye"  onclick="switchPsw(this);"></span>
                            <span class="error pwd_empty" style="display: none">请设置密码</span>
                            <span class="error pwd_allnumber" style="display: none">密码不能全为数字</span>
                            <span class="error pwd_allcharacter" style="display: none">密码不能全为英文</span>
                            <span class="error pwd_allsymbol" style="display: none">密码不能全为符号</span>
                            <span class="error pwd_length" style="display: none">密码应为6-20位字符</span>
                            <span class="error pwd_nbsp" style="display: none">密码中不能有空格</span>
                            <span class="success" style="display: none"></span>
                        </li>
                        <li class="register_li">
                            <span class="iptLabel">确认密码</span>
                            <input type="password" name="password" class="registerIpt rePwd"/>
                            <span class="eye" onclick="switchPsw(this);"></span>
                            <span class="error repwd_empty" style="display: none">确认密码不能为空</span>
                            <span class="error repwd_error" style="display: none">两次密码输入不一致</span>
                            <span class="success" style="display: none"></span>
                        </li>
                        <li class="agreementBox">
                            <input type="checkbox" id="agreement" class="check_box" checked/>
                            <label for="agreement">我已阅读并同意
                                <a href="/pro/template/resourceCenter/resource">《手迹云开放平台协议》</a> 的全部内容
                            </label>
                            <span class="error agreement_error" style="display: none">请同意手迹云注册协议</span>
                        </li>
                        <li class="registerBtnBox">
                            <input type="button" class="registerBtn" value="立即注册">
                        </li>
                    </ul>
                </dd>
            </dl>
        </div>
    </div>
    <footer class="footer"></footer>
</div>
</body>
<script src="/pro/js/public/public.js?$date"></script>
<script src="/pro/js/register.js?$date"></script>

</html>
