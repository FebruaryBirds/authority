package com.common.common.bean;

import io.swagger.annotations.ApiModelProperty;

public class Paging {
    @ApiModelProperty(value = "页码")
    private Integer pageNum = 1;
    @ApiModelProperty(value = "每页个数")
    private Integer pageSize = 50;

    public Paging(Integer pageNum, Integer pageSize){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum==null?1:pageNum;
    }

    public Integer getPageSize() {
        return pageSize==null?50:pageSize;
    }
}
