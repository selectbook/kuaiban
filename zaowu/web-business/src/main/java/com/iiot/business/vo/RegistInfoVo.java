package com.iiot.business.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "报名信息")
public class RegistInfoVo {

    /**
     * 姓名
     */
    @ApiModelProperty(name = "userName", value = "姓名", required = true, dataType = "String", example = "黄乐平")
    private String userName;

    /**
     * 年龄
     */
    @ApiModelProperty(name = "age", value = "年龄", required = true, dataType = "Long", example = "28")
    private Long age;

    /**
     * 手机号
     */
    @ApiModelProperty(name = "phonenumber", value = "手机号", required = true, dataType = "String", example = "18702515445")
    private String phonenumber;


    /**
     * 报名类型
     */
    @ApiModelProperty(name = "registType", value = "报名类型", required = false, dataType = "String", example = "机器人工程技术")
    private String registType;


    /**
     * 邮箱
     */
    @ApiModelProperty(name = "email", value = "邮箱", required = false, dataType = "String", example = "18702515445@163.com")
    private String email;


    /**
     * 学历
     */
    @ApiModelProperty(name = "degree", value = "学历", required = false, dataType = "String", example = "本科")
    private String degree;


    /**
     * 微信号
     */
    @ApiModelProperty(name = "wechat", value = "微信号", required = false, dataType = "String", example = "soundhearer")
    private String wechat;

    /**
     * 性别
     */
    @ApiModelProperty(name = "sex", value = "性别", required = false, dataType = "String", example = "男")
    private String sex;

    /**
     * 身份证号
     */
    @ApiModelProperty(name = "idcard", value = "身份证号", required = false, dataType = "String", example = "362525187567908790")
    private String idcard;

    /**
     * 考试科目
     */
    @ApiModelProperty(name = "subject", value = "考试科目", required = false, dataType = "String", example = "虚拟现实应用开发职业技能等级证书")
    private String subject;


}
