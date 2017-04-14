
<html>
<head>
    <title>字体详情</title>
    <meta name="keywords" content="手迹云开放平台">
    <meta name="description" content="手迹云开放平台">
    <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1,width=device-width,user-scalable=no">
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="expires" content="0"/>
    <link rel="shortcut icon" href="html/img/favicon.png">

    <script type="text/javascript">
        var date = new Date().getTime();
        if (navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.split(";")[1].replace(/[ ]/g, "") == "MSIE6.0" || navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.split(";")[1].replace(/[ ]/g, "") == "MSIE7.0" || navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.split(";")[1].replace(/[ ]/g, "") == "MSIE8.0" || navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.split(";")[1].replace(/[ ]/g, "") == "MSIE9.0") {
            window.location.href = 'html/template/lowversion.html';
        }
        window.onload = function () {
            setRem();
        };
        function setRem() {
            var rem = document.documentElement.clientWidth / 19.2;
            document.getElementsByTagName('html')[0].style.fontSize = rem + 'px';
        }
        addEventListener('resize', function () {
            setRem();
        }, false);

    </script>
    <link rel="stylesheet" href="../public/css/header.css">
    <link rel="stylesheet" href="../../style/css/fontDetail.css">

</head>
<body>
<div class="wrapper">
    <header class="header-wrapper">
        <div class="header">
            <div class="logo" title="手迹云开放平台"></div>
            <ul class="nav">
                <a href="../index.jsp">
                    <li class="navChild ">首页</li>
                </a>
                <a href="index.jsp">
                    <li class="navChild checked">字体中心</li>
                </a>
                <a href="../resourceCenter/index.jsp">
                    <li class="navChild">资源中心</li>
                </a>
                <a href="../manageCenter/index.jsp">
                    <li class="navChild">管理中心</li>
                </a>
                <a href="../accountCenter/index.jsp">
                    <li class="navChild">账号中心</li>
                </a>
            </ul>
            <div class="loginAndRegister">
                <a class="login">登录</a>
                <b class="line">|</b>
                <a href="../register/index.jsp" class="register">注册</a>
            </div>
        </div>
    </header>
    <div class="container">
        <div class="detail">
            <h3 id="fontName">美玉体</h3>
            <span id="author" class="lineRight">作者名</span>
            <span id="price"  class="lineRight">免费</span>
            <span id="size" class="lineRight">3.54M</span>
            <span id="updateTime" class="lineRight">更新时间</span>
            <span id="version" class="lineRight">版本号</span>
            <span id="authorize" class="rightBtn">授权使用</span>
            <span id="download" class="rightBtn">下载</span>
        </div>
        <div class="fontLabelBox">
            <span class="rightBtn fontLabel">优美</span>
            <span class="rightBtn fontLabel">简洁</span>
            <span class="rightBtn fontLabel">手写体</span>
            <span class="rightBtn fontLabel">美玉</span>
        </div>
        <div class="imgshow"></div>
        <div class="fontIntroduce">
            <h4>字体简介：</h4>
            <p>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean euismod bibendum laoreet. Proin gravida dolor sit amet lacus accumsan et viverra justo commodo. Proin sodales pulvinar tempor. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nam fermentum, nulla luctus pharetra vulputate, felis tellus mollis orci, sed rhoncus sapien nunc eget.</p>
        </div>
        <div class="prospectus">
            <h4>字体样张：</h4>
            <div>

            </div>
        </div>

    </div>
    <footer class="footer"></footer>
</div>
</body>
</html>
