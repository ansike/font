<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/WEB-INF/pro/template/common/header.jsp" %>
    <link rel="stylesheet" href="/pro/style/css/application.css?$date">
    <script src="/pro/js/createApp.js?$date"></script>
</head>
<body>
<div class="wrapper">
    <div class="container">
        <div class="content_top line">
            <p class="top_index">
                <a href="/pro/template/manageCenter/application">管理中心</a>
                <span>创建应用</span>
            </p>
        </div>
       <div style="margin-top:30px;">
           <ul class="app_create" id="appCreate">
               <li class="clearfix">
                   <span class="form_left">应用名称：</span>
                   <div class="form_right">
                       <input type="text" class="form-control" id="appNameEdit" placeholder="支持汉字、数字、英文、下划线,最多10个字">
                       <span class="clear_input">×</span>
                       <p id="nameError" class="name_tip">应用名称仅限修改三次，将以应用名称生成字体展示图（大约5分钟）</p>
                   </div>
               </li>
               <li class="clearfix">
                   <span class="form_left">应用类型：</span>
                   <div class="form_right textarea_wrap">
                       <select class="form-control" id="selectApp">
                           <option value="null" selected>请选择应用类型</option>
                       </select>
                       <p id="selError" class="error_tip"></p>
                   </div>
               </li>
               <li class="clearfix">
                   <span class="form_left">ICON：</span>
                   <div class="form_right">
                       <div class="upload_box">
                           <span id="delImg" class="del_btn"></span>
                           <img id="iconPre" class="upload_pre" src="">
                           <div id="uploadBtns" class="upload_btns">
                               <form id="uploadForm">
                                   <input type="file" name="file" id="fileBtn" class="file_btn" onclick="this.form.reset();">
                                   <button  id="uploadBtn" class="upload_btn"></button>
                               </form>
                           </div>
                       </div>
                       <p class="img_tip">仅支持PNG及JPG，大小不超过300KB</p>
                       <p id="imgError" class="error_tip"></p>
                   </div>
               </li>
               <li>
                   <div class="form_right check_line">
                       <input type="checkbox" id="agree" class="check_box">
                       <label for="agree">我已阅读并同意</label>
                       <a href="/pro/template/resourceCenter/resource?id=item2" target="_blank" class="link_a">《手迹云API服务条款》</a>
                       <p class="error_tip" id="checkTip"></p>
                   </div>
               </li>
               <li>
                   <p class="form_right btn_group">
                       <button id="cancel"  class=" btn button btn_blank">取消</button>
                       <button  id="submit" class=" btn button btn_default">创建</button>
                   </p>
               </li>
           </ul>
       </div>
    </div>
</div>
</body>
<script src="/pro/js/public/public.js?$date"></script>
</html>
