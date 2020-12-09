package com.atguigu.rmqservice.service;

public interface VideoNumService {

    //播放量自增一
    public void addVideoPlayCount(String videoSourceId);
}
