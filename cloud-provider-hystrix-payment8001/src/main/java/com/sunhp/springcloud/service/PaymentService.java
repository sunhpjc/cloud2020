package com.sunhp.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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
}
