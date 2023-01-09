package com.common.authority.system.dao;

import com.common.authority.system.entity.PrivilegeMenuFunctionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PrivilegeMenuFunctionMapper {
    Integer insert(PrivilegeMenuFunctionEntity privilegeMenuFunctionEntity);

    Integer batchInsert(List<PrivilegeMenuFunctionEntity> list);

    PrivilegeMenuFunctionEntity getByParam(Map<String, Object> param);

    List<PrivilegeMenuFunctionEntity> listByParam(Map<String, Object> param);

    Integer update(PrivilegeMenuFunctionEntity privilegeMenuFunctionEntity);

    Integer delete(List<Integer> ids);

    void deleteByParam(Integer privilegeId);
}
