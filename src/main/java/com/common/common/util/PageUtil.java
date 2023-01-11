package com.common.common.util;

import com.github.pagehelper.Page;
import com.common.common.bean.PageList;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class PageUtil {
    public static <T> PageList parse(Page<T> page){
        PageList<T> pageList = new PageList<>();
        pageList.setTotal(page.getTotal());
        pageList.setPageNum(page.getPageNum());
        pageList.setPageSize(page.getPageSize());
        pageList.setRows(page.getResult());
        return pageList;
    }

    public static <T> PageList<T> parseList(PageList<?> list, Class<T> clazz){
        PageList<T> newList = new PageList<>();
        newList.setPageSize(list.getPageSize());
        newList.setPageNum(list.getPageNum());
        newList.setTotal(list.getTotal());
        List<T> rows = BeanUtil.copyCollection(list.getRows(), clazz);
        newList.setRows(rows);
        return newList;
    }
}
