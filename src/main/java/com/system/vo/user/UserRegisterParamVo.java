package com.system.vo.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class UserRegisterParamVo {
    private Integer id;
    private String account;
    private String password;
    private String name;
    private Integer age;
    private Integer sex;
    private String phone;
    private String nickName;
    private String mail;
}
