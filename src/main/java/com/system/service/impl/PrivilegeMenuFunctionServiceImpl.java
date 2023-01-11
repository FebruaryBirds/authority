package com.system.service.impl;

import com.common.common.bean.PageList;
import com.common.common.bean.Paging;
import com.system.dao.PrivilegeMenuFunctionMapper;
import com.system.entity.PrivilegeMenuFunctionEntity;
import com.system.service.PrivilegeMenuFunctionService;
import com.system.vo.privilegemenufunc.PrivilegeMenuFunctionAddParamVo;
import com.system.vo.privilegemenufunc.PrivilegeMenuFunctionDetailVo;
import com.system.vo.privilegemenufunc.PrivilegeMenuFunctionQueryVo;
import com.common.verify.service.TokenInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PrivilegeMenuFunctionServiceImpl implements PrivilegeMenuFunctionService {
    @Resource
    private PrivilegeMenuFunctionMapper mapper;
    @Resource
    private TokenInfoService tokenInfoService;

    @Override
    public int add(PrivilegeMenuFunctionAddParamVo privilegeMenuFunctionVo) {
        List<PrivilegeMenuFunctionEntity> list = new ArrayList<>();
        Integer privilegeId = privilegeMenuFunctionVo.getPrivilegeId();
        for (Integer menuFunctionId : privilegeMenuFunctionVo.getMenuFunctionIdList()) {
            PrivilegeMenuFunctionEntity privilegeMenuFunctionEntity = new PrivilegeMenuFunctionEntity();
            privilegeMenuFunctionEntity.setPrivilegeId(privilegeId);
            privilegeMenuFunctionEntity.setMenuFunctionId(menuFunctionId);
            setProperties(privilegeMenuFunctionEntity, true);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public int update(PrivilegeMenuFunctionAddParamVo privilegeMenuFunctionVo) {
        Integer privilegeId = privilegeMenuFunctionVo.getPrivilegeId();

        mapper.deleteByParam(privilegeId);

        return add(privilegeMenuFunctionVo);
    }

    @Override
    public PrivilegeMenuFunctionDetailVo detail(Integer privilegeId) {
        Map<String, Object> param = new HashMap<>();
        param.put("privilegeId", privilegeId);
        List<PrivilegeMenuFunctionEntity> list = mapper.listByParam(param);
        PrivilegeMenuFunctionDetailVo privilegeMenuFunctionDetailVo = new PrivilegeMenuFunctionDetailVo();
        if (CollectionUtils.isEmpty(list)) {
            return privilegeMenuFunctionDetailVo;
        }
        List<Integer> menuFunctionIdList = list.stream().map(PrivilegeMenuFunctionEntity::getMenuFunctionId).collect(Collectors.toList());
        privilegeMenuFunctionDetailVo.setPrivilegeId(privilegeId);
        privilegeMenuFunctionDetailVo.setMenuFunctionId(menuFunctionIdList);
        return privilegeMenuFunctionDetailVo;
    }

    @Override
    public int delete(List<Integer> ids) {
        return mapper.delete(ids);
    }

    @Override
    public PageList<PrivilegeMenuFunctionDetailVo> list(PrivilegeMenuFunctionQueryVo privilegeMenuFunctionQueryVo, Paging paging) {
        return null;
    }

    private void setProperties(PrivilegeMenuFunctionEntity privilegeMenuFunctionEntity, boolean create) {
        if (create) {
            privilegeMenuFunctionEntity.setCreateTime(new Date());
            if (tokenInfoService.getTokenInfo() != null) {
                privilegeMenuFunctionEntity.setCreateUser(tokenInfoService.getTokenInfo().getUserId());
            }
        }
        privilegeMenuFunctionEntity.setUpdateTime(new Date());
        if (tokenInfoService.getTokenInfo() != null) {
            privilegeMenuFunctionEntity.setUpdateUser(tokenInfoService.getTokenInfo().getUserId());
        }
    }
}
