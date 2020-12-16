package com.iiot.business.domain;

import com.iiot.common.annotation.Excel;
import com.iiot.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 标签管理对象 cms_tags
 * 
 * @author wujiyue
 * @date 2019-10-29
 */
public class Tags extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long tagId;

    /** 标签类型，如s系统标签，p个人标签 */
    @Excel(name = "标签类型，如s系统标签，p个人标签")
    private String tagType;

    /** 谁增加的该标签 */
    @Excel(name = "谁增加的该标签")
    private String userId;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String tagName;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 链接 */
    @Excel(name = "链接")
    private String url;

    /**
     * 是否选中，默认否。
     */
    private boolean selected=false;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setTagId(Long tagId)
    {
        this.tagId = tagId;
    }

    public Long getTagId() 
    {
        return tagId;
    }
    public void setTagType(String tagType) 
    {
        this.tagType = tagType;
    }

    public String getTagType() 
    {
        return tagType;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setTagName(String tagName) 
    {
        this.tagName = tagName;
    }

    public String getTagName() 
    {
        return tagName;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tagId", getTagId())
            .append("tagType", getTagType())
            .append("userId", getUserId())
            .append("tagName", getTagName())
            .append("sort", getSort())
            .append("status", getStatus())
            .append("url", getUrl())
            .toString();
    }
}
