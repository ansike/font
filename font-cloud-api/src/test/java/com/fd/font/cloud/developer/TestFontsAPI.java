package com.fd.font.cloud.developer;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.json.Json;
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
import com.fd.font.cloud.common.vo.ErrorCode;
import com.fd.font.cloud.common.vo.Errors;
import com.fd.font.cloud.developer.vo.AccessTokenRequest;
import com.fd.font.cloud.developer.vo.AccessTokenResponse;
import com.fd.font.cloud.font.vo.FontsResponse;

/**
 * @author xieyi
 * 测试 /v1/fonts
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
    @ContextConfiguration("classpath:/applicationContext.xml"),
    @ContextConfiguration("classpath:/spring-mvc.xml")
})
@Transactional
public class TestFontsAPI {

	@Autowired
    private WebApplicationContext wac;

	private MockMvc mockMvc; 
	
	private String fontsUrl = "/v1/fonts";
	
	@Before  
    public void setup() {  
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();  
        //this.mockMvc = MockMvcBuilders.standaloneSetup(new RecommendController()).build();
    }
	
	@Test
	public void test_fonts_no_access_token_param() throws Exception{
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/json");
		String result = mockMvc.perform(MockMvcRequestBuilders
				.get(fontsUrl)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.headers(httpHeaders))
				.andExpect(jsonPath("$.errors[0].code", is(ErrorEnum.ACCESS_TOKEN_PARAM_NOT_EXIST.getCode())))
				.andReturn().getResponse()
				.getContentAsString();
		System.out.println(result);		
	}
	
	@Test
	public void test_fonts_ilegal_access_token() throws Exception{
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/json");
		String accessTokenNotExist = "***";
		String result = mockMvc.perform(MockMvcRequestBuilders
				.get(fontsUrl)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.headers(httpHeaders)
				.param("accessToken", accessTokenNotExist ))
				.andExpect(jsonPath("$.errors[0].code", is(ErrorEnum.ILEGAL_ACCESS_TOKEN.getCode())))
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
	public void test_fonts_success() throws Exception {
		//首先调用 /v1/accessToken 获取accessToken
		String content = IOUtils.toString(TestAccessTokenAPI.class.getResourceAsStream("/json/access_token_success.json"));
		
		String appkey = "yewbergg";
		String appSecret = "hhdshhds";
		
		AccessTokenRequest request = Json.fromJson(AccessTokenRequest.class, content);
		
		//填充时间
		Long time = new Date().getTime();
		request.setTime(time.toString());
		
		String src = request.getAppkey() + "|" + request.getPartnerUserId() +  "|" + time;
		String generatedMac = HmacUtils.hmacMd5Hex(appSecret, src);
		request.setMac(generatedMac);
		
		content = Json.toJson(request);
		System.out.println("content:"+content);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/json");
		String result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/accessToken").headers(httpHeaders).content(content)).andReturn().getResponse().getContentAsString();
		System.out.println(result);
		AccessTokenResponse response = Json.fromJson(AccessTokenResponse.class, result);
		
		String accessToken =  response.getAccessToken();
		
		httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/json");
		result = mockMvc.perform(MockMvcRequestBuilders
				.get(fontsUrl).headers(httpHeaders)
				.param("accessToken", accessToken))
				.andReturn().getResponse()
				.getContentAsString();
		System.out.println(result);

		FontsResponse fontsResponse = Json.fromJson(FontsResponse.class, result);
		
		Assert.assertTrue(fontsResponse.getFonts().size()>=0);
	}
	
}
