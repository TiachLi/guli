package com.atguigu.eduorder.service.impl;


import com.atguigu.eduorder.client.EduClient;
import com.atguigu.eduorder.client.UcenterClient;
import com.atguigu.eduorder.entity.Order;
import com.atguigu.eduorder.entity.ordervo.CourseWebVoOrder;
import com.atguigu.eduorder.entity.ordervo.OrderVo;
import com.atguigu.eduorder.entity.ordervo.UcenterMemberOrder;
import com.atguigu.eduorder.mapper.OrderMapper;
import com.atguigu.eduorder.service.OrderService;
import com.atguigu.eduorder.utils.OrderNoUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-03-13
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    //1 生成订单的方法
    @Override
    public String createOrders(String courseId, String memberId) {
        //通过远程调用根据课程id获取课信息
        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);

        //通过远程调用根据用户id获取用户信息
        UcenterMemberOrder userInfoOrder = ucenterClient.getUserInfoOrder(memberId);



        //创建Order对象，向order对象里面设置需要数据
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());//订单号
        order.setCourseId(courseId); //课程id
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        order.setStatus(0);  //订单状态（0：未支付 1：已支付）
        order.setPayType(1);  //支付类型 ，微信1
        baseMapper.insert(order);
         //返回订单号
        return order.getOrderNo();
    }
    //分页查询带条件
    @Override
    public Map<String, Object> getOrderByPage(OrderVo orderVo, String current, String limit) {
        //构建查询Querry,动态拼接sql
        String courseTitle = orderVo.getCourseTitle();//课程名
        String mobile = orderVo.getMobile();//手机号
        String orderNo = orderVo.getOrderNo();//订单号
        String begin = orderVo.getBegin();//开始时间
        String end = orderVo.getEnd();//结束时间
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(courseTitle)){
            orderQueryWrapper.like("course_title", courseTitle);
        }
        if (!StringUtils.isEmpty(mobile)){
            orderQueryWrapper.like("mobile", mobile);
        }
        if (!StringUtils.isEmpty(orderNo)){
            orderQueryWrapper.like("order_no", orderNo);
        }
        if(!com.baomidou.mybatisplus.core.toolkit.StringUtils.isEmpty(begin)) {
            orderQueryWrapper.ge("gmt_create", begin);
        }
        if(!com.baomidou.mybatisplus.core.toolkit.StringUtils.isEmpty(end)) {
            orderQueryWrapper.le("gmt_create", end);
        }
        //根据创建时间降序排列
        orderQueryWrapper.orderByDesc("gmt_create");
        //构建分页对象
        Page<Order> orderPage = new Page<>(Integer.parseInt(current), Integer.parseInt(limit));

        IPage<Order> orderIPage = baseMapper.selectPage(orderPage, orderQueryWrapper);
        HashMap<String, Object> pageMap = new HashMap<>();
        pageMap.put("total",orderIPage.getTotal());
        pageMap.put("list",orderIPage.getRecords());
        return pageMap;
    }
}
