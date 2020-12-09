package com.atguigu.rmqservice.service.impl;

import com.atguigu.commonutils.MD5;
import com.atguigu.rmqservice.client.UcenterClinet;
import com.atguigu.rmqservice.entity.UcenterMember;
import com.atguigu.rmqservice.entity.vo.RegisterVo;
import com.atguigu.rmqservice.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-11-13
 */
@Service
public class UcenterMemberServiceImpl implements UcenterMemberService {

    @Resource
    UcenterClinet ucenterClinet;
    //从消息队列中接收用户数据，进行注册
    @RabbitListener(queues = "guli.news")
    public void register(RegisterVo registerVo){
        //数据添加数据库中
        ucenterClinet.registerUserToDataSource(registerVo);
    }

    @RabbitListener(queues = "loginNum.news")
    public void updateLoginTime(String mobile){
        System.out.println("loginNum.news 接收到消息");
        ucenterClinet.updateLoginTime(mobile);
    }
}
