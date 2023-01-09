package com.common.authority.system.entity;

import com.common.authority.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "privilege", description = "权限实体类")
public class PrivilegeEntity extends BaseEntity {
    @ApiModelProperty(value = "权限名称")
    private String name;
    @ApiModelProperty(value = "权限编码")
    private String code;
    @ApiModelProperty(value = "父级权限编码")
    private Integer parentId;
    @ApiModelProperty(value = "权限类别：1 菜单权限；2 操作权限；3 功能权限；4 数据权限")
    private Integer type;
}
