package com.kotall.learn.java8;

import org.junit.Test;

import java.net.URLDecoder;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/8/24 7:32
 */
public class MapTest {

    @Test
    public void testMap() {
        Map<String, String> map = new TreeMap<String, String>(){{
            put("aabc", "12321");
            put("cabc", "12323");
            put("debc", "12325");
            put("dabc", "12324");
            put("babc", "12322");
        }};
        Map<String, String> treeMap = new TreeMap<>(map);
        map.forEach((k,v) -> System.out.println(k + "=" + v));
    }

    @Test
    public void urlDecode() throws Exception {
        String str = "industry_id=1911&monitor_click=%5B%22http%3A%2F%2Ft.chengadx.com%3A6011%2Fac%3Fadxid%3D147%26rmd5%3D__EXT__%22%5D&ad_type=5&dsp_id=165&material=%5B%7B%22item%22%3A%5B%7B%22file_url%22%3A%22https%3A%2F%2Fimg.zcdsp.com%2Fcreative%2Fmaterial%2F8f3916d50bc93d07b43e14466f6fd13e.jpg%22%2C%22file_md5%22%3A%22866c0b993d85a8262c6b25c6e8aa983f%22%2C%22file_type%22%3A1%2C%22width%22%3A592%2C%22height%22%3A296%7D%2C%7B%22layout%22%3A2%2C%22picture%22%3A%7B%22cover%22%3A%22https%3A%2F%2Fimg.zcdsp.com%2Fcreative%2Fmaterial%2F8f3916d50bc93d07b43e14466f6fd13e.jpg%22%2C%22width%22%3A592%2C%22height%22%3A296%7D%7D%5D%2C%22source%22%3A%7B%22name%22%3A%22%E6%99%BA%E6%A9%99-native%22%2C%22action%22%3A1%2C%22jump%22%3A%7B%22landing%22%3A%22http%3A%2F%2Fwww.zcyidong.cn%3A4100%2Ftemplate%2F3%22%2C%22click%22%3A%22http%3A%2F%2Fwww.zcyidong.cn%3A4100%2Ftemplate%2F3%22%7D%7D%7D%5D&signature=455bc4648707c815b0c5c188a74c44847e896d62&vendor_ids=%5B29%5D&name=263722136&landing_page=http%3A%2F%2Fwww.zcyidong.cn%3A4100%2Ftemplate%2F3&advertiser_id=11000133&monitor_winnotice=%5B%22http%3A%2F%2Fadx.bcadx.com%3A6011%2Fvmdsp%3Fadxid%3D147%26price%3D__PRICE__%26rmd5%3D__EXT__%22%5D";

        String url = URLDecoder.decode(str, "utf-8");
        System.out.println(url);
    }
}
