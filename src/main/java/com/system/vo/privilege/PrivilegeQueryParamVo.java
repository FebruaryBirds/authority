package com.system.vo.privilege;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class PrivilegeQueryParamVo {
    @ApiModelProperty(value = "权限名称")
    private String name;
    @ApiModelProperty(value = "权限编码")
    private String code;
    @ApiModelProperty(value = "父级权限编码")
    private Integer parentId;
    @ApiModelProperty(value = "权限类别：1 菜单权限；2 操作权限；3 功能权限；4 数据权限")
    private Integer type;
}
