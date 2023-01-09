package com.common.authority.common.bean;

public class BusinessException extends RuntimeException{
    public BusinessException(String msg) {
        super(msg);
    }
}
