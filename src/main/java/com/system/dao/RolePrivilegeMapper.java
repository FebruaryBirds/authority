package com.system.dao;

import com.system.entity.RolePrivilegeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RolePrivilegeMapper {
    Integer insert(RolePrivilegeEntity rolePrivilegeEntity);

    RolePrivilegeEntity getByParam(Map<String, Object> param);

    List<RolePrivilegeEntity> listByParam(Map<String, Object> param);

    Integer update(RolePrivilegeEntity rolePrivilegeEntity);

    Integer delete(List<Integer> ids);
}
