package com.common.authority.system.vo.roleprivilege;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class RolePrivilegeAddParamVo {
    @ApiModelProperty(value = "角色id")
    private Integer roleId;
    @ApiModelProperty(value = "权限ID")
    private Integer privilegeId;
}
