package com.server.service.impl;

import com.server.domain.Test;
import com.server.domain.TestExample;
import com.server.mapper.TestMapper;
import com.server.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    private TestMapper testMapper;

    @Override
    public List<Test> list(){

        TestExample testExample = new TestExample();
        testExample.createCriteria().andIdEqualTo(2);
        return testMapper.selectByExample(testExample);
    }
}
