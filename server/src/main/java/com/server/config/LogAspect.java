package com.server.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import com.server.dto.LogDto;
import com.server.dto.LoginDto;
import com.server.service.LogService;
import com.server.utils.UuidUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;


@Aspect
@Component
public class LogAspect {

    private static final Logger LOGGER  = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private LogService logService;

    @Resource
    private RedisTemplate redisTemplate;


    /**
     * 操作版本号，例如项目启动时候 java -jar xxx.jar --version=20210412
     */
    @Value("${version}")
    private String version;



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

    /**
     * 正常返回通知，拦截用户操作日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
     * @param joinPoint 切入点
     * @param keys 返回结果
     */
    @AfterReturning(value = "ControllerPointcut()",returning = "keys") /*后置通知*/
    public void saveLog(JoinPoint joinPoint,Object keys){
        try {
            //获取requestAttributes
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

            //从requestAttributes获取servletRequest的信息
            HttpServletRequest request = (HttpServletRequest)
                    requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

            LogDto log = new LogDto();

            //从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取切入点所在方法
            Method method = signature.getMethod();
            //反射获取方法上的注解
            OperLog operLog = method.getAnnotation(OperLog.class);
            if(operLog != null){
                String operModul = operLog.operModul(); //操作模块
                String operDesc = operLog.operDesc();//操作描述
                String operType = operLog.operType();//操作类型
                log.setOperModul(operModul);
                log.setOperDesc(operDesc);
                log.setOperType(operType);
            }

            //获取请求类明
            String className = joinPoint.getTarget().getClass().getName();
            //获取请求方法名
            String methodName = method.getName();
            methodName = className + "." + methodName;
            log.setRequiredMethod(methodName);
            log.setOperMethod(methodName);

            //请求参数

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
            String requiredParams = JSON.toJSONString(JSONObject.toJSONString(arguments));
            log.setRequiredParam(requiredParams); //请求参数
            // JSON.toJSONString(keys);/*返回结果*/
            String token = request.getHeader("token");
            if(token != null){
                String object =  (String)redisTemplate.opsForValue().get(token);
                LoginDto loginDto = (LoginDto) JSONArray.parseObject(object,LoginDto.class);
                if(loginDto != null){
                    log.setOperUserId(loginDto.getId());
                    log.setOperUserName(loginDto.getName());
                    log.setOperUri(request.getRequestURI().toString());
                    log.setOperIp(request.getRemoteAddr());
                    log.setCreatedAt(new Date());
                    log.setOperVer(version);
                }

                logService.save(log);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


