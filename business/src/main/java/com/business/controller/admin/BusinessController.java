package com.business.controller.admin;


import com.server.domain.Chapter;
import com.server.dto.ChapterDto;
import com.server.service.ChapterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class BusinessController {

    @Resource
    private ChapterService chapterService;

    /**
     * 查询大章列表
     */
    @RequestMapping("/queryCharacterList")
    public List<ChapterDto> queryCharacterList(){

        return chapterService.list();
    }
}
