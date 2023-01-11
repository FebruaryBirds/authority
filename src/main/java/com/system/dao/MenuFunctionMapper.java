package com.system.dao;

import com.system.entity.MenuFunctionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MenuFunctionMapper {
    Integer insert(MenuFunctionEntity privilegeEntity);

    MenuFunctionEntity getByParam(Map<String, Object> param);

    List<MenuFunctionEntity> listByParam(Map<String, Object> param);

    Integer update(MenuFunctionEntity privilegeEntity);

    Integer delete(List<Integer> ids);
}
