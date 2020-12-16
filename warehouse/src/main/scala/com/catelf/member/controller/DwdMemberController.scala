package com.catelf.member.controller

import com.catelf.member.service.EtlDataService
import com.catelf.util.HiveUtil
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object DwdMemberController {
  def main(args: Array[String]): Unit = {
    System.setProperty("HADOOP_USER_NAME", "hdfs")
    val sparkConf = new SparkConf().setAppName("dwd_member_import").setMaster("local[*]")
    val sparkSession = SparkSession.builder().config(sparkConf).enableHiveSupport().getOrCreate()
    val ssc = sparkSession.sparkContext
    HiveUtil.openDynamicPartition(sparkSession) //开启动态分区
    HiveUtil.openCompression(sparkSession) //开启压缩
    HiveUtil.useSnappyCompression(sparkSession) //使用snappy压缩
    //对用户原始数据进行数据清洗 存入bdl层表中
    EtlDataService.etlBaseAdLog(ssc, sparkSession) //导入基础广告表数据
    EtlDataService.etlBaseWebSiteLog(ssc, sparkSession) //导入基础网站表数据
    EtlDataService.etlMemberLog(ssc, sparkSession) //清洗用户数据
    EtlDataService.etlMemberRegtypeLog(ssc, sparkSession) //清洗用户注册数据
    EtlDataService.etlMemPayMoneyLog(ssc, sparkSession) //导入用户支付情况记录
    EtlDataService.etlMemVipLevelLog(ssc, sparkSession) //导入vip基础数据
  }
}
