package com.iiot.business.domain;


import com.iiot.common.annotation.Excel;
import com.iiot.common.core.domain.TreeEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 栏目分类对象 cms_category
 * 
 * @author wujiyue
 * @date 2019-10-28
 */
public class Category extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 分类ID */
    private Long categoryId;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String categoryName;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;

    /** 分类描述 */
    @Excel(name = "分类描述")
    private String description;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private Integer status;

    /** 删除标志 */
    private Integer delFlag;


    private Category parent;//扩展字段
    private List<Category> nodes;//扩展字段

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getNodes() {
        return nodes;
    }

    public void setNodes(List<Category> nodes) {
        this.nodes = nodes;
    }

    public void setCategoryId(Long categoryId)
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId()
    {
        return categoryId;
    }
    public void setCategoryName(String categoryName) 
    {
        this.categoryName = categoryName;
    }

    public String getCategoryName() 
    {
        return categoryName;
    }
    public void setSort(Integer sort) 
    {
        this.sort = sort;
    }

    public Integer getSort() 
    {
        return sort;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setDelFlag(Integer delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("categoryId", getCategoryId())
            .append("categoryName", getCategoryName())
            .append("parentId", getParentId())
            .append("ancestors", getAncestors())
            .append("sort", getSort())
            .append("description", getDescription())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
