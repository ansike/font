package com.fd.font.cloud.font.entity;

import java.util.Date;

public class FontVersion {
    private Integer fontVersionId;

    private Integer fontId;

    private String versionName;

    private String ttfDownloadUrl;

    private String ttfSize;

    private String defaultCoverPic;

    private String defaultDetailPic;

    private Byte status;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private Byte isDeleted;

    public Integer getFontVersionId() {
        return fontVersionId;
    }

    public void setFontVersionId(Integer fontVersionId) {
        this.fontVersionId = fontVersionId;
    }

    public Integer getFontId() {
        return fontId;
    }

    public void setFontId(Integer fontId) {
        this.fontId = fontId;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getTtfDownloadUrl() {
        return ttfDownloadUrl;
    }

    public void setTtfDownloadUrl(String ttfDownloadUrl) {
        this.ttfDownloadUrl = ttfDownloadUrl;
    }

    public String getTtfSize() {
        return ttfSize;
    }

    public void setTtfSize(String ttfSize) {
        this.ttfSize = ttfSize;
    }

    public String getDefaultCoverPic() {
        return defaultCoverPic;
    }

    public void setDefaultCoverPic(String defaultCoverPic) {
        this.defaultCoverPic = defaultCoverPic;
    }

    public String getDefaultDetailPic() {
        return defaultDetailPic;
    }

    public void setDefaultDetailPic(String defaultDetailPic) {
        this.defaultDetailPic = defaultDetailPic;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
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