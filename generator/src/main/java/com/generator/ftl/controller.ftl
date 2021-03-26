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
     * 查询${tableNameCn}列表
     */
    @RequestMapping("/query${Domain}List")
    public ResponseDto query${Domain}List(@RequestBody PageDto pageDto){

        ${domain}Service.list(pageDto);

        return new ResponseDto(true,200,null,pageDto);
    }

    /**
     * 保存${tableNameCn}列表列表 新增/修改
     * @param ${domain}Dto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody ${Domain}Dto ${domain}Dto){


        //保存校验
        <#list filedList as  filed>
            <#if !filed.nullAble>
          ValidatorUtil.required(${domain}Dto.get${filed.nameBigHump}(),"${filed.nameCn}");
            </#if>
            <#if (filed.length) > 0>
          ValidatorUtil.length(${domain}Dto.get${filed.nameBigHump}(),"${filed.nameCn}",1,${filed.length});
            </#if>
        </#list>


        ${domain}Service.save(${domain}Dto);

        return new ResponseDto(true,200,null,${domain}Dto);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseDto deleteById(@PathVariable("id") String id){

            ${domain}Service.deleteById(id);
        return new ResponseDto(true,200,null,null);
    }
}
