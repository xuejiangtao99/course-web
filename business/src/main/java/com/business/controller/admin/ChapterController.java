package com.business.controller.admin;


import com.server.domain.Chapter;
import com.server.dto.ChapterDto;
import com.server.dto.PageDto;
import com.server.dto.ResponseDto;
import com.server.service.ChapterService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    /**
     * 查询大章列表
     */
    @RequestMapping("/queryCharacterList")
    public ResponseDto queryCharacterList(@RequestBody PageDto pageDto){

        chapterService.list(pageDto);

        return new ResponseDto(true,200,null,pageDto);
    }

    @RequestMapping("/save")
    public ResponseDto save(@RequestBody ChapterDto chapterDto){

        chapterService.save(chapterDto);

        return new ResponseDto(true,200,null,chapterDto);
    }
}
