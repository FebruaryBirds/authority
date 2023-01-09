package com.common.authority.system.service;

import com.common.authority.common.bean.PageList;
import com.common.authority.common.bean.Paging;
import com.common.authority.system.vo.roleprivilege.RolePrivilegeAddParamVo;
import com.common.authority.system.vo.roleprivilege.RolePrivilegeDetailVo;
import com.common.authority.system.vo.roleprivilege.RolePrivilegeQueryParamVo;

import java.util.List;

public interface RolePrivilegeService {
    int add(RolePrivilegeAddParamVo rolePrivilegeAddParamVo);

    int update(RolePrivilegeAddParamVo rolePrivilegeAddParamVo);

    RolePrivilegeDetailVo detail(Integer id);

    int delete(List<Integer> ids);

    PageList<RolePrivilegeDetailVo> list(RolePrivilegeQueryParamVo rolePrivilegeQueryParamVo, Paging paging);
}
