package com.catelf.qz.bean

case class DwdQzQuestion(questionid: Int, parentid: Int, questypeid: Int, quesviewtype: Int, content: String, answer: String,
                         analysis: String, limitminute: String, scoe: BigDecimal, splitcore: BigDecimal, status: String,
                         optnum: Int, lecture: String, creator: String, createtime: String, modifystatus: String,
                         attanswer: String, questag: String, vanalysisaddr: String, difficulty: String, quesskill: String,
                         vdeoaddr: String, dt: String, dn: String)
