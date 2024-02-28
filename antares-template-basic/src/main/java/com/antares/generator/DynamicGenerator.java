package com.antares.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.antares.model.MainTemplateConfig;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class DynamicGenerator {
    public static void main(String[] args) throws IOException, TemplateException {
        // new 出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        // 指定模板文件所在的路径
        configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");
        
        // 创建模板对象，加载指定模板
        Template template = configuration.getTemplate("MainTemplate.java.ftl");   
        
        //创建数据模型
        MainTemplateConfig config = new MainTemplateConfig();
        config.setAuthor("antares");
        config.setLoop(false);
        config.setOutputText("计算结果：");

        // 生成
        Writer out = new FileWriter("MainTemplate.java");
        template.process(config, out);

        // 生成文件后别忘了关闭哦
        out.close();
    }

    public static void doGenerate(String inputPath, String outputPath, Object model) throws IOException, TemplateException {
        // new 出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        // 指定模板文件所在的路径
        configuration.setDirectoryForTemplateLoading(new File(inputPath).getParentFile());
        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");
        
        // 创建模板对象，加载指定模板
        Template template = configuration.getTemplate(new File(inputPath).getName());   

        // 生成
        Writer out = new FileWriter("MainTemplate.java");
        template.process(model, out);
        out.close();
    }
}
