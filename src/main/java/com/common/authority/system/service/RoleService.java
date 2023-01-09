package com.common.authority.system.service;

import com.common.authority.common.bean.PageList;
import com.common.authority.common.bean.Paging;
import com.common.authority.system.vo.role.RoleDetailResultVo;
import com.common.authority.system.vo.role.RoleParamVo;

import java.util.List;

public interface RoleService {
    int add(RoleParamVo roleParamVo);

    int update(RoleParamVo roleParamVo);

    RoleDetailResultVo detail(Integer id);

    int delete(List<Integer> ids);

    PageList<RoleDetailResultVo> list(RoleParamVo roleParamVo, Paging paging);
}
