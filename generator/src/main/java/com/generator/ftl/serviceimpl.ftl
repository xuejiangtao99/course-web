package com.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.server.domain.${Domain};
import com.server.dto.${Domain}Dto;
import com.server.dto.PageDto;
import com.server.example.${Domain}Example;
import com.server.mapper.${Domain}Mapper;
import com.server.service.${Domain}Service;
import com.server.utils.CopyUtil;
import com.server.utils.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ${Domain}ServiceImpl implements ${Domain}Service {

    @Resource
    private ${Domain}Mapper ${domain}Mapper;

    @Override
    public void list(PageDto pageDto){
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        ${Domain}Example ${domain}Example = new ${Domain}Example();
        <#list fieldList as filed>
            <#if filed.name == "sort">
                ${domain}Example.setOrderByClause("sort asc");
            </#if>
        </#list>
        List<${Domain}> ${domain}s = ${domain}Mapper.selectByExample(${domain}Example);
        PageInfo<${Domain}> pageInfo = new PageInfo<>(${domain}s);
        List<${Domain}Dto> list = CopyUtil.copyList(${domain}s, ${Domain}Dto.class);
        pageDto.setTotal((int) pageInfo.getTotal());
        pageDto.setList(list);
    }


    public void save(${Domain}Dto ${domain}Dto){
        ${Domain} ${domain} = CopyUtil.copyObject(${domain}Dto,${Domain}.class);
        if(StringUtils.isEmpty(${domain}Dto.getId())){
            this.insert(${domain});
        }else{
            this.update(${domain});
        }
    }

    @Override
    public void deleteById(String id) {
        ${domain}Mapper.deleteByPrimaryKey(id);
    }

    private void insert(${Domain} ${domain}){

        ${domain}.setId(UuidUtil.getShortUuid());
        ${domain}Mapper.insert(${domain});
    }

    private void update(${Domain} ${domain}){
        ${domain}Mapper.updateByPrimaryKey(${domain});
    }
}
