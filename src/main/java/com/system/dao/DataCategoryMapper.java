package com.system.dao;

import com.system.entity.DataCategoryEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DataCategoryMapper {
    Integer insert(DataCategoryEntity dataCategoryEntity);

    DataCategoryEntity getByParam(Map<String, Object> param);

    List<DataCategoryEntity> listByParam(Map<String, Object> param);

    Integer update(DataCategoryEntity dataCategoryEntity);

    Integer delete(List<Integer> ids);
}
