package com.server.exception;

public class BusinessException extends RuntimeException {

    private BusinessExceptionCode desc;

    public BusinessException(BusinessExceptionCode desc){

        super(desc.getDesc());
    }

    public BusinessExceptionCode getDesc() {

        return desc;
    }

    public void setCode(BusinessExceptionCode businessExceptionCode) {
        this.desc = businessExceptionCode;
    }


    /**
     * 不写入堆栈信息，提高性能
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
