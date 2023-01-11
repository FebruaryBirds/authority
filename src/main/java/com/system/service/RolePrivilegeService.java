package com.system.service;

import com.common.common.bean.PageList;
import com.common.common.bean.Paging;
import com.system.vo.roleprivilege.RolePrivilegeAddParamVo;
import com.system.vo.roleprivilege.RolePrivilegeDetailVo;
import com.system.vo.roleprivilege.RolePrivilegeQueryParamVo;

import java.util.List;

public interface RolePrivilegeService {
    int add(RolePrivilegeAddParamVo rolePrivilegeAddParamVo);

    int update(RolePrivilegeAddParamVo rolePrivilegeAddParamVo);

    RolePrivilegeDetailVo detail(Integer id);

    int delete(List<Integer> ids);

    PageList<RolePrivilegeDetailVo> list(RolePrivilegeQueryParamVo rolePrivilegeQueryParamVo, Paging paging);
}
