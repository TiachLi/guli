package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-10-18
 */
public interface EduSubjectService extends IService<EduSubject> {

    //保存课程分类
    void saveSubject(MultipartFile file, EduSubjectService subjectService) throws IOException;
    //课程分类列表（树形）
    List<OneSubject> getAllOneTwoSubject();
}
