package com.server.service;

import com.server.dto.CourseDto;
import com.server.dto.PageDto;


public interface CourseService {

    void list(PageDto pageDto);

    void save(CourseDto courseDto);

    void deleteById(String id);
}
