package com.common.authority.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class ResultData<T> {
    @ApiModelProperty(value = "状态码,正常：200， 业务异常：516")
    private int status = 200;
    @ApiModelProperty(value = "提示信息")
    private String message = "success";
    @ApiModelProperty(value = "其他信息")
    private T data;
    public ResultData() {
    }

    public ResultData(T data) {
        this.data = data;
        this.status = 200;
        this.message = "success";
    }

    public ResultData(Integer status, String message) {
        this.data = null;
        this.status = status;
        this.message = message;
    }

    public ResultData(Integer status, T data, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }
}
