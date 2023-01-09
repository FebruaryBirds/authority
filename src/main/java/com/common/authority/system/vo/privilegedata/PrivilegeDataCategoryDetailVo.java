package com.common.authority.system.vo.privilegedata;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "PrivilegeDataCategoryDetailVo", description = "权限数据详情接口参数")
public class PrivilegeDataCategoryDetailVo {
    @ApiModelProperty(value = "权限ID")
    private Integer privilegeId;
    @ApiModelProperty(value = "菜单/数据目录ID")
    private List<Integer> dataCategoryIdList;
}
