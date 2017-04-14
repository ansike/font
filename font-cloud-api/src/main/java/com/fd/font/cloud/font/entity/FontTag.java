package com.fd.font.cloud.font.entity;

import java.util.Date;

public class FontTag {
    private Integer fontTagId;

    private Integer fontId;

    private Integer tagId;

    private Byte isDeleted;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date upateTime;

    public Integer getFontTagId() {
        return fontTagId;
    }

    public void setFontTagId(Integer fontTagId) {
        this.fontTagId = fontTagId;
    }

    public Integer getFontId() {
        return fontId;
    }

    public void setFontId(Integer fontId) {
        this.fontId = fontId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
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

    public Date getUpateTime() {
        return upateTime;
    }

    public void setUpateTime(Date upateTime) {
        this.upateTime = upateTime;
    }
}