package com.generator.server;

import com.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServerGenerator {
    static String toPath = "server\\src\\main\\java\\com\\server\\service\\impl\\";
    static String toServicePath = "server\\src\\main\\java\\com\\server\\service\\";

    public static void main(String[] args) throws IOException, TemplateException {
        Map<String,Object> map = new HashMap<>();
        String Domain = "Section";
        String domain = "section";
        map.put("Domain",Domain);
        map.put("domain",domain);
        FreemarkerUtil.initConfig("serviceimpl.ftl");
        FreemarkerUtil.generator(toPath+Domain+"ServiceImpl.java",map);
        FreemarkerUtil.initConfig("service.ftl");
        FreemarkerUtil.generator(toServicePath+Domain+"Service.java",map);
    }
}
