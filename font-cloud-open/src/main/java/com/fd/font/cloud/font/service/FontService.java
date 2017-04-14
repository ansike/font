package com.fd.font.cloud.font.service;

import java.util.Map;
import java.util.Set;

import com.fd.font.cloud.base.ControllerException;
import com.fd.font.cloud.font.entity.FontVersion;
import com.fd.font.cloud.font.vo.FontDetailVo;
import com.fd.font.cloud.font.vo.FontVo;
import com.github.pagehelper.PageInfo;

public interface FontService {

	PageInfo<FontVo> findFontByPage(Integer pageNum,Integer pageSize,Map<String,String> params);
	/**
	 * 联想查询，字体名，作者名
	 * @param search
	 * @return
	 */
	Set<String> findNameThink(String search);
	Set<String> findAuthorThink(String search);
	FontDetailVo findFontDetail(Map<String, String> params);
	/**
	 * 添加下载次数
	 * @author XiRuiQiang
	 * @param parseInt
	 * @throws ControllerException 
	 */
	FontVersion addDownNumByFontVersionId(int fontVersionId) throws ControllerException;
	/**
	 * 添加下载次数
	 * @author XiRuiQiang
	 * @param parseInt
	 * @throws ControllerException 
	 */
	FontVersion addDownNumByFontId(int fontId) throws ControllerException;
	/**
	 * h5分页查询--主要查询透明图片
	 * @param pageNum
	 * @param pageSize
	 * @param params
	 * @return
	 */
	PageInfo<FontDetailVo> findH5FontByPage(Integer pageNum, Integer pageSize, Map<String, String> params);
}
