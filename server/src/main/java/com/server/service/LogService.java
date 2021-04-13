package com.server.service;

import com.server.dto.LogDto;
import com.server.dto.PageDto;


public interface LogService {

    void list(PageDto pageDto);

    void save(LogDto logDto);

    void deleteById(String id);
}
