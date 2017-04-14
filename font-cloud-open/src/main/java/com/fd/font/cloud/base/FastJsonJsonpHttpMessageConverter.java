package com.fd.font.cloud.base;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

public class FastJsonJsonpHttpMessageConverter extends FastJsonHttpMessageConverter {

    private String[] jsonpParameterNames = new String[]{"callback","jsonp"};

    @SuppressWarnings("deprecation")
	@Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String text = JSON.toJSONString(obj, getFeatures());
        String callback = null;
        for (String jsonpParameterName : jsonpParameterNames) {
            callback = request.getParameter(jsonpParameterName);
            if (callback != null) {
                break;
            }
        }
        if(StringUtils.isNotBlank(callback)){
            text = callback + "(" + text + ")";
        }
        outputMessage.getBody().write(text.getBytes(getCharset()));
    }

    public void setJsonpParameterNames(String[] jsonpParameterNames) {
        this.jsonpParameterNames = jsonpParameterNames;
    }

}