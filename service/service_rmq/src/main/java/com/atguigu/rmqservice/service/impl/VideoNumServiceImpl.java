package com.atguigu.rmqservice.service.impl;

import com.atguigu.rmqservice.client.EduClient;
import com.atguigu.rmqservice.service.VideoNumService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class VideoNumServiceImpl implements VideoNumService {

    @Resource
    EduClient eduClient;

    @Override
    @RabbitListener(queues = "videoNum.news")
    public void addVideoPlayCount(String videoSourceId) {
        System.out.println("videoNum.news 接收到消息");
        eduClient.addVideoPlayCount(videoSourceId);
    }
}
