package com.atguigu.eduorder.client.impl;

import com.atguigu.eduorder.client.UcenterClient;
import com.atguigu.eduorder.entity.ordervo.UcenterMemberOrder;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import org.springframework.stereotype.Component;

@Component
public class UcenterClientImpl implements UcenterClient {
    @Override
    public UcenterMemberOrder getUserInfoOrder(String id) {
        throw new GuliException(200001,"服务降级");
    }
}
