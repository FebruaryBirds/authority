package com.system.service;


import com.common.common.bean.PageList;
import com.common.common.bean.Paging;
import com.system.vo.privilegemenufunc.PrivilegeMenuFunctionAddParamVo;
import com.system.vo.privilegemenufunc.PrivilegeMenuFunctionDetailVo;
import com.system.vo.privilegemenufunc.PrivilegeMenuFunctionQueryVo;

import java.util.List;

public interface PrivilegeMenuFunctionService {
    int add(PrivilegeMenuFunctionAddParamVo privilegeMenuFunctionVo);

    int update(PrivilegeMenuFunctionAddParamVo privilegeMenuFunctionVo);


    PrivilegeMenuFunctionDetailVo detail(Integer id);

    int delete(List<Integer> ids);

    PageList<PrivilegeMenuFunctionDetailVo> list(PrivilegeMenuFunctionQueryVo privilegeMenuFunctionQueryVo, Paging paging);
}
