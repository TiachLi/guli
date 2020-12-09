package com.atguigu.eduservice.service;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-10-24
 */
public interface EduVideoService extends IService<EduVideo> {
    // 根据课程id删除小节
    R removeVideoByCourseId(String courseId);
    // 根据章节id删除小节
    R removeVideoByChapterId(String chapterId);
     // 删除视频
    R removeVideo(String id);
    //更新视频
    R updateVideo(EduVideo eduVideo);
    //播放量增加1
    void addVideoPlayCount(String videoSourceId);
    //查询视频播放量
    Integer getVideoPlayCount();
}
