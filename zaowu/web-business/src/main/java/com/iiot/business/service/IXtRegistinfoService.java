package com.iiot.business.service;

import com.iiot.business.domain.XtRegistinfo;

import java.util.List;

/**
 * 报名信息Service接口
 * 
 * @author soundhearer
 * @date 2020-11-28
 */
public interface IXtRegistinfoService 
{
    /**
     * 查询报名信息
     * 
     * @param id 报名信息ID
     * @return 报名信息
     */
    public XtRegistinfo selectXtRegistinfoById(Long id);

    /**
     * 根据用户名查询报名信息
     * @param userName
     * @return
     */
    public XtRegistinfo selectXtRegistinfoByName(String userName);

    /**
     * 查询报名信息列表
     * 
     * @param xtRegistinfo 报名信息
     * @return 报名信息集合
     */
    public List<XtRegistinfo> selectXtRegistinfoList(XtRegistinfo xtRegistinfo);

    /**
     * 新增报名信息
     * 
     * @param xtRegistinfo 报名信息
     * @return 结果
     */
    public int insertXtRegistinfo(XtRegistinfo xtRegistinfo);

    /**
     * 修改报名信息
     * 
     * @param xtRegistinfo 报名信息
     * @return 结果
     */
    public int updateXtRegistinfo(XtRegistinfo xtRegistinfo);

    /**
     * 批量删除报名信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteXtRegistinfoByIds(String ids);

    /**
     * 删除报名信息信息
     * 
     * @param id 报名信息ID
     * @return 结果
     */
    public int deleteXtRegistinfoById(Long id);
}
