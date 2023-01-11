package com.system.entity;

import com.common.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "dataCategory", description = "数据目录实体类")
public class DataCategoryEntity extends BaseEntity {
    @ApiModelProperty(value = "数据类型")
    private Integer type;
    @ApiModelProperty(value = "数据目录")
    private String category;
    @ApiModelProperty(value = "数据区域")
    private String dataRegion;
}
