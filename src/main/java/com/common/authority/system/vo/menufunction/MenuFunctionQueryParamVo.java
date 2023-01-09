package com.common.authority.system.vo.menufunction;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class MenuFunctionQueryParamVo {
    @ApiModelProperty(value = "菜单/功能名称")
    private String name;
    @ApiModelProperty(value = "父级菜单ID")
    private Integer parentId;
    @ApiModelProperty(value = "菜单编码")
    private String code;
    @ApiModelProperty(value = "搜索码编码")
    private String searchCode;
    @ApiModelProperty(value = "全称")
    private String fullName;
    @ApiModelProperty(value = "类型：1.菜单；2.功能")
    private Integer type;
    @ApiModelProperty(value = "排序号")
    private Integer sort;
}
