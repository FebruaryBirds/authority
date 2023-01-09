package com.common.authority.system.entity;

import com.common.authority.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "role", description = "角色实体类")
public class RoleEntity extends BaseEntity {
    private Integer parentId;
    private String code;
    private String name;
    private Integer type;
    private String remark;
}
