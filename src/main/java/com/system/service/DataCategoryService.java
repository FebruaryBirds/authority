package com.system.service;

import com.common.common.bean.PageList;
import com.common.common.bean.Paging;
import com.system.vo.datacategory.DataCategoryAddParamVo;
import com.system.vo.datacategory.DataCategoryDetailVo;
import com.system.vo.datacategory.DataCategoryQueryVo;

import java.util.List;

public interface DataCategoryService {
    int add(DataCategoryAddParamVo paramVo);

    int update(DataCategoryAddParamVo paramVo);

    DataCategoryDetailVo detail(Integer id);

    int delete(List<Integer> ids);

    PageList<DataCategoryDetailVo> list(DataCategoryQueryVo queryVo, Paging paging);
}
