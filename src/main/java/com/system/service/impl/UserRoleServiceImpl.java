package com.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.common.common.bean.BusinessException;
import com.common.common.bean.PageList;
import com.common.common.bean.Paging;
import com.common.common.util.MapUtil;
import com.common.common.util.PageUtil;
import com.system.dao.UserRoleMapper;
import com.system.entity.UserRoleEntity;
import com.system.service.UserRoleService;
import com.system.vo.userrole.*;
import com.common.verify.service.TokenInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private TokenInfoService tokenInfoService;

    @Override
    public int userAddRole(UserRoleParamVo userRoleParamVo) {
        if (userRoleParamVo.getUserId() == null || CollectionUtils.isEmpty(userRoleParamVo.getRoleIdList())) {
            throw new BusinessException("用户ID和角色列表不能为空！");
        }
        List<UserRoleEntity> userRoleEntityList = new ArrayList<>();
        Integer userId = userRoleParamVo.getUserId();
        for (Integer roleId : userRoleParamVo.getRoleIdList()){
            userRoleEntityList.add(setEntity(userId, roleId));
        }
        return userRoleMapper.batchInsert(userRoleEntityList);
    }

    @Override
    public int roleAddUser(RoleUserParamVo roleUserParamVo) {
        if (roleUserParamVo.getRoleId() == null || CollectionUtils.isEmpty(roleUserParamVo.getUserIdList())) {
            throw new BusinessException("角色ID和用户列表不能为空！");
        }
        List<UserRoleEntity> userRoleEntityList = new ArrayList<>();
        Integer roleId = roleUserParamVo.getRoleId();
        for (Integer userId : roleUserParamVo.getUserIdList()) {
            userRoleEntityList.add(setEntity(userId, roleId));
        }
        return userRoleMapper.batchInsert(userRoleEntityList);
    }

    @Override
    public UserRoleResultVo userRole(Integer id) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", id);
        List<UserRoleEntity> userRoleEntityList = userRoleMapper.getByParam(param);

        UserRoleResultVo userRoleResultVo = new UserRoleResultVo();
        userRoleResultVo.setUserId(id);
        userRoleResultVo.setRoleIdList(userRoleEntityList.stream()
                .map(UserRoleEntity::getRoleId).collect(Collectors.toList()));

        return userRoleResultVo;
    }

    @Override
    public int delete(UserRoleParamVo userRoleParamVo) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userRoleParamVo.getUserId());
        int i = 0;
        for (Integer roleId : userRoleParamVo.getRoleIdList()) {
            param.put("roleId", roleId);
            i = userRoleMapper.deleteByParam(param);
        }
        return i;
    }

    @Override
    public PageList<RoleUserInfo> roleUser(Integer id, Paging paging) {

        Page<RoleUserInfo> list = PageHelper.startPage(paging.getPageNum(), paging.getPageSize())
                .doSelectPage(() -> userRoleMapper.getUserByRole(id));


        return PageUtil.parse(list);
    }

    @Override
    public PageList<RoleUserResultVo> roleList(RoleUserListParamVo roleUserListParamVo, Paging paging) {
        Page<RoleUserResultVo> list = PageHelper.startPage(paging.getPageNum(), paging.getPageSize())
                .doSelectPage(() -> userRoleMapper.listRoleUser(MapUtil.formatMap(roleUserListParamVo)));

        return PageUtil.parse(list);
    }

    private UserRoleEntity setEntity(Integer userId, Integer roleId) {
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserId(userId);
        userRoleEntity.setRoleId(roleId);
        userRoleEntity.setCreateTime(new Date());
        userRoleEntity.setUpdateTime(new Date());
        if (tokenInfoService.getTokenInfo() != null) {
            userRoleEntity.setCreateUser(tokenInfoService.getTokenInfo().getUserId());
            userRoleEntity.setUpdateUser(tokenInfoService.getTokenInfo().getUserId());
        }
        userRoleEntity.setDeleted(0);
        return userRoleEntity;
    }
}
