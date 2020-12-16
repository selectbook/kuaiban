package com.iiot.business.domain;

import com.iiot.common.annotation.Excel;
import com.iiot.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 课程介绍对象 xt_course_info
 * 
 * @author soundhearer
 * @date 2020-12-04
 */
public class XtCourseInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程ID */
    private Long id;

    /** 课程介绍内容 */
    @Excel(name = "课程介绍内容")
    private String content;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String imgUrl;

    /** 课程类型 */
    @Excel(name = "课程类型")
    private String courseType;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setImgUrl(String imgUrl) 
    {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() 
    {
        return imgUrl;
    }
    public void setCourseType(String courseType) 
    {
        this.courseType = courseType;
    }

    public String getCourseType() 
    {
        return courseType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("content", getContent())
            .append("imgUrl", getImgUrl())
            .append("courseType", getCourseType())
            .toString();
    }
}
