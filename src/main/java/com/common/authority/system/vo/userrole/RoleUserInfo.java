package com.common.authority.system.vo.userrole;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class RoleUserInfo {
    private Integer id;
    private String account;
    private String name;
    private Integer age;
    private Integer sex;
    private String phone;
    private String nickName;
    private String mail;
}
