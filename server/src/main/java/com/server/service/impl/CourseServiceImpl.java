package com.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.server.domain.Course;
import com.server.dto.CourseDto;
import com.server.dto.PageDto;
import com.server.example.CourseExample;
import com.server.mapper.CourseMapper;
import com.server.service.CourseService;
import com.server.utils.CopyUtil;
import com.server.utils.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Date;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Resource
    private CourseMapper courseMapper;

    @Override
    public void list(PageDto pageDto){
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        CourseExample courseExample = new CourseExample();
                courseExample.setOrderByClause("sort asc");
        List<Course> courses = courseMapper.selectByExample(courseExample);
        PageInfo<Course> pageInfo = new PageInfo<>(courses);
        List<CourseDto> list = CopyUtil.copyList(courses, CourseDto.class);
        pageDto.setTotal((int) pageInfo.getTotal());
        pageDto.setList(list);
    }


    public void save(CourseDto courseDto){
        Course course = CopyUtil.copyObject(courseDto,Course.class);
        if(StringUtils.isEmpty(courseDto.getId())){
            this.insert(course);
        }else{
            this.update(course);
        }
    }

    @Override
    public void deleteById(String id) {
        courseMapper.deleteByPrimaryKey(id);
    }

    private void insert(Course course){
        Date date = new Date();
        course.setCreatedAt(date);
        course.setUpdatedAt(date);
        course.setId(UuidUtil.getShortUuid());
        courseMapper.insert(course);
    }

    private void update(Course course){
    Date date = new Date();
        course.setUpdatedAt(date);
        courseMapper.updateByPrimaryKey(course);
    }
}
