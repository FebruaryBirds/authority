package com.common.authority.system.vo.datacategory;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class DataCategoryQueryVo {
    @ApiModelProperty(value = "数据类型")
    private Integer type;
    @ApiModelProperty(value = "数据目录")
    private String category;
    @ApiModelProperty(value = "数据区域")
    private String dataRegion;
}
