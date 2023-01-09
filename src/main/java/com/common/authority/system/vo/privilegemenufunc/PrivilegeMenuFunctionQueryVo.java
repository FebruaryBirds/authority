package com.common.authority.system.vo.privilegemenufunc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "PrivilegeMenuFunctionQueryVo", description = "权限菜单查询接口参数")
public class PrivilegeMenuFunctionQueryVo {
    @ApiModelProperty(value = "权限ID")
    private Integer privilegeId;
}
