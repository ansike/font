package com.fd.font.cloud.font.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.fd.font.cloud.base.ControllerException;
import com.fd.font.cloud.font.dao.FontExMapper;
import com.fd.font.cloud.font.dao.FontMapper;
import com.fd.font.cloud.font.dao.FontVersionDownloadMapper;
import com.fd.font.cloud.font.dao.FontVersionMapper;
import com.fd.font.cloud.font.dao.FontVersionPicMapper;
import com.fd.font.cloud.font.entity.Font;
import com.fd.font.cloud.font.entity.FontVersion;
import com.fd.font.cloud.font.entity.FontVersionDownload;
import com.fd.font.cloud.font.entity.FontVersionDownloadExample;
import com.fd.font.cloud.font.entity.FontVersionPic;
import com.fd.font.cloud.font.service.FontService;
import com.fd.font.cloud.font.vo.FontDetailVo;
import com.fd.font.cloud.font.vo.FontVo;
import com.fd.font.cloud.util.ImgUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;

@Service(value = "fontService")
public class FontServiceImpl implements FontService {
	public static final String[] FBSARR = { "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|","@","!","-","\"",":",";","'" };
	@Autowired
	private FontExMapper fontExMapper;
	@Autowired
	private FontMapper fontMapper;
	
	@Autowired
	private FontVersionMapper fontVersionMapper;
	@Autowired
	private FontVersionPicMapper fontVersionPicMapper;
	
	@Autowired
	private FontVersionDownloadMapper fontVersionDownloadMapper;

	@Override
	public PageInfo<FontVo> findFontByPage(Integer pageNum, Integer pageSize, Map<String, String> params) {
		PageHelper.startPage(pageNum, pageSize);
		if(!StringUtil.isEmpty(params.get("tagValue"))){
			String value="";
			for(String str:params.get("tagValue").split(",")){
				value+="'"+str+"',";
			}
			value=value.substring(0, value.length()-1);
			params.put("tagValue", value);
		}
		String searchValue=params.get("searchValue");
		if(!StringUtils.isEmpty(searchValue)){
			for (String key : FontServiceImpl.FBSARR) {
				if (searchValue.contains(key)) {
					searchValue = searchValue.replace(key, "\\\\" + key);
					System.out.println(searchValue);
				}
			}
			params.put("searchValue", searchValue);
		}
		return new PageInfo<>(fontExMapper.findList(params));
	}

	@Override
	public Set<String> findNameThink(String search) {
		//处理特殊字符串
        for (String key : FontServiceImpl.FBSARR) {
            if (search.contains(key)) {
            	search = search.replace(key, "\\\\" + key);
            }
        }  
		Set<String> names=fontExMapper.findNameThink(search);
		return names;
	}

	@Override
	public Set<String> findAuthorThink(String search) {
        for (String key : FontServiceImpl.FBSARR) {
            if (search.contains(key)) {
            	search = search.replace(key, "\\\\" + key);
            }
        }
		Set<String> authors=fontExMapper.findAuthorThink(search);
		return authors;
	}

	@Override
	public FontDetailVo findFontDetail(Map<String, String> params) {
		return fontExMapper.findFontDetail(params);
	}

	@Override
	public FontVersion addDownNumByFontVersionId(int fontVersionId) throws ControllerException {
		FontVersion fv=fontVersionMapper.selectByPrimaryKey(fontVersionId);
		if(fv==null){
			throw new ControllerException();
		}
		FontVersionDownloadExample example=new FontVersionDownloadExample();
		example.createCriteria().andFontVersionIdEqualTo(fontVersionId);
		List<FontVersionDownload> list=fontVersionDownloadMapper.selectByExample(example);
		if(list.size()==0){
			FontVersionDownload fvd =new FontVersionDownload();
			fvd.setFontVersionId(fv.getFontVersionId());
			fvd.setDownloadNum("1");
			fontVersionDownloadMapper.insertSelective(fvd);
		}else if(list.size()==1){
			FontVersionDownload fvd=list.get(0);
			Integer num=Integer.parseInt(fvd.getDownloadNum());
			fvd.setDownloadNum((num+1)+"");
			fontVersionDownloadMapper.updateByPrimaryKeySelective(fvd);
		}else{
			throw new ControllerException();
		}
		return fv;
	}

	@Override
	public FontVersion addDownNumByFontId(int fontId) throws ControllerException{
		Font font=fontMapper.selectByPrimaryKey(fontId);
		if(font==null){
			throw new ControllerException();
		}else{
			return addDownNumByFontVersionId(font.getCurrFontVersionId());
		}
	}

	@Override
	public PageInfo<FontDetailVo> findH5FontByPage(Integer pageNum, Integer pageSize, Map<String, String> params) {
		PageHelper.startPage(pageNum, pageSize);
		if(!StringUtil.isEmpty(params.get("tagValue"))){
			String value="";
			for(String str:params.get("tagValue").split(",")){
				value+="'"+str+"',";
			}
			value=value.substring(0, value.length()-1);
			params.put("tagValue", value);
		}
		String searchValue=params.get("searchValue");
		if(!StringUtils.isEmpty(searchValue)){
			for (String key : FontServiceImpl.FBSARR) {
				if (searchValue.contains(key)) {
					searchValue = searchValue.replace(key, "\\\\" + key);
				}
			}
			params.put("searchValue", searchValue);
		}
		List<FontDetailVo> fonts=fontExMapper.findH5List(params);
		for(FontDetailVo font:fonts){
			if(StringUtils.isEmpty(font.getH5PicUrl())){
				//创建字体图片
				String fontName=font.getName();
				String ttf=font.getTtfDownloadUrl();
				if(ttf.startsWith("http")){
					ttf=ttf.substring(ttf.indexOf("/download/")+10);
				}else{
					ttf=ttf.substring(ttf.indexOf("download/")+9);
				}
				ImgUtils.PictureInfo picInfo=ImgUtils.createFontPic(ttf,font.getCode()+"/", fontName);
				font.setH5PicUrl(picInfo.getPicUrl());
				//更新数据库啊
				FontVersionPic record=new FontVersionPic();
				record.setFontId(font.getFontId());
				record.setFontVersionId(font.getCurrFontVersionId());
				record.setPicType((byte)3);
				record.setPicUrl(picInfo.getPicUrl());
				record.setWidth(picInfo.getWidth());
				record.setHeight(picInfo.getHeight());
				record.setCreateBy(0);
				record.setUpdateBy(0);
				fontVersionPicMapper.insertSelective(record);
			}
		}
		return new PageInfo<FontDetailVo>(fonts);
	}

}
