package com.catelf.qz.dao

import org.apache.spark.sql.SparkSession

object QzChapterDao {

  /**
   * 查询qz_chapter基础数据
   *
   * @param sparkSession
   * @return
   */
  def getDwdQzChapter(sparkSession: SparkSession, dt: String) = {
    sparkSession.sql("select chapterid,chapterlistid,chaptername,sequence,showstatus,creator as " +
      "chapter_creator,createtime as chapter_createtime,courseid as chapter_courseid,chapternum,outchapterid,dt,dn from dwd.dwd_qz_chapter where " +
      s"dt='$dt'")

  }

  /**
   * 查询qz_chapter_list基础数据
   *
   * @param sparkSession
   * @param dt
   */
  def getDwdQzChapterList(sparkSession: SparkSession, dt: String) = {
    sparkSession.sql("select chapterlistid,chapterlistname,chapterallnum,dn from dwd.dwd_qz_chapter_list " +
      s"where dt='$dt'")
  }

  /**
   * 查询qz_point基础数据
   *
   * @param sparkSession
   * @param dt
   */
  def getDwdQzPoint(sparkSession: SparkSession, dt: String) = {
    sparkSession.sql("select pointid,pointname,pointyear,chapter,excisenum,pointlistid,chapterid," +
      "pointdescribe,pointlevel,typelist,score as point_score,thought,remid,pointnamelist,typelistids,pointlist,dn from " +
      s"dwd.dwd_qz_point where dt='$dt'")
  }

  /**
   * 查询qz_point_question基础数据
   *
   * @param sparkSession
   * @param dt
   */
  def getDwdQzPointQuestion(sparkSession: SparkSession, dt: String) = {
    sparkSession.sql(s"select pointid,questionid,questype,dn from dwd.dwd_qz_point_question where dt='$dt'")
  }
}
