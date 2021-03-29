package com.system.controller.admin;



import com.server.dto.UserDto;
import com.server.dto.PageDto;
import com.server.dto.ResponseDto;
import com.server.service.UserService;
import com.server.utils.ValidatorUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    public static final String BUSINESS_NAME = "用户";
    @Resource
    private UserService userService;

    /**
     * 查询用户列表
     */
    @RequestMapping("/queryUserList")
    public ResponseDto queryUserList(@RequestBody PageDto pageDto){

        userService.list(pageDto);

        return new ResponseDto(true,200,null,pageDto);
    }

    /**
     * 保存用户列表列表 新增/修改
     * @param userDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody UserDto userDto){


        // 保存校验
        ValidatorUtil.required(userDto.getLoginName(), "登陆名");
        ValidatorUtil.length(userDto.getLoginName(), "登陆名", 1, 50);
        ValidatorUtil.length(userDto.getName(), "昵称", 1, 50);
        ValidatorUtil.required(userDto.getPassword(), "密码");
        userService.save(userDto);

        return new ResponseDto(true,200,null,userDto);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseDto deleteById(@PathVariable("id") String id){

            userService.deleteById(id);
        return new ResponseDto(true,200,null,null);
    }
}
