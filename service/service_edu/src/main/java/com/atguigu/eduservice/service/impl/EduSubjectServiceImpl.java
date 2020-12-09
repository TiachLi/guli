package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.entity.subject.TwoSubject;
import com.atguigu.eduservice.listener.SubjectExcelListener;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-10-18
 */
@Service("eduSubjectService")
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Resource
    EduSubjectServiceImpl eduSubjectService;

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) throws IOException {
        //获取文件输入流
        InputStream inputStream = file.getInputStream();
        EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //1 查询所有一级分类  parentid = 0
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);
        //2 查询所有二级分类  parentid != 0
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);
        //将二级分类放到Map中
        HashMap<String, List<TwoSubject>> twoSubjectMap = new HashMap<>();
        for (EduSubject eduSubject : twoSubjectList) {
            TwoSubject twoSubject = new TwoSubject();
            BeanUtils.copyProperties(eduSubject,twoSubject);
            List<TwoSubject> listInMap = twoSubjectMap.get(eduSubject.getParentId());
            if (listInMap==null){
                listInMap = new ArrayList<>();
                listInMap.add(twoSubject);
                twoSubjectMap.put(eduSubject.getParentId(),listInMap);
            }else {
                listInMap.add(twoSubject);
            }
            //twoSubjectMap.put(eduSubject.getParentId(),listInMap);
        }
        //创建list集合，用于存储最终封装数据
        List<OneSubject> finalSubjectList = new ArrayList<>();

        //3 封装一级分类
        //查询出来所有的一级分类list集合遍历，得到每个一级分类对象，获取每个一级分类对象值，
        //封装到要求的list集合里面 List<OneSubject> finalSubjectList
        for (int i = 0; i < oneSubjectList.size(); i++) { //遍历oneSubjectList集合
            //得到oneSubjectList每个eduSubject对象
            EduSubject eduSubject = oneSubjectList.get(i);
            //把eduSubject里面值获取出来，放到OneSubject对象里面
            OneSubject oneSubject = new OneSubject();
            //eduSubject值复制到对应oneSubject对象里面
            BeanUtils.copyProperties(eduSubject,oneSubject);
            //从二级目录的HashMap中找到一级分类id对应的List集合
            List<TwoSubject> twoEduSubjects = twoSubjectMap.get(eduSubject.getId());
            //把一级下面所有二级分类放到一级分类里面
            oneSubject.setChildren(twoEduSubjects);
            //多个OneSubject放到finalSubjectList里面
            finalSubjectList.add(oneSubject);
        }
        return finalSubjectList;
    }
}
