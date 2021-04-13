package com.business.controller.admin;



import com.server.config.OperLog;
import com.server.dto.ChapterDto;
import com.server.dto.PageDto;
import com.server.dto.ResponseDto;
import com.server.service.ChapterService;
import com.server.utils.ValidatorUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
public class ChapterController {

    public static final String BUSINESS_NAME = "大章";
    @Resource
    private ChapterService chapterService;

    /**
     * 查询大章列表
     */
    @RequestMapping("/queryCharacterList")
    @OperLog(operModul = "大章管理-查询大章",operType = "查询",operDesc = "查询大章列表")
    public ResponseDto queryCharacterList(@RequestBody PageDto pageDto){

        chapterService.list(pageDto);

        return new ResponseDto(true,200,null,pageDto);
    }

    /**
     * 保存大章列表 新增/修改
     * @param chapterDto
     * @return
     */
    @OperLog(operModul = "大章管理-新增/修改大章",operType = "操作",operDesc = "新增大章")
    @PostMapping("/save")
    public ResponseDto save(@RequestBody ChapterDto chapterDto){

        //保存校验
        ValidatorUtil.required(chapterDto.getName(),"大章名称");
        ValidatorUtil.required(chapterDto.getCourseId(),"课程");

        chapterService.save(chapterDto);

        return new ResponseDto(true,200,null,chapterDto);
    }

    @OperLog(operModul = "大章管理-删除大章",operType = "删除",operDesc = "大章删除功能")
    @DeleteMapping("/deleteById/{id}")
    public ResponseDto deleteById(@PathVariable("id") String id){

            chapterService.deleteById(id);
        return new ResponseDto(true,200,null,null);
    }
}
