package com.system.entity;

import com.common.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "privilegeMenuFunction", description = "权限菜单功能实体类")
public class PrivilegeMenuFunctionEntity extends BaseEntity {
    @ApiModelProperty(value = "权限ID")
    private Integer privilegeId;
    @ApiModelProperty(value = "菜单/功能ID")
    private Integer menuFunctionId;
}
