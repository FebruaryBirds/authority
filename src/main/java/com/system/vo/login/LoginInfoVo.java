package com.system.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class LoginInfoVo {
    @ApiModelProperty(value = "用户id")
    private Integer id;
    @ApiModelProperty(value = "用户名称")
    private String name;
    @ApiModelProperty(value = "登录账号")
    private String account;
    @ApiModelProperty(value = "令牌")
    private String token;
}
