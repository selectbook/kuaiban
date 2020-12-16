package com.iiot.business.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "修改密码")
public class UpdatePassVo {
    /**
     * 用户ID
     */
    @ApiModelProperty(name = "userId", value = "用户ID", required = true, dataType = "Long", example = "102")
    private Long userID;

    /**
     * 密码
     */
    @ApiModelProperty(name = "password", value = "密码", required = true, dataType = "String", example = "le123456")
    private String password;

}
