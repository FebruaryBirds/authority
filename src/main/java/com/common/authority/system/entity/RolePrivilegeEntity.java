package com.common.authority.system.entity;

import com.common.authority.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "rolePrivilege", description = "角色权限实体类")
public class RolePrivilegeEntity extends BaseEntity {
    @ApiModelProperty(value = "角色id")
    private Integer roleId;
    @ApiModelProperty(value = "权限ID")
    private Integer privilegeId;
}
