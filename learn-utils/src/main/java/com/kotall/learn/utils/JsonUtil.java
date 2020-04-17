package com.kotall.learn.utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述 json解析工具类
 * @author chongmengzhao
 *
 */
public class JsonUtil {

    private static Integer level = 0;
    //当前层级
    private static Integer currentLevel = 0;
    //上一层级
    private static Integer upperLevel = 0;

    public static void main(String[] args) {
        String json1 = "{\n" +
                "\t\"status\": \"OK\",\n" +
                "\t\"code\": \"10000\",\n" +
                "\t\"message\": \"成功响应\",\n" +
                "\t\"data\": {\n" +
                "\t\t\"pageNum\": 1,\n" +
                "\t\t\"pageSize\": 10,\n" +
                "\t\t\"size\": 1,\n" +
                "\t\t\"startRow\": 1,\n" +
                "\t\t\"endRow\": 1,\n" +
                "\t\t\"total\": 1,\n" +
                "\t\t\"pages\": 1,\n" +
                "\t\t\"list\": [{\n" +
                "\t\t\t\"id\": 60,\n" +
                "\t\t\t\"remarks\": null,\n" +
                "\t\t\t\"status\": \"1\",\n" +
                "\t\t\t\"createDate\": 1571378796000,\n" +
                "\t\t\t\"updateDate\": null,\n" +
                "\t\t\t\"courseClass\": 1,\n" +
                "\t\t\t\"companyId\": 116,\n" +
                "\t\t\t\"courseName\": \"性能测试01\",\n" +
                "\t\t\t\"courseUrl\": \"https://za-zhs-tst.oss-cn-hzfinance.aliyuncs.com/plutoTemp/zhs/2019-10/a42713bb717fab487e2417400ebbf6ec20191018.mp4\",\n" +
                "\t\t\t\"coverUrl\": \"https://za-zhs-tst.oss-cn-hzfinance.aliyuncs.com/plutoTemp/zhs/2019-10/a69dc001717f3b371be097e206201d0f20191018.jpg\",\n" +
                "\t\t\t\"productDataUrl\": \"https://za-zhs-tst.oss-cn-hzfinance.aliyuncs.com/plutoTemp/zhs/2019-10/a69dc001717fd2046ea17a0bbb09df1a20191018.jpg\",\n" +
                "\t\t\t\"playLength\": \"1311\",\n" +
                "\t\t\t\"type\": \"1\",\n" +
                "\t\t\t\"top\": 0,\n" +
                "\t\t\t\"isActive\": \"1\",\n" +
                "\t\t\t\"activeDate\": 1573801860000,\n" +
                "\t\t\t\"activeBy\": 191,\n" +
                "\t\t\t\"resourceType\": null,\n" +
                "\t\t\t\"bankId\": null,\n" +
                "\t\t\t\"reportBackUrl\": null,\n" +
                "\t\t\t\"reportSendWord\": null,\n" +
                "\t\t\t\"random\": null,\n" +
                "\t\t\t\"playCount\": 407,\n" +
                "\t\t\t\"playPosition\": null,\n" +
                "\t\t\t\"maxPlayPosition\": null,\n" +
                "\t\t\t\"isFinishPlay\": null,\n" +
                "\t\t\t\"isFinishAsk\": null,\n" +
                "\t\t\t\"companyName\": null,\n" +
                "\t\t\t\"questions\": null,\n" +
                "\t\t\t\"isNeedIntegral\": null,\n" +
                "\t\t\t\"integralRules\": null,\n" +
                "\t\t\t\"finishRuleNos\": null,\n" +
                "\t\t\t\"isFinishIntegral\": null\n" +
                "\t\t}],\n" +
                "\t\t\"prePage\": 0,\n" +
                "\t\t\"nextPage\": 0,\n" +
                "\t\t\"isFirstPage\": true,\n" +
                "\t\t\"isLastPage\": true,\n" +
                "\t\t\"hasPreviousPage\": false,\n" +
                "\t\t\"hasNextPage\": false,\n" +
                "\t\t\"navigatePages\": 8,\n" +
                "\t\t\"navigatepageNums\": [1],\n" +
                "\t\t\"navigateFirstPage\": 1,\n" +
                "\t\t\"navigateLastPage\": 1,\n" +
                "\t\t\"firstPage\": 1,\n" +
                "\t\t\"lastPage\": 1\n" +
                "\t},\n" +
                "\t\"date\": 1586398034647\n" +
                "}";


        System.out.println(json1);

        String returnValue=null;
        String children = "topic";
        returnValue = parseJsonString(json1, children);
        System.out.println("返回值为："+returnValue);

    }

    /**
     * @描述 将json字符串解析为map
     * @author chongmengzhao
     */
    public static String parseJsonString(String json, String targetKey) {
        //json先转换成LinkedHashMap
        String returnJson="";
        LinkedHashMap<String, Object> jsonMap = JSON.parseObject(json, new TypeReference<LinkedHashMap<String, Object>>() {});

        for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
            returnJson = parseJsonMap(entry, targetKey);
        }
        return returnJson;
    }

    /**
     * @描述 map按动态的key解析值
     * @author chongmengzhao
     */
    public static String parseJsonMap(Map.Entry<String, Object> entry, String targetKey) {
        String returnJson_m="";
        //如果是单个map继续遍历
        if (entry.getValue() instanceof Map) {
            LinkedHashMap<String, Object> jsonMap = JSON.parseObject(entry.getValue().toString(), new TypeReference<LinkedHashMap<String, Object>>() {
            });
            for (Map.Entry<String, Object> entry2 : jsonMap.entrySet()) {
                returnJson_m = parseJsonMap(entry2, targetKey);
            }
        }

        //如果是list就提取出来
        if (entry.getValue() instanceof List && ((List) entry.getValue()).size() > 0) {
            level++;
            currentLevel = level;
            List list = (List) entry.getValue();
            for (int i = 0; i < list.size(); i++) {
                returnJson_m = parseJsonString(list.get(i).toString(), targetKey);
            }
            if (upperLevel < currentLevel) {
                level = level - 1;
            }
        }

        //如果是String就获取它的值
        if (entry.getValue() instanceof String) {
            //System.out.println("开始解析第"+level+"层数据key："+entry.getKey() + ",   value:" + entry.getValue());
            if (upperLevel == currentLevel) {
                upperLevel = level;
            }
            if (targetKey.equals(entry.getKey())) {
                System.out.println("开始解析第" + level + "层数据key：" + entry.getKey() + ",   value:" + entry.getValue());
                returnJson_m =entry.getValue().toString();
            }
        }
        //如果是空JSONArray,就返回它的值
        if (entry.getValue() instanceof JSONArray && ((JSONArray) entry.getValue()).size() == 0) {
            //System.out.println("开始解析第"+level+"层数据key："+entry.getKey() + ",   value: []");
            if (upperLevel == currentLevel) {
                upperLevel = level;
            }
        }
        return returnJson_m;
    }

}
