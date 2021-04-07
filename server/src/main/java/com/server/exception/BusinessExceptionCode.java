package com.server.exception;

public enum BusinessExceptionCode {

    MEMBER_NOT_EXIST("会员不存在","10001"),
    USER_LOGIN_NAME_EXIST("登录名已存在","1002"),
    LOGIN_USER_ERROR("用户名不存在或密码错误","1003"),
    LOGIN_MEMBER_ERROR("手机号不存在或密码错误","1004"),
    MOBILE_CODE_TOO_FREQUENT("短信请求过于频繁","1005"),
    MOBILE_CODE_ERROR("短信验证码不正确","1006"),
    MOBILE_CODE_EXPIRED("短信验证码不存在或已过期，请重新发送短信","1007"),
    IMAGE_CODE_TIMEOUT("验证码过期","2001"),
    IMAGE_CODE_DIVERSE("验证码不一致","2002"),;

    private String desc;

    private String code;

    BusinessExceptionCode(String desc,String code) {
        this.desc = desc;
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
