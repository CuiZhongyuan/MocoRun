package com.api.controller;

import com.api.util.JsonUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 测试后实际结果查询
 *
 **/
@RestController
@RequestMapping("/api")
public class ApiController {

    String str = "{\"code\":200,\"userid\":\"userid123456\",\"data\":[{\"key1\":\"value\",\"key2\":\"value2\"},{\"key3\":\"value3\",\"key4\":\"value4\"}]}";
    /**
     * 获取实际get测试的结果
     *
     **/
    @GetMapping(value = "/test/get")
    public String Case1(){
        return JsonUtils.jsonFormatter(str);
    }
    /**
     * 获取实际post测试的结果
     *
     **/
    @PostMapping(value = "/test/poat")
    public String getACtual(@RequestBody String reqJson){
        return JsonUtils.jsonFormatter(reqJson);
    }
}
