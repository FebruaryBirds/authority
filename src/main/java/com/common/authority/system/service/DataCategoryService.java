package com.common.authority.system.service;

import com.common.authority.common.bean.PageList;
import com.common.authority.common.bean.Paging;
import com.common.authority.system.vo.datacategory.DataCategoryAddParamVo;
import com.common.authority.system.vo.datacategory.DataCategoryDetailVo;
import com.common.authority.system.vo.datacategory.DataCategoryQueryVo;

import java.util.List;

public interface DataCategoryService {
    int add(DataCategoryAddParamVo paramVo);

    int update(DataCategoryAddParamVo paramVo);

    DataCategoryDetailVo detail(Integer id);

    int delete(List<Integer> ids);

    PageList<DataCategoryDetailVo> list(DataCategoryQueryVo queryVo, Paging paging);
}
