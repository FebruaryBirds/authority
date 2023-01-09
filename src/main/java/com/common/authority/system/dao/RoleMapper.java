package com.common.authority.system.dao;

import com.common.authority.system.entity.RoleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleMapper {
    Integer insert(RoleEntity roleEntity);

    RoleEntity getById(Integer id);

    RoleEntity getByParam(Map<String, Object> param);

    Integer update(RoleEntity roleEntity);

    List<RoleEntity> getByParentId(Integer parentId);

    Integer delete(List<Integer> ids);

    List<RoleEntity> list(Map<String, Object> param);
}
