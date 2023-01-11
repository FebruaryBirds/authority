package com.system.service;

import com.common.common.bean.PageList;
import com.common.common.bean.Paging;
import com.system.vo.menufunction.MenuFunctionAddParamVo;
import com.system.vo.menufunction.MenuFunctionDetailVo;
import com.system.vo.menufunction.MenuFunctionQueryParamVo;

import java.util.List;

public interface MenuFunctionService {
    int add(MenuFunctionAddParamVo menuFunctionAddParamVo);

    int update(MenuFunctionAddParamVo menuFunctionAddParamVo);

    MenuFunctionDetailVo detail(Integer id);

    int delete(List<Integer> ids);

    PageList<MenuFunctionDetailVo> list(MenuFunctionQueryParamVo menuFunctionQueryParamVo, Paging paging);
}
