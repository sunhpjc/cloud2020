package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

    int save(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
