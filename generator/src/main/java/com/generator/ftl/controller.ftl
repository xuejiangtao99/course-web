package com.${module}.controller.admin;



import com.server.dto.${Domain}Dto;
import com.server.dto.PageDto;
import com.server.dto.ResponseDto;
import com.server.service.${Domain}Service;
import com.server.utils.ValidatorUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/${domain}")
public class ${Domain}Controller {

    public static final String BUSINESS_NAME = "${tableNameCn}";
    @Resource
    private ${Domain}Service ${domain}Service;

    /**
     * 查询大章列表
     */
    @RequestMapping("/query${Domain}List")
    public ResponseDto query${Domain}List(@RequestBody PageDto pageDto){

        ${domain}Service.list(pageDto);

        return new ResponseDto(true,200,null,pageDto);
    }

    /**
     * 保存大章列表 新增/修改
     * @param ${domain}Dto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody ${Domain}Dto ${domain}Dto){

        //保存校验
        ValidatorUtil.required(${domain}Dto.getName(),"大章名称");
        ValidatorUtil.required(${domain}Dto.getCourseId(),"课程");

        ${domain}Service.save(${domain}Dto);

        return new ResponseDto(true,200,null,${domain}Dto);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseDto deleteById(@PathVariable("id") String id){

            ${domain}Service.deleteById(id);
        return new ResponseDto(true,200,null,null);
    }
}
