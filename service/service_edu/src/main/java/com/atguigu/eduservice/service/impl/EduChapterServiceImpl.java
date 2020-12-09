
package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.chapter.VideoVo;
import com.atguigu.eduservice.mapper.EduChapterMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-10-24
 */

@Service
@Transactional
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    EduVideoService videoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //创建list集合，用于最终封装数据
        List<ChapterVo> finalList = new ArrayList<>();

        QueryWrapper<EduChapter> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        //1 根据课程id查询课程里面所有的章节
        List<EduChapter> eduChapters = baseMapper.selectList(queryWrapper);

        //2 根据课程id查询课程里面所有的小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        List<EduVideo> eduVideoList = videoService.list(wrapperVideo);
        //讲小节根据章节id放入HashMap中
        HashMap<String, List<VideoVo>> eduVideMap = new HashMap<>();
        for (EduVideo eduVideo : eduVideoList) {
            VideoVo videoVo = new VideoVo();
            BeanUtils.copyProperties(eduVideo,videoVo);
            String chapterId = eduVideo.getChapterId();
            List<VideoVo> eduVideos = eduVideMap.get(chapterId);
            if(eduVideos==null){
                eduVideos=new ArrayList<>();
                eduVideos.add(videoVo);
                eduVideMap.put(chapterId,eduVideos);
            }else {
                eduVideos.add(videoVo);
            }
        }
        for (EduChapter eduChapter : eduChapters) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            String chapterId = chapterVo.getId();
            List<VideoVo> videoVos = eduVideMap.get(chapterId);
            chapterVo.setChildren(videoVos);
            finalList.add(chapterVo);
        }
        return finalList;
    }

    ////删除章节的方法
    @Override
    public boolean deleteChapter(String chapterId) {
        //根据章节删除章节下的小结
        videoService.removeVideoByChapterId(chapterId);
        //删除章节
        int result = baseMapper.deleteById(chapterId);
        //成功  1>0   0>0
        return result>0;
    }
    //2 根据课程id删除章节
    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }

}

