package com.fd.font.cloud.developer;

import java.util.Date;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.json.Json;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fd.font.cloud.common.constant.ErrorEnum;
import com.fd.font.cloud.developer.vo.AccessTokenRequest;
import com.fd.font.cloud.developer.vo.AccessTokenResponse;

import org.junit.Assert;


/**
 * @author xieyi
 * 测试 /v1/accessToken
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
    @ContextConfiguration("classpath:/applicationContext.xml"),
    @ContextConfiguration("classpath:/spring-mvc.xml")
})
@Transactional
public class TestAccessTokenAPI {

	@Autowired
    private WebApplicationContext wac;

	private MockMvc mockMvc; 
	
	private String uri = "/v1/accessToken";
	
	@Before  
    public void setup() {  
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();  
        //this.mockMvc = MockMvcBuilders.standaloneSetup(new RecommendController()).build();
    }
	
	@Test
	public void test_access_token_no_request_param() throws Exception{
		
		String content = IOUtils.toString(TestAccessTokenAPI.class.getResourceAsStream("/json/access_token_no_request_param.json"));
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/json");
		
		String result = mockMvc.perform(MockMvcRequestBuilders
				.post(uri).headers(httpHeaders)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.content(content))
				.andExpect(jsonPath("$.errors[0].code", is(ErrorEnum.APPKEY_PARAM_NOT_EXIST.getCode())))
				.andExpect(jsonPath("$.errors[1].code", is(ErrorEnum.MAC_PARAM_NOT_EXIST.getCode())))
				.andExpect(jsonPath("$.errors[2].code", is(ErrorEnum.TIME_PARAM_NOT_EXIST.getCode())))
				.andReturn().getResponse()
				.getContentAsString();
		System.out.println(result);
	}
	
	@Test
	public void test_access_token_time_param_limit_inner_15_miniutes() throws Exception{
		AccessTokenRequest request = new AccessTokenRequest();
		Long time = new Date().getTime();
		time = time - 15*60*1000 - 1;
		request.setTime(time.toString());
		request.setAppkey(RandomStringUtils.randomAlphabetic(32));
		request.setPartnerUserId(RandomStringUtils.randomAlphabetic(32));
		request.setMac(RandomStringUtils.randomAlphabetic(32));
		String content = Json.toJson(request);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/json");
		String result = mockMvc.perform(MockMvcRequestBuilders
				.post(uri).headers(httpHeaders)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.content(content))
				.andExpect(jsonPath("$.errors[0].code", is(ErrorEnum.TIME_PARAM_LIMIT_INNER_15_MINIUTES.getCode())))
				.andReturn().getResponse()
				.getContentAsString();
		System.out.println(result);	
	}
	
	@Test
	@Sql(value = {"classpath:/sql/init-data.sql"},
    config =
    @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--",
            dataSource = "dataSource"))
	@Rollback(true)
	public void test_access_token_app_not_exist() throws Exception{
		
		String content = IOUtils.toString(TestAccessTokenAPI.class.getResourceAsStream("/json/access_token_app_not_exist.json"));
		AccessTokenRequest request = Json.fromJson(AccessTokenRequest.class, content);
		
		//填充时间
		Long time = new Date().getTime();
		request.setTime(time.toString());
		request.setMac(RandomStringUtils.randomAlphabetic(32));
		
		content = Json.toJson(request);
		System.out.println("content:"+content);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/json");
		String result = mockMvc.perform(MockMvcRequestBuilders
				.post(uri).headers(httpHeaders)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.content(content))
				.andExpect(jsonPath("$.errors[0].code", is(ErrorEnum.APP_NOT_EXIST.getCode())))
				.andReturn().getResponse()
				.getContentAsString();
		System.out.println(result);
		
	}
	
	@Test
	@Sql(value = {"classpath:/sql/init-data.sql"},
    config =
    @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--",
            dataSource = "dataSource"))
	@Rollback(true)
	public void test_access_token_mac_not_match() throws Exception{
		String content = IOUtils.toString(TestAccessTokenAPI.class.getResourceAsStream("/json/access_token_mac_not_match.json"));
		
		String errorAppSecret = "*****";
		
		AccessTokenRequest request = Json.fromJson(AccessTokenRequest.class, content);
		
		//填充时间
		Long time = new Date().getTime();
		request.setTime(time.toString());
		
		String src = request.getAppkey() + "|" +request.getPartnerUserId() + "|" + time;
		String generatedMac = HmacUtils.hmacMd5Hex(errorAppSecret, src);
		request.setMac(generatedMac);
		
		content = Json.toJson(request);
		System.out.println("content:"+content);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/json");
		String result = mockMvc.perform(MockMvcRequestBuilders
				.post(uri).headers(httpHeaders)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.content(content))
				.andExpect(jsonPath("$.errors[0].code", is(ErrorEnum.MAC_NOT_MATCH.getCode())))
				.andReturn().getResponse()
				.getContentAsString();
		System.out.println(result);	
	}
	
	@Test
	@Sql(value = {"classpath:/sql/init-data.sql"},
    config =
    @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--",
            dataSource = "dataSource"))
	@Rollback(true)
	public void test_access_token_success() throws Exception{
		String content = IOUtils.toString(TestAccessTokenAPI.class.getResourceAsStream("/json/access_token_success.json"));
		
		String appkey = "yewbergg";
		String appSecret = "hhdshhds";
		
		AccessTokenRequest request = Json.fromJson(AccessTokenRequest.class, content);
		
		//填充时间
		Long time = new Date().getTime();
		request.setTime(time.toString());
		
		String src = request.getAppkey()  + "|" +request.getPartnerUserId() + "|" + time;
		String generatedMac = HmacUtils.hmacMd5Hex(appSecret, src);
		request.setMac(generatedMac);
		
		content = Json.toJson(request);
		System.out.println("content:"+content);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/json");
		String result = mockMvc.perform(MockMvcRequestBuilders
				.post(uri).headers(httpHeaders)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.content(content))
				.andReturn().getResponse()
				.getContentAsString();
		System.out.println(result);
		AccessTokenResponse response = Json.fromJson(AccessTokenResponse.class, result);
		Assert.assertTrue("accesstoken exist", StringUtils.isNotEmpty(response.getAccessToken()));
	}
	
	
}
