package com.system.entity;

import com.common.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "User", description = "用户实体类")
public class UserEntity extends BaseEntity {
    private String account;
    private String password;
    private String name;
    private Integer age;
    private Integer sex;
    private String phone;
    private String nickName;
    private String mail;
}
