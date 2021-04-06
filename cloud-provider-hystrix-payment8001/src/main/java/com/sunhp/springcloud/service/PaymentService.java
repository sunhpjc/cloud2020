package com.sunhp.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    /**
     * 正常访问，OK
     * @param id
     * @return
     */
    public String paymentInfo_OK(Long id){
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_OK, id: "+id+"\t"+" O(n_n)O哈哈~ ";
    }

    /**
     * 访问超时
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")})
    public String paymentInfo_TimeOut(Long id){
        int timeNumber = 3;
//        int age = 6/0;
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_Timeout, id: "+id+"\t"+" O(n_n)O哈哈~ "+" 耗时(秒): "+ timeNumber;
    }

    public String paymentInfo_TimeOutHandler(Long id){
        return "线程池："+Thread.currentThread().getName()+" 8001运行超时或系统出错, id: "+id+"\t"+" O(n_n)O哈哈~ "+" 耗时(秒): ";
    }

    //=========服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),//失败率达到多少后跳匝
            //配置表示10秒内请求10次超过6成失败即断路
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0){
            throw new RuntimeException("*********id不能为复数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id不能为负数，请稍后重试，id："+ id;
    }
}
