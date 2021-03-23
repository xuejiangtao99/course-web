package com.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {

    /*成功失败*/
    private boolean success = true;

    /*状态码*/
    private int code;

    /*返回信息*/
    private String msg;

    /*返回泛型数据,自定义类型*/
    private T content;
}
