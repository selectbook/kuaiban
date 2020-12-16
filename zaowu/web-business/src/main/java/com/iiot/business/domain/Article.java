package com.iiot.business.domain;

import com.iiot.common.annotation.Excel;
import com.iiot.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 文章管理对象 cms_article
 * 
 * @author wujiyue
 * @date 2019-10-28
 */
public class Article extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键，文章ID */
    private String id;

    /** 作者 */
    @Excel(name = "作者")
    private String yhid;

    /** 推荐到哪个专区。比如：头条、精选、最新、热门、评论最多等 */
    @Excel(name = "推荐到哪个专区。比如：头条、精选、最新、热门、评论最多等")
    private String articleRegion;

    /** 原文作者 */
    @Excel(name = "原文作者")
    private String author;

    /** 发布时间 */
    @Excel(name = "发布时间")
    private String publishTime;

    /** 文章标题 */
    @Excel(name = "文章标题")
    private String title;

    /** 关键词 */
    @Excel(name = "关键词")
    private String keywords;

    /** 摘要 */
    @Excel(name = "摘要")
    private String description;

    /** 封面图片 */
    @Excel(name = "封面图片")
    private String coverImage;

    /** 文章模型 */
    @Excel(name = "文章模型")
    private String articleModel;

    /** 转载标志 */
    @Excel(name = "转载标志")
    private Integer copyFlag;

    /** 频道栏目ID */
    @Excel(name = "频道栏目ID")
    private String categoryId;

    /** 个人分类，多个用逗号分隔 */
    @Excel(name = "个人分类，多个用逗号分隔")
    private String personalCategory;

    /** 原始链接 */
    @Excel(name = "原始链接")
    private String link;

    /** 静态化后url */
    @Excel(name = "静态化后url")
    private String staticUrl;

    /** 标签 */
    @Excel(name = "标签")
    private String tags;

    /** 点击数 */
    @Excel(name = "点击数")
    private Long hit;

    /** 回复数 */
    @Excel(name = "回复数")
    private Long replyNum;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Long upVote;

    /** 差评数 */
    @Excel(name = "差评数")
    private Long downVote;

    /** 热点标志 */
    @Excel(name = "热点标志")
    private Integer hotFlag;

    /** 新增标志 */
    @Excel(name = "新增标志")
    private Integer newFlag;

    /** 是否开启评论 */
    @Excel(name = "是否开启评论")
    private String commentFlag;

    /** 置顶标志 */
    @Excel(name = "置顶标志")
    private Integer topFlag;

    /** 收藏数 */
    @Excel(name = "收藏数")
    private Long favourite;

    /** 趴取任务的ID */
    @Excel(name = "趴取任务的ID")
    private String missionId;

    /** 生成静态页面的模板(cms_template表中的name) */
    @Excel(name = "生成静态页面的模板(cms_template表中的name)")
    private String templateName;

    /** 状态标志 */
    @Excel(name = "状态标志")
    private Integer available;

    /** 删除标志 */
    @Excel(name = "删除标志")
    private Integer deleted;

    /** 附加字段1 */
    @Excel(name = "附加字段1")
    private String extra1;

    /** 附加字段2 */
    @Excel(name = "附加字段2")
    private String extra2;

    /** 附加字段3 */
    @Excel(name = "附加字段3")
    private String extra3;

    /** 文章内容 */
    private String content;

    /** 文章markdown源码 */
    private String content_markdown_source;

    private String tags_name;//扩展字段。标签名称

    private Category category;//栏目分类

    private List<Tags> tagList;//扩展字段
    private String tag;//扩展字段，存放一个标签id

    private String extraName;//扩展字段
    public List<Tags> getTagList() {
        return tagList;
    }

    public String getExtraName() {
        return extraName;
    }

    public void setExtraName(String extraName) {
        this.extraName = extraName;
    }

    public void setTagList(List<Tags> tagList) {
        this.tagList = tagList;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTags_name() {
        return tags_name;
    }

    public void setTags_name(String tags_name) {
        this.tags_name = tags_name;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setYhid(String yhid) 
    {
        this.yhid = yhid;
    }

    public String getYhid() 
    {
        return yhid;
    }
    public void setArticleRegion(String articleRegion) 
    {
        this.articleRegion = articleRegion;
    }

    public String getArticleRegion() 
    {
        return articleRegion;
    }
    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public String getAuthor() 
    {
        return author;
    }
    public void setPublishTime(String publishTime) 
    {
        this.publishTime = publishTime;
    }

    public String getPublishTime() 
    {
        return publishTime;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setKeywords(String keywords) 
    {
        this.keywords = keywords;
    }

    public String getKeywords() 
    {
        return keywords;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setCoverImage(String coverImage) 
    {
        this.coverImage = coverImage;
    }

    public String getCoverImage() 
    {
        return coverImage;
    }
    public void setArticleModel(String articleModel) 
    {
        this.articleModel = articleModel;
    }

    public String getArticleModel() 
    {
        return articleModel;
    }
    public void setCopyFlag(Integer copyFlag) 
    {
        this.copyFlag = copyFlag;
    }

    public Integer getCopyFlag() 
    {
        return copyFlag;
    }
    public void setCategoryId(String categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getCategoryId()
    {
        return categoryId;
    }
    public void setPersonalCategory(String personalCategory) 
    {
        this.personalCategory = personalCategory;
    }

    public String getPersonalCategory() 
    {
        return personalCategory;
    }
    public void setLink(String link) 
    {
        this.link = link;
    }

    public String getLink() 
    {
        return link;
    }
    public void setStaticUrl(String staticUrl) 
    {
        this.staticUrl = staticUrl;
    }

    public String getStaticUrl() 
    {
        return staticUrl;
    }
    public void setTags(String tags) 
    {
        this.tags = tags;
    }

    public String getTags() 
    {
        return tags;
    }
    public void setHit(Long hit) 
    {
        this.hit = hit;
    }

    public Long getHit() 
    {
        return hit;
    }
    public void setReplyNum(Long replyNum) 
    {
        this.replyNum = replyNum;
    }

    public Long getReplyNum() 
    {
        return replyNum;
    }
    public void setUpVote(Long upVote) 
    {
        this.upVote = upVote;
    }

    public Long getUpVote() 
    {
        return upVote;
    }
    public void setDownVote(Long downVote) 
    {
        this.downVote = downVote;
    }

    public Long getDownVote() 
    {
        return downVote;
    }
    public void setHotFlag(Integer hotFlag) 
    {
        this.hotFlag = hotFlag;
    }

    public Integer getHotFlag() 
    {
        return hotFlag;
    }
    public void setNewFlag(Integer newFlag) 
    {
        this.newFlag = newFlag;
    }

    public Integer getNewFlag() 
    {
        return newFlag;
    }
    public void setCommentFlag(String commentFlag)
    {
        this.commentFlag = commentFlag;
    }

    public String getCommentFlag()
    {
        return commentFlag;
    }
    public void setTopFlag(Integer topFlag) 
    {
        this.topFlag = topFlag;
    }

    public Integer getTopFlag() 
    {
        return topFlag;
    }
    public void setFavourite(Long favourite) 
    {
        this.favourite = favourite;
    }

    public Long getFavourite() 
    {
        return favourite;
    }
    public void setMissionId(String missionId) 
    {
        this.missionId = missionId;
    }

    public String getMissionId() 
    {
        return missionId;
    }
    public void setTemplateName(String templateName) 
    {
        this.templateName = templateName;
    }

    public String getTemplateName() 
    {
        return templateName;
    }
    public void setAvailable(Integer available) 
    {
        this.available = available;
    }

    public Integer getAvailable() 
    {
        return available;
    }
    public void setDeleted(Integer deleted) 
    {
        this.deleted = deleted;
    }

    public Integer getDeleted() 
    {
        return deleted;
    }
    public void setExtra1(String extra1) 
    {
        this.extra1 = extra1;
    }

    public String getExtra1() 
    {
        return extra1;
    }
    public void setExtra2(String extra2) 
    {
        this.extra2 = extra2;
    }

    public String getExtra2() 
    {
        return extra2;
    }
    public void setExtra3(String extra3) 
    {
        this.extra3 = extra3;
    }

    public String getExtra3() 
    {
        return extra3;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent_markdown_source() {
        return content_markdown_source;
    }

    public void setContent_markdown_source(String content_markdown_source) {
        this.content_markdown_source = content_markdown_source;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("yhid", getYhid())
            .append("articleRegion", getArticleRegion())
            .append("author", getAuthor())
            .append("publishTime", getPublishTime())
            .append("title", getTitle())
            .append("keywords", getKeywords())
            .append("description", getDescription())
            .append("coverImage", getCoverImage())
            .append("articleModel", getArticleModel())
            .append("copyFlag", getCopyFlag())
            .append("categoryId", getCategoryId())
            .append("personalCategory", getPersonalCategory())
            .append("link", getLink())
            .append("staticUrl", getStaticUrl())
            .append("tags", getTags())
            .append("hit", getHit())
            .append("replyNum", getReplyNum())
            .append("upVote", getUpVote())
            .append("downVote", getDownVote())
            .append("hotFlag", getHotFlag())
            .append("newFlag", getNewFlag())
            .append("commentFlag", getCommentFlag())
            .append("topFlag", getTopFlag())
            .append("favourite", getFavourite())
            .append("missionId", getMissionId())
            .append("templateName", getTemplateName())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("available", getAvailable())
            .append("deleted", getDeleted())
            .append("extra1", getExtra1())
            .append("extra2", getExtra2())
            .append("extra3", getExtra3())
                .append("content", getContent())
                .append("content_markdown_source", getContent_markdown_source())
            .toString();
    }
}
