package com.atguigu.msmservice.controller;

import com.atguigu.msmservice.service.CodeService;
import com.atguigu.msmservice.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 验证码
 */
@RestController
@RequestMapping("/edumsm/msm")
public class CheckCodeController {

	@Resource
	CodeService codeService;
	@Resource
	RedisTemplate<String,String> redisTemplate;

	@GetMapping("/getCheckCode/{phoneNum}")
	public void getCheckCode(HttpServletRequest request, HttpServletResponse response,
							 @PathVariable("phoneNum") String phoneNum)throws ServletException, IOException {
		String checkCode = codeService.getCheckCode(request, response);
		redisTemplate.opsForValue().set(phoneNum+"checkCode",checkCode,3,TimeUnit.MINUTES);
	}
}



