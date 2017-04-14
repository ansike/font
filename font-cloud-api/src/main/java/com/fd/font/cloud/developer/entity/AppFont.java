package com.fd.font.cloud.developer.entity;

import java.util.Date;

public class AppFont {
    private Integer appFontId;

    private Integer appId;

    private Integer fontId;

    private Byte status;

    private Date authzDate;

    private Date unauthzDate;

    private String appFontPic;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private Byte isDeleted;

    private Integer fontVersionId;

    public Integer getAppFontId() {
        return appFontId;
    }

    public void setAppFontId(Integer appFontId) {
        this.appFontId = appFontId;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getFontId() {
        return fontId;
    }

    public void setFontId(Integer fontId) {
        this.fontId = fontId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getAuthzDate() {
        return authzDate;
    }

    public void setAuthzDate(Date authzDate) {
        this.authzDate = authzDate;
    }

    public Date getUnauthzDate() {
        return unauthzDate;
    }

    public void setUnauthzDate(Date unauthzDate) {
        this.unauthzDate = unauthzDate;
    }

    public String getAppFontPic() {
        return appFontPic;
    }

    public void setAppFontPic(String appFontPic) {
        this.appFontPic = appFontPic;
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

    public Integer getFontVersionId() {
        return fontVersionId;
    }

    public void setFontVersionId(Integer fontVersionId) {
        this.fontVersionId = fontVersionId;
    }
}