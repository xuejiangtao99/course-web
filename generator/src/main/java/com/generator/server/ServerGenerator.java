package com.generator.server;

import com.generator.util.DbUtil;
import com.generator.util.Field;
import com.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ServerGenerator {

    static final String MODULE = "business";
    static String toPath = "server\\src\\main\\java\\com\\server\\service\\impl\\";
    static String toServicePath = "server\\src\\main\\java\\com\\server\\service\\";
    static String toDtoPath = "server\\src\\main\\java\\com\\server\\dto\\";
    static String toControllerPath = MODULE+"\\src\\main\\java\\com\\"+MODULE+"\\controller\\admin\\";
    static String generatorConfigPath = "server\\src\\main\\resources\\generator\\generatorConfig.xml";
    public static void main(String[] args) throws Exception {
        Map<String,Object> map = new HashMap<>();
//        String Domain = "Section";
       // String domain = "section";
        //只生成配置文件中第一个table节点
        File file = new File(generatorConfigPath);
        SAXReader reader=new SAXReader();
        //读取xml文件到Document中
        Document doc = reader.read(file);
        //获取xml文件的根节点
        Element rootElement = doc.getRootElement();
        //读取context节点
        Element context = rootElement.element("context");
        //定义一个Element用于遍历
        Element tableElement;
        //取的第一个”table"的节点
        tableElement=context.elementIterator("table").next();
        String Domain = tableElement.attributeValue("domainObjectName"); //读取属性
        String tableName = tableElement.attributeValue("tableName");
        String tableNameCn = DbUtil.getTableComment(tableName);
        String domain = Domain.substring(0, 1).toLowerCase() + Domain.substring(1);
        System.out.println("表："+tableElement.attributeValue("tableName"));
        System.out.println("Domain："+tableElement.attributeValue("domainObjectName"));
        List<Field> fieldList = DbUtil.getColumnByTableName(domain);
        Set<String> javaTypes = getJavaTypes(fieldList);


        String module = MODULE;

        map.put("Domain",Domain);
        map.put("domain",domain);
        map.put("module",module);
        map.put("tableNameCn",tableNameCn);
        map.put("fieldList",fieldList);
        map.put("typeSet",javaTypes);

        //生成service及serviceImpl
        FreemarkerUtil.initConfig("serviceimpl.ftl");
        FreemarkerUtil.generator(toPath+Domain+"ServiceImpl.java",map);
        FreemarkerUtil.initConfig("service.ftl");
        FreemarkerUtil.generator(toServicePath+Domain+"Service.java",map);

        //生成Controller代码
        FreemarkerUtil.initConfig("controller.ftl");
        FreemarkerUtil.generator(toControllerPath+Domain+"Controller.java",map);


        //todo
        //生成Dto层代码
        FreemarkerUtil.initConfig("dto.ftl");
        FreemarkerUtil.generator(toDtoPath+Domain+"Dto.java",map);




    }


    /**
     * Java类型去重，导包
     * @param fieldList
     * @return
     */
    private static Set<String> getJavaTypes(List<Field> fieldList){

        Set<String> set = new HashSet<>();

        fieldList.forEach(item->{
            set.add(item.getJavaType());
        });

        return set;
    }
}
