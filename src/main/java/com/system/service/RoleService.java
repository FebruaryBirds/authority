package com.system.service;

import com.common.common.bean.PageList;
import com.common.common.bean.Paging;
import com.system.vo.role.RoleDetailResultVo;
import com.system.vo.role.RoleParamVo;

import java.util.List;

public interface RoleService {
    int add(RoleParamVo roleParamVo);

    int update(RoleParamVo roleParamVo);

    RoleDetailResultVo detail(Integer id);

    int delete(List<Integer> ids);

    PageList<RoleDetailResultVo> list(RoleParamVo roleParamVo, Paging paging);
}
