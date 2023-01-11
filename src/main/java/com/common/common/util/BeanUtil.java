package com.common.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
public class BeanUtil {
    public static <T> T copyProperties(Object source, Class<T> clazz) {
        if (null == source) {
            return null;
        }
        T obj;
        try {
            obj = clazz.newInstance();
        } catch (Exception e) {
            log.error(" toJSONString error：{}", e);
            return null;
        }
        BeanUtils.copyProperties(source, obj);
        return obj;
    }

    public static <T> List<T> copyCollection(Collection source, Class<T> clazz) {
        if (null == source) {
            return new ArrayList<>();
        }
        List<T> list = new ArrayList<>();
        for (Object o : source) {
            list.add(copyProperties(o, clazz));
        }
        return list;
    }

    public static <T> List<String> split(List<T> list, String field) {
        if (CollectionUtils.isEmpty(list) || StringUtils.isEmpty(field)) {
            return null;
        }
        List<String> ids = new ArrayList<>();
        for (T obj : list) {
            Object val = getDeclaredFields(obj, field);
            if (val == null || "".equals(val.toString())) {
                continue;
            }
            ids.add(val.toString());
        }
        return ids;
    }

    public static <T> List<List<String>> split(List<T> list, String field, Integer count) {
        if (CollectionUtils.isEmpty(list) || StringUtils.isEmpty(field)) {
            return null;
        }
        List<List<String>> ids = new ArrayList<>();
        List<String> idlist = new ArrayList<>();
        for (T obj : list) {
            Object val = getDeclaredFields(obj, field);
            if (val == null || "".equals(val.toString())) {
                continue;
            }
            idlist.add(val.toString());
            if (idlist.size() >= count) {
                ids.add(idlist);
                idlist = new ArrayList<>();
            }
        }
        if (idlist.size() > 0) {
            ids.add(idlist);
        }
        return ids;
    }

    public static List<List<String>> split(List<String> list, Integer count) {
        if (CollectionUtils.isEmpty(list) || count == null || count < 1) {
            return null;
        }
        List<List<String>> ids = new ArrayList<>();
        List<String> idlist = new ArrayList<>();
        for (String obj : list) {
            if (StringUtils.isEmpty(obj)) {
                continue;
            }
            idlist.add(obj);
            if (idlist.size() >= count) {
                ids.add(idlist);
                idlist = new ArrayList<>();
            }
        }
        if (idlist.size() > 0) {
            ids.add(idlist);
        }
        return ids;
    }

    public static Object getDeclaredFields(Object data, String name) {
        Field field = null;
        Class<?> clazz = data.getClass();
        while (clazz != null) {
            try {
                field = clazz.getDeclaredField(name);
                break;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        try {
            // 私有属性必须设置访问权限
            if (field != null) {
                field.setAccessible(true);
                return field.get(data);
            }

        } catch (Exception e) {
        }
        return null;
    }
}
