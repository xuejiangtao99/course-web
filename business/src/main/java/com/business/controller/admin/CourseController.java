package com.business.controller.admin;



import com.server.dto.CourseDto;
import com.server.dto.PageDto;
import com.server.dto.ResponseDto;
import com.server.service.CourseService;
import com.server.utils.ValidatorUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/course")
public class CourseController {

    public static final String BUSINESS_NAME = "课程";
    @Resource
    private CourseService courseService;

    /**
     * 查询课程列表
     */
    @RequestMapping("/queryCourseList")
    public ResponseDto queryCourseList(@RequestBody PageDto pageDto){

        courseService.list(pageDto);

        return new ResponseDto(true,200,null,pageDto);
    }

    /**
     * 保存课程列表列表 新增/修改
     * @param courseDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody CourseDto courseDto){


        // 保存校验
        ValidatorUtil.required(courseDto.getName(), "名称");
        ValidatorUtil.length(courseDto.getName(), "名称", 1, 50);
        ValidatorUtil.length(courseDto.getSummary(), "概述", 1, 2000);
        ValidatorUtil.length(courseDto.getImage(), "封面", 1, 100);
        courseService.save(courseDto);

        return new ResponseDto(true,200,null,courseDto);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseDto deleteById(@PathVariable("id") String id){

            courseService.deleteById(id);
        return new ResponseDto(true,200,null,null);
    }
}
