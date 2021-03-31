package com.system.controller.admin;

import com.server.domain.User;
import com.server.dto.LoginDto;
import com.server.dto.ResponseDto;
import com.server.dto.UserDto;
import com.server.exception.BusinessExceptionCode;
import com.server.service.UserService;
import com.server.utils.CopyUtil;
import com.server.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin")
public class LoginController {

        @Autowired
        private UserService userService;


        @PostMapping("/login")
        public ResponseDto login(@RequestBody UserDto userDto, HttpServletRequest request){

            User user = userService.selectByUserName(userDto.getLoginName());
            if(user == null || !user.getPassword().equals(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()))){
//                throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
               return new ResponseDto(false,Integer.valueOf(BusinessExceptionCode.LOGIN_USER_ERROR.getCode()),BusinessExceptionCode.LOGIN_USER_ERROR.getDesc(), null);
            }
            LoginDto loginDto = CopyUtil.copyObject(user, LoginDto.class);
            request.getSession().setAttribute(StringUtils.LOGIN_USER,loginDto);
            return new ResponseDto(true,200,"登录成功",loginDto);
        }

        @GetMapping("/logout")
        public ResponseDto logOut(HttpServletRequest request){

            request.getSession().removeAttribute(StringUtils.LOGIN_USER);
            return new ResponseDto(true,200,"退出登录",null);
        }

}
