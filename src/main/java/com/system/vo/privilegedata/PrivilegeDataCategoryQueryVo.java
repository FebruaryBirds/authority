package com.system.vo.privilegedata;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "PrivilegeDataCategoryQueryVo", description = "权限数据查询接口参数")
public class PrivilegeDataCategoryQueryVo {
    @ApiModelProperty(value = "权限ID")
    private Integer privilegeId;
}
