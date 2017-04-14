package com.fd.font.cloud.font.controller;


import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.nutz.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fd.font.cloud.common.constant.ErrorEnum;
import com.fd.font.cloud.common.exception.BusinessException;
import com.fd.font.cloud.common.vo.ErrorCode;
import com.fd.font.cloud.common.vo.Errors;
import com.fd.font.cloud.font.business.FontBusiness;
import com.fd.font.cloud.font.vo.FontDetailResponse;
import com.fd.font.cloud.font.vo.FontsResponse;

@Controller
@RequestMapping
public class FontController {

	@Autowired
	private FontBusiness fontBusiness;
	
	private static final Logger _log = LoggerFactory.getLogger(FontController.class);
	
	@RequestMapping(value = "/v1/fonts", method = RequestMethod.GET)
	@ResponseBody
	public Object list(String accessToken) {
		_log.info("/v1/fonts");
		Errors errors = new Errors();
		FontsResponse response = null;
		
		if(StringUtils.isEmpty(accessToken)){
			errors.add(new ErrorCode(ErrorEnum.ACCESS_TOKEN_PARAM_NOT_EXIST));
			return errors;
		}
		
		try{
			response = fontBusiness.findAll(accessToken);
		}catch(BusinessException be){
			_log.error(be.getMessage(), be);
			errors.add(be.getErrorCode());
			return errors;
		}catch(Exception e){
			_log.error(e.getMessage(), e);
			errors.add(new ErrorCode(ErrorEnum.SYSTEM_ERROR));
			return errors;
		}
		return response;
	}
	
	
	
	
	public FontBusiness getFontBusiness() {
		return fontBusiness;
	}




	public void setFontBusiness(FontBusiness fontBusiness) {
		this.fontBusiness = fontBusiness;
	}




	/**
	 * 
	 * @param fontId
	 *            font id.
	 * @return Font.
	 */
	@RequestMapping(value = "/v1/fonts/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object get(@PathVariable("id") String id, String accessToken) {
		
		_log.info("/v1/fonts/{id}", id);
		Errors errors = new Errors();
		
		if(StringUtils.isEmpty(id)){
			errors.add(new ErrorCode(ErrorEnum.ID_PARAM_NOT_EXIST));
		}
		
		if(StringUtils.isEmpty(accessToken)){
			errors.add(new ErrorCode(ErrorEnum.ACCESS_TOKEN_PARAM_NOT_EXIST));
		}
		
		if(errors.getErrors().size()>0){
			return errors;
		}
		
		FontDetailResponse response = null;
		
		try{
			response = fontBusiness.findFontByCode(id, accessToken);
		}catch(BusinessException be){
			_log.error(be.getMessage(), be);
			errors.add(be.getErrorCode());
			return errors;
		}catch(Exception e){
			_log.error(e.getMessage(), e);
			errors.add(new ErrorCode(ErrorEnum.SYSTEM_ERROR));
			return errors;
		}
		
		return response;
	}
	
	@RequestMapping(value = "/download/fonts", method = RequestMethod.GET)
	public void fontDownload(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String code = request.getParameter("code");
		String accessToken = request.getParameter("accessToken");
		Errors errors = new Errors();

		if(StringUtils.isEmpty(accessToken)){
			errors.add(new ErrorCode(ErrorEnum.ACCESS_TOKEN_PARAM_NOT_EXIST));
		}
		
		if(StringUtils.isEmpty(code)){
			errors.add(new ErrorCode(ErrorEnum.ID_PARAM_NOT_EXIST));
		}
		InputStream is = null;
		try{
			//response = fontBusiness.findFontByCode(id, accessToken);
			is = fontBusiness.fontDownload(code, accessToken);
		}catch(BusinessException be){
			_log.error(be.getMessage(), be);
			errors.add(be.getErrorCode());
		}catch(Exception e){
			_log.error(e.getMessage(), e);
			errors.add(new ErrorCode(ErrorEnum.SYSTEM_ERROR));
		}
		
		if(errors.getErrors().size()>0){
			error(response, errors);
		}else{
			success(response, is, code);
			is.close();
		}
	}
	
	private void success(HttpServletResponse response, InputStream is, String code) throws IOException{
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");

		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		
		response.setHeader("Content-Disposition", "attachment;filename="+code+".ttf");
		
		IOUtils.copy(is, response.getOutputStream());
	}
	
	private void error(HttpServletResponse response, Errors errors) throws IOException{
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");

		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(Json.toJson(errors));
	}
	
}
