package com.common.authority.system.dao;

import com.common.authority.system.entity.UserRoleEntity;
import com.common.authority.system.vo.userrole.RoleUserInfo;
import com.common.authority.system.vo.userrole.RoleUserResultVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRoleMapper {
    Integer insert(UserRoleEntity userRoleEntity);

    UserRoleEntity getById(Integer id);

    List<UserRoleEntity> getByParam(Map<String, Object> param);

    Integer update(UserRoleEntity userRoleEntity);

    Integer delete(List<Integer> ids);

    Integer deleteByParam(Map<String, Object> param);

    List<RoleUserInfo> getUserByRole(Integer roleId);

    Integer batchInsert(List<UserRoleEntity> userRoleEntityList);

    List<RoleUserResultVo> listRoleUser(Map<String, Object> param);
}
