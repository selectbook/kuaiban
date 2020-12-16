package com.iiot.business.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户登录")
public class RegisteVO {
    /**
     * 账号
     */
    @ApiModelProperty(name = "username", value = "账号", required = true, dataType = "String", example = "admin")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(name = "password", value = "密码", required = true, dataType = "String", example = "admin123")
    private String password;

    /**
     * 年龄
     */
    @ApiModelProperty(name = "age", value = "年龄", required = true, dataType = "int", example = "18")
    private int age;

    /**
     * 联系方式
     */
    @ApiModelProperty(name = "phonenumber", value = "联系方式", required = true, dataType = "String", example = "18702515445")
    private String phonenumber;

    /**
     * 邮箱
     */
    @ApiModelProperty(name = "email", value = "邮箱", required = true, dataType = "String", example = "18702515445@163.com")
    private String email;


    /**
     * 确认密码
     */
    @ApiModelProperty(name = "repassword", value = "确认密码", required = true, dataType = "String", example = "admin123")
    private String repassword;

}
