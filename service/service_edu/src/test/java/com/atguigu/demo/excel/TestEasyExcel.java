package com.atguigu.demo.excel;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.service.impl.EduSubjectServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {



   // @Test
    public  void test1(String[] args) {
        //实现excel写的操作
        //1 设置写入文件夹地址和excel文件名称
        String filename = "D:\\eduSubject.xlsx";
        //2 调用easyexcel里面的方法实现写操作
        //write方法两个参数：第一个参数文件路径名称，第二个参数实体类class
        EasyExcel.write(filename,DemoData.class).sheet("学生列表").doWrite(getData());
        //实现excel读操作
       // String filename = "D:\\write.xlsx";
      //  EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();
    }

    //创建方法返回list集合
    private static List<EduSubject> getData() {
        EduSubjectServiceImpl eduSubjectService = new EduSubjectServiceImpl();
        List<EduSubject> list = eduSubjectService.list(null);
        return list;
    }
}
