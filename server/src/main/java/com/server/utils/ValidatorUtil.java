package com.server.utils;

import com.server.exception.ValidatorException;
import org.springframework.util.StringUtils;

/** 
 * @author XueJiangTao
 * @date 2021/3/23
 * @param 
 * @return 
 */
public class ValidatorUtil {

    /**
     * 非空校验
     * @param str
     * @param fieldName
     */
    public static void required(String str,String fieldName){

        if(StringUtils.isEmpty(str)){

            throw new ValidatorException(fieldName+"不能为空");
        }
    }

    /**
     * 长度校验
     * @param str
     * @param filedName
     * @param max
     * @param min
     */
    public static void length(String str,String filedName,int min,int max){

        int length = 0;

        if(!StringUtils.isEmpty(str)){
            length = str.length();
        }
        if(length >max || length <min){
            throw new ValidatorException(filedName + "长度" + min+"~"+max + "位" );
        }
    }

}
