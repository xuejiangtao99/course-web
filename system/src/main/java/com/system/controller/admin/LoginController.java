package com.system.controller.admin;

import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.server.domain.User;
import com.server.dto.LoginDto;
import com.server.dto.ResponseDto;
import com.server.dto.UserDto;
import com.server.exception.BusinessExceptionCode;
import com.server.service.UserService;
import com.server.utils.CopyUtil;
import com.server.utils.StringUtils;
import com.server.utils.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/admin")
public class LoginController {

        public static final String BUSINESS_NAME = "登录";

        @Autowired
        private UserService userService;

        @Resource
        private RedisTemplate redisTemplate;

        @Qualifier("getDefaultKaptcha")
        @Autowired
        private DefaultKaptcha defaultKaptcha;

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

        @PostMapping("/login")
        public ResponseDto login(@RequestBody UserDto userDto, HttpServletRequest request){

          String imageCode =   (String)redisTemplate.opsForValue().get(userDto.getImageCodeToken());
          LOG.info("从Redis中取出来图片验证码：{}"+imageCode);
          LOG.info("接收到的imageCodeToken为：{}",userDto.getImageCodeToken());
          if(org.springframework.util.StringUtils.isEmpty(imageCode)){
              return new ResponseDto(false,Integer.valueOf(BusinessExceptionCode.IMAGE_CODE_TIMEOUT.getCode()),BusinessExceptionCode.IMAGE_CODE_TIMEOUT.getDesc(),null);
          }else if(!imageCode.toLowerCase() .equals(userDto.getImageCode().toLowerCase())){
              return new ResponseDto(false,Integer.valueOf(BusinessExceptionCode.IMAGE_CODE_DIVERSE.getCode()),BusinessExceptionCode.IMAGE_CODE_DIVERSE.getDesc(),null);
          }else{
              redisTemplate.delete(userDto.getImageCodeToken());
          }
            User user = userService.selectByUserName(userDto.getLoginName());
            if(user == null || !user.getPassword().equals(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()))){
//                throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
               return new ResponseDto(false,Integer.valueOf(BusinessExceptionCode.LOGIN_USER_ERROR.getCode()),BusinessExceptionCode.LOGIN_USER_ERROR.getDesc(), null);
            }
            LoginDto loginDto = CopyUtil.copyObject(user, LoginDto.class);
            String token = UuidUtil.getShortUuid();
            loginDto.setToken(token);
            //request.getSession().setAttribute(LOGIN_USER,loginDto);
            redisTemplate.opsForValue().set(token, JSON.toJSONString(loginDto),3600,TimeUnit.SECONDS);
            return new ResponseDto(true,200,"登录成功",loginDto);
        }

        @GetMapping("/logout/{token}")
        public ResponseDto logOut(@PathVariable("token") String token){

           // request.getSession().removeAttribute(LOGIN_USER);
            redisTemplate.delete(token);
            LOG.info("退出登录成功：{}",token);
            return new ResponseDto(true,200,"退出登录",null);
        }

        //验证码
        @GetMapping("/image-code/{imageCodeToken}")
        public void defaultKaptcha(@PathVariable("imageCodeToken") String imageCodeToken,HttpServletResponse httpServletResponse) throws Exception {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            LOG.info("接收到图形验证的token:{}",imageCodeToken);
            try{
                //生成验证码
                String text = defaultKaptcha.createText();


                //将图片token保存到redis中
                redisTemplate.opsForValue().set(imageCodeToken,text,300, TimeUnit.SECONDS);

                //使用验证码字符串生成验证码图片
                BufferedImage image = defaultKaptcha.createImage(text);
                //使用imageIo操作图片
                ImageIO.write(image,"jpg",outputStream);

            }catch (IllegalArgumentException e){
                httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            }

            //定义response输出类型为image/jpeg类型,使用response输出流输出图片的byte数组
            byte[] bytes = outputStream.toByteArray();
            httpServletResponse.setHeader("Cache-Control","no-store");
            httpServletResponse.setHeader("Pragma","no-cache");
            httpServletResponse.setDateHeader("Expires",0);
            httpServletResponse.setContentType("image/jpeg");
            ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
            responseOutputStream.write(bytes);
            responseOutputStream.flush();
            responseOutputStream.close();
        }

}
