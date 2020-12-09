package com.atguigu.eduservice.entity.chapter;

import lombok.Data;

@Data
public class VideoVo {

    private String id;

    private String title;
    //是否可以试听：0收费 1免费"
    private Integer isFree;

    private String videoSourceId;//视频id
}
