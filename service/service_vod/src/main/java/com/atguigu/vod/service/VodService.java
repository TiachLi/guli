package com.atguigu.vod.service;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    //上传视频到阿里云
    String uploadVideoAly(MultipartFile file);
    //删除阿里云中的视频
    void removeVideoAly(String videoId) throws Exception;

    void removeMoreAlyVideo(List<String> videoIdList) throws Exception;
}
