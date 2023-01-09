package com.common.authority.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.common.authority.common.bean.PageList;
import com.common.authority.common.bean.Paging;
import com.common.authority.common.util.BeanUtil;
import com.common.authority.common.util.MapUtil;
import com.common.authority.common.util.PageUtil;
import com.common.authority.system.dao.DataCategoryMapper;
import com.common.authority.system.entity.DataCategoryEntity;
import com.common.authority.system.service.DataCategoryService;
import com.common.authority.system.vo.datacategory.DataCategoryAddParamVo;
import com.common.authority.system.vo.datacategory.DataCategoryDetailVo;
import com.common.authority.system.vo.datacategory.DataCategoryQueryVo;
import com.common.authority.verify.service.TokenInfoService;
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
public class DataCategoryServiceImpl implements DataCategoryService {
    @Resource
    private DataCategoryMapper dataCategoryMapper;
    @Resource
    private TokenInfoService tokenInfoService;

    @Override
    public int add(DataCategoryAddParamVo paramVo) {
        DataCategoryEntity dataCategoryEntity = BeanUtil.copyProperties(paramVo, DataCategoryEntity.class);
        setProperties(dataCategoryEntity, true);
        return dataCategoryMapper.insert(dataCategoryEntity);
    }

    @Override
    public int update(DataCategoryAddParamVo paramVo) {
        DataCategoryEntity dataCategoryEntity = BeanUtil.copyProperties(paramVo, DataCategoryEntity.class);
        setProperties(dataCategoryEntity, false);
        return dataCategoryMapper.update(dataCategoryEntity);
    }

    @Override
    public DataCategoryDetailVo detail(Integer id) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        DataCategoryEntity dataCategoryEntity = dataCategoryMapper.getByParam(param);

        return BeanUtil.copyProperties(dataCategoryEntity, DataCategoryDetailVo.class);
    }

    @Override
    public int delete(List<Integer> ids) {
        return dataCategoryMapper.delete(ids);
    }

    @Override
    public PageList<DataCategoryDetailVo> list(DataCategoryQueryVo queryVo, Paging paging) {
        Map<String, Object> param = MapUtil.formatMap(queryVo);
        Page<DataCategoryEntity> page = PageHelper.startPage(paging.getPageNum(), paging.getPageSize())
                .doSelectPage(() -> dataCategoryMapper.listByParam(param));
        if (CollectionUtils.isEmpty(page)) {
            return new PageList<>();
        }
        return PageUtil.parseList(PageUtil.parse(page), DataCategoryDetailVo.class);
    }

    private void setProperties(DataCategoryEntity dataCategoryEntity, boolean create) {
        if (create) {
            dataCategoryEntity.setCreateTime(new Date());
            if (tokenInfoService.getTokenInfo() != null) {
                dataCategoryEntity.setCreateUser(tokenInfoService.getTokenInfo().getUserId());
            }
        }
        dataCategoryEntity.setUpdateTime(new Date());
        if (tokenInfoService.getTokenInfo() != null) {
            dataCategoryEntity.setUpdateUser(tokenInfoService.getTokenInfo().getUserId());
        }
        dataCategoryEntity.setDeleted(0);
    }
}
