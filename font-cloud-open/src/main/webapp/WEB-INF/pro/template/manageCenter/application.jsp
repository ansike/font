<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/WEB-INF/pro/template/common/header.jsp" %>
    <link rel="stylesheet" href="/pro/style/css/application.css?$date">
    <script src="/pro/js/application.js?$date"></script>
</head>
<body>

<div class="wrapper">
    <div class="container">
        <div class="content_top">
            <button id="addLink" class="btn button btn_default">添加应用</button>
            <span class="tool_tip" id="toolTip">还可创建10个应用</span>
        </div>
        <div class="divide"></div>
        <div style="margin-top:30px;" >
            <p id="nullMsg" class="null_msg" style="display:none">创建应用后即可使用免费字体</p>
            <div class="table-responsive" id="appList" style="display:none;">
                <table class="table table_app ">
                    <thead>
                        <tr>
                            <td>应用名称</td>
                            <td>ICON</td>
                            <td>APPKEY</td>
                            <td>&nbsp;&nbsp;操作&nbsp;&nbsp;</td>
                        </tr>
                    </thead>
                    <tbody id="tbody"></tbody>
                </table>
            </div>
        </div>

    </div>
</div>
</body>
<script src="/pro/js/public/public.js?$date"></script>
</html>
