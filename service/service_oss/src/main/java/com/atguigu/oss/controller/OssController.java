package com.atguigu.oss.controller;

import com.atguigu.commonutils.R;
import com.atguigu.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;

    //上传文件的方法
    @PostMapping
    public R uploadOssFile(MultipartFile file) {
            String url = ossService.uploadFileAvatar(file);
            return R.ok().data("url",url);
    }
    //删除文件的方法
    @DeleteMapping
    public R deleteOssFile(String url) {

        String fileName =url.substring(url.lastIndexOf("/cover/")+7);
        ossService.deleteFileAvatar(fileName);
        System.out.println(fileName);
        return R.ok();
    }
}
