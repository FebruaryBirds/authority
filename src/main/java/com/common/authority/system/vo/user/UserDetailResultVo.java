package com.common.authority.system.vo.user;

import com.common.authority.system.entity.UserEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "User", description = "用户信息")
public class UserDetailResultVo extends UserEntity {

}
