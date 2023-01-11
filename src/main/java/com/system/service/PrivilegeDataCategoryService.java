package com.system.service;

import com.common.common.bean.PageList;
import com.common.common.bean.Paging;
import com.system.vo.privilegedata.PrivilegeDataCategoryAddParamVo;
import com.system.vo.privilegedata.PrivilegeDataCategoryDetailVo;
import com.system.vo.privilegedata.PrivilegeDataCategoryQueryVo;

import java.util.List;

public interface PrivilegeDataCategoryService {

    int add(PrivilegeDataCategoryAddParamVo privilegeDataCategoryAddParamVo);

    int update(PrivilegeDataCategoryAddParamVo privilegeDataCategoryAddParamVo);

    PrivilegeDataCategoryDetailVo detail(Integer id);

    int delete(List<Integer> ids);

    PageList<PrivilegeDataCategoryDetailVo> list(PrivilegeDataCategoryQueryVo privilegeDataCategoryQueryVo, Paging paging);
}
