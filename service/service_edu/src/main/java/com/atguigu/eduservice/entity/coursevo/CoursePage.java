package com.atguigu.eduservice.entity.coursevo;

import com.atguigu.eduservice.entity.EduCourse;

import java.util.List;

public class CoursePage {
    long total;
    List<EduCourse> eduCourseList;

    public void setTotal(long total) {
        this.total = total;
    }

    public void setEduCourseList(List<EduCourse> eduCourseList) {
        this.eduCourseList = eduCourseList;
    }

    public List<EduCourse> getEduCourseList() {
        return eduCourseList;
    }

    public long getTotal() {
        return total;
    }
}
