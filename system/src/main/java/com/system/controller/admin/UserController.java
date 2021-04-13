package com.system.controller.admin;



import com.server.config.OperLog;
import com.server.dto.UserDto;
import com.server.dto.PageDto;
import com.server.dto.ResponseDto;
import com.server.service.UserService;
import com.server.utils.ValidatorUtil;
import org.apache.ibatis.annotations.Update;
import org.springframework.util.DigestUtils;
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
    @OperLog(operModul = "用户管理-用户查询",operType = "查询" ,operDesc = "查询用户列表")
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
    @OperLog(operModul = "用户管理-新增/修改大章",operType = "操作" ,operDesc = "用户新增功能")
    @PostMapping("/save")
    public ResponseDto save(@RequestBody UserDto userDto){

        userDto.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));
        // 保存校验
        ValidatorUtil.required(userDto.getLoginName(), "登陆名");
        ValidatorUtil.length(userDto.getLoginName(), "登陆名", 1, 50);
        ValidatorUtil.length(userDto.getName(), "昵称", 1, 50);
        ValidatorUtil.required(userDto.getPassword(), "密码");
        userService.save(userDto);

        return new ResponseDto(true,200,null,userDto);
    }

    @OperLog(operModul = "用户管理-删除用户",operType = "删除",operDesc = "用户删除功能")
    @DeleteMapping("/deleteById/{id}")
    public ResponseDto deleteById(@PathVariable("id") String id){

            userService.deleteById(id);
        return new ResponseDto(true,200,null,null);
    }

    @OperLog(operModul = "用户管理-重置密码",operType = "操作",operDesc = "用户重置密码功能")
    @Update("/resetPassword/{id}")
    public ResponseDto resetPassword(@PathVariable("id") String id){

        userService.resetPassword(id);
        return new ResponseDto(true,200,null,null);
    }
}
