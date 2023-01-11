package com.system.entity;

import com.common.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "privilegeData", description = "权限数据表")
public class PrivilegeDataCategoryEntity extends BaseEntity {
    @ApiModelProperty(value = "权限ID")
    private Integer privilegeId;
    @ApiModelProperty(value = "数据目录信息ID")
    private Integer dataCatId;
}
