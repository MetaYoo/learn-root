package com.kotall.learn.freemarker;

import freemarker.core.Environment;
import freemarker.template.*;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

public class RepeatDirective implements TemplateDirectiveModel {

    private static final String PARAM_NAME_COUNT = "count";
    private static final String PARAM_NAME_HR = "hr";

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
                        TemplateDirectiveBody body) throws TemplateException, IOException {

        // ---------------------------------------------------------------------
        // 处理参数

        int countParam = 0;
        boolean countParamSet = false;
        boolean hrParam = false;

        Iterator paramIter = params.entrySet().iterator();
        while (paramIter.hasNext()) {
            Map.Entry ent = (Map.Entry) paramIter.next();

            String paramName = (String) ent.getKey();
            TemplateModel paramValue = (TemplateModel) ent.getValue();

            if (paramName.equals(PARAM_NAME_COUNT)) {
                if (!(paramValue instanceof TemplateNumberModel)) {
                    throw new TemplateModelException("The \"" + PARAM_NAME_HR
                            + "\" parameter " + "must be a number.");
                }
                countParam = ((TemplateNumberModel) paramValue).getAsNumber()
                        .intValue();
                countParamSet = true;
                if (countParam < 0) {
                    throw new TemplateModelException("The \"" + PARAM_NAME_HR
                            + "\" parameter " + "can't be negative.");
                }
            } else if (paramName.equals(PARAM_NAME_HR)) {
                if (!(paramValue instanceof TemplateBooleanModel)) {
                    throw new TemplateModelException("The \"" + PARAM_NAME_HR
                            + "\" parameter " + "must be a boolean.");
                }
                hrParam = ((TemplateBooleanModel) paramValue).getAsBoolean();
            } else {
                throw new TemplateModelException("Unsupported parameter: "
                        + paramName);
            }
        }
        if (!countParamSet) {
            throw new TemplateModelException("The required \""
                    + PARAM_NAME_COUNT + "\" paramter" + "is missing.");
        }

        if (loopVars.length > 1) {
            throw new TemplateModelException(
                    "At most one loop variable is allowed.");
        }

        // Yeah, it was long and boring...

        // ---------------------------------------------------------------------
        // 真正开始处理输出内容

        Writer out = env.getOut();
        if (body != null) {
            for (int i = 0; i < countParam; i++) {
                // 输出  <hr> 如果 参数hr 设置为true
                if (hrParam && i != 0) {
                    out.write("<hr>");
                }

                // 设置循环变量
                if (loopVars.length > 0) {
                    loopVars[0] = new SimpleNumber(i + 1);
                }

                // 执行标签内容(same as <#nested> in FTL).
                body.render(env.getOut());
            }
        }
    }

}
