package com.common.authority.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.common.authority.common.bean.PageList;
import com.common.authority.common.bean.Paging;
import com.common.authority.common.util.MapUtil;
import com.common.authority.common.util.PageUtil;
import com.common.authority.system.dao.RoleMapper;
import com.common.authority.system.entity.RoleEntity;
import com.common.authority.system.service.RoleService;
import com.common.authority.system.vo.role.RoleDetailResultVo;
import com.common.authority.system.vo.role.RoleParamVo;
import com.common.authority.verify.service.TokenInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private TokenInfoService tokenInfoService;

    @Override
    public int add(RoleParamVo roleParamVo) {
        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(roleParamVo, roleEntity);
        roleEntity.setCreateTime(new Date());
        roleEntity.setUpdateTime(new Date());
        if (tokenInfoService.getTokenInfo() != null) {
            roleEntity.setCreateUser(tokenInfoService.getTokenInfo().getUserId());
            roleEntity.setUpdateUser(tokenInfoService.getTokenInfo().getUserId());
        }
        roleEntity.setDeleted(0);
        return roleMapper.insert(roleEntity);
    }

    @Override
    public int update(RoleParamVo roleParamVo) {
        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(roleParamVo, roleEntity);
        roleEntity.setUpdateTime(new Date());
        if (tokenInfoService.getTokenInfo() != null) {
            roleEntity.setUpdateUser(tokenInfoService.getTokenInfo().getUserId());
        }
        return roleMapper.update(roleEntity);
    }

    @Override
    public RoleDetailResultVo detail(Integer id) {
        RoleEntity roleEntity = roleMapper.getById(id);
        RoleDetailResultVo roleDetailResultVo = new RoleDetailResultVo();
        BeanUtils.copyProperties(roleEntity, roleDetailResultVo);
        return roleDetailResultVo;
    }

    @Override
    public int delete(List<Integer> ids) {
        return roleMapper.delete(ids);
    }

    @Override
    public PageList<RoleDetailResultVo> list(RoleParamVo roleParamVo, Paging paging) {
        Map<String, Object> param = MapUtil.formatMap(roleParamVo);
//        List<RoleEntity> roleEntityList = roleMapper.list(param);
        Page<RoleEntity> list = PageHelper.startPage(paging.getPageNum(), paging.getPageSize())
                .doSelectPage(() -> roleMapper.list(param));

        return PageUtil.parseList(PageUtil.parse(list), RoleDetailResultVo.class);
    }



}
