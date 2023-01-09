package com.common.authority.system.service;

import com.common.authority.system.entity.UserEntity;
import com.common.authority.system.vo.user.UserDetailResultVo;
import com.common.authority.system.vo.user.UserRegisterParamVo;

/**
 * 用户接口
 */
public interface UserService {
    int register(UserRegisterParamVo userRegisterParamVo);

    UserDetailResultVo detail(Integer id);

    UserEntity getByAccount(String account);

    int update(UserRegisterParamVo userRegisterParamVo);
}
