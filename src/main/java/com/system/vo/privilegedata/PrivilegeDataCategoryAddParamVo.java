package com.system.vo.privilegedata;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "PrivilegeDataCategoryAddParamVo", description = "权限数据增改接口参数")
public class PrivilegeDataCategoryAddParamVo {
    @ApiModelProperty(value = "权限ID")
    private Integer privilegeId;
    @ApiModelProperty(value = "数据目录ID")
    private List<Integer> dataCategoryIdList;
}
