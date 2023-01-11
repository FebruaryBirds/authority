package com.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.common.common.bean.PageList;
import com.common.common.bean.Paging;
import com.common.common.util.BeanUtil;
import com.common.common.util.MapUtil;
import com.common.common.util.PageUtil;
import com.system.dao.PrivilegeMapper;
import com.system.entity.PrivilegeEntity;
import com.system.service.PrivilegeService;
import com.system.vo.privilege.PrivilegeAddParamVo;
import com.system.vo.privilege.PrivilegeDetailVo;
import com.system.vo.privilege.PrivilegeQueryParamVo;
import com.common.verify.service.TokenInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PrivilegeServiceImpl implements PrivilegeService {

    @Resource
    private PrivilegeMapper privilegeMapper;
    @Resource
    private TokenInfoService tokenInfoService;


    @Override
    public int add(PrivilegeAddParamVo privilegeAddParamVo) {
        PrivilegeEntity privilegeEntity = BeanUtil.copyProperties(privilegeAddParamVo, PrivilegeEntity.class);
        setProperties(privilegeEntity, true);
        return privilegeMapper.insert(privilegeEntity);
    }

    @Override
    public int update(PrivilegeAddParamVo privilegeAddParamVo) {
        PrivilegeEntity privilegeEntity = BeanUtil.copyProperties(privilegeAddParamVo, PrivilegeEntity.class);
        setProperties(privilegeEntity, false);
        return privilegeMapper.update(privilegeEntity);
    }

    @Override
    public PrivilegeDetailVo detail(Integer id) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        PrivilegeEntity privilegeEntity = privilegeMapper.getByParam(param);
        return BeanUtil.copyProperties(privilegeEntity, PrivilegeDetailVo.class);
    }

    @Override
    public int delete(List<Integer> ids) {
        return privilegeMapper.delete(ids);
    }

    @Override
    public PageList<PrivilegeDetailVo> list(PrivilegeQueryParamVo privilegeQueryParamVo, Paging paging) {
        Map<String, Object> param = MapUtil.formatMap(privilegeQueryParamVo);
        Page<PrivilegeEntity> list = PageHelper.startPage(paging.getPageNum(), paging.getPageSize())
                .doSelectPage(()->privilegeMapper.listByParam(param));
        if (CollectionUtils.isEmpty(list)) {
            return new PageList<>();
        }
        return PageUtil.parseList(PageUtil.parse(list), PrivilegeDetailVo.class);
    }

    private void setProperties(PrivilegeEntity privilegeEntity, boolean create) {
        if (create) {
            privilegeEntity.setCreateTime(new Date());
            if (tokenInfoService.getTokenInfo() != null) {
                privilegeEntity.setCreateUser(tokenInfoService.getTokenInfo().getUserId());
            }
        }
        privilegeEntity.setUpdateTime(new Date());
        if (tokenInfoService.getTokenInfo() != null) {
            privilegeEntity.setUpdateUser(tokenInfoService.getTokenInfo().getUserId());
        }
        privilegeEntity.setDeleted(0);
    }
}
