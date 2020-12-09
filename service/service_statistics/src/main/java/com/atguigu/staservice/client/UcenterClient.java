package com.atguigu.staservice.client;

import com.atguigu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    //查询某一天注册人数
    @GetMapping("/educenter/member/countRegister/{day}")
    public R countRegister(@PathVariable("day") String day);
    @PostMapping("/educenter/member/updateLoginTime")
    public void updateLoginTime(String mobile);

    //查询某一天登录人数
    @GetMapping("/educenter/member/countLogin/{day}")
    public Integer countLogin(@PathVariable String day);
}
