package com.system.dao;

import com.system.entity.PrivilegeDataCategoryEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PrivilegeDataCategoryMapper {
    Integer insert(PrivilegeDataCategoryEntity privilegeDataCategoryEntity);

    PrivilegeDataCategoryEntity getByParam(Map<String, Object> param);

    List<PrivilegeDataCategoryEntity> listByParam(Map<String, Object> param);

    Integer update(PrivilegeDataCategoryEntity privilegeDataCategoryEntity);

    Integer delete(List<Integer> ids);

    int batchInsert(List<PrivilegeDataCategoryEntity> list);

    void deleteByParam(Integer privilegeId);
}
