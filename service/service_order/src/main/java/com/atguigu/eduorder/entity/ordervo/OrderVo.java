package com.atguigu.eduorder.entity.ordervo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class OrderVo {
    @ApiModelProperty(value = "订单号")
    private String orderNo;
    @ApiModelProperty(value = "课程名称")
    private String courseTitle;
    @ApiModelProperty(value = "会员手机")
    private String mobile;
    @ApiModelProperty(value = "开始时间")
    private String begin;
    @ApiModelProperty(value = "结束时间")
    private String end;
}
