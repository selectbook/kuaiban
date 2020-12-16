package com.catelf.member.dao

import org.apache.spark.sql.SparkSession

object DwsMemberDao {

  /**
   * 查询用户宽表数据
   *
   * @param sparkSession
   * @return
   */
  def queryIdlMemberData(sparkSession: SparkSession) = {
    sparkSession.sql("select uid,ad_id,memberlevel,register,appregurl,regsource,regsourcename,adname," +
      "siteid,sitename,vip_level,cast(paymoney as decimal(10,4)) as paymoney,dt,dn from dws.dws_member ")
  }

  /**
   * 统计注册来源url人数
   *
   * @param sparkSession
   */
  def queryAppregurlCount(sparkSession: SparkSession, dt: String) = {
    sparkSession.sql(s"select appregurl,count(uid),dn,dt from dws.dws_member where dt='${dt}' group by appregurl,dn,dt")
  }

  //统计所属网站人数
  def querySiteNameCount(sparkSession: SparkSession, dt: String) = {
    sparkSession.sql(s"select sitename,count(uid),dn,dt from dws.dws_member where dt='${dt}' group by sitename,dn,dt")
  }

  //统计所属来源人数
  def queryRegsourceNameCount(sparkSession: SparkSession, dt: String) = {
    sparkSession.sql(s"select regsourcename,count(uid),dn,dt from dws.dws_member where dt='${dt}' group by regsourcename,dn,dt ")
  }

  //统计通过各广告注册的人数
  def queryAdNameCount(sparkSession: SparkSession, dt: String) = {
    sparkSession.sql(s"select adname,count(uid),dn,dt from dws.dws_member where dt='${dt}' group by adname,dn,dt")
  }

  //统计各用户等级人数
  def queryMemberLevelCount(sparkSession: SparkSession, dt: String) = {
    sparkSession.sql(s"select memberlevel,count(uid),dn,dt from dws.dws_member where dt='${dt}' group by memberlevel,dn,dt")
  }

  //统计各用户vip等级人数
  def queryVipLevelCount(sparkSession: SparkSession, dt: String) = {
    sparkSession.sql(s"select vip_level,count(uid),dn,dt from dws.dws_member group where dt='${dt}' by vip_level,dn,dt")
  }

  //统计各memberlevel等级 支付金额前三的用户
  def getTop3MemberLevelPayMoneyUser(sparkSession: SparkSession, dt: String) = {
    sparkSession.sql("select *from(select uid,ad_id,memberlevel,register,appregurl,regsource" +
      ",regsourcename,adname,siteid,sitename,vip_level,cast(paymoney as decimal(10,4)),row_number() over" +
      s" (partition by dn,memberlevel order by cast(paymoney as decimal(10,4)) desc) as rownum,dn from dws.dws_member where dt='${dt}') " +
      " where rownum<4 order by memberlevel,rownum")
  }
}