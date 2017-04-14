<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/WEB-INF/pro/template/common/header.jsp" %>
    <link rel="stylesheet" href="/pro/style/css/application.css?$date">
    <script src="/pro/js/applicationDetail.js?$date"></script>
</head>
<body>
    <div class="wrapper" >
        <input id="appKeyVal" type="hidden" value="${param.appKey}">
        <input id="appType" type="hidden" value="">
        <input id="appId" type="hidden" value="">
        <div class="container box_wrap" style="position: relative;z-index:0">
            <div class="content_top line">
                <p class="top_index">
                    <a href="/pro/template/manageCenter/application">管理中心</a>
                    <span>应用详情</span>
                </p>
            </div>
            <!--应用信息-->
            <div style="padding:50px 0;border-bottom:1px solid #e8e8e8;">
                <div class="app_info clearfix" id="appInfo">
                    <div class="icon_box app_left">
                        <img id="icon" src="" alt="应用ICON">
                    </div>
                    <div class="app_right">
                        <button class="btn button btn_warn" data-toggle="modal" data-target="#delAppDialog">删除应用</button>
                        <button class="btn button btn_blank" onclick="linkEdit();" style="margin-right:30px;">修改</button>
                    </div>
                    <div class=" app_center">
                        <p id="appName" style="color:#494949;font-size:16px;"></p>
                        <p id="appTypeValue" style="color:#bababa;font-size:14px;"></p>
                        <ul>
                            <li>
                                <span>AppKey：</span><span class="app_key"></span>
                            </li>
                            <li>
                                <span>AppSecret：</span><span class="app_secret"></span>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="app_info clearfix" id="appEdit" style="display:none;">
                    <div class="icon_box app_left">
                        <span id="delImg" class="del_btn"></span>
                        <img id="iconPre" class="icon" src="" alt="icon" >
                        <div id="uploadBtns" class="upload_btns" style="display:none;">
                            <form id="uploadForm">
                                <input type="file" name="file" id="fileBtn" class="file_btn" onclick="this.form.reset();">
                            </form>
                            <button id="uploadBtn" class="upload_btn"><span>+</span><br>点击上传</button>
                        </div>
                        <p id="imgError" class="error_tip" ></p>
                    </div>
                    <div class="app_center">
                        <div class="form-group input">
                            <input id="appNameEdit" type="text" class="form-control">
                            <span class="clear_input">×</span>
                            <p id="nameError" class="error_tip"></p>
                        </div>
                        <div class="form-group input">
                            <select class="form-control" id="appTypeEdit"></select>
                        </div>
                        <ul style="margin-top:-5px;">
                            <li><span>AppKey：</span><span class="app_key"></span></li>
                            <li><span>AppSecret：</span><span class="app_secret"></span></li>
                        </ul>
                        <div class="btns">
                            <button class="btn button btn_blank" onclick="linkCancel();">取消</button>
                            <button id="updateApp" class="btn button btn_default" style="margin-left:100px;">保存</button>
                        </div>
                    </div>
                </div>
            </div>
            <!--字体列表-->
            <div>
                <div class="authorized_font">
                    <p class="authorized_top clearfix">
                        <span id="fontTitle">应用字体</span>
                    </p>
                    <div class="table_font">
                        <div style="height:50px;">
                            <ul id="table_top" class="row table_top">
                               <%-- <li class="col-sm-2">
                                    <input type="checkbox" class="check_all" id="checkAll">
                                    <label for="checkAll">全选</label>
                                </li>--%>
                                <li class="col-sm-3">字体名称</li>
                                <li class="col-sm-3">字体示例图</li>
                                <li class="col-sm-3">授权时间</li>
                                <li class="col-sm-3">状态</li>
                            </ul>
                        </div>
                        <div id="fontList" class="table_main"></div>
                    </div>
                    <div style="height: 100px;">
                        <div class="authorized_bottom" id="authorizedBottom">
                            <a class="btn button btn_default" id="authAll" onclick="authorAll()" style="display:none;">一键授权</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--删除应用-->
        <div class="modal fade " id="delAppDialog" tabindex="-1" role="dialog"
             aria-hidden="true">
            <div class="modal-dialog" style="width:0;">
                <div class=" normal_alert">
                    <p class="normal_header" style="background:#DF4D47 url('/pro/img/delete.png') no-repeat 20px center;background-size:20px 20px">
                        您确定要删除应用吗？
                    </p>
                    <p class="normal_body" style="text-align:center;">删除后应用将不能获得手迹云服务！</p>
                    <p class="normal_footer clearfix">
                        <button class="normal_btn cancle" data-dismiss="modal">取消</button>
                        <button class="normal_btn ok" id="delApp" onclick="confirmDel();">确定</button>
                    </p>
                </div>
            </div>
        </div>
        <%--图片放大--%>
        <div id="largeImgBox" class="enlarge_box" >
            <div class="img_wrap">
                <span class="close_img">×</span>
                <img id="largeImg" src="">
            </div>
        </div>
    </div>
</body>
<script src="/pro/js/public/public.js?$date"></script>
</html>
