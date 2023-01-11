package com.system.dao;

import com.system.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    Integer insert(UserEntity userEntity);

    UserEntity getById(Integer id);

    UserEntity getByParam(Map<String, Object> param);

    Integer update(UserEntity userEntity);

    Integer delete(List<Integer> ids);
}
