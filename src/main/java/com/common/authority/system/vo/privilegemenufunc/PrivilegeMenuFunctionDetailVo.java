package com.common.authority.system.vo.privilegemenufunc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "PrivilegeMenuFunctionDetailVo", description = "权限菜单详情接口参数")
public class PrivilegeMenuFunctionDetailVo {
    @ApiModelProperty(value = "权限ID")
    private Integer privilegeId;
    @ApiModelProperty(value = "菜单/功能ID")
    private List<Integer> menuFunctionId;
}
