package com.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDto<T> {

    /*当前页面*/
    protected int page;

    /*每页条数*/
    protected int size;

    /*总条数*/
    protected int total;
    
    protected List<T> list;
}
