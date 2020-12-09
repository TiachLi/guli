package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.frontvo.CourseWebVo;
import com.atguigu.eduservice.entity.coursevo.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-10-24
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    //后台确认课程信息
    @Select("        SELECT ec.id,ec.title,ec.price,ec.lesson_num AS lessonNum,ec.cover,\n" +
            "               et.name AS teacherName, " +
            "               es1.title AS subjectLevelOne, " +
            "               es2.title AS subjectLevelTwo " +
            "        FROM edu_course ec LEFT OUTER JOIN edu_course_description ecd ON ec.id=ecd.id " +
            "                           LEFT OUTER JOIN edu_teacher et ON ec.teacher_id=et.id " +
            "                           LEFT OUTER JOIN edu_subject es1 ON ec.subject_parent_id=es1.id " +
            "                   LEFT OUTER JOIN edu_subject es2 ON ec.subject_id=es2.id " +
            "        WHERE ec.id=#{courseId} ")
    CoursePublishVo getPublishCourseInfo(String courseId);

    //注解编写sql
    @Select("        SELECT ec.id,ec.title,ec.price,ec.lesson_num AS lessonNum,ec.cover, " +
            "               ec.buy_count AS buyCount,ec.view_count AS viewCount, " +
            "               ecd.description, " +
            "               et.id AS teacherId,et.name AS teacherName,et.intro,et.avatar, " +
            "               es1.id AS subjectLevelOneId,es1.title AS subjectLevelOne, " +
            "               es2.id AS subjectLevelTwoId,es2.title AS subjectLevelTwo " +
            "      FROM edu_course ec LEFT OUTER JOIN edu_course_description ecd ON ec.id=ecd.id " +
            "                    LEFT OUTER JOIN edu_teacher et ON ec.teacher_id=et.id " +
            "                    LEFT OUTER JOIN edu_subject es1 ON ec.subject_parent_id=es1.id " +
            "             LEFT OUTER JOIN edu_subject es2 ON ec.subject_id=es2.id " +
            "        WHERE ec.id=#{courseId}")
    CourseWebVo getBaseCourseInfo(String courseId);

    @Select("select count(*) from edu_course where DATE(gmt_create)=#{day}")
    Integer selectCourseNumDaily(String day);
}
