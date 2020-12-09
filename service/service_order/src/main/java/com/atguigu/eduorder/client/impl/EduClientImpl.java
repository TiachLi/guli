package com.atguigu.eduorder.client.impl;

import com.atguigu.eduorder.client.EduClient;
import com.atguigu.eduorder.entity.ordervo.CourseWebVoOrder;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import org.springframework.stereotype.Component;

@Component
public class EduClientImpl implements EduClient {
    @Override
    public CourseWebVoOrder getCourseInfoOrder(String id) {
        throw new GuliException(20001,"服务降级");
    }
}
