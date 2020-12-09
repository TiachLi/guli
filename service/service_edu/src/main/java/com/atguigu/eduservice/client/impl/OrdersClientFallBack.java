package com.atguigu.eduservice.client.impl;

import com.atguigu.eduservice.client.OrdersClient;
import org.springframework.stereotype.Component;

@Component
public class OrdersClientFallBack implements OrdersClient {
    @Override
    public boolean isBuyCourse(String courseId, String memberId) {
        System.out.println("执行服务降级 ordersClient");
        return false;
    }
}
