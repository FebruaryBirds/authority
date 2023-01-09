package com.common.authority.system.service;

import com.common.authority.common.bean.PageList;
import com.common.authority.common.bean.Paging;
import com.common.authority.system.vo.menufunction.MenuFunctionAddParamVo;
import com.common.authority.system.vo.menufunction.MenuFunctionDetailVo;
import com.common.authority.system.vo.menufunction.MenuFunctionQueryParamVo;

import java.util.List;

public interface MenuFunctionService {
    int add(MenuFunctionAddParamVo menuFunctionAddParamVo);

    int update(MenuFunctionAddParamVo menuFunctionAddParamVo);

    MenuFunctionDetailVo detail(Integer id);

    int delete(List<Integer> ids);

    PageList<MenuFunctionDetailVo> list(MenuFunctionQueryParamVo menuFunctionQueryParamVo, Paging paging);
}
