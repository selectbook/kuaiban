package com.catelf.qz.dao

import org.apache.spark.sql.SparkSession

object QzQuestionDao {

  def getQzQuestion(sparkSession: SparkSession, dt: String) = {
    sparkSession.sql("select questionid,parentid,questypeid,quesviewtype,content,answer,analysis,limitminute," +
      "score,splitscore,status,optnum,lecture,creator,createtime,modifystatus,attanswer,questag,vanalysisaddr,difficulty," +
      s"quesskill,vdeoaddr,dt,dn from  dwd.dwd_qz_question where dt='$dt'")
  }

  def getQzQuestionType(sparkSession: SparkSession, dt: String) = {
    sparkSession.sql("select questypeid,viewtypename,description,papertypename,remark,splitscoretype,dn from " +
      s"dwd.dwd_qz_question_type where dt='$dt'")
  }
}
