package com.common.authority.system.service.impl;

import com.common.authority.system.dao.UserMapper;
import com.common.authority.system.entity.UserEntity;
import com.common.authority.system.service.UserService;
import com.common.authority.system.vo.user.UserDetailResultVo;
import com.common.authority.system.vo.user.UserRegisterParamVo;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int register(UserRegisterParamVo userRegisterParamVo) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userRegisterParamVo, userEntity);
        userEntity.setDeleted(0);
        userEntity.setCreateTime(new Date());
        userEntity.setUpdateTime(new Date());
        userEntity.setCreateUser(1L);
        userEntity.setUpdateUser(1L);
        userEntity.setPassword(BCrypt.hashpw(userRegisterParamVo.getPassword().trim(), BCrypt.gensalt()));
        return userMapper.insert(userEntity);
    }

    @Override
    public UserDetailResultVo detail(Integer id) {
        UserEntity userEntity = userMapper.getById(id);
        UserDetailResultVo userDetailResultVo = new UserDetailResultVo();
        BeanUtils.copyProperties(userEntity, userDetailResultVo);
        return userDetailResultVo;
    }

    @Override
    public UserEntity getByAccount(String account) {
        Map<String, Object> param = new HashMap<>();
        param.put("account", account);
        UserEntity userEntity = userMapper.getByParam(param);
        return userEntity;
    }

    @Override
    public int update(UserRegisterParamVo userRegisterParamVo) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userRegisterParamVo, userEntity);
        userEntity.setUpdateTime(new Date());
        userEntity.setUpdateUser(1L);
        userEntity.setPassword(BCrypt.hashpw(userRegisterParamVo.getPassword().trim(), BCrypt.gensalt()));
        return userMapper.update(userEntity);
    }
}
