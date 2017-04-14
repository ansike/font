package com.fd.font.cloud.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpUtil {
	public static Map<String, String> parseRequestParams(HttpServletRequest request) {
		Map<String, String> params = new HashMap<>();
		for (Entry<String, String[]> e : request.getParameterMap().entrySet()) {
			String value = null;
			for (String v : e.getValue())
				if (value == null)
					value = v;
				else
					value += "," + v;
			params.put(e.getKey(), value);
		}
		return params;
	}
	
	public static void downloadLocal(String path,HttpServletResponse response) throws FileNotFoundException {
        // 下载本地文件
        File file=new File(path);
        // 读到流中
        InputStream inStream = new FileInputStream(path);// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
