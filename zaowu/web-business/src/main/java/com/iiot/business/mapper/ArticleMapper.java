package com.iiot.business.mapper;


import com.iiot.business.domain.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 文章管理Mapper接口
 * 
 * @author wujiyue
 * @date 2019-10-28
 */
public interface ArticleMapper 
{
    /**
     * 查询文章
     * 
     * @param id 文章ID
     * @return 文章
     */
    public Article selectArticleById(String id);

    /**
     * 查询文章内容
     * @param article_id
     * @return
     */
    public Map<String,Object> getArticleContent(String article_id);
    /**
     * 查询文章列表
     * 
     * @param article 文章
     * @return 文章集合
     */
    public List<Article> selectArticleList(Article article);

    /**
     * 查询文章专区不为空的文章列表，这类文章才可以在博客首页展示
     *
     * @param article 文章
     * @return 文章集合
     */
    public List<Article> selectArticlesRegionNotNull(Article article);

    /**
     * 查询文章专区为空的文章，这类文章可用于猜你喜欢来推荐
     * 注意这类文章和上面的区别是：
     * 上面方法的文章是网站管理员精心编辑策划的、来画好专区的，是由很大几率展示的
     * 而下面的文章属于坐冷板凳的
     *
     * @param article 文章
     * @return 文章集合
     */
    public List<Article> selectArticlesRegionIsNull(Article article);

    /**
     * 新增文章管理
     *
     * @param article 文章
     * @return 结果
     */
    public int insertArticle(Article article);

    /**
     * 插入文章内容
     * @param article
     */
    public int insertArticleContent(Article article);
    /**
     * 修改文章
     * 
     * @param article 文章
     * @return 结果
     */
    public int updateArticle(Article article);

    /**
     *  更新文章内容
     * @param article
     * @return
     */
    public int updateArticleContent(Article article);
    /**
     * 删除文章
     * 
     * @param id 文章ID
     * @return 结果
     */
    public int deleteArticleById(String id);
    /**
     * 删除文章内容
     * @param id 文章ID
     * @return 结果
     */
    public int deleteArticleContentById(String id);
    /**
     * 批量删除文章
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteArticleByIds(String[] ids);

    /**
     * 批量删除文章内容
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteArticleContentByIds(String[] ids);


    public int checkExistsByTitleAndLink(@Param("title") String title, @Param("link") String link);

    /**
     * 文章点赞+1
     * @param id
     * @return
     */
    public int upVote(String id);
    /**
     * 文章点击数+1
     * @param id
     * @return
     */
    public int articleLook(String id);

    public void batchPublish(String[] ids);
}
