package com.iiot.business.domain;

import com.iiot.common.annotation.Excel;
import com.iiot.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 报名信息对象 xt_registinfo
 *
 * @author soundhearer
 * @date 2020-11-28
 */
public class XtRegistinfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * null
     */
    private Long id;

    /**
     * 用户id
     */
    @Excel(name = "用户id")
    private Long userId;

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    private String userName;

    /**
     * 手机号
     */
    @Excel(name = "手机号")
    private String phonenumber;

    /**
     * 报名类型
     */
    @Excel(name = "报名类型 ")
    private String registType;

    /**
     * 年龄
     */
    @Excel(name = "年龄")
    private Long age;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱")
    private String email;

    /**
     * 学历
     */
    @Excel(name = "学历")
    private String degree;

    /**
     * 微信号
     */
    @Excel(name = "微信号")
    private String wechat;

    /**
     * 性别
     */
    @Excel(name = "性别")
    private String sex;

    /**
     * 身份证号
     */
    @Excel(name = "身份证号")
    private String idcard;

    /**
     * 考试科目
     */
    @Excel(name = "考试科目")
    private String subject;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setRegistType(String registType) {
        this.registType = registType;
    }

    public String getRegistType() {
        return registType;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getAge() {
        return age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getWechat() {
        return wechat;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getIdcard() {
        return idcard;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("userId", getUserId())
                .append("userName", getUserName())
                .append("phonenumber", getPhonenumber())
                .append("registType", getRegistType())
                .append("age", getAge())
                .append("email", getEmail())
                .append("degree", getDegree())
                .append("wechat", getWechat())
                .append("sex", getSex())
                .append("idcard", getIdcard())
                .append("subject", getSubject())
                .toString();
    }
}
