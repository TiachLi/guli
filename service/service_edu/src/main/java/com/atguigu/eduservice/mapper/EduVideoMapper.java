package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 课程视频 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-10-24
 */
public interface EduVideoMapper extends BaseMapper<EduVideo> {
    //视频播放量自增1
    @Update("UPDATE edu_video SET play_count=play_count+1,gmt_modified=#{currentDate} WHERE video_source_id=#{videoSourceId}")
    void addVideoPlayCount(@Param("videoSourceId") String videoSourceId, @Param("currentDate")Date date);

    //获取视频播放总量
    @Select("select SUM(play_count) from edu_video ")
    Integer getVideoPlatCountDaily();
}
