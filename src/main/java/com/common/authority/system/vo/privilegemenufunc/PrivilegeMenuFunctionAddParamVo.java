package com.common.authority.system.vo.privilegemenufunc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "PrivilegeMenuFunctionVo", description = "权限菜单增改接口参数")
public class PrivilegeMenuFunctionAddParamVo {
    @ApiModelProperty(value = "权限ID")
    private Integer privilegeId;
    @ApiModelProperty(value = "菜单/功能ID")
    private List<Integer> menuFunctionIdList;
}
