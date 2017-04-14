<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/WEB-INF/pro/template/common/header.jsp" %>
    <link rel="stylesheet" href="/pro/style/css/resource.css?$date">
    <script>
        $(function(){
            var id=$("#index").val();
            if (id) {
                $('.nav-tabs a[href=#'+id+']').tab('show');
            }
            $("#wrapper").fadeIn(10);
        })
    </script>
</head>
<body>
<input type="hidden" id="index" value="${param.id}">
<div id="wrapper" class="wrapper" style="display:none;">
    <div class="container">
        <div class=" headline">
            <p class=" headline_font">开发文档</p>
        </div>
        <div class="divide"></div>
        <div class="row">
            <ul class="sidebar nav nav-tabs col-md-3">
                <li class="active">
                    <a href="#item1" data-toggle="tab">
                        1 手迹云开放平台协议
                    </a>
                </li>
                <li><a href="#item2" data-toggle="tab">2 手迹云授权协议</a></li>
                <li><a href="#item3" data-toggle="tab">3 手迹云API</a></li>
                <li><a href="#item4" data-toggle="tab">4 手迹云H5</a></li>
            </ul>
            <div  class="tab-content resource_main col-md-9">
                <div class="tab-pane fade in active agreement" id="item1">
                    <p class="t_title">手迹云开放平台协议</p>
                    <h4>一、定义</h4>
                    <p>欢迎您与我方共同签署本《手迹云开放平台服务协议》（下称“本协议”）并使用手迹云开放平台服务！
                        在使用手迹云开放平台各项服务之前，请您务必仔细阅读并透彻理解本协议，如果您不同意本协议其中任何内容，您应立即停止使用手迹云开放平台服务。
                        当您同意本协议并且按照注册页面提示填写信息完成全部注册步骤后，即表示您已充分阅读、理解并接受本协议的全部内容，并与我方达成一致，成为手迹云开放平台”用户”。

                        “账户”是指您访问我方服务时所创建的账户。
                        “服务”是指我方手迹云开放平台。
                        “用户内容”是指您在服务上保存、通过服务上传或传输的所有数据。这包括您的个人资料，图片或软件中的书写内容等内容。
                    </p>
                    <h4>二、条款的变更</h4>
                    <p>
                        我方保留经自行决定随时更改、修改、增加或删除本条款的权利，如需作此变更，我方将在我方的网站上或在服务内发布修订后的条款。除我方另有说明外，所有变更自发布之时起生效。如果您在变更发布后继续使用服务，则表示您同意该变更。</p>
                    <h4>三、账户信息和安全性</h4>
                    <p>
                        为了使用我方的服务，我方可要求您创建一个账户与一个密码并向我方提供特定的个人信息。您同意向我方提供您完整、准确、真实的最新信息，尤其是您的手机号。您应承担起维护个人账户安全的责任。请勿将您的账户详情与他人分享或允许其他人访问或使用您的账户。您应对您的账户活动（无论是否由您授权）承担全部责任。如您发现或怀疑您的账户或账户密码存在丢失、被盗、涉及欺诈或有人未经授权的情况，请立即向我方报告。</p>
                    <h4>四、隐私</h4>
                    <p>除用户登陆账号之必要保密信息外，其余用户内容将在我方服务中呈现公开状态，所有用户及我方都有权浏览，评论这些内容。
                        如果您不同意以上内容，请您务必停止使用我们的服务。
                    </p>
                    <h4>五、使用我们的服务</h4>
                    <p>我们服务的使用者：我们很高兴您能够选择我们的服务，但是我们对服务的使用者有一定的限制。
                        如果有下列情形之一的，则您不得使用我们的服务：
                        您无法与我方签署具有约束力的合同；
                        您曾被禁止参与任何我方服务。
                        如果您属于未成年人（16周岁以下）、限制行为能力人或无行为能力人，则您需在您的法定监护人已审阅并同意了本条款后方可注册，若已注册我方默认为您已征得法定监护人的同意。</p>
                    <h4>六、字体版权</h4>
                    <p>
                        如果您从我们的服务中下载并使用了字体，在未授权的情况下，该字体不得用于商业用途，您不得涂改、删除或以任何方法毁损字体上之著作权与商标权等标示。您同意不以增添任何功能为目的而改变字体初始设定，且不得进行还原工程、反编辑、拆解锁定，以其他任何方式试图发现、改编、修改、转变、转换或更改字体档案和字体资料原始码，或为其他足以影响字体权益之行为。不得通过任何手段影响手迹云平台权益的行为。

                        服务更新及限制：
                        由于服务不断推陈出新，我们可能会要求您接受对服务以及条款做出的所有更新。

                        我方有权随时暂停或永久终止提供和（或）支持服务、特定服务的部分内容，在暂停或终止之时，您使用服务或服务中任何部分的许可证将自动暂停或终止。

                        我方可自行决定，限制、暂停、终止、修改或删除账户或对服务或服务中部分内容的访问，禁止用户访问我们的软件和网站及其相关内容、服务和工具，延迟发布或者移除所托管的内容，且我方对由此给您造成的任何损失或后果不承担赔偿责任。

                        删除您的账户：
                        您可以随时停止使用服务，也可随时要求我们停止主动使用您的数据。除非您所在地的当地法律另有要求外，若您要求我们删除您的账户，我们无需为此退款、提供福利或其他补偿。
                    </p>
                    <h4>七、所有权：有限许可</h4>
                    <p>软件和服务：
                        服务由我方拥有的作品组成，受我国版权、商标、商业外观、专利和其他知识产权及其他适用法律、法规或规则的保护。我方拥有、已获授权或有权使用服务中出现的所有内容。本条款不会向您或任何其他各方赋予服务或服务中任何内容的任何权利、所有权或利益。
                        只要您遵守本条款的任何其他规则，我方会赋予您非排他、不可转让且可撤销的有限许可（受本条款所规定的限制），以便您使用我方支持的网页浏览器或移动设备访问并使用服务，但仅限个人非商业性的娱乐目的。您同意不将服务用作其他用途。
                        如果您违反本条款或我们其他任何适用于您的条款，我们可对您采取行动，其中可包括永久暂停您的账户。另外，您的行为可能会触犯法律，包括侵犯我方的知识产权。您干扰或破坏服务的任何尝试，包括但不限于破坏或操纵任何我方服务的合法运营，均会违反我方的政策，而且可能也会触犯相关法律。
                    </p>
                    <h4>八、责任豁免</h4>
                    <p>您承认，我方及我方关联机构对以下事项不承担任何责任：
                        （1）任何因使用服务或无法使用服务而以任何方式引起的所有间接、偶然、特殊、惩戒性或结果性损害赔偿，包括对利润、商誉或数据损失要求的赔偿；或者
                        （2）第三方的行为，包括服务的其他用户与外部网站运营商的行为。
                        使用服务及外部网站的风险大小以及因服务和外部网站而造成伤害的风险大小完全取决于您。
                        在适用法律允许的最大范围内，本条款中的责任豁免适用于所有因服务而引起或与使用服务或无法使用服务相关的所有损害或伤害，而无论在任何司法辖区内提出何种诉讼因由，包括但不限于因违反保证、违约或侵权行为（包括过失侵权）而引发的诉讼。
                        如果您使用或滥用了服务，或如果您违反了本条款或任何其他适用准则，且您的行为导致出现损失或损害或引起针对我方和（或）我方关联机构的索赔或责任，您同意保护、辩护并使我方和（或）我方关联机免受由此产生的损失、损害、索赔或责任，包括向我方和（或）适用我方关联机构赔偿我方的法律费用。</p>
                    <h4>九、权利转让</h4>
                    <p>
                        无论是否取得您的同意，我方可能随时将此类条款下我方权利或义务转让至任何他人或实体。在未取得我方事先书面同意的情况下，您不得转让您在此类条款下的权利或义务，任何此类情况下的试图转让将是无效的。</p>
                    <h4>十、不可抗力</h4>
                    <p>
                        我方就超出我方控制范围的变故或问题不承担任何责任，如由自然灾害、战争、恐怖活动、暴乱、禁运、民事或军事行动、火灾、洪灾、交通事故、网络基础设施故障、罢工，或运输设施、燃料、能源、劳动力或材料短缺所引起的变故或问题。</p>
                </div>
                <div class="tab-pane fade agreement" id="item2">
                    <p class="t_title">手迹云授权协议</p>
                    <h4>一、授权范围：</h4>
                    <p>
                        可在手迹云平台使用API服务的APP软件仅限可再行提供字体服务类的软件。即APP的用户可以在APP内进行字体购买及下载，所获得的字体仅限在APP内使用。APP用户不能使用字体或包含字体的其它成品（生成图片等）进行商用。其他类型软件不可免费使用我方字体。
                        同意本协议后，您可以选择20款字体，通过集成手迹云API获得并使用这些字体，但仅限您的APP内使用，您的APP的用户不限制下载次数，但标注分成的字体，您必须标明该字体的价格，字体价格不得少于1元，不能免费由APP的用户使用。若我方发现您在管理您的APP过程中违反本条规定，我方有权单方解除本授权协议，并要求您承担违约责任。
                    </p>
                    <h4>二、授权时间：</h4>
                    <p>所有从API列表中获取的字体默认授权时间为半年，若授权时间到期，我方无义务再提供字体服务，您需重新登录我方平台进行在线字体授权服务，重新授权后我方将继续提供字体服务。</p>
                    <h4>三、知识财产权：</h4>
                    <p>
                        我方授权您使用手迹云字体之名称、字体资料和字体文件等相关权利，包含且不限于知识财产权，如著作权、商标权、专利权等。任何未经授权许可之使用，手迹云字体版权方将依侵权知识财产权等相关法律处理。所有未在本授权书中授予您之权利，均归属我方。</p>
                    <h4>四、所开发字库的商业化及收益分配：</h4>
                    <p>您与我方均有权开展有关所合作开发字库的商业宣传。
                        针对标注分成字体产品的真实销售额，您获得百分之七十，我方获得百分之三十。每季度核算一次，请您于核算后1个月内向我方支付。在您支付上述费用时，我方自行承担发生的所得税等税费费用,
                        并提供增值税专用发票。</p>
                    <h4>五、授权限制：</h4>
                    <p>(一) 您不得涂改、删除或以任何方法毁损从我方获取的字体上之著作权与商标权等标示。
                        (二)
                        您同意不以增添任何功能为目的而改变我方字体初始设定，且不得进行还原工程、反编辑、拆解锁定，以其他任何方式试图发现、改编、修改、转变、转换或更改我方字体资料原始码，或为其他足以影响我方字体权益之行为。
                        (三)
                        除本授权书有明文规定者外，您不得重制、销售、出租、再授权、互易、出借、公开展示或散布我方字体（包括且不限于CD光碟、DVD或其他储存媒介、字型驱动程式、字体资料…等），或为其他足以影响我方字体权益之行为。
                        (四) 不论有偿或无偿，您不得将我方字体外框化档案透过ASP（应用服务供应商）或其他类似方法，进行销售、散布、出租、出借、再授权或其他足以影响我方字体权益之行为。
                    </p>
                    <h4>六、纠纷管辖</h4>
                    <p>双方如有因字体授权事宜出现法律纠纷时，应先一协商解决，如协商不成的，可向北京市海淀区人民法院进行相关的诉讼活动。</p>
                </div>
                <div class="tab-pane fade api" id="item3">
                    <h1>方正手迹云 API 设计文档</h1>
                    <h2>一、API 通用格式说明</h2>
                    <p>所有 API 都需要通过 <code>https</code> 访问，<code>v1</code> 是第一个版本标识，API 地址的结构如下：</p>
                    <p><pre><code>https://&lt;domain&gt;/&lt;version&gt;/&lt;api-endpoint&gt;</code></pre></p>
                    <p>例如获取字体列表的 API 路径为：</p>
                    <p><pre><code>https://api.myfont.me/v1/fonts</code></pre></p>
                    <h3>请求格式</h3>
                    <ul>
                        <li>
                            <p>HTTP GET:</p>
                            <ul>
                                <li>参数可以在 url 中以子路径形式存在，如 /{version}/fonts/{id}</li>
                                <li>参数可以通过 query string 形式存在，如 param1=xxx&amp;param2=xxx</li>
                            </ul>
                        </li>
                        <li>
                            <p>HTTP POST:</p>
                            <ul>
                                <li>参数为 json 格式。</li>
                            </ul>
                        </li>
                    </ul>
                    <h3>响应格式</h3>
                    <p>API 访问成功时，服务器会返回 <code>status</code> 为 <code>200</code> 的响应，<code>Content-Type</code> 为 <code>application/json</code>，响应内容为 <code>JSON</code> 格式的字符串。统一为 json 格式串。</p>
                    <h3>异常响应</h3>
                    <p>API 访问不成功时，响应的 <code>status</code> 会参照 <code>HTTP</code> 标准设置相应的数值，比如：<br>
                        * <code>400</code> 表示请求参数有误<br>
                        * <code>401</code> 表示未授权<br>
                        * <code>404</code> 表示 API 不存在<br>
                        * <code>500</code> 表示服务器发生错误。</p>
                    <p>异常响应内容是 <code>JSON</code> 格式的字符串，<code>Content-Type</code> 为 <code>application/json</code>，例如：</p>
                    <p><pre>
{
    "errors": [{ "code": 1001,
        "message": "The font is not found"
    }]
}</pre>
                    </p>
                    <h3>通用请求参数列表</h3>
                    <table class="table" style="border:1px solid #ccc;">
                        <tr>
                            <td>名称</td>
                            <td>必填</td>
                            <td>类型</td>
                            <td>描述</td>
                        </tr>
                        <tr>
                            <td>accessToken</td>
                            <td>true</td>
                            <td>string</td>
                            <td>访问令牌</td>
                        </tr>
                    </table>
                    <h2>二、API列表</h2>
                    <ul>
                        <li><a href="#post-v1accesstoken">POST /v1/accessToken</a></li>
                        <li><a href="#get-v1fonts">GET /v1/fonts</a></li>
                        <li><a href="#get-v1fontsid">GET /v1/fonts/{id}</a></li>
                    </ul>
                    <h3 id="post-v1accesstoken">2.1 POST /v1/accessToken</h3>
                    <p>获取访问令牌，用于访问其它所有 API 。</p>
                    <p><strong>请求参数</strong></p>
                    <p><pre>
{
  "appkey": "nuJXIVNDcRjwcilCzyNgeATjJJCfgPVe", // app key
  "partnerUserId": "34344534535", // 合作方用户唯一标识
  "time": "1487928517882", // Unix 时间戳，单位是毫秒数。
  "mac": "9d07f0f4c09a575fcb18afe7bcf46d0e" // 消息认证码
            // mac = mac(appkey + '|' + time, app_secret)
}</pre>
                    </p>
                    <p>Linux 下通过以下命令生成 HMAC：</p>
                    <p><pre><code>echo -n "nuJXIVNDcRjwcilCzyNgeATjJJCfgPVe|34344534535|1487928517882" | openssl dgst -md5 -hmac "bgEOExRYtImFRgeUPKrGIZNWoosfyMyj";</code></pre>
                    </p>
                    <h3>请求</h3>
                    <p>
                    <pre>
curl -v "https://api.myfont.me/v1/accessToken" -H "Content-Type: application/json" \
-d '{
  "appkey": "nuJXIVNDcRjwcilCzyNgeATjJJCfgPVe",
  "partnerUserId": "34344534535",
  "time": "1487928517882",
  "mac": "9d07f0f4c09a575fcb18afe7bcf46d0e"
}'</pre></p>

                    <h3>响应</h3>
                    <p>请求成功后会得到状态码为<code>200</code>，<code>Content-Type</code>为<code>application/json</code>的<code>JSON</code>数据：</p>
                    <p><pre>
{
  "accessToken": "84h3h4uu544yhuyfd", // 访问令牌
  "expireAt": "1484819251" // 访问令牌过期时间点，unix time ，单位是秒数。
}</pre>
                    </p>
                    <h3 id="get-v1fonts">2.2 GET /v1/fonts</h3>
                    <p>字体商品列表。</p>
                    <p><strong>请求参数</strong>
                        * <em>accessToken</em> - string required 访问令牌</p>
                    <h3>请求</h3>
                    <p>
                    <pre>
curl -v \
-G -d 'accessToken=84hhuf8hjhuj' \
&quot;https://api.myfont.me/v1/fonts&quot;</pre>
                    </p>
                    <h3>响应</h3>
                    <p>请求成功后会得到状态码为<code>200</code>，<code>Content-Type</code>为<code>application/json</code>的<code>JSON</code>数据：</p>
                    <p>
                    <pre>
{
    "fonts": [{
        "id": "jifengti", // 字体商品的唯一标识
        "name": "疾风体",
        "description": "字形个性，造型挺拔，如跳动的音符，旋律灵动，悠扬铿锵，装饰性极强。",
        "icons": { // 字体图片
                "950x450": "http://img.myfont.me/img/fonts/jifengti/icons/jifengti_1.0_web_detail.png"
        },
        "links": {
                "href": "/v1/fonts/jifengti"
        }
    }]
}</pre>
                    </p>
                    <h3 id="get-v1fontsid">2.3 GET /v1/fonts/{id}</h3>
                    <p>字体商品详情。</p>
                    <p><strong>请求参数列表</strong>
                        * id - required  字体id
                        * accessToken - required 访问令牌</p>
                    <h3>请求</h3>
                    <p><pre>
curl -v \
-G -d 'accessToken=84hhuf8hjhuj' \
&quot;https://api.myfont.me/v1/fonts/jifengti?embed=links&quot;</pre>
                    </p>
                    <h3>响应</h3>
                    <p>
                    <pre>
{
  "id": "jifengti", // 字体商品的业务主键
  "name": "疾风体", // 字体商品名称
  "description": "字形个性，造型挺拔，如跳动的音符，旋律灵动，悠扬铿锵，装饰性极强。", // 字体描述
  "author": "刘燕南", // 字体作者
  "version": "1.0", // 当前版本
  "appFontPic": "http://img.myfont.me/app/jifengti/xieyitest.png", // 应用字体图片
  "icons": [ // 字体图片 如字体样张图片
    {
      "950x450": "http://img.myfont.me/img/fonts/jifengti/icons/jifengti_1.0_web_detail.png"
    },
    {
      "40x40": "http://img.myfont.me/img/fonts/jifengti/icons/jifengti-yangzhang1.jpg"
    },
    {
      "120x120": "http://img.myfont.me/img/fonts/jifengti/icons/jifengti-yangzhang3.png"
    }
  ],
  "links": {
    "ttf": {
      "href": "https://api.myfont.me/download/fonts/jifengti?accessToken=84h3h4uu544yhuyfd"
    }
  }
}</pre>
                    </p>

                </div>
                <div class="tab-pane fade agreement" id="item4">
                    <h1 class="t_title">方正手迹云H5</h1>
                    <h4>一、场景介绍：</h4>
                    <p>本H5页面主要用于开发者接入后展示字体信息，为其用户提供下载使用等功能。
                        满足应用内多种个性化字体使用需求，开发者可以方便的获取正版免费字体，一键接入，轻松提高应用体验。
                    </p>
                    <h4>二、功能介绍：</h4>
                    <p>字体列表，展示字体信息，开发者可自行开发应用内下载、使用、分享等功能。</p>
                    <h4>三、效果展示：</h4>
                    <ul class="img_wrap row">
                        <li class="col-md-6">
                            <img src="/pro/img/h5_app.jpg" alt="">
                        </li>
                        <li class="col-md-6">
                            <img src="/pro/img/h5_font.jpg" alt="">
                        </li>
                    </ul>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
<script src="/pro/js/public/public.js?$date"></script>
</html>
