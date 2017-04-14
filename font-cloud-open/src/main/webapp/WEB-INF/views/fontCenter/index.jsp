<html>
<head>
    <title>字体中心</title>
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
    <link rel="stylesheet" href="../../style/css/fontCenter.css">
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
                <a href="#">
                    <li class="navChild checked">字体中心</li>
                </a>
                <a href="../template/resourceCenter/index.jsp">
                    <li class="navChild">资源中心</li>
                </a>
                <a href="../template/manageCenter/index.jsp">
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
        <div class="searchFont clearfix">
            <div class="searchBox">
                <input type="text" class="searchIpt" placeholder="搜索关键字"/>
                <span class="searchBtn">搜索</span>
            </div>
            <select class="selection">
                <option>全部</option>
                <option>字体名</option>
                <option>作者名</option>
                <option>分类</option>
            </select>
        </div>
        <div class="hotLabel">
            <span class="fontLabelTitle">热门标签：</span>
            <span id="wechatFont" class="fontLabel">微信小程序</span>
            <span id="starFont" class="fontLabel">明星字体</span>
            <span id="handFont" class="fontLabel">手写体</span>
            <span id="artFont" class="fontLabel">艺术字</span>
        </div>
        <p class="hotOrNew">
            <span class="categories rightLine">最热</span>
            <span class="categories">最新</span>
        </p>
        <div class="fontListBox">
            <ul class="fontList clearfix">

                <li class="font">
                    <a href="fontDetail.jsp"><img src="../public/img/bg.jpg" class="fontImg"/></a>
                    <dl class="fontBox">
                        <dt class="fontTitle">
                            【灵犀体】
                        </dt>
                        <dd class="fontDetail">
                            <p class="fontInfo">
                                <span class="author">刘殿儒</span>
                                <span class="price">免费</span>
                                <span class="size">3.5M</span>
                                <span class="time">2016-09-24</span>
                            </p>
                            <p class="operation">
                                <span class="download">下载</span>
                                <span class="addCar">加入购物车</span>
                            </p>
                        </dd>
                    </dl>
                </li>
                <li class="font">
                    <a href=""><img src="../public/img/bg.jpg" class="fontImg"/></a>
                    <dl class="fontBox">
                        <dt class="fontTitle">
                            【灵犀体】
                        </dt>
                        <dd class="fontDetail">
                            <p class="fontInfo">
                                <span class="author">刘殿儒</span>
                                <span class="price">免费</span>
                                <span class="size">3.5M</span>
                                <span class="time">2016-09-24</span>
                            </p>
                            <p class="operation">
                                <span class="download">下载</span>
                                <span class="addCar">加入购物车</span>
                            </p>
                        </dd>
                    </dl>
                </li>
                <li class="font">
                    <a href=""><img src="../public/img/bg.jpg" class="fontImg"/></a>
                    <dl class="fontBox">
                        <dt class="fontTitle">
                            【灵犀体】
                        </dt>
                        <dd class="fontDetail">
                            <p class="fontInfo">
                                <span class="author">刘殿儒</span>
                                <span class="price">免费</span>
                                <span class="size">3.5M</span>
                                <span class="time">2016-09-24</span>
                            </p>
                            <p class="operation">
                                <span class="download">下载</span>
                                <span class="addCar">加入购物车</span>
                            </p>
                        </dd>
                    </dl>
                </li>
                <li class="font">
                    <a href=""><img src="../public/img/bg.jpg" class="fontImg"/></a>
                    <dl class="fontBox">
                        <dt class="fontTitle">
                            【灵犀体】
                        </dt>
                        <dd class="fontDetail">
                            <p class="fontInfo">
                                <span class="author">刘殿儒</span>
                                <span class="price">免费</span>
                                <span class="size">3.5M</span>
                                <span class="time">2016-09-24</span>
                            </p>
                            <p class="operation">
                                <span class="download">下载</span>
                                <span class="addCar">加入购物车</span>
                            </p>
                        </dd>
                    </dl>
                </li>
                <li class="font">
                    <a href=""><img src="../public/img/bg.jpg" class="fontImg"/></a>
                    <dl class="fontBox">
                        <dt class="fontTitle">
                            【灵犀体】
                        </dt>
                        <dd class="fontDetail">
                            <p class="fontInfo">
                                <span class="author">刘殿儒</span>
                                <span class="price">免费</span>
                                <span class="size">3.5M</span>
                                <span class="time">2016-09-24</span>
                            </p>
                            <p class="operation">
                                <span class="download">下载</span>
                                <span class="addCar">加入购物车</span>
                            </p>
                        </dd>
                    </dl>
                </li>


            </ul>
            <div class="pagination">
                <span class="left circle"><</span>
                <p class="pageNumBox">
                    <span class="pageNum circle">1</span>
                    <span class="pageNum circle">2</span>
                    <span class="pageNum circle">3</span>
                    <span class="pageNum circle">4</span>
                </p>
                <span class="right circle">></span>
                <span>共11页&nbsp;跳转到：</span>
                <input type="text" class="pageIpt circle" placeholder="1"></input>
                <button id="turnToPage" class="circle">GO</button>
            </div>
        </div>
    </div>
    <footer class="footer"></footer>
</div>
</body>
<script src="../plugins/js/jquery-1.10.2.min.js"></script>
<script src="../../js/fontcenter.js"></script>
</html>
