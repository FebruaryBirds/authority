package com.common.authority.system.dao;

import com.common.authority.system.entity.PrivilegeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PrivilegeMapper {
    Integer insert(PrivilegeEntity privilegeEntity);

    PrivilegeEntity getByParam(Map<String, Object> param);

    List<PrivilegeEntity> listByParam(Map<String, Object> param);

    Integer update(PrivilegeEntity privilegeEntity);

    Integer delete(List<Integer> id);
}
