package com.atguigu.demo;

import com.atguigu.eduservice.EduApplication;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EduApplication.class)
public class MapperTest {
    @Resource
    EduSubjectMapper eduSubjectMapper;
    @Test
    public void test1(){
        List<EduSubject> eduSubjects = eduSubjectMapper.selectList(null);
        System.out.println(eduSubjects);
    }
}
