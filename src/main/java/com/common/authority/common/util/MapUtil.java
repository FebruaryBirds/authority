package com.common.authority.common.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MapUtil {
    public static Map<String, String> formatMapString(Object parameter) {
        //将参数统一转化成Map
        Map<String, String> map = new HashMap<>(32);
        // 获取f对象对应类中的所有属性域
        Field[] fields = parameter.getClass().getDeclaredFields();
        for (Field field : fields) {
            String varName = field.getName();
            // 获取原来的访问控制权限
            boolean accessFlag = field.isAccessible();
            // 修改访问控制权限
            field.setAccessible(true);
            // 获取在对象f中属性fields[i]对应的对象中的变量
            Object o = null;
            try {
                o = field.get(parameter);
                if (o != null) {
                    map.put(varName, o.toString());
                    // 恢复访问控制权限
                    field.setAccessible(accessFlag);
                }
            } catch (IllegalAccessException e) {
                log.error("失败", e);
            }
        }
        return map;
    }



    public static Map<String, Object> formatMap(Object parameter) {
        //将参数统一转化成Map
        Map<String, Object> map = new HashMap<>(32);
        // 获取f对象对应类中的所有属性域
        Field[] fields = parameter.getClass().getDeclaredFields();
        for (Field field : fields) {
            String varName = field.getName();
            // 获取原来的访问控制权限
            boolean accessFlag = field.isAccessible();
            // 修改访问控制权限
            field.setAccessible(true);
            // 获取在对象f中属性fields[i]对应的对象中的变量
            Object o = null;
            try {
                o = field.get(parameter);
                if (o != null) {
                    map.put(varName, o);
                    // 恢复访问控制权限
                    field.setAccessible(accessFlag);
                }
            } catch (IllegalAccessException e) {
                log.error("失败", e);
            }
        }
        return map;
    }

    public static Map<String,Object> formatMapHasNull(Object parameter) {
        //将参数统一转化成Map
        Map<String, Object> map = new HashMap<>(32);
        // 获取f对象对应类中的所有属性域
        Field[] fields = parameter.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            // 获取原来的访问控制权限
            boolean accessFlag = fields[i].isAccessible();
            // 修改访问控制权限
            fields[i].setAccessible(true);
            // 获取在对象f中属性fields[i]对应的对象中的变量
            Object o = null;
            try {
                o = fields[i].get(parameter);
                map.put(varName, o);
                // 恢复访问控制权限
                fields[i].setAccessible(accessFlag);
            } catch (IllegalAccessException e) {
                log.error("失败", e);
            }
        }
        return map;
    }
}
