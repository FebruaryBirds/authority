package com.common.authority.common.util;

import com.common.authority.common.bean.ResultData;
import org.springframework.stereotype.Service;

@Service
public class ResultUtils {
    public static ResultData success(){
        return new ResultData();
    }

    public static ResultData error(String msg) {
        return new ResultData(516,msg);
    }

    public static <T> ResultData render(T data){
        return new ResultData(data);
    }

    public static ResultData error(Integer code, String msg){
        return new ResultData(code,msg);
    }
}
