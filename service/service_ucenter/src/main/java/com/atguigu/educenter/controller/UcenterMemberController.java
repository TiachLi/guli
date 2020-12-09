package com.atguigu.educenter.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.ordervo.UcenterMemberOrder;
import com.atguigu.educenter.entity.vo.LoginVo;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.atguigu.educenter.service.UcenterMemberService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import net.sf.jsqlparser.statement.execute.Execute;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-03-09
 */
@RestController
@RequestMapping("/educenter/member")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    //登录
    @PostMapping("login")
    public R loginUser(@RequestBody LoginVo member) {
        //member对象封装手机号和密码
        //调用service方法实现登录
        //返回token值，使用jwt生成
        try {
            String token = memberService.login(member);
            return R.ok().data("token",token);
        }catch (GuliException e){
            e.printStackTrace();
            return R.error(e.getCode()).message(e.getMessage());
        }
    }
    //更新登陆时间
    @PostMapping("/updateLoginTime/{mobile}")
    public void updateLoginTime(@PathVariable("mobile") String mobile){
        memberService.updateLoginTime(mobile);
    }
    //注册
    @PostMapping("register")
    public R registerUser(@RequestBody RegisterVo registerVo) {
        try {
            memberService.register(registerVo);
            return R.ok();
        }catch (GuliException e){
            return R.error(e.getCode()).message(e.getMessage());
        }
    }
    //注册
    @PostMapping("registerToDataSource")
    public R registerUserToDataSource(@RequestBody RegisterVo registerVo) {
        try {
            memberService.registerToDataSource(registerVo);
            return R.ok();
        }catch (GuliException e){
            return R.error(e.getCode()).message(e.getMessage());
        }
    }

    //根据token获取用户信息
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        UcenterMember member = memberService.getById(memberId);
        return R.ok().data("userInfo",member);
    }
    //根据用户id获取用户信息
    @PostMapping("getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id) {
        UcenterMember member = memberService.getById(id);
        //把member对象里面值复制给UcenterMemberOrder对象
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member,ucenterMemberOrder);
        return ucenterMemberOrder;
    }
    //查询某一天注册人数
    @GetMapping("countRegister/{day}")
    public R countRegister(@PathVariable String day) {
        Integer count = memberService.countRegisterDay(day);
        return R.ok().data("countRegister",count);
    }
    //查询某一天注册人数
    @GetMapping("countLogin/{day}")
    public Integer countLogin(@PathVariable String day) {
        return   memberService.countLoginDay(day);
    }

}

