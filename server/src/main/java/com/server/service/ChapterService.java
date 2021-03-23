package com.server.service;

import com.server.dto.ChapterDto;
import com.server.dto.PageDto;


public interface ChapterService {

    void list(PageDto pageDto);

    void save(ChapterDto chapterDto);
}
