<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <%@include file="/WEB-INF/pro/template/common/header.jsp" %>
    <link rel="stylesheet" href="/pro/style/css/fontCenter.css?$date">
    <script type="text/javascript">
        var pages =${page.pages==null?0:page.pages};
    </script>
</head>
<body>
<div class="wrapper">
    <div class="container">
        <div class="searchFont clearfix">
            <div class="searchBox">
                <input type="text" class="search_ipt" id="search_ipt"
                       value="<%=requestMap.get("searchValue")==null?"":requestMap.get("searchValue") %>"
                       placeholder="搜索你喜欢的"/>
                <span class="searchBtn"></span>
                <ul class="ul_res search_result" style="display: none;">
                    <dl class="result_box font_name">
                        <dt class="dl_title">字体名</dt>
                        <dd class="dl_body">
                            <p class="res_items"><span class="ipted">方正</span>流行体</p>
                        </dd>
                    </dl>
                    <dl class="result_box author_name">
                        <dt class="dl_title">作者名</dt>
                        <dd class="dl_body">
                            <p class="res_items"><span class="ipted">方正</span>四</p>
                        </dd>
                    </dl>
                </ul>
                <ul class="ul_res localhistory" style="display: none">
                </ul>
            </div>
        </div>
        <%--<div class="hotLabel">--%>
            <%--<span class="fontLabelTitle">热门标签：</span>--%>
            <%--<c:forEach items="${tags}" var="tag">--%>
                <%--<a class="fontLabel ${tag.tagValue=='微信小程序'?'new':'' }" data-value="${tag.tagValue }"--%>
                   <%--style="text-decoration: none">--%>
                        <%--${tag.tagValue }--%>
                <%--</a>--%>
            <%--</c:forEach>--%>
        <%--</div>--%>
        <div class="label_hot clearfix">
            <ul class="font_label_box">
                <c:choose>
                    <c:when test="${fontcenter_tagValue==null }">
                        全部字体
                    </c:when>
                    <c:otherwise>
                        <c:forTokens items="${fontcenter_tagValue }" delims="," var="value">
                            <span onclick="removeLabel(this)" class="fontLabel checked"
                                  data-value="${value }">${value }</span>
                        </c:forTokens>
                    </c:otherwise>
                </c:choose>
            </ul>
            <p class="hot_New">
                <% if ("hot".equals(requestMap.get("order"))) { %>
                <span class="categories hot active">最热</span>
                <a href="/font/fontCenter/fontcenter?<%=requestParams.indexOf("order")>-1?requestParams.replaceAll("order=(hot|new)[&]", ""):requestParams %>order=new">
                    <span class="categories new">最新</span>
                </a>
                <% } else if ("new".equals(requestMap.get("order"))) { %>
                <a href="/font/fontCenter/fontcenter?<%=requestParams.indexOf("order")>-1?requestParams.replaceAll("order=(hot|new)[&]", ""):requestParams %>order=hot">
                    <span class="categories hot">最热</span>
                </a>
                <span class="categories new active">最新</span>
                <% } else { %>
                <span class="categories hot active">最热</span>
                <a href="/font/fontCenter/fontcenter?order=new">
                    <span class="categories new">最新</span>
                </a>
                <%} %>
            </p>
        </div>

        <div class="fontListBox">
            <ul class="fontList ">
                <c:if test="${fn:length(page.list)>0 }">
                <c:forEach items="${page.list}" var="font">
                    <li class="font">
                        <div>
                            <div style="cursor: pointer;" onclick="goPage('fontDetail?fontId=${font.fontId }')"><img src="${font.picUrl }" class="fontImg"/></div>
                            <dl class="fontBox">
                                <dt class="fontTitle">【${font.name }】</dt>
                                <dd class="fontDetail">
                                    <p class="fontInfo">
                                        <span class="author">${font.author==null?"佚名":font.author }</span>
                                        <span class="price"> </span>
                                        <span class="size">
										<fmt:formatNumber type="number" value="${font.fontSize/1024/1024 }"
                                                          pattern="0.00" maxFractionDigits="2"/>M</span>
                                        <span class="time"><fmt:formatDate type="date"
                                                                           value="${font.createTime}"/></span>
                                    </p>
                                </dd>
                            </dl>
                        </div>
                    </li>
                </c:forEach>
            </ul>
            <div class="font_pagination">
                <span class="previous circle"></span>
                <p class="pageNumBox">
                    <c:forEach var="i" begin="1" end="${page.pages }">
                        <c:if test="${i == page.pageNum}">
                            <span class="pageNum circle active">${i }</span>
                        </c:if>
                        <c:if test="${i != page.pageNum}">
                            <a href="/font/fontCenter/fontcenter?<%=requestParams.indexOf("pageNum")>-1?requestParams.replaceAll("pageNum=[0-9]+[&]", ""):requestParams %>pageNum=${i }"><span
                                    class="pageNum circle">${i }</span></a>
                        </c:if>
                    </c:forEach>
                </p>
                <span class="next circle"></span> <span>共${page.pages }页&nbsp;跳转到：</span>
                <input type="text" class="pageIpt circle"/>
                <button id="turn_page" class="circle">GO</button>
            </div>
            </c:if>
            <c:if test="${fn:length(page.list)==0 }">
                <div class="fontfind">
                    <span>抱歉，暂未找到相关搜索字体，您可以尝试其它搜索条件！</span>
                </div>
            </c:if>
        </div>
    </div>
    <footer class="footer"></footer>
</div>
</body>
<script src="/pro/js/public/public.js?$date"></script>
<script src="/pro/js/fontcenter.js?$date"></script>
</html>
