<!DOCTYPE html>
<html lang="en" >

<head>
    <meta charset="UTF-8">
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
    <link rel="stylesheet" href="../../style/css/feedback.css">
    <link rel="stylesheet" href="../plugins/css/xcConfirm.css">
    <script src="../plugins/js/jquery-1.10.2.min.js"></script>
    <script src="../plugins/js/xcConfirm.js"></script>

</head>

<body>
<div class="wrapper">
    <header class="header-wrapper">
        <div class="header">
            <div class="logo" title="手迹云开放平台"></div>
            <ul class="nav">
                <a href="../index.jsp">
                    <li class="navChild checked">首页</li>
                </a>
                <a href="fontCenter/index.jsp">
                    <li class="navChild">字体中心</li>
                </a>
                <a href="resourceCenter/index.jsp">
                    <li class="navChild">资源中心</li>
                </a>
                <a href="manageCenter/index.jsp">
                    <li class="navChild">管理中心</li>
                </a>
                <a href="accountCenter/index.jsp">
                    <li class="navChild">账号中心</li>
                </a>
            </ul>
            <div class="loginAndRegister">
                <a class="login">登录</a>
                <b class="line">|</b>
                <a href="register/" class="register">注册</a>
            </div>
        </div>
    </header>
    <div class="container">
        <div class="feedback-main">
            <p class="feedback-nav">问题反馈</p>
            <div class="feedback-container  clearfix">
                <h2 class="title">对您给予的帮助和支持，表示感谢！</h2>
                <div class="tab-container">
                    <div class="tab-content clearfix ">
                        <div class="form-box">
                            <div class="form-section">
                                <div class="xm-select">
                                    <div class="dropdown require">
                                        <select class="form-control">
                                            <option value="">问题类型</option>
                                        </select>
                                        <span ng-show="verifyConfig.problemCategory" class="error_tips">请选择问题类型</span>
                                        <a class="icon"></a>
                                    </div>
                                </div>
                            </div>
                            <div class="form-section">
                                <div class="text-arearight require">
                                <textarea class="form-control input-text input-textarea " ng-change="textareaChange()" ng-keyup="textareaChange()" ng-keydown="textareaChange()"
                                          ng-model="question" name="feedbackQuestion"
                                          placeholder="在这里描述您遇到的问题，须大于10小于200个汉字"
                                          ng-focus="verify.questionConFocus()"
                                          ng-blur="verify.questionConBlur()"></textarea>
                                    <label class="input-label checkFontNum">您还可以输入<span>{{surplusFontsNum}}</span>个字</label>
                                </div>
                                <span ng-show="verifyConfig.fontNumErr" class="error_tips">请输入大于10个汉字</span>
                            </div>
                            <div class="form-section addUrl">
                                <input class="form-control input-text input-url" type="text" ng-model="feedbackQuestionUrl"
                                       name="feedbackQuestionUrl" placeholder="页面链接（选填）"/>
                            </div>
                            <div class="upload-image">
                                <p class="showTxt">仅支持 .jpg .png .jpeg 格式的图片</p>
                                <ul big-img>
                                    <li ng-repeat="imgSrc1 in imgArr">
                                        <img ng-src={{imgSrc1.pic}} ng-click="lg=!lg" style="background: url(/html/img/waiting2.gif) no-repeat center center; border:0;outline: none;" />
                                        <a class="deleteImg" ng-click="delImg($index)"></a>
                                        <%--<div ng-show="lg">--%>
                                            <%--<div class="showBigImgbox">--%>
                                                <%--<div class="bigImgPar">--%>
                                                    <%--<img class="bigImg" ng-src="{{imgSrc1.pic}}" alt="">--%>
                                                    <%--<span class="bigImgClose" ng-click="lg=!lg">X</span>--%>
                                                <%--</div>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                    </li>
                                    <form class="chose-image" ng-show="verifyConfig.choseImg">
                                        <!--<a href="" ng-click="angular.element(this).scope().uploadPic()">选择图片</a>-->
                                        <button name="button" type="button" id="btn1" class="upimg cleanBoxSahow"
                                                onclick="img.click();"
                                                value="添加图片" >
                                        </button>
                                        <input ng-show="style" type="file" name="pic" id="img" onclick="this.form.reset();" ng-model="appInfo.appLogo"
                                               onchange="angular.element(this).scope().uploadPic()" style="display:none">
                                    </form>
                                </ul>

                            </div>
                            <div class="checkCode form-group input pr">
                                <div class="input_txt require">
                                    <input type="text" name="imgCode" class="form-control verify_input"
                                           ng-blur="verify.imgCodeBlur()" ng-focus="verify.imgCodeFocus()"
                                           ng-model="randomCode" placeholder="验证码" required>
                                    <p class="code_img" style="border:none;">
                                        <img id="Imgs" class="imgs" ng-click="changeCodeImg()" style="width: 100%; height: 100%;"
                                             ng-src="{{checkImgSrc}}" alt="">
                                    </p>
                                </div>
                                <div class="tip feedback_tip">
                                    <span ng-show="verifyConfig.imgCodeEmpty" class="error_tips ">请输入验证码</span>
                                    <span class="warn_color error_tips"
                                          ng-show="verifyConfig.imgCodeError">验证码错误,重新输入！</span>
                                    <span class="pass_color" ng-show="verifyConfig.imgCodeValid"><img
                                            src="/html/img/right.png"></span>
                                </div>
                            </div>
                            <a class="btn btn-primary" id="J_submitQuestion" ng-click="save()">提交问题</a>
                        </div>
                    </div>
                </div>
                <div class="feedback-list">
                    <h3 class="title2">常见问题</h3>
                    <ul class="list " >
                        <li >
                            <dl>
                                <dt>{{fqa.title}}</dt>
                                <dd>{{fqa.content}}</dd>
                            </dl>
                        </li>
                    </ul>
                    <!--<p class="add" ng-click="changeClassName1()" ng-show="verifyConfig.addShow">展开</p>-->
                    <!--<p class="add" ng-click="changeClassName2()" ng-show="verifyConfig.addGet">收起</p>-->
                </div>
            </div>
        </div>
    </div>

</div>
</body>
<script src="../public/js/public.js"></script>
<script src="../../js/feedback.js"></script>
</html>
