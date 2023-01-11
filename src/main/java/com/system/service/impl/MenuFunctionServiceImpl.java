package com.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.common.common.bean.PageList;
import com.common.common.bean.Paging;
import com.common.common.util.BeanUtil;
import com.common.common.util.MapUtil;
import com.common.common.util.PageUtil;
import com.system.dao.MenuFunctionMapper;
import com.system.entity.MenuFunctionEntity;
import com.system.service.MenuFunctionService;
import com.system.vo.menufunction.MenuFunctionAddParamVo;
import com.system.vo.menufunction.MenuFunctionDetailVo;
import com.system.vo.menufunction.MenuFunctionQueryParamVo;
import com.common.verify.service.TokenInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MenuFunctionServiceImpl implements MenuFunctionService {
    @Resource
    private MenuFunctionMapper menuFunctionMapper;
    @Resource
    private TokenInfoService tokenInfoService;

    @Override
    public int add(MenuFunctionAddParamVo menuFunctionAddParamVo) {
        MenuFunctionEntity menuFunctionEntity = BeanUtil.copyProperties(menuFunctionAddParamVo, MenuFunctionEntity.class);
        setProperties(menuFunctionEntity,  true);
        return menuFunctionMapper.insert(menuFunctionEntity);
    }

    @Override
    public int update(MenuFunctionAddParamVo menuFunctionAddParamVo) {
        MenuFunctionEntity menuFunctionEntity = BeanUtil.copyProperties(menuFunctionAddParamVo, MenuFunctionEntity.class);
        setProperties(menuFunctionEntity,  false);
        return menuFunctionMapper.update(menuFunctionEntity);
    }

    @Override
    public MenuFunctionDetailVo detail(Integer id) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", param);
        MenuFunctionEntity menuFunctionEntity = menuFunctionMapper.getByParam(param);
        return BeanUtil.copyProperties(menuFunctionEntity, MenuFunctionDetailVo.class);
    }

    @Override
    public int delete(List<Integer> ids) {
        return menuFunctionMapper.delete(ids);
    }

    @Override
    public PageList<MenuFunctionDetailVo> list(MenuFunctionQueryParamVo menuFunctionQueryParamVo, Paging paging) {
        Map<String, Object> param = MapUtil.formatMap(menuFunctionQueryParamVo);
        Page<MenuFunctionEntity> pageList = PageHelper.startPage(paging.getPageNum(), paging.getPageSize())
                .doSelectPage(() -> menuFunctionMapper.listByParam(param));
        if (CollectionUtils.isEmpty(pageList)) {
            return new PageList<>();
        }
        return PageUtil.parseList(PageUtil.parse(pageList), MenuFunctionDetailVo.class);
    }


    private void setProperties(MenuFunctionEntity menuFunctionEntity, boolean create) {
        if (create) {
            menuFunctionEntity.setCreateTime(new Date());
            if (tokenInfoService.getTokenInfo() != null) {
                menuFunctionEntity.setCreateUser(tokenInfoService.getTokenInfo().getUserId());
            }
        }
        menuFunctionEntity.setUpdateTime(new Date());
        if (tokenInfoService.getTokenInfo() != null) {
            menuFunctionEntity.setUpdateUser(tokenInfoService.getTokenInfo().getUserId());
        }
        menuFunctionEntity.setDeleted(0);
    }
}
