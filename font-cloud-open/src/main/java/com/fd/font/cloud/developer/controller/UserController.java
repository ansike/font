package com.fd.font.cloud.developer.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nutz.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fd.font.cloud.base.BaseController;
import com.fd.font.cloud.base.ControllerException;
import com.fd.font.cloud.base.ControllerResult;
import com.fd.font.cloud.base.FontCloudConstant;
import com.fd.font.cloud.base.ReturnCode;
import com.fd.font.cloud.developer.entity.Developer;
import com.fd.font.cloud.developer.service.DeveloperService;
import com.fd.font.cloud.util.FontCloudDateUtil;
import com.fd.font.cloud.util.MD5Util;
import com.fd.font.cloud.util.PropertiesUtil;
import com.fd.font.cloud.util.SendSmsTool;
import com.github.pagehelper.StringUtil;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
 * 登录，注册，验证码等操作控制
 * 
 * @author XiRuiQiang 2017年2月15日 上午9:58:31
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
	Logger _log = LoggerFactory.getLogger(getClass());
	/**
	 * 图片验证码生产
	 */
	@Autowired
	private Producer kaptchaProducer;
	/**
	 * 开发者管理
	 */
	@Autowired
	private DeveloperService developerService;

	/**
	 * 用户注册接口
	 * 
	 * @author XiRuiQiang 2017年2月15日 上午10:15:42
	 * @return
	 */
	@RequestMapping("/register")
	public ControllerResult register() {
		try {
			String mobile = getParamNotNull("mobile");
			String smsCode = getParamNotNull("smsCode");
			String password = getParamNotNull("password");
			String confirmPassword = getParamNotNull("confirmPassword");
			if (!password.equals(confirmPassword)) {
				// 两次密码不一致
				return new ControllerResult(ReturnCode.CONFIRM_PASSWORD_ERROR);
			}
			checkRandomCode(getParamNotNull("randomCode"));
			checkSmsCode(mobile, FontCloudConstant.REGISTER_ACCOUNT, smsCode,true);
			// 短信验证码正确
			developerService.insert(mobile, confirmPassword);
			return success();
		} catch (ControllerException e) {
			return e.getResult();
		}
	}

	/**
	 * 登录
	 * 
	 * @author XiRuiQiang 2017年2月15日 下午4:42:44
	 * @return
	 */
	@RequestMapping("/login")
	public ControllerResult login(HttpServletResponse response) {
		try {
			String mobile = getParamNotNull("mobile");
			String randomCode = getParamNotNull("randomCode");
			String tag = getParamNotNull("tag");
			checkRandomCode(randomCode);
			Developer developer = developerService.findByMobile(mobile);
			if (developer == null) {
				// 手机号未注册
				throw new ControllerException(ReturnCode.MOBILE_NOT_EXIST);
			}
			if (tag.equalsIgnoreCase("password")) {
				// 密码登录
				if (!MD5Util.string2MD5(getParamNotNull("password")).equals(developer.getPassword())) {
					return faile(ReturnCode.LOGIN_PASSWORD_ERROR);
				}
			} else if (tag.equalsIgnoreCase("mobile")) {
				// 手机验证码登录
				checkSmsCode(mobile, FontCloudConstant.MOBILE_LOGIN, getParamNotNull("smsCode"),true);
			} else {
				return faile();
			}
			//记住我
			if ("true".equalsIgnoreCase(getParam("rememberMe"))) {
				String redisLoginCookieValue = MD5Util.string2MD5(""+developer.getDeveloperId() + developer.getCreateTime().getTime())+ new Random().nextInt(1000);
				Cookie cookie = new Cookie(FontCloudConstant.USER_SESSION_ID, redisLoginCookieValue);
				getStringRedis().opsForValue().set(redisLoginCookieValue, JSON.toJSONString(developer));
				getStringRedis().boundValueOps(redisLoginCookieValue).expire(7,TimeUnit.DAYS);// 记住一周
				cookie.setMaxAge(604800);// 秒：60*60*24*7//记住7天
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			getSession().setAttribute(FontCloudConstant.USER_SESSION_ID, developer);
			return success();
		} catch (ControllerException e) {
			return e.getResult();
		}
	}
	/**
	 * 注销登录
	 * @return
	 */
	@RequestMapping("/logout")
	public ControllerResult logout() {
		String redisKey=null;
		for(Cookie c:getRequest().getCookies()){
			if(c.getName().equals(FontCloudConstant.USER_SESSION_ID)){
				redisKey=c.getValue();
				break;
			}
		}
		if(!StringUtil.isEmpty(redisKey)){
			getStringRedis().delete(redisKey);
		}
		getSession().setAttribute(FontCloudConstant.USER_SESSION_ID, null);
		return success();
	}

	/**
	 * 获取图片验证码
	 * 
	 * @author XiRuiQiang 2017年2月15日 上午11:04:18
	 * @throws IOException
	 */
	@RequestMapping("/getRandomCode")
	public void getRandomCode(HttpServletResponse response) throws IOException {
		HttpSession session = getSession();

		response.setDateHeader("Expires", 0);

		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");

		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");

		// return a jpeg
		response.setContentType("image/jpeg");

		// create the text for the image
		String capText = kaptchaProducer.createText();

		// store the text in the session
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

		// create the image with the text
		BufferedImage bi = kaptchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();

		// write the data out
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}

	}

	/**
	 * 校验图片验证码
	 * 
	 * @author XiRuiQiang 2017年2月16日 下午12:37:51
	 * @return
	 */
	@RequestMapping("/webCheckRandomCode")
	public ControllerResult webCheckRandomCode() {
		try {
			checkRandomCode(getParamNotNull("randomCode"));
		} catch (ControllerException e) {
			return e.getResult();
		}
		return success();
	}

	/**
	 * 发送短信验证码
	 * 
	 * @author XiRuiQiang 2017年2月15日 上午11:05:38
	 * @return
	 */
	@RequestMapping("/sendSmsCode")
	public ControllerResult sendSmsCode() {
		try {
			String templateCode = getParamNotNull("templateCode");
			String mobile = getParamNotNull("mobile");
			String template;
			Developer developer = developerService.findByMobile(mobile);
			boolean checkRandomCodeBoolean = true;// 是否检查验证码
			String mobileRedisKey = FontCloudConstant.SMS_REDIS_KEY + mobile;
			switch (templateCode) {
			case FontCloudConstant.REGISTER_ACCOUNT:
				if (developer != null) {
					// 手机号已被注册
					return new ControllerResult(ReturnCode.MOBILE_EXIST);
				}
				template = SendSmsTool.REGISTER_ACCOUNT_TEMPLATE;
				break;
			case FontCloudConstant.MODIFY_PASSWORD:// 修改密码
				getDeveloper();// 检查是否登录
				template = SendSmsTool.MODIFY_PASSWORD_TEMPLATE;
				checkRandomCodeBoolean = true;// 不需要验证码
				break;
			case FontCloudConstant.FORGOT_PASSWORD:
				if (developer == null) {
					// 手机号未注册
					return new ControllerResult(ReturnCode.MOBILE_NOT_EXIST);
				}
				template = SendSmsTool.FORGOT_PASSWORD_TEMPLATE;
				break;
			case FontCloudConstant.MOBILE_LOGIN:
				// 短信登录
				if (developer == null) {
					// 手机号未注册
					return new ControllerResult(ReturnCode.MOBILE_NOT_EXIST);
				}
				template = SendSmsTool.FORGOT_PASSWORD_TEMPLATE;
				break;
			default:
				return faile("未知的模版");
			}
			if (checkRandomCodeBoolean) {
				// 验证图片验证码
				checkRandomCode(getParamNotNull("randomCode"));
			}
			String smsRandomCode = SendSmsTool.getSmsRandomCode(4);
			//短信发送环境，true真发短信，false接口返回，其他不响应
			String smsFact=PropertiesUtil.getContextProperty("sms.send.fact");
			if(StringUtil.isEmpty(smsFact)){
				_log.warn("properties sms.send.fact not config!");
			}else{
				_log.info("sms.send.fact="+smsFact);
			}
			// 发送短信验证码并记录
			if (getStringRedis().hasKey(mobileRedisKey)) {
				Long expire=getStringRedis().getExpire(mobileRedisKey);;
				String userInfo = getStringRedis().opsForValue().get(mobileRedisKey);
				JSONObject userSendInfo = Json.fromJson(JSONObject.class, Json.toJson(userInfo));
				if (userSendInfo.getIntValue("sendTimes") < 10) {
					userSendInfo.put("sendTimes", userSendInfo.getIntValue("sendTimes") + 1);
					userSendInfo.put(templateCode, smsRandomCode);
					userSendInfo.put(templateCode+"_dieTime", FontCloudDateUtil.getSmsDieTime());
					if("true".equalsIgnoreCase(smsFact)){
						SendSmsTool.sendSms(template, mobile,smsRandomCode);
					}
					getStringRedis().opsForValue().set(mobileRedisKey, userSendInfo.toString());
					getStringRedis().boundValueOps(mobileRedisKey).expire(expire,TimeUnit.SECONDS);
					_log.info(mobileRedisKey+":过期时间剩余:" +expire);
				} else {
					return new ControllerResult(ReturnCode.SMS_MAX_ERROR);
				}
			} else {
				JSONObject userSendInfo = new JSONObject();
				userSendInfo.put("sendTimes", 1);
				userSendInfo.put(templateCode, smsRandomCode);
				userSendInfo.put(templateCode+"_dieTime", FontCloudDateUtil.getSmsDieTime());
				if("true".equalsIgnoreCase(smsFact)){
					SendSmsTool.sendSms(template, mobile,smsRandomCode);
				}
				getStringRedis().opsForValue().set(mobileRedisKey, userSendInfo.toString());
				// 计算现在到明天00:00:00多长时间(秒)
				Long seconds = FontCloudDateUtil.getToLaseTime();
				_log.info(seconds + "秒后过期：" + mobileRedisKey);
				getStringRedis().boundValueOps(mobileRedisKey).expire(seconds, TimeUnit.SECONDS);
			}
			if("false".equalsIgnoreCase(smsFact)){
				return success(smsRandomCode);
			}
			return success();
		} catch (ControllerException e) {
			return e.getResult();
		}
	}

	/**
	 * 校验短信验证码接口
	 * 
	 * @author XiRuiQiang 2017年2月16日 下午12:37:33
	 * @return
	 */
	@RequestMapping("/webCheckSmsCode")
	public ControllerResult webCheckSmsCode() {
		try {
			checkSmsCode(getParamNotNull("mobile"), getParamNotNull("templateCode"), getParamNotNull("code"),false);
		} catch (ControllerException e) {
			return e.getResult();
		}
		return success();
	}

	@RequestMapping("webCheck")
	public ControllerResult webCheck() {
		try {
			switch (getParamNotNull("type")) {
			case FontCloudConstant.MOBILE:// 手机号是否存在
				if (developerService.findByMobile(getParamNotNull("mobile")) == null) {
					// 不存在
					return faile();
				}
				break;

			default:
				return faile();
			}
			return success();
		} catch (ControllerException e) {
			return e.getResult();
		}
	}

	/**
	 * 获取开发者帐号信息手机号，邮箱
	 * 
	 * @return
	 */
	@RequestMapping("/developerInfo")
	public ControllerResult developerInfo() {
		try {
			Developer developer = getDeveloper();
			developer.setPassword(null);
			developer.setStatus(null);
			developer.setCreateBy(null);
			developer.setCreateTime(null);
			developer.setUpdateBy(null);
			developer.setUpdateTime(null);
			developer.setIsDeleted(null);
			return success(developer);
		} catch (ControllerException e) {
			return e.getResult();
		}
	}

	/**
	 * 根据手机号和开发者ID更新开发者邮箱，联系地址
	 * 
	 * @return
	 */
	@RequestMapping("updateDeveloper")
	public ControllerResult updateDeveloper() {
		try {
			Integer id = Integer.parseInt(getParamNotNull("id"));
			String mobile = getParamNotNull("mobile");
			String email = getParam("email");
			if (!StringUtil.isEmpty(email)) {
				String regEx = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";
				Pattern pattern = Pattern.compile(regEx);
				Matcher matcher = pattern.matcher(email);
				if (!matcher.matches()) {
					// 邮箱格式错误
					throw new ControllerException(ReturnCode.EMAIL_FORMAT_ERROR);
				}
			}
			String address = getParam("address");
			getDeveloper();
			getSession().setAttribute(FontCloudConstant.USER_SESSION_ID, developerService.updateEmailByIdOrMobile(id, mobile, email, address));
			return success();
		} catch (ControllerException e) {
			return e.getResult();
		}

	}

	/**
	 * 修改用户密码,修改分两步骤,都用这一个接口
	 * 
	 * @return
	 */
	@RequestMapping("/updatePassword")
	public ControllerResult updatePassword() {
		try {
			String step = getParamNotNull("step");
			String mobile = getParamNotNull("mobile");
			if (step.equals("1")) {
				// 验证短信验证码
				checkRandomCode(getParamNotNull("randomCode"));
				checkSmsCode(mobile, FontCloudConstant.MODIFY_PASSWORD, getParamNotNull("smsCode"),true);
				getSession().setAttribute(getClass().getName() + ".updatePassword", true);
			} else if (step.equals("2")) {
				// 修改密码
				Object tag = getSession().getAttribute(getClass().getName() + ".updatePassword");
				if (tag == null || !(boolean) tag) {
					// 步骤错误
					throw new ControllerException(ReturnCode.STEP_ERROR);
				}
				getSession().setAttribute(getClass().getName() + ".updatePassword", null);
				String password = getParamNotNull("password");
				String confirmPassword = getParamNotNull("confirmPassword");
				if (!password.equals(confirmPassword)) {
					throw new ControllerException(ReturnCode.CONFIRM_PASSWORD_ERROR);
				}
				developerService.updatePasswordByMobile(mobile, MD5Util.string2MD5(password));
				logout();
			} else {
				return faile();
			}
			return success();
		} catch (ControllerException e) {
			return e.getResult();
		}
	}

	/**
	 * 忘记密码,分两步骤,都用这一个接口
	 * 
	 * @return
	 */
	@RequestMapping("/forgetPassword")
	public ControllerResult forgetPassword() {
		try {
			String step = getParamNotNull("step");
			String mobile = getParamNotNull("mobile");
			if (step.equals("1")) {
				// 验证短信验证码
				checkSmsCode(mobile, FontCloudConstant.FORGOT_PASSWORD, getParamNotNull("smsCode"),true);
				getSession().setAttribute(getClass().getName() + ".forgetPassword", true);
			} else if (step.equals("2")) {
				// 修改密码
				Object tag = getSession().getAttribute(getClass().getName() + ".forgetPassword");
				if (tag == null || !(boolean) tag) {
					// 步骤错误
					throw new ControllerException(ReturnCode.STEP_ERROR);
				}
				getSession().setAttribute(getClass().getName() + ".forgetPassword", null);
				String password = getParamNotNull("password");
				String confirmPassword = getParamNotNull("confirmPassowrd");
				if (!password.equals(confirmPassword)) {
					throw new ControllerException(ReturnCode.CONFIRM_PASSWORD_ERROR);
				}
				developerService.updatePasswordByMobile(mobile, MD5Util.string2MD5(password));
			} else {
				return faile();
			}
			return success();
		} catch (ControllerException e) {
			return e.getResult();
		}
	}
}
