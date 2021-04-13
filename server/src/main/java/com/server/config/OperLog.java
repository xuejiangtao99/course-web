package com.server.config;

import java.lang.annotation.*;

@Target(ElementType.METHOD) //注解放置目标位置,METHOD是注解可以放在方法级别上面
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented
public @interface OperLog {

    String operModul() default ""; //操作模块
    String operType() default ""; //操作类型
    String operDesc() default ""; //操作说明
}
