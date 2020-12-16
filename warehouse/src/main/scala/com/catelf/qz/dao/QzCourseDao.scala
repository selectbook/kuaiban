package com.catelf.qz.dao

import org.apache.spark.sql.SparkSession

object QzCourseDao {

  def getDwdQzSiteCourse(sparkSession: SparkSession, dt: String) = {
    sparkSession.sql("select sitecourseid,siteid,courseid,sitecoursename,coursechapter,sequence,status," +
      "creator as sitecourse_creator,createtime as sitecourse_createtime,helppaperstatus,servertype,boardid,showstatus,dt,dn " +
      s"from dwd.dwd_qz_site_course where dt='${dt}'")
  }

  def getDwdQzCourse(sparkSession: SparkSession, dt: String) = {
    sparkSession.sql("select courseid,majorid,coursename,isadvc,chapterlistid,pointlistid,dn from " +
      s"dwd.dwd_qz_course where dt='${dt}'")
  }

  def getDwdQzCourseEduSubject(sparkSession: SparkSession, dt: String) = {
    sparkSession.sql("select courseeduid,edusubjectid,courseid,dn from dwd.dwd_qz_course_edusubject " +
      s"where dt='${dt}'")
  }
}
