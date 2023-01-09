package com.common.authority.system.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


@ApiModel
@Data
public class LoginVo {
    @NotEmpty(message="账号不能为空")
    @ApiModelProperty(value = "账号")
    private String account;
    @NotEmpty(message="密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;
}
