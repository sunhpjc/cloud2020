package com.sunhp.cloudalibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Payment {
    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/payment/nacos/{str}")
    public String echo(@PathVariable Long str){
        return "Hello Nacos Discovery" + str + "====>端口：" + port;
    }

}
