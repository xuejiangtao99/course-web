package com.generator.util;


import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FreemarkerUtil {
    static String filePath = "generator\\src\\main\\java\\com\\generator\\ftl";

    private static Template template;


    public static void initConfig(String fileName) throws IOException {

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setDirectoryForTemplateLoading(new File(filePath));
        cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_29));
        template = cfg.getTemplate(fileName);

    }

    public static void generator(String toPath, Map<String,Object> map) throws IOException, TemplateException {

        FileWriter fw = new FileWriter(toPath);
        BufferedWriter bw = new BufferedWriter(fw);
        template.process(map,bw);
        bw.flush();
        bw.close();
    }
}
