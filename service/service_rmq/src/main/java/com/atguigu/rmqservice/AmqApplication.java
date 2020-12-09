package com.atguigu.rmqservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@EnableRabbit
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.atguigu.rmqservice.mapper")
@ComponentScan("com.atguigu")
@EnableFeignClients
public class AmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmqApplication.class, args);
    }

}
