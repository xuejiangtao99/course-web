package com.generator.server;

import com.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServerGenerator {

    static final String MODULE = "business";
    static String toPath = "server\\src\\main\\java\\com\\server\\service\\impl\\";
    static String toServicePath = "server\\src\\main\\java\\com\\server\\service\\";
    static String toControllerPath = MODULE+"\\src\\main\\java\\com\\"+MODULE+"\\controller\\admin\\";
    public static void main(String[] args) throws IOException, TemplateException {
        Map<String,Object> map = new HashMap<>();
        String Domain = "Section";
        String domain = "section";
        map.put("Domain",Domain);
        map.put("domain",domain);

        //生成service及serviceImpl
        FreemarkerUtil.initConfig("serviceimpl.ftl");
        FreemarkerUtil.generator(toPath+Domain+"ServiceImpl.java",map);
        FreemarkerUtil.initConfig("service.ftl");
        FreemarkerUtil.generator(toServicePath+Domain+"Service.java",map);

        //生成Controller代码
        FreemarkerUtil.initConfig("controller.ftl");
        FreemarkerUtil.generator(toControllerPath+Domain+"Controller.java",map);

    }
}
