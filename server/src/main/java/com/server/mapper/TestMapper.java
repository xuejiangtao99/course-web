package com.server.mapper;

import com.server.domain.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {

    List<Test>  list();
}
