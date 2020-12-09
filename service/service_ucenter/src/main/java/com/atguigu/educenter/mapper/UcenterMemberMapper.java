package com.atguigu.educenter.mapper;

import com.atguigu.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-03-09
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {
    //统计某一天注册人数
    @Select({"       SELECT COUNT(*) FROM ucenter_member uc " +
            "         WHERE DATE(uc.gmt_create)=#{day}"})
    Integer countRegisterDay(String day);

    //更新登陆时间
    @Update("update ucenter_member set gmt_modified=#{currentDate} where mobile=#{mobile}")
    void updateLoginTime(@Param("currentDate") Date current,@Param("mobile") String mobile);

    //统计某一天登录人数
    @Select({"       SELECT COUNT(*) FROM ucenter_member uc " +
            "         WHERE DATE(uc.gmt_modified)=#{day}"})
    Integer countLoginDay(String day);
}
