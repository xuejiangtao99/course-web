package com.server.service;

import com.server.dto.SectionDto;
import com.server.dto.PageDto;


public interface SectionService {

    void list(PageDto pageDto);

    void save(SectionDto sectionDto);

    void deleteById(String id);
}
