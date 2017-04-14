<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<%@include file="/WEB-INF/pro/template/common/header.jsp"%>
<link rel="stylesheet" href="/pro/style/css/fontDetail.css?$date">
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<div class="detail">
				<div class="fl  ">
					<h3 id="fontName">${detail.name }</h3>
					<span id="author" class="detail_line">${detail.author }</span> <span id="size" class="detail_line"> <fmt:formatNumber type="number" value="${detail.fontSize/1024/1024 }" pattern="0.00" maxFractionDigits="2" />M
					</span> <span id="updateTime"> <fmt:formatDate type="date" value="${detail.createTime}" />
					</span>
				</div>

				<%--<c:choose>--%>
					<%--<c:when test="${fn:length(authzFonts)== 1 && appSize==1}">--%>
						<%--<span id="authorize" class="rightBtn interaction withDraw"> 取消授权 </span>--%>
					<%--</c:when>--%>
					<%--<c:when test="${fn:length(authzFonts)>1}">--%>
						<%--<span id="authorize" class="rightBtn interaction"> 修改授权</span>--%>
					<%--</c:when>--%>
					<%--<c:when test="${fn:length(authzFonts)==1 && appSize>1}">--%>
						<%--<span id="authorize" class="rightBtn interaction"> 修改授权</span>--%>
					<%--</c:when>--%>
					<%--<c:otherwise>--%>
						<%--<span id="authorize" class="rightBtn interaction"> 授权使用</span>--%>
					<%--</c:otherwise>--%>
				<%--</c:choose>--%>
				<span id="download" onclick="download()" class="rightBtn interaction">下载</span>
			</div>
			<div class="imgshow_box clearfix">
				<img class="showImg1 box_sha" src="${detail.picUrl }" />
				<%--<div class="showImg_bg"></div>--%>
			</div>
			<div class="fontIntroduce">
				<h4 class="detail_title">字体简介：</h4>
				<p class="introduce_text">${detail.introduction }</p>
			</div>
			<div class="prospectus">
				<h4 class="detail_title">字体样张：</h4>
				<div class="pic_box clearfix">
					<c:forEach items="${pics }" var="pic" varStatus="status">
						<c:if test="${status.index==0 }">
							<div class="top_left_pic">
								<img class="box_sha" src="${pic.picUrl }">
							</div>
						</c:if>
						<c:if test="${status.index==1 }">
							<div class="top_right_pic box_sha">
								<img src="${pic.picUrl }">
						</c:if>
						<c:if test="${status.index==2 }">
								<img src="${pic.picUrl }">
							</div>
						</c:if>
					</c:forEach>
					<div class="bg_blue bg1"></div>
					<div class="bg_blue bg2"></div>
					<div class="bg_blue bg3"></div>
				</div>
			</div>
		</div>
		<footer class="footer"></footer>
	</div>
</body>
<script>
	var status = "${fn:length(authzFonts)}";
	//    alert(status);
	var fontName = "${detail.name }";
</script>
<script src="/pro/js/public/public.js?$date"></script>
<script src="/pro/js/fontDetail.js?$date"></script>
</html>
