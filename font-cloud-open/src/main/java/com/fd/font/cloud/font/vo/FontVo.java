package com.fd.font.cloud.font.vo;

import java.io.Serializable;
import java.util.Date;

public class FontVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer fontId;
	/**
	 * 字体名
	 */
	private String name;
	private String code;
	/**
	 * 字体简介
	 */
	private String introduction;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 0启用1禁用
	 */
	private Integer status;
	/**
	 * 创建热ID
	 */
	private Integer createBy;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 当前版本对应Id
	 */
	private Integer currFontVersionId;
	private Long fontSize;
	private String picUrl;
	/**
	 * 透明背景字体图片
	 */
	private String h5PicUrl;
	

	public String getH5PicUrl() {
		return h5PicUrl;
	}

	public void setH5PicUrl(String h5PicUrl) {
		this.h5PicUrl = h5PicUrl;
	}

	public Integer getFontId() {
		return fontId;
	}

	public void setFontId(Integer fontId) {
		this.fontId = fontId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCurrFontVersionId() {
		return currFontVersionId;
	}

	public void setCurrFontVersionId(Integer currFontVersionId) {
		this.currFontVersionId = currFontVersionId;
	}

	public Long getFontSize() {
		return fontSize;
	}

	public void setFontSize(Long fontSize) {
		this.fontSize = fontSize;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

}
