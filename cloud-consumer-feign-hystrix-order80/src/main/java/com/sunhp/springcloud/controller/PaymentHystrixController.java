package com.sunhp.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sunhp.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod")
public class PaymentHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id")Long id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        log.info("*****result:"+result);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand
//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")})
    public String paymentInfo_TimeOut(@PathVariable("id")Long id){
        int a = 7/0;
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        log.info("*****result:"+result);
        return result;
    }

    public String paymentTimeOutFallbackMethod(@PathVariable("id")Long id){
        return "我只消费者80，我只能接受1.5s延迟";
    }

    public String paymentGlobalFallbackMethod(){
        return "我是消费者80，我是全局fallback";
    }
}
