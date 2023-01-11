package com.system.vo.userrole;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel()
public class UserRoleParamVo {
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    @ApiModelProperty(value = "角色id列表")
    private List<Integer> roleIdList;
}
