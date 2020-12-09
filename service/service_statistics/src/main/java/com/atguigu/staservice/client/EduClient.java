package com.atguigu.staservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(name = "service-edu")
public interface EduClient {
    //查询视频播放总量
    @PostMapping("/eduservice/video/getVideoPlayCount")
    public Integer getVideoPlayCount();

    //查询某一天增加的课程数
    @GetMapping("/eduservice/course/getCourseNum/{day}")
    public Integer getCourseNum(@PathVariable("day") String day);
}
