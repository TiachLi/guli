package com.atguigu.eduservice.entity.coursevo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CourseVo {

    @ApiModelProperty(value = "课程标题,模糊查询")
    private String title;

    @ApiModelProperty(value = "是否发布 normal 发布 draft 未发布")
    private String status;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;

}
