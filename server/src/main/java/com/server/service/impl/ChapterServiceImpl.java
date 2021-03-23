package com.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.server.domain.Chapter;
import com.server.dto.ChapterDto;
import com.server.dto.PageDto;
import com.server.example.ChapterExample;
import com.server.mapper.ChapterMapper;
import com.server.service.ChapterService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    @Override
    public void list(PageDto pageDto){
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        List<Chapter> chapters = chapterMapper.selectByExample(new ChapterExample());
        List<ChapterDto> list = new ArrayList<>();
        PageInfo<Chapter> pageInfo = new PageInfo(chapters);
        pageDto.setTotal((int) pageInfo.getTotal());
        chapters.forEach(item->{
            ChapterDto chapterDto = new ChapterDto();
            BeanUtils.copyProperties(item,chapterDto);
            list.add(chapterDto);
        });
        pageDto.setList(list);
    }
}
