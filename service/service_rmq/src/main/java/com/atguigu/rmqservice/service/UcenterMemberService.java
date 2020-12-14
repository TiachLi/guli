package com.atguigu.rmqservice.service;

import com.atguigu.rmqservice.entity.UcenterMember;
import com.atguigu.rmqservice.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-11-13
 */
public interface UcenterMemberService  {
    //接收用户注册的消息并添加到数据库
    void register(RegisterVo registerVo);
    //更新用户登陆时间
    void updateLoginTime(String mobile);
}
