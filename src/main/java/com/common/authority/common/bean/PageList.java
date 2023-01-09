package com.common.authority.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class PageList<E> {
    @ApiModelProperty(value = "总数")
    private long total;
    @ApiModelProperty(value = "页码")
    private int pageNum;
    @ApiModelProperty(value = "分页条数")
    private int pageSize;
    @ApiModelProperty(value = "数据集合")
    private List<E> rows;
}
