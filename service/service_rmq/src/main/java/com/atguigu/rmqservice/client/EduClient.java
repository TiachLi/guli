package com.atguigu.rmqservice.client;

import com.atguigu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(name = "service-edu")
public interface EduClient {
    //增加视频播放量
    @PostMapping("/eduservice/video/addVideoPlayCount/{videoSourceId}")
    R addVideoPlayCount(@PathVariable String videoSourceId);
}
