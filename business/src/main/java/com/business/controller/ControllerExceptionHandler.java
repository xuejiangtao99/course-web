package com.business.controller;

import com.server.dto.ResponseDto;
import com.server.exception.ValidatorException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value=ValidatorException.class)
    @ResponseBody
    public ResponseDto  validatorExceptionHandler(ValidatorException e){

        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(false);
        responseDto.setMsg(e.getMessage());
        return responseDto;
    }
}
