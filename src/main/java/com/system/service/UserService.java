package com.system.service;

import com.system.entity.UserEntity;
import com.system.vo.user.UserDetailResultVo;
import com.system.vo.user.UserRegisterParamVo;

/**
 * 用户接口
 */
public interface UserService {
    int register(UserRegisterParamVo userRegisterParamVo);

    UserDetailResultVo detail(Integer id);

    UserEntity getByAccount(String account);

    int update(UserRegisterParamVo userRegisterParamVo);
}
