package com.system.controller;

import com.server.dto.ResponseDto;
import com.server.exception.BusinessException;
import com.server.exception.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static  final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);
    @ExceptionHandler(value= BusinessException.class)
    @ResponseBody
    public ResponseDto  businessExceptionHandler(BusinessException e){

        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(false);
        logger.error(e.getDesc().getDesc());
        responseDto.setMsg(e.getDesc().getDesc());
        return responseDto;
    }
}
