package com.sunhp.cloudalibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class NacosConsumerMain83 {
    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerMain83.class, args);
    }
}
