package com.business.controller;

import com.server.dto.ResponseDto;
import com.server.exception.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static  final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);
    @ExceptionHandler(value=ValidatorException.class)
    @ResponseBody
    public ResponseDto  validatorExceptionHandler(ValidatorException e){

        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(false);

        logger.warn(e.getMessage());
        responseDto.setMsg("请求参数异常");
        return responseDto;
    }
}
