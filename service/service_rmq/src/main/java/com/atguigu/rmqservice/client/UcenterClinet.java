package com.atguigu.rmqservice.client;

import com.atguigu.commonutils.R;
import com.atguigu.rmqservice.entity.vo.RegisterVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "service-ucenter")
public interface UcenterClinet {

    @PostMapping("/educenter/member/registerToDataSource")
    public R registerUserToDataSource(@RequestBody RegisterVo registerVo);

    @PostMapping("/educenter/member/updateLoginTime/{mobile}")
    public void updateLoginTime(@PathVariable("mobile") String mobile);
}
