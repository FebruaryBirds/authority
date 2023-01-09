package com.common.authority.system.service;


import com.common.authority.common.bean.PageList;
import com.common.authority.common.bean.Paging;
import com.common.authority.system.vo.privilegemenufunc.PrivilegeMenuFunctionAddParamVo;
import com.common.authority.system.vo.privilegemenufunc.PrivilegeMenuFunctionDetailVo;
import com.common.authority.system.vo.privilegemenufunc.PrivilegeMenuFunctionQueryVo;

import java.util.List;

public interface PrivilegeMenuFunctionService {
    int add(PrivilegeMenuFunctionAddParamVo privilegeMenuFunctionVo);

    int update(PrivilegeMenuFunctionAddParamVo privilegeMenuFunctionVo);


    PrivilegeMenuFunctionDetailVo detail(Integer id);

    int delete(List<Integer> ids);

    PageList<PrivilegeMenuFunctionDetailVo> list(PrivilegeMenuFunctionQueryVo privilegeMenuFunctionQueryVo, Paging paging);
}
