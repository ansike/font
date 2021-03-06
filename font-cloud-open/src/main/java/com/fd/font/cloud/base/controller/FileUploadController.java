package com.fd.font.cloud.base.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fd.font.cloud.base.BaseController;
import com.fd.font.cloud.base.ControllerException;
import com.fd.font.cloud.base.ControllerResult;
import com.fd.font.cloud.util.PropertiesUtil;
import com.github.pagehelper.StringUtil;

@Controller
@RequestMapping("/file")
public class FileUploadController extends BaseController {
	Logger _log = LoggerFactory.getLogger(getClass());
	public static String UPLOAD_PATH = File.separator + "home" + File.separator + "pictures" + File.separator;

	@RequestMapping(value="/uploadpic", method = RequestMethod.POST)
	public @ResponseBody ControllerResult uploadPic(@RequestParam MultipartFile file) {
		try {
			getDeveloper();
			if(!StringUtil.isEmpty(PropertiesUtil.getContextProperty("upload.path"))){
				FileUploadController.UPLOAD_PATH=PropertiesUtil.getContextProperty("upload.path");
			}
			InputStream is = file.getInputStream();
			byte[] buffer = new byte[1024];
			String extention = getExtention(file.getOriginalFilename());// 图片后缀
			String uploadFileName = RandomStringUtils.randomAlphabetic(8) + extention;
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH)+1;
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			int hour=calendar.get(Calendar.HOUR_OF_DAY);
			int minute = calendar.get(Calendar.MINUTE);          //获取当前分钟
			int second = calendar.get(Calendar.SECOND); 
			String fileName = hour+"_"+minute+"_"+second+"_"+new Random().nextInt(1000)+new Random().nextInt(100)+"_" + uploadFileName;
			String filePath =year + "/" + month + "/" + day + "/";
			File destFile = new File(FileUploadController.UPLOAD_PATH+filePath);
			if (!destFile.isDirectory()) {
				destFile.mkdirs();
			}
			File absFile=new File(FileUploadController.UPLOAD_PATH+filePath+fileName);
			_log.info(absFile.getAbsolutePath());
			OutputStream output = new FileOutputStream(absFile);
			IOUtils.copyLarge(is, output, buffer);
			IOUtils.closeQuietly(output);
			IOUtils.closeQuietly(is);
			
			String url =null;
			if(!StringUtil.isEmpty(PropertiesUtil.getContextProperty("upload.path.prefix"))){
				String uploadPrefix=PropertiesUtil.getContextProperty("upload.path.prefix");
				if(!uploadPrefix.startsWith("http://")){
					uploadPrefix="http://"+uploadPrefix;
				}
				if(!uploadPrefix.endsWith("/")){
					uploadPrefix=uploadPrefix+"/";
				}
				url=uploadPrefix + filePath+fileName;
			}else{
				url=ServletUriComponentsBuilder.fromCurrentContextPath().toUriString() + filePath+fileName;
			}

			Map<String, String> map = new HashMap<>(2);
			map.put("uri", filePath+fileName);
			map.put("url", url);
			return success(map);
		} catch (IOException e) {
			_log.error(e.getMessage(), e);
			return faile();
		} catch (ControllerException e){
			return e.getResult();
		}
	}

	public static String getExtention(String fileName) {
		if (fileName == null || fileName.equals(""))
			return "";
		int pos = fileName.lastIndexOf(".");
		if (pos < 0) {
			return "." + fileName.substring(fileName.length() - 3).toUpperCase();
		} else {
			return fileName.substring(pos).toUpperCase();
		}
	}
}
