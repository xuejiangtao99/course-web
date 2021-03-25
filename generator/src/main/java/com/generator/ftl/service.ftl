package com.server.service;

import com.server.dto.${Domain}Dto;
import com.server.dto.PageDto;


public interface ${Domain}Service {

    void list(PageDto pageDto);

    void save(${Domain}Dto ${domain}Dto);

    void deleteById(String id);
}
