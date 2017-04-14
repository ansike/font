package com.fd.font.cloud.developer.entity;

import java.util.Date;

public class FontDownloadLog {
    private Integer downloadId;

    private String appKey;

    private Integer appId;

    private String partnerUserId;

    private String fontCode;

    private Integer fontId;

    private Integer fontVersionId;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private Byte isDeleted;

    public Integer getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(Integer downloadId) {
        this.downloadId = downloadId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getPartnerUserId() {
        return partnerUserId;
    }

    public void setPartnerUserId(String partnerUserId) {
        this.partnerUserId = partnerUserId;
    }

    public String getFontCode() {
        return fontCode;
    }

    public void setFontCode(String fontCode) {
        this.fontCode = fontCode;
    }

    public Integer getFontId() {
        return fontId;
    }

    public void setFontId(Integer fontId) {
        this.fontId = fontId;
    }

    public Integer getFontVersionId() {
        return fontVersionId;
    }

    public void setFontVersionId(Integer fontVersionId) {
        this.fontVersionId = fontVersionId;
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

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }
}