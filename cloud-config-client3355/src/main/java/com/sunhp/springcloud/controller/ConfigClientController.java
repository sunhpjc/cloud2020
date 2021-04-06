package com.sunhp.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController {

//    @Value("${server.port}")
    private String port;
    @Value("${my.test}")
    private String test;

    @GetMapping("/getServerPort")
    public String getServerPort(){
        return port;
    }

    @GetMapping("/getTest")
    public String getTest(){
        return test;
    }
}
