<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/WEB-INF/pro/template/common/header.jsp" %>
    <link rel="stylesheet" href="/pro/style/css/account.css?$date">
    <link rel="stylesheet" href="/pro/plugins/emailAuto/emailAutoComplete.css?$date">
    <script src="/pro/js/account.js?$date"></script>
    <script src="/pro/plugins/emailAuto/emailAutoComplete.js?$date"></script>
</head>
<body>
<div class="wrapper">
    <div class="container" style="padding-bottom:40px;">
        <input id="userId" type="hidden" value="">
        <div  class="account">
            <p class="account_title clearfix">
                <span>账号信息</span>
                <button id="updateLink" class="btn button pull-right btn_right btn_blank" onclick="editUserInfo()">修改</button>
            </p>
            <p class="divide"></p>
            <div class="account_info">
                <ul class="info_dis" id="accountDis"  >
                    <li class="clearfix">
                        <span class="left">手机号：</span>
                        <p class="right mobile"></p>
                    </li>
                    <li class="clearfix">
                        <span class="left">邮箱：</span>
                        <p class="right email"></p>
                    </li>
                    <li class="clearfix">
                        <span class="left">联系地址：</span>
                        <p class="right address"></p>
                    </li>
                </ul>
                <ul class="info_update" id="infoUpdate" style="display:none;">
                    <li class="clearfix">
                        <span class="left" style="line-height:22px;">手机号：</span>
                        <p id="mobile" class="right mobile"></p>
                    </li>
                    <li class="clearfix">
                        <span class="left">邮箱：</span>
                        <div class="right parentCls">
                            <input id="email" type="text" maxlength="64"  class="form-control inputElem">
                            <p id="emailTip" class="error_tip"></p>
                        </div>
                    </li>
                    <li class="clearfix">
                        <span class="left">联系地址：</span>
                        <p class="right textarea_wrap">
                            <textarea id="address" class="form-control" maxLength="100" rows="3" name="textarea" ></textarea>
                            <span id="textareaTip">联系地址不能超过100个字</span>
                        </p>
                    </li>
                    <li>
                        <p class="right btn_group">
                            <button  class=" btn button btn_blank" onclick="showUserInfo();">取消</button>
                            <button id="updateUser" class=" btn button btn_default">保存</button>
                        </p>
                    </li>
                </ul>
            </div>
        </div>
        <div id="resetPsw" class="reset_psw">
            <p class="account_title clearfix" style="border-bottom:1px solid #68BFFC;">
                <span>账号安全</span>
            </p>
            <div id="resetTip" class="reset_tip clearfix">
                <span>密码管理</span>由于互联网存在盗号风险，请定期更换您的密码，并且不要和其他账号共用同一个密码
                <button onclick="toResetPsw()"  class="btn button pull-right btn_right btn_blank">修改密码</button>
            </div>
            <div id="pswStep" class="psw_step" style="display:none;">
                <div id="step1" class="tab-pane">
                    <p class="user_mobile">手机号: <span class="mobile"></span></p>
                    <div class="input clearfix">
                        <span class="input_left">图片验证码</span>
                        <input type="text" class="form-control code_text" id="imgCode">
                        <img id="codeImg" class="img_code"  src="">
                        <p class="error_tip" id="imgTip"></p>
                    </div>
                    <div class="input clearfix">
                        <span class="input_left">验证码</span>
                        <input type="text" class="form-control code_text" id="smsCode">
                        <input id="getSmsCode"  type="button" class="btn code_btn" value="获取验证码">
                        <p class="error_tip" id="smsTip"></p>
                    </div>
                    <div class="next_btns">
                        <button onclick="cancelReset();"  class=" btn button btn_blank">取消</button>
                        <button id="nextPsw" class=" btn button btn_default">下一步</button>
                    </div>
                </div>
                <div id="step2" class="tab-pane" style="display:none;">
                    <div class="input clearfix">
                        <span class="input_left">新密码</span>
                        <input type="password" name="password" class="form-control" id="psw" placeholder="6-20位英文、符号或数字的组合，区分大小写">
                        <span class="eye" onclick="switchPsw(this);"></span>
                        <p id="pswTip" class="error_tip"></p>
                    </div>
                    <div class="input clearfix">
                        <span class="input_left">确认密码</span>
                        <input type="password" name="password" class="form-control" id="confirmPsw">
                        <span class="eye" onclick="switchPsw(this);"></span>
                        <p id="confirmTip" class="error_tip"></p>
                    </div>
                    <div class="next_btns">
                        <button onclick="cancelReset();" class=" btn button btn_blank">取消</button>
                        <button id="updatePsw" class=" btn button btn_default">保存</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="/pro/js/public/public.js?$date"></script>

</html>
