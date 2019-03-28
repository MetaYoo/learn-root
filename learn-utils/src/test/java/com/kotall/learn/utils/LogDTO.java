package com.kotall.learn.utils;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

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
public class LogDTO extends BaseDTO {

    @NotNull(message = "计数器为null")
    @NotBlank(message = "计数器为空串")
    @JSONField(name = "c")
    private Integer count;

    @NotNull(message = "标题为null")
    @NotBlank(message = "标题为空串")
    @JSONField(name = "t")
    private String title;

}
