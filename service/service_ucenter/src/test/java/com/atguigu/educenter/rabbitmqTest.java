package com.atguigu.educenter;

import com.atguigu.educenter.entity.vo.RegisterVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class rabbitmqTest {

    @Resource
    RabbitTemplate rabbitTemplate;
    @Test
    public void contextLoads() {
        //Message需要自己构造一个;定义消息体内容和消息头
        //rabbitTemplate.send(exchage,routeKey,message);

        //object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq；
        //rabbitTemplate.convertAndSend(exchage,routeKey,object);

        RegisterVo registerVo = new RegisterVo();
        registerVo.setNickname("test111");
        registerVo.setMobile("110000");
        //对象被默认序列化以后发送出去
        rabbitTemplate.convertAndSend("guli.direct","guli",registerVo);
    }

}
