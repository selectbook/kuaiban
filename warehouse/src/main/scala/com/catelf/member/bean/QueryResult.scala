package com.catelf.member.bean

case class QueryResult(uid: Int,
                       ad_id: Int,
                       memberlevel: String,
                       register: String,
                       appregurl: String, //注册来源url
                       regsource: String,
                       regsourcename: String,
                       adname: String,
                       siteid: String,
                       sitename: String,
                       vip_level: String,
                       paymoney: BigDecimal,
                       dt: String,
                       dn: String)
