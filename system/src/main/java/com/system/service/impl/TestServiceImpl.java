package com.system.service.impl;

import com.system.domain.Test;
import com.system.mapper.TestMapper;
import com.system.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    private TestMapper testMapper;

    @Override
    public List<Test> list(){

        return testMapper.list();
    }
}
