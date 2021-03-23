package com.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.server.domain.Chapter;
import com.server.dto.ChapterDto;
import com.server.dto.PageDto;
import com.server.example.ChapterExample;
import com.server.mapper.ChapterMapper;
import com.server.service.ChapterService;
import com.server.utils.CopyUtil;
import com.server.utils.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ChapterServiceImpl implements ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    @Override
    public void list(PageDto pageDto){
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        List<Chapter> chapters = chapterMapper.selectByExample(new ChapterExample());
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapters);
        List<ChapterDto> list = CopyUtil.copyList(chapters, ChapterDto.class);
        pageDto.setTotal((int) pageInfo.getTotal());
        pageDto.setList(list);
    }


    public void save(ChapterDto chapterDto){
        Chapter chapter = new Chapter();
        BeanUtils.copyProperties(chapterDto,chapter);
        chapter.setId(UuidUtil.getShortUuid());
        chapterMapper.insert(chapter);
    }
}
