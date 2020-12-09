package com.atguigu.msmservice.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface CodeService {
    //获取验证码
    String getCheckCode(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
