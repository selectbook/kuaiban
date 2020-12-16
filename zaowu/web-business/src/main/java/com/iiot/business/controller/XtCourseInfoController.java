package com.iiot.business.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.iiot.common.annotation.Log;
import com.iiot.common.enums.BusinessType;
import com.iiot.business.domain.XtCourseInfo;
import com.iiot.business.service.IXtCourseInfoService;
import com.iiot.common.core.controller.BaseController;
import com.iiot.common.core.domain.AjaxResult;
import com.iiot.common.utils.poi.ExcelUtil;
import com.iiot.common.core.page.TableDataInfo;

/**
 * 课程介绍Controller
 * 
 * @author soundhearer
 * @date 2020-12-04
 */
@Controller
@RequestMapping("/business/info")
public class XtCourseInfoController extends BaseController
{
    private String prefix = "business/info";

    @Autowired
    private IXtCourseInfoService xtCourseInfoService;

    @RequiresPermissions("business:info:view")
    @GetMapping()
    public String info()
    {
        return prefix + "/info";
    }

    /**
     * 查询课程介绍列表
     */
    @RequiresPermissions("business:info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(XtCourseInfo xtCourseInfo)
    {
        startPage();
        List<XtCourseInfo> list = xtCourseInfoService.selectXtCourseInfoList(xtCourseInfo);
        return getDataTable(list);
    }

    /**
     * 导出课程介绍列表
     */
    @RequiresPermissions("business:info:export")
    @Log(title = "课程介绍", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(XtCourseInfo xtCourseInfo)
    {
        List<XtCourseInfo> list = xtCourseInfoService.selectXtCourseInfoList(xtCourseInfo);
        ExcelUtil<XtCourseInfo> util = new ExcelUtil<XtCourseInfo>(XtCourseInfo.class);
        return util.exportExcel(list, "info");
    }

    /**
     * 新增课程介绍
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存课程介绍
     */
    @RequiresPermissions("business:info:add")
    @Log(title = "课程介绍", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(XtCourseInfo xtCourseInfo)
    {
        return toAjax(xtCourseInfoService.insertXtCourseInfo(xtCourseInfo));
    }

    /**
     * 修改课程介绍
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        XtCourseInfo xtCourseInfo = xtCourseInfoService.selectXtCourseInfoById(id);
        mmap.put("xtCourseInfo", xtCourseInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存课程介绍
     */
    @RequiresPermissions("business:info:edit")
    @Log(title = "课程介绍", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(XtCourseInfo xtCourseInfo)
    {
        return toAjax(xtCourseInfoService.updateXtCourseInfo(xtCourseInfo));
    }

    /**
     * 删除课程介绍
     */
    @RequiresPermissions("business:info:remove")
    @Log(title = "课程介绍", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(xtCourseInfoService.deleteXtCourseInfoByIds(ids));
    }
}
