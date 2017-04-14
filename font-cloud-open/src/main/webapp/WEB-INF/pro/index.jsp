<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <%@include file="/WEB-INF/pro/template/common/header.jsp" %>
    <link rel="stylesheet" href="/pro/style/css/index.css?$date">
</head>

<body>
<div class="wrapper">
    <div class="con1">
        <div class="bg">
            <%--<img src="/pro/img/bg.png"/>--%>
            <div class="bg_content">
                <%--<h1>正版免费字体服务提供商</h1>--%>
                <%--<ul>--%>
                <%--<li>一键接入，轻松提高应用体验</li>--%>
                <%--<li>为您免去字体法律纠纷烦恼</li>--%>
                <%--<li>全线上授权，免授权费，无最低分成门槛</li>--%>
                <%--</ul>--%>
                <a id="learn_more" href="/pro/template/resourceCenter/resource">了解更多</a>
            </div>

        </div>
        <div class="link_server clearfix">
            <%--<h2>稳定的服务</h2>--%>
            <%--<h4>手迹云开放平台提供个性化、正版字体下载及授权使用，以API形式向广大开发者免费开放</h4>--%>
            <div class="con_title">
                <img src="/pro/img/con1_title.png">
            </div>
            <div class="detail clearfix">
                <div class="detail_box to_font">
                    <dl>
                        <dt class="to_font_pic"></dt>
                        <dd>
                            方正手迹云提供字体在线授权及免费下载服务，帮助您丰富应用内容，使用户得到更好的体验。
                        </dd>
                    </dl>
                    <p class="check_detail"><a href="/font/fontCenter/fontcenter">查看详情</a></p>
                </div>
                <div class="detail_box to_api">
                    <dl>
                        <dt class="to_api_pic"></dt>
                        <dd>
                            手迹云API提供实时、安全及稳定的接口，一键接入，获取各类字体资源，打造个性化字体内容。
                        </dd>
                    </dl>
                    <p class="check_detail"><a href="/pro/template/resourceCenter/resource?id=item3">查看详情</a></p>
                </div>
            </div>
        </div>
    </div>
    <div class="novice">
        <%--<h2>专业的产品</h2>--%>
        <%--<h4>我们专注于满足移动互联网时代字体需求，为用户提供个性化的字体资源及服务</h4>--%>
        <div class="con_title">
            <img src="/pro/img/con2_title.png">
        </div>
        <div class="step_box clearfix">
            <dl class="step_child">
                <dt>
                    <%--<span href="javascript:;" class="step_num one"></span>--%>
                    <img src="/pro/img/icon-1.png">
                </dt>
                <dd>
                    <h5>成为开发者</h5>
                    <p>注册账号，成为手迹云开放平台开发者</p>
                    <div class="step">
                        <span class="step_en">step</span>
                        <span class="step_cn">1</span>
                    </div>
                </dd>
            </dl>
            <dl class="step_child">
                <dt>
                    <%--<span href="javascript:;" class="step_num two"></span>--%>
                    <img src="/pro/img/icon-2.png">
                </dt>
                <dd>
                    <h5>创建应用</h5>
                    <p>在平台创建专属应用账号</p>
                    <div class="step">
                        <span class="step_en">step</span>
                        <span class="step_cn">2</span>
                    </div>
                </dd>
            </dl>
            <dl class="step_child">
                <dt>
                    <%--<span href="javascript:;" class="step_num three"></span>--%>
                    <img src="/pro/img/icon-3.png">
                </dt>
                <dd>
                    <h5>接入使用</h5>
                    <p>了解API接入文档，完成集成开发</p>
                    <div class="step">
                        <span class="step_en">step</span>
                        <span class="step_cn">3</span>
                    </div>
                </dd>
            </dl>
        </div>

    </div>
    <div class="con2 clearfix">

        <div class="con2_child con2_1">
            <%--<h2>专业的产品</h2>--%>
            <%--<h4>我们专注于满足移动互联网时代字体需求，为用户提供个性化的字体资源及服务</h4>--%>
            <div class="con_title">
                <img src="/pro/img/con3_title.png">
            </div>
            <div class="section_box clearfix">
                <div class="section_child section_left">
                    <img class="solo_pic" src="/pro/img/index_1.png"/>
                </div>
                <div class="section_child section_right">
                    <%--<dl class="section_text">--%>
                    <%--<dt class="sec_title">手迹造字</dt>--%>
                    <%--<dd class="sec_content">--%>
                    <%--<p>只需书写775个字，即可免费拥有个人字体<br>--%>
                    <%--屏幕手写，拍照上传<br>--%>
                    <%--随时随地，修改方便<br>--%>
                    <%--快来创作属于自己的个性字体</p>--%>
                    <%--</dd>--%>
                    <%--<img class="sec_pic" src="/pro/img/QR_1.png"/>--%>
                    <%--</dl>--%>
                    <img class="QR_img fr" src="/pro/img/con2_1.png">
                </div>
            </div>
        </div>
        <div class="con2_child con2_2">
            <div class="section_box clearfix">
                <div class="section_child section_right">
                    <img class="solo_pic" src="/pro/img/index_2_new.png"/>
                </div>
                <div class="section_child section_left">
                    <%--<dl class="section_text">--%>
                    <%--<dt class="sec_title">手迹字体</dt>--%>
                    <%--<dd class="sec_content">--%>
                    <%--<p>为个人用户提供个性化和娱乐化移动端字体服务<br>--%>
                    <%--手机换字，任性换、随便换<br>--%>
                    <%--手机字体，玩到嗨、玩到爆、绝对酷炫</p>--%>
                    <%--</dd>--%>
                    <%--<img class="sec_pic" src="/pro/img/QR_2.png"/>--%>
                    <%--</dl>--%>
                    <img class="QR_img " src="/pro/img/con2_2.png">
                </div>
            </div>
        </div>
        <div class="con2_child con2_3">
            <div class="section_box clearfix">
                <div class="section_child section_left">
                    <img class="solo_pic" src="/pro/img/index_3.png"/>
                </div>
                <div class="section_child section_right">
                    <%--<dl class="section_text">--%>
                    <%--<dt class="sec_title">手迹秀&ensp;</dt>--%>
                    <%--<dd class="sec_content">--%>
                    <%--<p> 为个人用户提供丰富的字体使用场景<br>--%>
                    <%--丰富的玩法，最潮，最炫酷<br>--%>
                    <%--海量的字体，最全，最个性<br>--%>
                    <%--秀出你的个性字体</p>--%>
                    <%--</dd>--%>
                    <%--<img class="sec_pic" src="/pro/img/QR_3.png"/>--%>
                    <%--</dl>--%>
                    <img class="QR_img fr" src="/pro/img/con2_3.png">
                </div>
            </div>
        </div>
    </div>
    <footer class="footers">
        <div class="footer_content">
            <p class="footer_link">
                <a href="http://www.myfont.me/fzsj/show.html">关于我们&nbsp;&nbsp;</a>
                <a href="/pro/template/resourceCenter/resource">平台协议&nbsp;&nbsp;</a>
                <a href="/pro/template/resourceCenter/resource?id=item2">服务协议&nbsp;&nbsp;</a>
                <%--<a href="#">意见反馈</a>--%>
            </p>
            <p class="copyright">天津方正手迹数字技术有限公司&nbsp;&nbsp;

                版权所有&nbsp;&nbsp;

                津ICP备16009321号-1</p>
        </div>
    </footer>
</div>
</body>
<script src="/pro/js/public/public.js?$date"></script>
</html>
