package com.server.service.impl;

import com.server.domain.Chapter;
import com.server.dto.ChapterDto;
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
    public List<ChapterDto> list(){

        List<Chapter> chapters = chapterMapper.selectByExample(new ChapterExample());
        List<ChapterDto> list = new ArrayList<>();

        chapters.forEach(item->{
            ChapterDto chapterDto = new ChapterDto();
            BeanUtils.copyProperties(item,chapterDto);
            list.add(chapterDto);
        });
        return list;
    }
}
