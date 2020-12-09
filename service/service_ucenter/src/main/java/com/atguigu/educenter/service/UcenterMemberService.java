package com.atguigu.educenter.service;

import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.LoginVo;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-03-09
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    //登录的方法
    String login(LoginVo member);
    //注册到消息队列的方法
    void register(RegisterVo registerVo);
    //注册到数据库的方法
    void registerToDataSource(RegisterVo registerVo);
    //根据openid查找
    public UcenterMember getOpenIdMember(String openid);
    //统计某一天注册人数
    Integer countRegisterDay(String day);
    //更新登陆时间
    void updateLoginTime(String mobile);
    //统计某一天登录人数
    Integer countLoginDay(String day);
}
