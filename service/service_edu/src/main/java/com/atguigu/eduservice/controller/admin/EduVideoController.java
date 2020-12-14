package com.atguigu.eduservice.controller.admin;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.chapter.VideoVo;
import com.atguigu.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-10-24
 */
@RestController
@RequestMapping("/eduservice/video")
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return R.ok();
    }

    //删除小节
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {
       return videoService.removeVideo(id);
    }
    //查询小节
    @GetMapping("{id}")
    public R getVideo(@PathVariable String id){
        EduVideo video = videoService.getById(id);
        return R.ok().data("video",video);
    }

    //修改小节
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo) {
        videoService.updateVideo(eduVideo);
        return R.ok();
    }
    //根据视频id增加视频播放量
    @PostMapping("addVideoPlayCount/{videoSourceId}")
    public R addVideoPlayCount(@PathVariable String videoSourceId) {
        videoService.addVideoPlayCount(videoSourceId);
        return R.ok();
    }
    //查询某一天总视频播放量
    @PostMapping("getVideoPlayCount")
    public Integer getVideoPlayCount() {
        return videoService.getVideoPlayCount();
    }
}

