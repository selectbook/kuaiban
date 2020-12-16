package com.catelf.member.dao

import org.apache.spark.sql.SparkSession

object DwdMemberDao {
  def getDwdMember(sparkSession: SparkSession) = {
    sparkSession.sql("select uid,ad_id,email,fullname,iconurl,lastlogin,mailaddr,memberlevel," +
      "password,phone,qq,register,regupdatetime,unitname,userip,zipcode,dt,dn from dwd.dwd_member")
  }

  def getDwdMemberRegType(sparkSession: SparkSession) = {
    sparkSession.sql("select uid,appkey,appregurl,bdp_uuid,createtime as reg_createtime,isranreg," +
      "regsource,regsourcename,websiteid as siteid,dn from dwd.dwd_member_regtype ")
  }

  def getDwdBaseAd(sparkSession: SparkSession) = {
    sparkSession.sql("select adid as ad_id,adname,dn from dwd.dwd_base_ad")
  }

  def getDwdBaseWebSite(sparkSession: SparkSession) = {
    sparkSession.sql("select siteid,sitename,siteurl,delete as site_delete," +
      "createtime as site_createtime,creator as site_creator,dn from dwd.dwd_base_website")
  }

  def getDwdVipLevel(sparkSession: SparkSession) = {
    sparkSession.sql("select vip_id,vip_level,start_time as vip_start_time,end_time as vip_end_time," +
      "last_modify_time as vip_last_modify_time,max_free as vip_max_free,min_free as vip_min_free," +
      "next_level as vip_next_level,operator as vip_operator,dn from dwd.dwd_vip_level")
  }

  def getDwdPcentermemPayMoney(sparkSession: SparkSession) = {
    sparkSession.sql("select uid,cast(paymoney as decimal(10,4)) as paymoney,vip_id,dn from dwd.dwd_pcentermempaymoney")
  }
}
