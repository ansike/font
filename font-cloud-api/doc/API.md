# 方正手迹云 API 设计文档


## API 通用格式说明

所有 API 都需要通过 `https` 访问，`v1` 是第一个版本标识，API 地址的结构如下：

```
https://<domain>/<version>/<api-endpoint>
```

例如获取字体列表的 API 路径为：

```
https://api.myfont.me/v1/fonts
```


### 请求格式

+ HTTP GET:
    + 参数可以在 url 中以子路径形式存在，如 /{version}/fonts/{id}
    + 参数可以通过 query string 形式存在，如 param1=xxx&param2=xxx

+ HTTP POST:
    + 参数为 json 格式。

### 响应格式
API 访问成功时，服务器会返回 `status` 为 `200` 的响应，`Content-Type` 为 `application/json`，响应内容为 `JSON` 格式的字符串。统一为 json 格式串。
    
### 异常响应
API 访问不成功时，响应的 `status` 会参照 `HTTP` 标准设置相应的数值，比如：
* `400` 表示请求参数有误
* `401` 表示未授权
* `404` 表示 API 不存在
* `500` 表示服务器发生错误。

异常响应内容是 `JSON` 格式的字符串，`Content-Type` 为 `application/json`，例如：
 
```javascript
{
  "errors": [{
    "code": 1001,
    "message": "The font is not found"
  }]
}
```

### 通用请求参数列表
|名称    |必填    |类型   |描述  |
| --------- |-----| -------| -----:|
|   accessToken |true  |  string  | 访问令牌|


## API列表

* [POST /v1/accessToken](#post-v1accesstoken)
* [GET /v1/fonts](#get-v1fonts)
* [GET /v1/fonts/{id}](#get-v1fontsid)

## POST /v1/accessToken

获取访问令牌，用于访问其它所有 API 。

__请求参数__

```javascript
{
  "appkey": "nuJXIVNDcRjwcilCzyNgeATjJJCfgPVe", // app key
  "partnerUserId": "34344534535", // 合作方用户唯一标识
  "time": "1487928517882", // Unix 时间戳，单位是毫秒数。
  "mac": "9d07f0f4c09a575fcb18afe7bcf46d0e" // 消息认证码 
            // mac = mac(appkey + '|' + time, app_secret) 
}
```

Linux 下通过以下命令生成 HMAC：

```sh
echo -n "nuJXIVNDcRjwcilCzyNgeATjJJCfgPVe|34344534535|1487928517882" | openssl dgst -md5 -hmac "bgEOExRYtImFRgeUPKrGIZNWoosfyMyj"
```

### 请求

```sh
curl -v "https://api.myfont.me/v1/accessToken" -H "Content-Type: application/json" \
-d '{
  "appkey": "nuJXIVNDcRjwcilCzyNgeATjJJCfgPVe",
  "partnerUserId": "34344534535", 
  "time": "1487928517882",
  "mac": "9d07f0f4c09a575fcb18afe7bcf46d0e"
}'

```

### 响应

请求成功后会得到状态码为`200`，`Content-Type`为`application/json`的`JSON`数据：

```javascript
{
  "accessToken": "84h3h4uu544yhuyfd", // 访问令牌
  "expireAt": "1484819251" // 访问令牌过期时间点，unix time ，单位是秒数。
}
```

## GET /v1/fonts

字体商品列表。

__请求参数__
* _accessToken_ - string required 访问令牌

### 请求

```sh
curl -v \
-G -d 'accessToken=84h3h4uu544yhuyfd' \
"https://api.myfont.me/v1/fonts"
```

### 响应

请求成功后会得到状态码为`200`，`Content-Type`为`application/json`的`JSON`数据：

```javascript
{
  "fonts": [{
    "id": "jifengti", // 字体商品的唯一标识
    "name": "疾风体",
    "description": "字形个性，造型挺拔，如跳动的音符，旋律灵动，悠扬铿锵，装饰性极强。",
    "icons": [{ // 字体图片
      "950x450": "http://img.myfont.me/img/fonts/jifengti/icons/jifengti_1.0_web_detail.png"
    }],
    "links": {
      "href": "/v1/fonts/jifengti"
    }
  }]
}
```

## GET /v1/fonts/{id}

字体商品详情。

__请求参数列表__
* id - required  字体id 
* accessToken - required 访问令牌

### 请求

```sh
curl -v \
-G -d 'accessToken=84h3h4uu544yhuyfd' \
"https://api.myfont.me/v1/fonts/jifengti?embed=links"
```

### 响应

```javascript
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
      "href": "https://api.myfont.me/download/fonts/jifengti?accessToken=84h3h4uu544yhuyfd",
    }
  }
}
```
