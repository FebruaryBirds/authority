package com.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.common.common.bean.PageList;
import com.common.common.bean.Paging;
import com.common.common.util.BeanUtil;
import com.common.common.util.MapUtil;
import com.common.common.util.PageUtil;
import com.system.dao.DataCategoryMapper;
import com.system.entity.DataCategoryEntity;
import com.system.service.DataCategoryService;
import com.system.vo.datacategory.DataCategoryAddParamVo;
import com.system.vo.datacategory.DataCategoryDetailVo;
import com.system.vo.datacategory.DataCategoryQueryVo;
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
