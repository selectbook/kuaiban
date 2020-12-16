package com.iiot.business.service;

import java.util.List;
import com.iiot.business.domain.XtCourseInfo;

/**
 * 课程介绍Service接口
 * 
 * @author soundhearer
 * @date 2020-12-04
 */
public interface IXtCourseInfoService 
{
    /**
     * 查询课程介绍
     * 
     * @param id 课程介绍ID
     * @return 课程介绍
     */
    public XtCourseInfo selectXtCourseInfoById(Long id);

    /**
     * 查询课程介绍列表
     * 
     * @param xtCourseInfo 课程介绍
     * @return 课程介绍集合
     */
    public List<XtCourseInfo> selectXtCourseInfoList(XtCourseInfo xtCourseInfo);

    /**
     * 新增课程介绍
     * 
     * @param xtCourseInfo 课程介绍
     * @return 结果
     */
    public int insertXtCourseInfo(XtCourseInfo xtCourseInfo);

    /**
     * 修改课程介绍
     * 
     * @param xtCourseInfo 课程介绍
     * @return 结果
     */
    public int updateXtCourseInfo(XtCourseInfo xtCourseInfo);

    /**
     * 批量删除课程介绍
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteXtCourseInfoByIds(String ids);

    /**
     * 删除课程介绍信息
     * 
     * @param id 课程介绍ID
     * @return 结果
     */
    public int deleteXtCourseInfoById(Long id);
}
