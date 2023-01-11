package com.system.service;

import com.common.common.bean.PageList;
import com.common.common.bean.Paging;
import com.system.vo.privilege.PrivilegeAddParamVo;
import com.system.vo.privilege.PrivilegeDetailVo;
import com.system.vo.privilege.PrivilegeQueryParamVo;

import java.util.List;

public interface PrivilegeService {
    int add(PrivilegeAddParamVo privilegeAddParamVo);

    int update(PrivilegeAddParamVo privilegeAddParamVo);

    PrivilegeDetailVo detail(Integer id);

    int delete(List<Integer> ids);

    PageList<PrivilegeDetailVo> list(PrivilegeQueryParamVo privilegeQueryParamVo, Paging paging);
}
