package com.business.controller.admin;



import com.server.dto.SectionDto;
import com.server.dto.PageDto;
import com.server.dto.ResponseDto;
import com.server.service.SectionService;
import com.server.utils.ValidatorUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/section")
public class SectionController {

    public static final String BUSINESS_NAME = "小节";
    @Resource
    private SectionService sectionService;

    /**
     * 查询小节列表
     */
    @RequestMapping("/querySectionList")
    public ResponseDto querySectionList(@RequestBody PageDto pageDto){

        sectionService.list(pageDto);

        return new ResponseDto(true,200,null,pageDto);
    }

    /**
     * 保存小节列表列表 新增/修改
     * @param sectionDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody SectionDto sectionDto){


        //保存校验
          ValidatorUtil.required(sectionDto.getId(),"id");
          ValidatorUtil.required(sectionDto.getTitle(),"标题");


        sectionService.save(sectionDto);

        return new ResponseDto(true,200,null,sectionDto);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseDto deleteById(@PathVariable("id") String id){

            sectionService.deleteById(id);
        return new ResponseDto(true,200,null,null);
    }
}
