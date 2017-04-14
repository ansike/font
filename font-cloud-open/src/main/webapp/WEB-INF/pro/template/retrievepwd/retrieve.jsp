<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/pro/template/common/header.jsp" %>
    <link rel="stylesheet" href="/pro/style/css/retrieve.css?$date">
</head>
<body>
<div class="wrapper">

    <div class="container">
        <div class="retrieve">
            <div class="title_box clearfix">
                <h4 class="title">找回密码</h4>
            </div>
            <div class="retrieve_content">
                <div class="retrieve_tab clearfix">
                    <p class="step active">
                        <span>验证身份</span>
                        <span class="number">1</span>
                    </p>
                    <p class="step">
                        <span>设置新密码</span>
                        <span class="number">2</span>
                    </p>
                </div>
                <ul class="form_body check_id clearfix">
                    <li class="register_li">
                        <span class="iptLabel">手机号</span>
                        <input type="text" class="registerIpt phone" data-type="retrieve"/>
                        <span class="error phone_number_empty" style="display: none">请输入手机号</span>
                        <span class="error phone_number_error" style="display: none">手机号格式错误</span>
                        <span class="error phone_number_notregistered" style="display: none">手机号未注册</span>
                        <span class="success" style="display: none"></span>

                    </li>
                    <li class="clearfix">
                        <p class="register_li pic">
                            <span class="iptLabel">验证码</span>
                            <input type="text" class="registerIpt verification pic_verification"/>
                            <span class="error pic_code_empty" style="display: none">请输入图片验证码</span>
                            <span class="error pic_code_error" style="display: none">图片验证码错误</span>
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
                        <input type="button" class="getCode getMsgCode" value="获取验证码" data-type="FORGOT_PASSWORD"
                               disabled="disabled"/>
                    </li>
                </ul>
                <ul class="form_body set_pwd clearfix" style="display: none;">
                    <li class="register_li">
                        <span class="iptLabel">密码</span>
                        <input type="password" name="password" class="registerIpt pwd" />
                        <span class="eye" onclick="switchPsw(this);"></span>
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
                        <span class="error repwd_empty" style="display: none">请确认密码</span>
                        <span class="error repwd_error" style="display: none">两次密码输入不一致</span>
                        <span class="success" style="display: none"></span>
                    </li>
                </ul>
                <div class="footer">
                    <div class="registerBtnBox">
                        <input type="button" class="nextBtn" value="下一步">
                        <input type="button" class="retrieve_complete" value="完成" style="display: none">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer class="footer"></footer>
</div>
</body>
<script src="/pro/js/public/public.js?$date"></script>
<script src="/pro/js/retrieve.js?$date"></script>
</html>

