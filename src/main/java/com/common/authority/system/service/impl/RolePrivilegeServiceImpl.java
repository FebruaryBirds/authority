package com.common.authority.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.common.authority.common.bean.PageList;
import com.common.authority.common.bean.Paging;
import com.common.authority.common.util.BeanUtil;
import com.common.authority.common.util.MapUtil;
import com.common.authority.common.util.PageUtil;
import com.common.authority.system.dao.RolePrivilegeMapper;
import com.common.authority.system.entity.RolePrivilegeEntity;
import com.common.authority.system.service.RolePrivilegeService;
import com.common.authority.system.vo.roleprivilege.RolePrivilegeAddParamVo;
import com.common.authority.system.vo.roleprivilege.RolePrivilegeDetailVo;
import com.common.authority.system.vo.roleprivilege.RolePrivilegeQueryParamVo;
import com.common.authority.verify.service.TokenInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RolePrivilegeServiceImpl implements RolePrivilegeService {

    @Resource
    private RolePrivilegeMapper rolePrivilegeMapper;
    @Resource
    private TokenInfoService tokenInfoService;

    @Override
    public int add(RolePrivilegeAddParamVo rolePrivilegeAddParamVo) {
        RolePrivilegeEntity rolePrivilegeEntity = BeanUtil.copyProperties(rolePrivilegeAddParamVo, RolePrivilegeEntity.class);
        setProperties(rolePrivilegeEntity, true);
        return rolePrivilegeMapper.insert(rolePrivilegeEntity);
    }

    @Override
    public int update(RolePrivilegeAddParamVo rolePrivilegeAddParamVo) {
        RolePrivilegeEntity rolePrivilegeEntity = BeanUtil.copyProperties(rolePrivilegeAddParamVo, RolePrivilegeEntity.class);
        setProperties(rolePrivilegeEntity, false);
        return rolePrivilegeMapper.update(rolePrivilegeEntity);
    }

    @Override
    public RolePrivilegeDetailVo detail(Integer id) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        RolePrivilegeEntity rolePrivilegeEntity = rolePrivilegeMapper.getByParam(param);
        return BeanUtil.copyProperties(rolePrivilegeEntity,RolePrivilegeDetailVo.class);
    }

    @Override
    public int delete(List<Integer> ids) {
        return rolePrivilegeMapper.delete(ids);
    }

    @Override
    public PageList<RolePrivilegeDetailVo> list(RolePrivilegeQueryParamVo rolePrivilegeQueryParamVo, Paging paging) {
        Map<String, Object> param = MapUtil.formatMap(rolePrivilegeQueryParamVo);
        Page<RolePrivilegeEntity> rolePrivilegeEntityPageList = PageHelper
                .startPage(paging.getPageNum(),paging.getPageSize())
                .doSelectPage(() -> rolePrivilegeMapper.getByParam(param));
        if (CollectionUtils.isEmpty(rolePrivilegeEntityPageList)) {
            return new PageList<>();
        }
        return PageUtil.parseList(PageUtil.parse(rolePrivilegeEntityPageList), RolePrivilegeDetailVo.class);
    }

    private void setProperties(RolePrivilegeEntity rolePrivilegeEntity, boolean create) {
        if (create) {
            rolePrivilegeEntity.setCreateTime(new Date());
            if (tokenInfoService.getTokenInfo() != null) {
                rolePrivilegeEntity.setCreateUser(tokenInfoService.getTokenInfo().getUserId());
            }
        }
        rolePrivilegeEntity.setUpdateTime(new Date());
        if (tokenInfoService.getTokenInfo() != null) {
            rolePrivilegeEntity.setUpdateUser(tokenInfoService.getTokenInfo().getUserId());
        }
    }
}
