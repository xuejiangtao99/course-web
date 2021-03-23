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
import org.springframework.util.StringUtils;

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
        Chapter chapter = CopyUtil.copyObject(chapterDto,Chapter.class);
        if(StringUtils.isEmpty(chapterDto.getId())){
            this.insert(chapter);
        }else{
            this.update(chapter);
        }
    }

    @Override
    public void deleteById(String id) {
        chapterMapper.deleteByPrimaryKey(id);
    }

    private void insert(Chapter chapter){

        chapter.setId(UuidUtil.getShortUuid());
        chapterMapper.insert(chapter);
    }

    private void update(Chapter chapter){
        chapterMapper.updateByPrimaryKey(chapter);
    }
}
