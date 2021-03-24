package com.server.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import com.server.utils.UuidUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;


@Aspect
@Component
public class LogAspect {

    private static final Logger LOGGER  = LoggerFactory.getLogger(LogAspect.class);

    /**定义一个切入点*/
    @Pointcut("execution(public * com.*.controller..*Controller.*(..))")
    public void ControllerPointcut(){};

    /**前置增强*/
    @Before("ControllerPointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{ //JointPoint 连接点

        //日志编号
        MDC.put("UUID", UuidUtil.getShortUuid());

        //开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest(); //后续打印请求地址等
        Signature signature = joinPoint.getSignature();
        String name = signature.getName(); //AOP在方法执行前切入，获取方法名（连接点名Joinpoint）

        //打印业务操作
        String nameCn = "";
        if(name.contains("list") || name.contains("query")){
                nameCn = "查询";
        }else if(name.contains("save")){
                nameCn = "保存";
        }else if(name.contains("delete")){
                nameCn = "删除";
        }else {
            nameCn = "操作";
        }

        //使用反射获取业务名称
        Class declaringType = signature.getDeclaringType();
        Field field;
        String businessName = "";
        try {
            field = declaringType.getField("BUSINESS_NAME");
            if(!StringUtils.isEmpty(field)){
                businessName = (String) field.get(declaringType);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            LOGGER.error("未获取到业务名称");
        } catch (SecurityException e){
            LOGGER.error("获取业务名称失败",e);
        }

        //打印日志
        LOGGER.info("----------------【"+businessName+"】"+"【"+nameCn+"】"+"日志打印开始----------------");
        LOGGER.info("请求地址: {} {}",request.getRequestURI().toString(),request.getMethod());
        LOGGER.info("类名方法：{} {}",request.getDispatcherType(),name);
        LOGGER.info("远程地址: {}", request.getRemoteAddr());


        //打印请求参数
        Object[] args = joinPoint.getArgs();
        Object[] arguments = new Object[args.length];
        for (int i = 0; i <args.length ; i++) {
                if(
                        args[i] instanceof ServletRequest ||
                        args[i] instanceof ServletResponse ||
                        args[i] instanceof MultipartFile
                ){
                        continue;
                }else {
                    arguments[i] = args[i];
                }
        }

        //排除敏感字段
        String[] excludeProperties = {"shard"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludefilter = filters.addFilter();
        excludefilter.addExcludes(excludeProperties);
        LOGGER.info("请求参数：{}", JSONObject.toJSONString(arguments,excludefilter)); //为空的会不打印，但是像图片等长字段也会打印
    }

    @Around("ControllerPointcut()") /*环绕增强*/
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 排除字段，敏感字段或太长的字段不显示
        String[] excludeProperties = {"password", "shard"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludefilter = filters.addFilter();
        excludefilter.addExcludes(excludeProperties);
        LOGGER.info("返回结果: {}", JSONObject.toJSONString(result, excludefilter));
        LOGGER.info("------------- 结束 耗时：{} ms -------------", System.currentTimeMillis() - startTime);
        return result;
    }
}
