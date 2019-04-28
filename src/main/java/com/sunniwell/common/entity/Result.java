package com.sunniwell.common.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
    private Integer code; //返回码
    private boolean flag; //是否成功
    private String message; //返回消息
    private Object data; //返回结果

    public Result(boolean flag,Integer code, String message) {
        this.code = code;
        this.flag = flag;
        this.message = message;

    }

    public Result(boolean flag,Integer code,  String message, Object data) {
        this.code = code;
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public Result() {
    }
}
