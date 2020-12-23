package com.atguigu.eduservice.service.impl;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.client.VodClient;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.mapper.EduVideoMapper;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-10-24
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    VodClient vodClient;

    //1 根据课程id删除小节
    @Override
    public R removeVideoByCourseId(String courseId) {
        //1 根据课程id查询课程所有的视频id
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        wrapperVideo.select("video_source_id");
        List<EduVideo> eduVideoList = baseMapper.selectList(wrapperVideo);
        //删除视频
        R result = this.removeVideos(eduVideoList);
        //删除小节
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
        return result;
    }


    //根据章节id删除小节
    // 删除对应视频文件
    @Override
    public R removeVideoByChapterId(String chapterId) {
        //1 根据章节id查询课程所有的视频id
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("chapter_id",chapterId);
        wrapperVideo.select("video_source_id");
        List<EduVideo> eduVideoList = baseMapper.selectList(wrapperVideo);
        //删除视频
        R result = removeVideos(eduVideoList);
        //删除小节
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        baseMapper.delete(wrapper);
        return result;
    }
    //删除小节
    @Override
    public R removeVideo(String videoId) {
        //根据小节id获得视频id
        EduVideo eduVideo = baseMapper.selectById(videoId);
        String videoSourceId = eduVideo.getVideoSourceId();
        //删除视频
        R result =R.ok();
        if (!StringUtils.isEmpty(videoSourceId)){
             result = vodClient.removeAlyVideo(videoSourceId);
        }
        //删除小节
        baseMapper.deleteById(videoId);
        return result;
    }
    //根据小节集合删除视频
    private R removeVideos(List<EduVideo> eduVideoList){
        // List<EduVideo>变成List<String>
        List<String> videoIds = new ArrayList<>();
        for (int i = 0; i < eduVideoList.size(); i++) {
            EduVideo eduVideo = eduVideoList.get(i);
            String videoSourceId = eduVideo.getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)) {
                //放到videoIds集合里面
                videoIds.add(videoSourceId);
            }
        }
        //根据多个视频id删除多个视频
        if(videoIds.size()>0) {
            return vodClient.deleteBatch(videoIds);
        }
        return R.ok();
    }

    @Override
    public R updateVideo(EduVideo eduVideo) {
        R result =R.ok();
        //判断是否在数据库中已有视频id的情况下更新了视频，如果有需要先删除阿里云的视频
        String newVideoSourceId = eduVideo.getVideoSourceId();
        if (StringUtils.isEmpty(newVideoSourceId)){
            baseMapper.updateById(eduVideo);
            return result;
        }
        //先删除原有的视频
        String id = eduVideo.getId();
        //根据id查询数据库
        EduVideo eduVideoDataSource = baseMapper.selectById(id);
        //得到现有的视频id
        String videoSourceId = eduVideoDataSource.getVideoSourceId();
        if (!StringUtils.isEmpty(videoSourceId)){
            result = vodClient.removeAlyVideo(videoSourceId);
        }
        baseMapper.updateById(eduVideo);
        return result;
    }
    //增加视频播放量
    @Override
    public void addVideoPlayCount(String videoSourceId) {
        baseMapper.addVideoPlayCount(videoSourceId,new Date());
    }
    //视频播放总量
    @Override
    public Integer getVideoPlayCount() {
        return baseMapper.getVideoPlatCountDaily();
    }
}
