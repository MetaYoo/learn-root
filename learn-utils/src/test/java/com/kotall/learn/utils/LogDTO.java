package com.kotall.learn.utils;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/3/27 8:53
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogDTO {
    @JSONField(name = "c")
    private int count;

    @JSONField(name = "t")
    private String title;

}
