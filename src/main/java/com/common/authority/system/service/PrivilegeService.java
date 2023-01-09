package com.common.authority.system.service;

import com.common.authority.common.bean.PageList;
import com.common.authority.common.bean.Paging;
import com.common.authority.system.vo.privilege.PrivilegeAddParamVo;
import com.common.authority.system.vo.privilege.PrivilegeDetailVo;
import com.common.authority.system.vo.privilege.PrivilegeQueryParamVo;

import java.util.List;

public interface PrivilegeService {
    int add(PrivilegeAddParamVo privilegeAddParamVo);

    int update(PrivilegeAddParamVo privilegeAddParamVo);

    PrivilegeDetailVo detail(Integer id);

    int delete(List<Integer> ids);

    PageList<PrivilegeDetailVo> list(PrivilegeQueryParamVo privilegeQueryParamVo, Paging paging);
}
