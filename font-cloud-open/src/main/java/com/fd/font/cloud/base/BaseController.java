package com.fd.font.cloud.base;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ContextLoader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fd.font.cloud.developer.entity.Developer;
import com.fd.font.cloud.util.HttpUtil;
import com.github.pagehelper.StringUtil;
import com.google.code.kaptcha.Constants;

public class BaseController {

	Logger _log=LoggerFactory.getLogger(getClass());
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private StringRedisTemplate stringRedis;
	public Map<String, String> getParams() {
		return HttpUtil.parseRequestParams(request);
	}
	/**
	 * 校验图片验证码是否正确
	 * @author XiRuiQiang
	 * 2017年2月15日 上午11:21:21
	 * @param code
	 * @return 
	 * @throws ControllerException 
	 */
	public boolean checkRandomCode(String code) throws ControllerException{
		Object randomCode=getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if(randomCode==null||!code.equalsIgnoreCase(randomCode.toString())){
			throw new ControllerException(ReturnCode.RANDOM_CODE_ERROR);
		}
		//验证码验证一次后，立即清除
//		getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, null);
		return true;
	}
	/**
	 * 校验短信验证码是否正确
	 * @author XiRuiQiang
	 * 2017年2月15日 下午2:14:34
	 * @param mobile
	 * @param templateCode
	 * @param code
	 * @return
	 * @throws ControllerException 
	 */
	public boolean checkSmsCode(String mobile,String templateCode,String code,Boolean isClean) throws ControllerException{
		String mobileRedisKey=FontCloudConstant.SMS_REDIS_KEY+mobile;
		String userInfo=stringRedis.opsForValue().get(mobileRedisKey);
		JSONObject userSendInfo = Json.fromJson(JSONObject.class,Json.toJson(userInfo));
		if(userSendInfo==null){
			throw new ControllerException(ReturnCode.SMS_CODE_ERROR,"请先发送验证码");
		}else if(!code.equals(userSendInfo.getString(templateCode))){
			throw new ControllerException(ReturnCode.SMS_CODE_ERROR);
		}else if(userSendInfo.getLong(templateCode+"_dieTime")==null||userSendInfo.getLong(templateCode+"_dieTime")<System.currentTimeMillis()){
			//验证码失效
			throw new ControllerException(ReturnCode.SMS_CODE_DIE,"验证码10分钟失效");
		}else if(isClean!=null&&isClean){
			Long expire=stringRedis.boundValueOps(mobileRedisKey).getExpire();
			userSendInfo.remove(templateCode);
			stringRedis.opsForValue().set(mobileRedisKey, userSendInfo.toString());
			System.out.println(stringRedis.boundValueOps(mobileRedisKey).getExpire());
			stringRedis.boundValueOps(mobileRedisKey).expire(expire,TimeUnit.SECONDS);
		}
		_log.info(new Date(userSendInfo.getLong(templateCode+"_dieTime")).toString());
		return true;
	}
	public static Developer getDeveloperBySessionAndRedis(HttpServletRequest request){
		Developer user= (Developer) request.getSession().getAttribute(FontCloudConstant.USER_SESSION_ID);
		if(user==null){
			String redisKey=null;
			Cookie[] cookies=request.getCookies();
			if(cookies!=null){
				for(Cookie c:cookies){
					if(c.getName().equals(FontCloudConstant.USER_SESSION_ID)){
						redisKey=c.getValue();
						break;
					}
				}
			}
			if(!StringUtil.isEmpty(redisKey)){
				StringRedisTemplate redis=ContextLoader.getCurrentWebApplicationContext().getBean(StringRedisTemplate.class);
				String userJson=redis.opsForValue().get(redisKey);
				if(!StringUtil.isEmpty(userJson)){
					user= JSON.parseObject(userJson, Developer.class);
					request.getSession().setAttribute(FontCloudConstant.USER_SESSION_ID, user);
					return user;
				}
			}
			
		}else{
			return user;
		}
		return null;
	}
	public Developer getDeveloper() throws ControllerException{
		Developer user= getDeveloperBySessionAndRedis(request);
		if(user!=null){
			return user;
		}
		throw new ControllerException(ReturnCode.LOGIN_NO_SUCCESS);
	}

	public String getParam(String key) {
		return request.getParameter(key);
	}

	public String getParamNotNull(String key) throws ControllerException {
		String value = request.getParameter(key);
		if (!StringUtils.isEmpty(value)){
			if(key.equalsIgnoreCase("mobile")){
				//验证手机号格式
				String regEx = "1[3|4|5|7|8][0-9]\\d{8}";
				Pattern pattern = Pattern.compile(regEx);
				Matcher matcher = pattern.matcher(value);
				if (!matcher.matches()) {
					throw new ControllerException(ReturnCode.MOBILE_FORMAT_ERROR);
				}
			}
			return value;
		}
		throw new ControllerException(ReturnCode.FAILE, key + "不允许为空");
	}
	public Integer getParamNotNullInteger(String key) throws ControllerException {
		String value = request.getParameter(key);
		if (!StringUtils.isEmpty(value)){
			try{
				return Integer.parseInt(value);
			}catch (NumberFormatException e) {
				throw new ControllerException(ReturnCode.FAILE,key+"格式错误");
			}
		}
		throw new ControllerException(ReturnCode.FAILE, key + "不允许为空");
	}

	public ControllerResult success() {
		return new ControllerResult(ReturnCode.SUCCESS);
	}
	public ControllerResult success(Object data) {
		return new ControllerResult(ReturnCode.SUCCESS,data);
	}
	public ControllerResult faile() {
		return new ControllerResult(ReturnCode.FAILE);
	}
	public ControllerResult faile(Object data) {
		return new ControllerResult(ReturnCode.FAILE,data);
	}
	public ControllerResult faile(ReturnCode code) {
		return new ControllerResult(code);
	}
	public HttpSession getSession(){
		return request.getSession();
	}
	
	public StringRedisTemplate getStringRedis() {
		return stringRedis;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	
}
