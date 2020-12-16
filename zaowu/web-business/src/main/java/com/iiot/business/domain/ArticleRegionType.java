package com.iiot.business.domain;

/**
 * 文章所属推荐专区的专区类型。头条、热门、精选推荐、评论最多、最新
 * @author wujiyue
 */
public enum ArticleRegionType {
    REGION_TOP("top","头条"),
    REGION_FOCUS("focus","焦点"),
    REGION_HOT("hot","热门"),
    REGION_RECOMMEND("recommend","精选推荐"),
    REGION_MOST_COMMENT("most_comment","评论最多"),
    REGION_NEW("new","最新");

    private String val;
    private String desc;
    private ArticleRegionType(String v, String desc){
        this.val=v;
        this.desc=desc;
    }
    public String getDesc(){
        return desc;
    }
    public String getVal(){
        return val;
    }
    @Override
    public String toString() {
        return val;
    }
}
