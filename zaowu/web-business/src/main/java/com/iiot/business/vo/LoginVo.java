package com.iiot.business.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户登录")
public class LoginVo {

    /**
     * 账号
     */
    @ApiModelProperty(name = "phone", value = "手机号", required = true, dataType = "String", example = "18702515445")
    private String phone;

    /**
     * 密码
     */
    @ApiModelProperty(name = "password", value = "密码", required = true, dataType = "String", example = "le123456")
    private String password;
}
