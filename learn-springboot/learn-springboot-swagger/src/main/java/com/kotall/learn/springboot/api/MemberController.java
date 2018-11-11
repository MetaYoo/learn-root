package com.kotall.learn.springboot.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员中心接口
 * @author zpwang
 * @version 1.0.0
 */
@Api
@RestController
@RequestMapping("/api/member")
public class MemberController {

    @ApiOperation(value = "获取会员个人信息", notes = "根据会员id获取会员个人信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String",paramType = "path")
    @GetMapping("/info/{mid}")
    public String info(@PathVariable("mid") String uid) {
        return "info";
    }
}
