package com.atguigu.demo;

import com.atguigu.eduservice.entity.EduVideo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test2 {
    @Test
    public void test1(){
        HashMap<String, List<EduVideo>> stringListHashMap = new HashMap<>();
        ArrayList<EduVideo> eduVideos = new ArrayList<>();
        EduVideo eduVideo1 = new EduVideo();
        eduVideo1.setId("1");
        eduVideos.add(eduVideo1);
        stringListHashMap.put("1",eduVideos);
        List<EduVideo> eduVideos1 = stringListHashMap.get("1");
        EduVideo eduVideo2 = new EduVideo();
        eduVideo2.setId("2");
        eduVideos1.add(eduVideo2);
        List<EduVideo> eduVideos2 = stringListHashMap.get("1");
        System.out.println(eduVideos2);
    }
}
