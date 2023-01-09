package com.common.authority.system.service;

import com.common.authority.common.bean.PageList;
import com.common.authority.common.bean.Paging;
import com.common.authority.system.vo.privilegedata.PrivilegeDataCategoryAddParamVo;
import com.common.authority.system.vo.privilegedata.PrivilegeDataCategoryDetailVo;
import com.common.authority.system.vo.privilegedata.PrivilegeDataCategoryQueryVo;

import java.util.List;

public interface PrivilegeDataCategoryService {

    int add(PrivilegeDataCategoryAddParamVo privilegeDataCategoryAddParamVo);

    int update(PrivilegeDataCategoryAddParamVo privilegeDataCategoryAddParamVo);

    PrivilegeDataCategoryDetailVo detail(Integer id);

    int delete(List<Integer> ids);

    PageList<PrivilegeDataCategoryDetailVo> list(PrivilegeDataCategoryQueryVo privilegeDataCategoryQueryVo, Paging paging);
}
