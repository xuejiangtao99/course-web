package com.server.service;

import com.server.dto.ChapterDto;
import com.server.dto.PageDto;

import java.util.List;

public interface ChapterService {

    void list(PageDto pageDto);
}
