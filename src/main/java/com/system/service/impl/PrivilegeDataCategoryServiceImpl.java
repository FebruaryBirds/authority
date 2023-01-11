package com.system.service.impl;

import com.common.common.bean.PageList;
import com.common.common.bean.Paging;
import com.system.dao.PrivilegeDataCategoryMapper;
import com.system.entity.PrivilegeDataCategoryEntity;
import com.system.service.PrivilegeDataCategoryService;
import com.system.vo.privilegedata.PrivilegeDataCategoryAddParamVo;
import com.system.vo.privilegedata.PrivilegeDataCategoryDetailVo;
import com.system.vo.privilegedata.PrivilegeDataCategoryQueryVo;
import com.common.verify.service.TokenInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PrivilegeDataCategoryServiceImpl implements PrivilegeDataCategoryService {
    @Resource
    private PrivilegeDataCategoryMapper mapper;
    @Resource
    private TokenInfoService tokenInfoService;

    @Override
    public int add(PrivilegeDataCategoryAddParamVo privilegeDataCategoryAddParamVo) {
        List<PrivilegeDataCategoryEntity> list = new ArrayList<>();
        Integer privilegeId = privilegeDataCategoryAddParamVo.getPrivilegeId();
        for (Integer dataCategoryId : privilegeDataCategoryAddParamVo.getDataCategoryIdList()) {
            PrivilegeDataCategoryEntity privilegeDataCategoryEntity = new PrivilegeDataCategoryEntity();
            privilegeDataCategoryEntity.setPrivilegeId(privilegeId);
            privilegeDataCategoryEntity.setDataCatId(dataCategoryId);
            setProperties(privilegeDataCategoryEntity, true);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public int update(PrivilegeDataCategoryAddParamVo privilegeDataCategoryAddParamVo) {
        Integer privilegeId = privilegeDataCategoryAddParamVo.getPrivilegeId();

        mapper.deleteByParam(privilegeId);
        return add(privilegeDataCategoryAddParamVo);
    }

    @Override
    public PrivilegeDataCategoryDetailVo detail(Integer privilegeId) {
        Map<String, Object> param = new HashMap<>();
        param.put("privilegeId", privilegeId);
        List<PrivilegeDataCategoryEntity> list = mapper.listByParam(param);
        PrivilegeDataCategoryDetailVo privilegeDataCategoryDetailVo = new PrivilegeDataCategoryDetailVo();
        if (CollectionUtils.isEmpty(list)) {
            return privilegeDataCategoryDetailVo;
        }
        List<Integer> dataCategoryIdList = list.stream().map(PrivilegeDataCategoryEntity::getDataCatId).collect(Collectors.toList());
        privilegeDataCategoryDetailVo.setPrivilegeId(privilegeId);
        privilegeDataCategoryDetailVo.setDataCategoryIdList(dataCategoryIdList);
        return null;
    }

    @Override
    public int delete(List<Integer> ids) {
        return mapper.delete(ids);
    }

    @Override
    public PageList<PrivilegeDataCategoryDetailVo> list(PrivilegeDataCategoryQueryVo privilegeDataCategoryQueryVo, Paging paging) {
        return null;
    }

    private void setProperties(PrivilegeDataCategoryEntity privilegeDataCategoryEntity, boolean create) {
        if (create) {
            privilegeDataCategoryEntity.setCreateTime(new Date());
            if (tokenInfoService.getTokenInfo() != null) {
                privilegeDataCategoryEntity.setCreateUser(tokenInfoService.getTokenInfo().getUserId());
            }
        }
        privilegeDataCategoryEntity.setUpdateTime(new Date());
        if (tokenInfoService.getTokenInfo() != null) {
            privilegeDataCategoryEntity.setUpdateUser(tokenInfoService.getTokenInfo().getUserId());
        }
    }
}
