package com.iiot.business.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iiot.business.domain.Article;
import com.iiot.business.service.IArticleService;
import com.iiot.common.annotation.Log;
import com.iiot.common.config.Global;
import com.iiot.common.core.controller.BaseController;
import com.iiot.common.core.domain.AjaxResult;
import com.iiot.common.core.page.TableDataInfo;
import com.iiot.common.enums.BusinessType;
import com.iiot.common.exception.BusinessException;
import com.iiot.common.utils.file.FileUploadUtils;
import com.iiot.common.utils.file.MimeTypeUtils;
import com.iiot.common.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文章管理Controller
 * 
 * @author wujiyue
 * @date 2019-10-28
 */
@Controller
@RequestMapping("/business/article")
public class ArticleController extends BaseController
{
    private String prefix = "business/article";

    @Autowired
    private IArticleService articleService;



    @RequiresPermissions("business:article:view")
    @GetMapping()
    public String article()
    {
        return prefix + "/article";
    }

    /**
     * 查询文章管理列表
     */
    @RequiresPermissions("business:article:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Article article)
    {
        startPage();
        List<Article> list = articleService.selectArticleList(article);
        return getDataTable(list);
    }
    /**
     *这个接口对应 ueditor.config.js 中的，服务器统一请求接口路径
     *
     * */
    @GetMapping("/init/config")
    public String initConfig(){
        return "redirect:/ajax/libs/ueditor/1.4.3/config.json";
    }

    @PostMapping("/init/config")
    @ResponseBody
    public String upfile(String action, MultipartFile upfile, HttpServletRequest request){
        //图片
        String uploadimage="uploadimage";
        //视频
        String uploadscrawl="uploadscrawl";
        if (action.equals(uploadimage)||action.equals(uploadscrawl)){
            // 图片上传文件
            try {
                String pathUrl = FileUploadUtils.upload(Global.getProfile(), upfile);
                // 上传成功后返回的json数据
                /*
                 * {"state": "SUCCESS","original": "Hydrangeas.jpg","size": "595284","title":
                 * "1551927256870045443.jpg","type": ".jpg","url":
                 * "/upload/image/20190307/1551927256870045443.jpg"}
                 */
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("state", "SUCCESS") ;
                map.put("original", upfile.getName()) ;
                map.put("size", upfile.getSize()) ;
                map.put("title", upfile.getName()) ;
                map.put("type", upfile.getContentType()) ;
                map.put("url", pathUrl) ;
                String result = JSON.toJSONString(map);
                System.out.println("json : " + result);
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "上传失败";
    }
    /**
     * 导出文章管理列表
     */
    @RequiresPermissions("business:article:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Article article)
    {
        List<Article> list = articleService.selectArticleList(article);
        ExcelUtil<Article> util = new ExcelUtil<Article>(Article.class);
        return util.exportExcel(list, "article");
    }

    /**
     * 新增文章管理
     */
    @GetMapping("/add")
    public String add()
    {

        return prefix + "/add";

    }

    /**
     * 新增保存文章管理
     */
    @RequiresPermissions("business:article:add")
    @Log(title = "文章管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Article article)
    {
        return toAjax(articleService.insertArticle(article));
    }

    /**
     * 修改文章管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        Article article = articleService.selectArticleById(id);
        mmap.put("article", article);
        String tagIds=article.getTags();
        return prefix + "/edit";
    }

    /**
     * 修改保存文章管理
     */
    @RequiresPermissions("business:article:edit")
    @Log(title = "文章管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Article article)
    {
        return toAjax(articleService.updateArticle(article));
    }


    /**
     * 上传图片(markdown编辑器上传图片使用)
     */
    @PostMapping("/uploadImage")
    @ResponseBody
    public Object uploadImage(@RequestParam("editormd-image-file") MultipartFile file) throws Exception
    {
        try
        {
            /*// 上传的后台只需要返回一个 JSON 数据，结构如下：
                 {
                 success : 0 | 1,           // 0 表示上传失败，1 表示上传成功
                 message : "提示的信息，上传成功或上传失败及错误信息等。",
                 url     : "图片地址"        // 上传成功时才返回
                 }
                 */

            // 上传图片并返回新文件名称
            String path = FileUploadUtils.upload(Global.getUploadPath(), file, MimeTypeUtils.IMAGE_EXTENSION);
            Map map=new HashMap();
            map.put("success",1);
            map.put("url",path);
            map.put("message","上传成功!");
            return map;
        }
        catch (Exception e)
        {
            Map map=new HashMap();
            map.put("success",0);
            map.put("url","");
            map.put("message","上传失败!"+e.getMessage());
            return map;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/batchPublish/{ids}", method = RequestMethod.POST)
    public Object batchPublish(@PathVariable String[] ids) {
        try{
            articleService.batchPublish(ids);
            return success("批量发布成功！");
        }catch (Exception e){
            return error("批量发布失败！");
        }
    }


}
