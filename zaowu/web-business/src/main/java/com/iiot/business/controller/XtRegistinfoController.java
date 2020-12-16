package com.iiot.business.controller;

import java.util.List;

import com.iiot.business.domain.XtRegistinfo;
import com.iiot.business.service.IXtRegistinfoService;
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
import com.iiot.common.core.controller.BaseController;
import com.iiot.common.core.domain.AjaxResult;
import com.iiot.common.utils.poi.ExcelUtil;
import com.iiot.common.core.page.TableDataInfo;

/**
 * 报名信息Controller
 * 
 * @author soundhearer
 * @date 2020-11-28
 */
@Controller
@RequestMapping("/business/registinfo")
public class XtRegistinfoController extends BaseController
{
    private String prefix = "business/registinfo";

    @Autowired
    private IXtRegistinfoService xtRegistinfoService;

    @RequiresPermissions("business:registinfo:view")
    @GetMapping()
    public String registinfo()
    {
        return prefix + "/registinfo";
    }

    /**
     * 查询报名信息列表
     */
    @RequiresPermissions("business:registinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(XtRegistinfo xtRegistinfo)
    {
        startPage();
        List<XtRegistinfo> list = xtRegistinfoService.selectXtRegistinfoList(xtRegistinfo);
        return getDataTable(list);
    }

    /**
     * 导出报名信息列表
     */
    @RequiresPermissions("business:registinfo:export")
    @Log(title = "报名信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(XtRegistinfo xtRegistinfo)
    {
        List<XtRegistinfo> list = xtRegistinfoService.selectXtRegistinfoList(xtRegistinfo);
        ExcelUtil<XtRegistinfo> util = new ExcelUtil<XtRegistinfo>(XtRegistinfo.class);
        return util.exportExcel(list, "registinfo");
    }

    /**
     * 新增报名信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存报名信息
     */
    @RequiresPermissions("business:registinfo:add")
    @Log(title = "报名信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(XtRegistinfo xtRegistinfo)
    {
        return toAjax(xtRegistinfoService.insertXtRegistinfo(xtRegistinfo));
    }

    /**
     * 修改报名信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        XtRegistinfo xtRegistinfo = xtRegistinfoService.selectXtRegistinfoById(id);
        mmap.put("xtRegistinfo", xtRegistinfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存报名信息
     */
    @RequiresPermissions("business:registinfo:edit")
    @Log(title = "报名信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(XtRegistinfo xtRegistinfo)
    {
        return toAjax(xtRegistinfoService.updateXtRegistinfo(xtRegistinfo));
    }

    /**
     * 删除报名信息
     */
    @RequiresPermissions("business:registinfo:remove")
    @Log(title = "报名信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(xtRegistinfoService.deleteXtRegistinfoByIds(ids));
    }
}
