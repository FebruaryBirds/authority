package com.system.entity;

import com.common.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "UserRole", description = "用户角色实体类")
public class UserRoleEntity extends BaseEntity {
    private Integer userId;
    private Integer roleId;
}
