package com.sunhp.springcloud.service.impl;

import com.sunhp.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixServiceImpl implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Long id) {
        return "ok接口异常";
    }

    @Override
    public String paymentInfo_TimeOut(Long id) {
        return "timeout接口异常";
    }
}
