package com.catelf.member.service

import com.catelf.member.bean.QueryResult
import com.catelf.member.dao.DwsMemberDao
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{SaveMode, SparkSession}

object AdsMemberService {
  /**
   * 统计各项指标 使用api
   *
   * @param sparkSession
   */
  def queryDetailApi(sparkSession: SparkSession, dt: String) = {
    import sparkSession.implicits._ //隐式转换
    val result = DwsMemberDao.queryIdlMemberData(sparkSession).as[QueryResult].where(s"dt='${dt}'")
    result.cache()
    //统计注册来源url人数
    val a = result.mapPartitions(partition => {
      partition.map(item => (item.appregurl + "_" + item.dn + "_" + item.dt, 1))
    }).groupByKey(_._1)
      .mapValues(item => item._2).reduceGroups(_ + _)
      .map(item => {
        val keys = item._1.split("_")
        val appregurl = keys(0)
        val dn = keys(1)
        val dt = keys(2)
        (appregurl, item._2, dt, dn)
      }).toDF().coalesce(1).write.mode(SaveMode.Overwrite).insertInto("ads.ads_register_appregurlnum")
    //统计所属网站人数
    result.mapPartitions(partiton => {
      partiton.map(item => (item.sitename + "_" + item.dn + "_" + item.dt, 1))
    }).groupByKey(_._1).mapValues((item => item._2)).reduceGroups(_ + _)
      .map(item => {
        val keys = item._1.split("_")
        val sitename = keys(0)
        val dn = keys(1)
        val dt = keys(2)
        (sitename, item._2, dt, dn)
      }).toDF().coalesce(1).write.mode(SaveMode.Overwrite).insertInto("ads.ads_register_sitenamenum")
    //统计所属来源人数 pc mobile wechat app
    result.mapPartitions(partition => {
      partition.map(item => (item.regsourcename + "_" + item.dn + "_" + item.dt, 1))
    }).groupByKey(_._1).mapValues(item => item._2).reduceGroups(_ + _)
      .map(item => {
        val keys = item._1.split("_")
        val regsourcename = keys(0)
        val dn = keys(1)
        val dt = keys(2)
        (regsourcename, item._2, dt, dn)
      }).toDF().coalesce(1).write.mode(SaveMode.Overwrite).insertInto("ads.ads_register_regsourcenamenum")

    //统计通过各广告进来的人数
    result.mapPartitions(partition => {
      partition.map(item => (item.adname + "_" + item.dn + "_" + item.dt, 1))
    }).groupByKey(_._1).mapValues(_._2).reduceGroups(_ + _)
      .map(item => {
        val keys = item._1.split("_")
        val adname = keys(0)
        val dn = keys(1)
        val dt = keys(2)
        (adname, item._2, dt, dn)
      }).toDF().coalesce(1).write.mode(SaveMode.Overwrite).insertInto("ads.ads_register_adnamenum")

    //统计各用户等级人数
    result.mapPartitions(partition => {
      partition.map(item => (item.memberlevel + "_" + item.dn + "_" + item.dt, 1))
    }).groupByKey(_._1).mapValues(_._2).reduceGroups(_ + _)
      .map(item => {
        val keys = item._1.split("_")
        val memberlevel = keys(0)
        val dn = keys(1)
        val dt = keys(2)
        (memberlevel, item._2, dt, dn)
      }).toDF().coalesce(1).write.mode(SaveMode.Overwrite).insertInto("ads.ads_register_memberlevelnum")

    //统计各用户vip等级人数
    result.mapPartitions(partition => {
      partition.map(item => (item.vip_level + "_" + item.dn + "_" + item.dt, 1))
    }).groupByKey(_._1).mapValues(_._2).reduceGroups(_ + _)
      .map(item => {
        val keys = item._1.split("_")
        val vip_level = keys(0)
        val dn = keys(1)
        val dt = keys(2)
        (vip_level, item._2, dt, dn)
      }).toDF().coalesce(1).write.mode(SaveMode.Overwrite).insertInto("ads.ads_register_viplevelnum")

    //统计各memberlevel等级 支付金额前三的用户
    import org.apache.spark.sql.functions._
    result.withColumn("rownum", row_number().over(Window.partitionBy("dn", "memberlevel").orderBy(desc("paymoney"))))
      .where("rownum<4").orderBy("memberlevel", "rownum")
      .select("uid", "memberlevel", "register", "appregurl", "regsourcename", "adname"
        , "sitename", "vip_level", "paymoney", "rownum", "dt", "dn")
      .coalesce(1).write.mode(SaveMode.Overwrite).insertInto("ads.ads_register_top3memberpay")
  }


  /**
   * 统计各项指标 使用sql
   *
   * @param sparkSession
   */
  def queryDetailSql(sparkSession: SparkSession, dt: String) = {
    val appregurlCount = DwsMemberDao.queryAppregurlCount(sparkSession, dt)
    val siteNameCount = DwsMemberDao.querySiteNameCount(sparkSession, dt)
    val regsourceNameCount = DwsMemberDao.queryRegsourceNameCount(sparkSession, dt)
    val adNameCount = DwsMemberDao.queryAdNameCount(sparkSession, dt)
    val memberLevelCount = DwsMemberDao.queryMemberLevelCount(sparkSession, dt)
    val vipLevelCount = DwsMemberDao.queryMemberLevelCount(sparkSession, dt)
    val top3MemberLevelPayMoneyUser = DwsMemberDao.getTop3MemberLevelPayMoneyUser(sparkSession, dt).show()
  }
}
