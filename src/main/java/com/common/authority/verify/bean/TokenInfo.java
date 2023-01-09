package com.common.authority.verify.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class TokenInfo {
    @ApiModelProperty(value = "用户ID")
    private Long   userId;
    @ApiModelProperty(value = "用户姓名")
    private String    userName;
    @ApiModelProperty(value = "帐号")
    private String    account;
    @ApiModelProperty("登陆时间")
    private Long loginTime;
}
