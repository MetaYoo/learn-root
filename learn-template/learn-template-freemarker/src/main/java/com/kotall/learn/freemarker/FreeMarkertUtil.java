package com.kotall.learn.freemarker;



import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class FreeMarkertUtil {
    /**
     * @param templatePath     模板文件存放目录
     * @param templateName     模板文件名称
     * @param root             数据模型根对象
     * @param templateEncoding 模板文件的编码方式
     * @param out              输出流
     */
    public static void processTemplate(String templatePath, String templateName, String templateEncoding, Map<?, ?> root, Writer out) {
        try {
            Configuration config = new Configuration();
            File file = new File(templatePath);
            // 设置要解析的模板所在的目录，并加载模板文件
            config.setDirectoryForTemplateLoading(file);
            // 设置包装器，并将对象包装为数据模型
            config.setObjectWrapper(new DefaultObjectWrapper());

            // 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
            Template template = config.getTemplate(templateName, templateEncoding);
            // 合并数据模型与模板
            template.process(root, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }
}
