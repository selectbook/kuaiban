package com.iiot.business.service;


import com.iiot.business.domain.Article;
import com.iiot.business.domain.ArticleRegionType;

import java.util.List;

/**
 * 文章管理Service接口
 * 
 * @author wujiyue
 * @date 2019-10-28
 */
public interface IArticleService 
{
    /**
     * 查询文章管理
     * 
     * @param id 文章管理ID
     * @return 文章管理
     */
    public Article selectArticleById(String id);

    /**
     * 查询文章管理列表
     * 
     * @param article 文章管理
     * @return 文章管理集合
     */
    public List<Article> selectArticleList(Article article);

    /**
     * 新增文章管理
     * 
     * @param article 文章管理
     * @return 结果
     */
    public int insertArticle(Article article);

    /**
     * 修改文章管理
     * 
     * @param article 文章管理
     * @return 结果
     */
    public int updateArticle(Article article);

    /**
     * 批量删除文章管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteArticleByIds(String ids);


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
