package com.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.server.domain.Section;
import com.server.dto.SectionDto;
import com.server.dto.PageDto;
import com.server.enums.SectionChargeEnum;
import com.server.example.SectionExample;
import com.server.mapper.SectionMapper;
import com.server.service.SectionService;
import com.server.utils.CopyUtil;
import com.server.utils.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Date;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {

    @Resource
    private SectionMapper sectionMapper;

    @Override
    public void list(PageDto pageDto){
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        SectionExample sectionExample = new SectionExample();
                sectionExample.setOrderByClause("sort asc");
        List<Section> sections = sectionMapper.selectByExample(sectionExample);
        PageInfo<Section> pageInfo = new PageInfo<>(sections);
        List<SectionDto> list = CopyUtil.copyList(sections, SectionDto.class);
        pageDto.setTotal((int) pageInfo.getTotal());
        pageDto.setList(list);
    }


    public void save(SectionDto sectionDto){
        Section section = CopyUtil.copyObject(sectionDto,Section.class);
        if(StringUtils.isEmpty(sectionDto.getId())){
            this.insert(section);
        }else{
            this.update(section);
        }
    }

    @Override
    public void deleteById(String id) {
        sectionMapper.deleteByPrimaryKey(id);
    }

    private void insert(Section section){
        Date date = new Date();
        section.setCreatedAt(date);
        section.setUpdatedAt(date);
        section.setId(UuidUtil.getShortUuid());
        section.setCharge(SectionChargeEnum.CHARGE.getCode());
        sectionMapper.insert(section);
    }

    private void update(Section section){
    Date date = new Date();
        section.setUpdatedAt(date);
        sectionMapper.updateByPrimaryKey(section);
    }
}
