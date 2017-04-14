package com.fd.font.cloud.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil extends PropertyPlaceholderConfigurer {
	  
    private static Map<String, Object> ctxPropertiesMap;  
  
    @Override  
    protected void processProperties(ConfigurableListableBeanFactory beanFactory,
            Properties props)throws BeansException {
  
        super.processProperties(beanFactory, props);  
        //load properties to ctxPropertiesMap  
        ctxPropertiesMap = new HashMap<String, Object>();  
        for (Object key : props.keySet()) {  
            String keyStr = key.toString();  
            String value = props.getProperty(keyStr);  
            ctxPropertiesMap.put(keyStr, value);  
        }  
    }  
  
    //static method for accessing context properties  
    public static String getContextProperty(String name) {  
    	if (null==ctxPropertiesMap){
    		return "";	  
    	}else{
    		Object o= ctxPropertiesMap.get(name);  
    		if(o==null)return "";else return o.toString();
    	}
    }  
} 