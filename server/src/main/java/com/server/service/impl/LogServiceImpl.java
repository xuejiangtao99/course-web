package com.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.server.domain.Log;
import com.server.dto.LogDto;
import com.server.dto.PageDto;
import com.server.example.LogExample;
import com.server.mapper.LogMapper;
import com.server.service.LogService;
import com.server.utils.CopyUtil;
import com.server.utils.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Date;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;

    @Override
    public void list(PageDto pageDto){
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        LogExample logExample = new LogExample();
        List<Log> logs = logMapper.selectByExample(logExample);
        PageInfo<Log> pageInfo = new PageInfo<>(logs);
        List<LogDto> list = CopyUtil.copyList(logs, LogDto.class);
        pageDto.setTotal((int) pageInfo.getTotal());
        pageDto.setList(list);
    }


    public void save(LogDto logDto){
        Log log = CopyUtil.copyObject(logDto,Log.class);
        if(StringUtils.isEmpty(logDto.getId())){
            this.insert(log);
        }else{
            this.update(log);
        }
    }

    @Override
    public void deleteById(String id) {
        logMapper.deleteByPrimaryKey(id);
    }

    private void insert(Log log){
        Date date = new Date();
        log.setCreatedAt(date);
        log.setId(UuidUtil.getShortUuid());
        logMapper.insert(log);
    }

    private void update(Log log){
    Date date = new Date();
        logMapper.updateByPrimaryKey(log);
    }
}
