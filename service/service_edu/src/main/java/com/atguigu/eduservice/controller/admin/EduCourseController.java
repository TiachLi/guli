package com.atguigu.eduservice.controller.admin;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.coursevo.CourseInfoVo;
import com.atguigu.eduservice.entity.coursevo.CoursePublishVo;
import com.atguigu.eduservice.entity.coursevo.CourseVo;
import com.atguigu.eduservice.service.EduCourseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-10-24
 */
@RestController
@RequestMapping("/eduservice/course")
public class EduCourseController {
    @Resource
    private EduCourseService courseService;


    //添加课程基本信息的方法
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        //返回添加之后课程id，为了后面添加大纲使用
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",id);
    }

    //根据课程id查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo",courseInfoVo);
    }

    //修改课程信息
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    //根据课程id查询课程确认信息
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
        return R.ok().data("publishCourse",coursePublishVo);
    }

    //课程最终发布
    //修改课程状态
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");//设置课程发布状态
        courseService.updateById(eduCourse);
        return R.ok();
    }

    //课程列表 基本实现
    @PostMapping("getPageList/{current}/{limit}")
    public R getCourseList(@PathVariable String current,@PathVariable String limit,
            @RequestBody(required = false) CourseVo courseVo) {
        int currentInt = Integer.parseInt(current);
        int limitInt = Integer.parseInt(limit);
        Map<String, Object> resultMap = courseService.getListByCondition(currentInt,limitInt,courseVo);
        long total = (long)resultMap.get("total");
        Object list =resultMap.get("list");
        return R.ok().data("list",list).data("total",total);
    }

    //删除课程
    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable("courseId") String courseId) {
        courseService.removeCourse(courseId);
        return R.ok();
    }
    //查询某一天增加的课程数
    @GetMapping("getCourseNum/{day}")
    public Integer getCourseNum(@PathVariable("day") String day){
        return courseService.getCourseNum(day);
    }
}

